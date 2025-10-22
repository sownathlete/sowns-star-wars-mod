package com.sown.outerrim.commands;

import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

import javax.net.ssl.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;

public class CommandPatreonCheck extends CommandBase {

    private static final String PATRON_LIST_URL = "https://sownathlete.com/patrons.txt";

    @Override
    public String getCommandName() {
        return "patreoncheck";
    }

    @Override
    public List<String> getCommandAliases() {
        return Arrays.asList("ispatron");
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "/patreoncheck <player> or /ispatron <player> - Checks if a player is a patron.";
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) {
        if (args.length == 0) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Usage: /patreoncheck <player> or /ispatron <player>"));
            return;
        }

        EntityPlayerMP target = MinecraftServer.getServer().getConfigurationManager().func_152612_a(args[0]);

        if (target == null) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + "Player not found."));
            return;
        }

        String uuidWithHyphens = target.getUniqueID().toString();
        String uuidNoHyphens = uuidWithHyphens.replace("-", "");

        Set<String> patrons = fetchPatronUUIDs();

        System.out.println("Checking Patreon status for UUID: " + uuidWithHyphens + " / " + uuidNoHyphens);
        System.out.println("Patron List: " + patrons);

        if (patrons.contains(uuidWithHyphens) || patrons.contains(uuidNoHyphens)) {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.GOLD + target.getDisplayName() + " is a verified patron!"));
        } else {
            sender.addChatMessage(new ChatComponentText(EnumChatFormatting.RED + target.getDisplayName() + " is not a patron."));
        }
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 3;
    }

    @Override
    public List<String> addTabCompletionOptions(ICommandSender sender, String[] args) {
        if (args.length == 1) {
            return getListOfStringsMatchingLastWord(args, MinecraftServer.getServer().getAllUsernames());
        }
        return null;
    }

    private Set<String> fetchPatronUUIDs() {
        Set<String> patronUUIDs = new HashSet<>();
        try {
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

            URL url = new URL(PATRON_LIST_URL);
            HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Mozilla/5.0");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    patronUUIDs.add(line);
                    patronUUIDs.add(line.replace("-", ""));
                }
            }
            reader.close();
        } catch (Exception e) {
            System.err.println("Error fetching patron list: " + e.getMessage());
        }
        return patronUUIDs;
    }
}
