package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class CommandNavigate extends CommandBase {

    private static final String PLANET_DATA_URL = "https://www.sownathlete.com/planetData.js";
    private static final Map<String, double[]> PLANET_COORDINATES = new HashMap<>();
    private static final Map<String, Boolean> PLANET_IMPLEMENTATION = new HashMap<>();
    private static final List<String> ALIASES = Arrays.asList("nav", "route");

    @Override
    public String getCommandName() {
        return "navigate";
    }

    @Override
    public List<String> getCommandAliases() {
        return ALIASES;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/navigate <planet_name> - Provides navigation instructions to a planet.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This command can only be used by players."));
            return;
        }

        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /navigate <planet_name>"));
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender;
        World world = player.worldObj;

        if (world.provider.dimensionId != -2) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "You must be in space to use this command!"));
            return;
        }

        String planetName = String.join(" ", args).toLowerCase();

        if (PLANET_COORDINATES.isEmpty()) {
            fetchPlanetData();
        }

        String matchedPlanet = null;
        for (String key : PLANET_COORDINATES.keySet()) {
            if (key.equalsIgnoreCase(planetName)) {
                matchedPlanet = key;
                break;
            }
        }

        if (matchedPlanet == null) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Planet not found."));
            return;
        }

        double[] targetCoords = PLANET_COORDINATES.get(matchedPlanet);
        double targetX = targetCoords[0];
        double targetZ = targetCoords[1];
        boolean isImplemented = PLANET_IMPLEMENTATION.get(matchedPlanet);

        double playerX = player.posX;
        double playerZ = player.posZ;
        float playerYaw = player.rotationYaw;

        if (!isImplemented) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "[WARNING] " + matchedPlanet + " is not yet implemented!"));
        }

        double deltaX = targetX - playerX;
        double deltaZ = targetZ - playerZ;
        double totalDistance = Math.sqrt(deltaX * deltaX + deltaZ * deltaZ);
        
        String xDirection = deltaX > 0 ? "East" : "West";
        String zDirection = deltaZ > 0 ? "South" : "North";

        int absX = Math.abs((int) deltaX);
        int absZ = Math.abs((int) deltaZ);

        String facingX = getCorrectedRelativeDirection(playerYaw, xDirection);
        String facingZ = getCorrectedRelativeDirection(playerYaw, zDirection);

        String arrowX = getDirectionalArrow(facingX);
        String arrowZ = getDirectionalArrow(facingZ);

        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + "Navigating to " + matchedPlanet + ":"));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + arrowX + " Move " + absX + " blocks " + xDirection + " (" + facingX + ")"));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.YELLOW + arrowZ + " Move " + absZ + " blocks " + zDirection + " (" + facingZ + ")"));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.AQUA + "Total Distance: " + (int) totalDistance + " blocks"));
        sender.addChatMessage(new ChatComponentText(EnumChatFormatting.DARK_GRAY + "Target Coordinates: X=" + (int) targetX + ", Z=" + (int) targetZ));
    }

    private String getCorrectedRelativeDirection(float playerYaw, String direction) {
        // Normalize yaw to range [0, 360]
        float normalizedYaw = (playerYaw % 360 + 360) % 360;

        // Determine facing direction
        boolean facingNorth = (normalizedYaw >= 315 || normalizedYaw < 45);
        boolean facingEast = (normalizedYaw >= 45 && normalizedYaw < 135);
        boolean facingSouth = (normalizedYaw >= 135 && normalizedYaw < 225);
        boolean facingWest = (normalizedYaw >= 225 && normalizedYaw < 315);

        // Correct relative movement based on player facing direction
        switch (direction) {
            case "North":
                return facingNorth ? "Backward" : (facingSouth ? "Forward" : (facingEast ? "Right" : "Left"));
            case "South":
                return facingSouth ? "Backward" : (facingNorth ? "Forward" : (facingEast ? "Left" : "Right"));
            case "East":
                return facingEast ? "Backward" : (facingWest ? "Forward" : (facingNorth ? "Left" : "Right"));
            case "West":
                return facingWest ? "Backward" : (facingEast ? "Forward" : (facingNorth ? "Right" : "Left"));
            default:
                return "Unknown";
        }
    }

    private void fetchPlanetData() {
        try {
            // Disable SSL certificate validation
            TrustManager[] trustAllCertificates = new TrustManager[]{
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    public X509Certificate[] getAcceptedIssuers() { return null; }
                }
            };

            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, trustAllCertificates, new SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
            HttpsURLConnection.setDefaultHostnameVerifier((hostname, session) -> true);

            URL url = new URL(PLANET_DATA_URL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            StringBuilder jsonString = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonString.append(line);
            }
            reader.close();

            Pattern pattern = Pattern.compile("const objects = (\\[.*\\]);", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(jsonString.toString());

            if (matcher.find()) {
                String jsonArrayString = matcher.group(1);
                parsePlanetData(jsonArrayString);
            }

        } catch (Exception e) {
            System.err.println("Error fetching planet data: " + e.getMessage());
        }
    }

    private void parsePlanetData(String jsonArrayString) {
        try {
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(jsonArrayString).getAsJsonArray();

            for (int i = 0; i < jsonArray.size(); i++) {
                JsonObject planet = jsonArray.get(i).getAsJsonObject();
                String name = planet.get("name").getAsString();
                double x = planet.get("lng").getAsDouble() * 1000;
                double z = planet.get("lat").getAsDouble() * 1000;
                boolean implemented = planet.get("implemented").getAsBoolean();

                PLANET_COORDINATES.put(name, new double[]{x, z});
                PLANET_IMPLEMENTATION.put(name, implemented);
            }
        } catch (Exception e) {
            System.err.println("Error parsing planet data: " + e.getMessage());
        }
    }

    private String getDirectionalArrow(String movementDirection) {
        switch (movementDirection) {
            case "Forward": return "?";
            case "Backward": return "?";
            case "Left": return "?";
            case "Right": return "?";
            default: return "?";
        }
    }
    
    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }
    
}
