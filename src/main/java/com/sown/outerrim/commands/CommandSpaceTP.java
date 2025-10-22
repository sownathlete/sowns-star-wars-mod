package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;

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

public class CommandSpaceTP extends CommandBase {

    private static final String PLANET_DATA_URL = "https://www.sownathlete.com/planetData.js";
    private static final Map<String, double[]> PLANET_COORDINATES = new HashMap<>();
    private static final Map<Integer, double[]> PLANET_COORDINATES_BY_ID = new HashMap<>();
    private static final Map<Integer, String> PLANET_NAMES_BY_ID = new HashMap<>();
    private static final List<String> ALIASES = Arrays.asList("stp", "spacetp");
    private static final int SPACE_DIMENSION = -2;

    @Override
    public String getCommandName() {
        return "spacetp";
    }

    @Override
    public List<String> getCommandAliases() {
        return ALIASES;
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/spacetp <planet_name|dimension_id> - Teleports you to space above a planet.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (!(sender instanceof EntityPlayerMP)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "This command can only be used by players."));
            return;
        }

        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /spacetp <planet_name|dimension_id>"));
            return;
        }

        EntityPlayerMP player = (EntityPlayerMP) sender;
        World world = player.worldObj;

        if (PLANET_COORDINATES.isEmpty()) {
            fetchPlanetData();
        }

        String input = String.join(" ", args).trim().toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
        double[] targetCoords = null;
        String planetName = "Unknown Planet";

        try {
            int dimensionId = Integer.parseInt(input);
            targetCoords = PLANET_COORDINATES_BY_ID.get(dimensionId);
            planetName = PLANET_NAMES_BY_ID.getOrDefault(dimensionId, "Unknown Planet");
        } catch (NumberFormatException e) {
            if (PLANET_COORDINATES.containsKey(input)) {
                targetCoords = PLANET_COORDINATES.get(input);
                for (Map.Entry<Integer, String> entry : PLANET_NAMES_BY_ID.entrySet()) {
                    if (entry.getValue().toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "").equals(input)) {
                        planetName = entry.getValue();
                        break;
                    }
                }
            }
        }

        if (targetCoords == null) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Planet not found."));
            return;
        }

        teleportPlayerToSpace(player, targetCoords[0], 10, targetCoords[1], planetName);
    }

    private void teleportPlayerToSpace(EntityPlayerMP player, double x, double y, double z, String planetName) {
        if (player.dimension != SPACE_DIMENSION) {
            player.mcServer.getConfigurationManager().transferPlayerToDimension(player, SPACE_DIMENSION);
        }
        player.setPositionAndUpdate(x, y, z);
        player.addChatMessage(new ChatComponentText(EnumChatFormatting.GREEN + "Teleported to space above " + planetName + " at X: " + (int)x + " Z: " + (int)z));
    }

    private void fetchPlanetData() {
        try {
            TrustManager[] trustAllCertificates = new TrustManager[]{
                new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {}
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {}
                    public X509Certificate[] getAcceptedIssuers() { return new X509Certificate[0]; }
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

            Pattern pattern = Pattern.compile("const objects = (\\[.*?\\]);", Pattern.DOTALL);
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
                String name = planet.get("name").getAsString().trim();
                String normalizedName = name.toLowerCase().replaceAll("[^a-zA-Z0-9 ]", "");
                double x = planet.get("lng").getAsDouble() * 1000;
                double z = planet.get("lat").getAsDouble() * 1000;
                
                if (planet.has("dimension") && planet.get("dimension").isJsonPrimitive() && planet.get("dimension").getAsJsonPrimitive().isNumber()) {
                    int dimension = planet.get("dimension").getAsInt();
                    PLANET_COORDINATES_BY_ID.put(dimension, new double[]{x, z});
                    PLANET_NAMES_BY_ID.put(dimension, name);
                }
                
                PLANET_COORDINATES.put(normalizedName, new double[]{x, z});
            }
        } catch (Exception e) {
            System.err.println("Error parsing planet data: " + e.getMessage());
        }
    }
}