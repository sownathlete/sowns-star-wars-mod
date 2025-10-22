package com.sown.outerrim.bounty;

import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.event.HoverEvent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.management.ServerConfigurationManager;
import net.minecraft.util.*;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import com.sown.outerrim.registry.ItemRegister;
import com.google.common.base.Charsets;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mojang.authlib.GameProfile;

import java.io.*;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class BountyManager {

    private static final Map<UUID, Bounty> activeBounties = new HashMap<>();
    private static final Set<UUID> hunters = new HashSet<>();

    private static final Item CREDIT_1 = ItemRegister.getRegisteredItem("galactic_credit_1");
    private static final Item CREDIT_10 = ItemRegister.getRegisteredItem("galactic_credit_10");
    private static final Item CREDIT_100 = ItemRegister.getRegisteredItem("galactic_credit_100");
    private static final Item CREDIT_1000 = ItemRegister.getRegisteredItem("galactic_credit_1000");

    private static final long MIN_REMOVE_DELAY = 5 * 60 * 1000; // 5 minutes

    public static class Bounty {
        private final String targetName;
        private final Map<String, Integer> contributors = new LinkedHashMap<>();
        private final long createdTime;
        private String owner;
        private int amount;

        public Bounty(String targetName, long createdTime) {
            this.targetName = targetName;
            this.createdTime = createdTime;
            this.amount = 0;
        }

        public void increaseAmount(int extra, String placer) {
            this.amount += extra;
            contributors.put(placer, contributors.getOrDefault(placer, 0) + extra);
            if (owner == null) {
                owner = placer;
            }
        }

        public boolean isOwner(String name) {
            return owner != null && owner.equalsIgnoreCase(name);
        }

        public void removeContributor(String name) {
            if (!contributors.containsKey(name)) return;

            int removed = contributors.remove(name);
            this.amount -= removed;

            if (name.equalsIgnoreCase(owner)) {
                owner = contributors.keySet().stream().findFirst().orElse(null);
            }
        }

        public long getCreatedTime() {
            return createdTime;
        }

        public int getAmount() {
            return amount;
        }

        public String getTargetName() {
            return targetName;
        }
        
        public long getCreatedAt() {
            return createdTime;
        }

        public Map<String, Integer> getContributors() {
            return contributors;
        }

        public void decreaseAmount(int amount) {
            this.amount -= amount;
        }
        
        public String getOwner() {
            return owner;
        }

        public boolean hasContributor(String name) {
            return contributors.containsKey(name);
        }

        public int getContribution(String name) {
            return contributors.getOrDefault(name, 0);
        }
    }

    private static File getSaveFile() {
        World world = MinecraftServer.getServer().getEntityWorld();
        if (world != null && world.getSaveHandler() != null) {
            return new File(world.getSaveHandler().getWorldDirectory(), "bounties.json");
        }
        return new File("bounties.json");
    }

    public static void placeBounty(EntityPlayerMP placer, String targetName, int amount) {
        if (amount <= 0) {
            sendError(placer, "Amount must be positive.");
            return;
        }
        UUID targetId = fetchRealUUIDFromPlayerDB(targetName);
        if (targetId == null) {
            sendError(placer, "Could not fetch player UUID. Make sure the username is correct.");
            return;
        }

        boolean isAdmin = MinecraftServer.getServer().getConfigurationManager().func_152596_g(placer.getGameProfile());
        if (!isAdmin && placer.getUniqueID().equals(targetId)) {
            sendError(placer, "You can't place a bounty on yourself.");
            return;
        }

        GameProfile profile = new GameProfile(targetId, targetName);

        if (!removeCreditsFromInventory(placer, amount)) {
            sendError(placer, "You don't have enough credits to place this bounty.");
            return;
        }

        boolean isNew = false;
        Bounty bounty = activeBounties.get(targetId);
        if (bounty == null) {
            bounty = new Bounty(profile.getName(), System.currentTimeMillis());
            activeBounties.put(targetId, bounty);
            isNew = true;
        }

        bounty.increaseAmount(amount, placer.getCommandSenderName());
        String label = bounty.getAmount() == 1 ? "credit" : "credits";

        if (isNew) {
            broadcast("A bounty of " + amount + " " + label + " has been placed on " + profile.getName() + "!", EnumChatFormatting.GOLD);
        } else {
            broadcast("The bounty on " + profile.getName() + " has increased to " + bounty.getAmount() + " " + label + "!", EnumChatFormatting.GOLD);
        }
    }

    private static GameProfile getCachedProfile(String name) {
        MinecraftServer server = MinecraftServer.getServer();

        EntityPlayerMP online = server.getConfigurationManager().func_152612_a(name);
        if (online != null) {
            return online.getGameProfile();
        }

        List<String> knownNames = Arrays.asList(server.getConfigurationManager().getAllUsernames());
        if (knownNames.contains(name)) {
            UUID uuid = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8));
            return new GameProfile(uuid, name);
        }

        try {
            String urlStr = "https://playerdb.co/api/player/minecraft/" + name;
            HttpURLConnection conn = (HttpURLConnection) new URL(urlStr).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "OuterRim-Mod");

            if (conn.getResponseCode() == 200) {
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
                    Gson gson = new Gson();
                    JsonObject json = gson.fromJson(reader, JsonObject.class);
                    JsonObject data = json.getAsJsonObject("data");
                    if (data != null) {
                        JsonObject player = data.getAsJsonObject("player");
                        String id = player.get("id").getAsString();
                        String username = player.get("username").getAsString();
                        UUID uuid = UUID.fromString(id.replaceFirst(
                            "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"
                        ));
                        return new GameProfile(uuid, username);
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("[BountyManager] Failed to fetch UUID for " + name);
            e.printStackTrace();
        }

        UUID offlineId = UUID.nameUUIDFromBytes(("OfflinePlayer:" + name).getBytes(Charsets.UTF_8));
        return new GameProfile(offlineId, name);
    }

    public static void increaseBounty(EntityPlayerMP sender, String targetName, int amount) {
        if (amount <= 0) {
            sendError(sender, "Amount must be positive.");
            return;
        }
        EntityPlayerMP target = sender.mcServer.getConfigurationManager().func_152612_a(targetName);
        if (target == null) {
            sendError(sender, "Target player not found.");
            return;
        }

        UUID targetId = target.getUniqueID();
        Bounty bounty = activeBounties.get(targetId);
        if (bounty == null) {
            sendInfo(sender, "No active bounty found on that player.");
            return;
        }

        if (!removeCreditsFromInventory(sender, amount)) {
            sendError(sender, "You don't have enough credits to increase the bounty.");
            return;
        }

        bounty.increaseAmount(amount, sender.getCommandSenderName());
        broadcast("The bounty on " + target.getCommandSenderName() + " has increased to " + bounty.getAmount() + " credits!", EnumChatFormatting.GOLD);
    }

    public static void claimBounty(EntityPlayerMP killer, EntityPlayerMP target) {
        if (killer.getUniqueID().equals(target.getUniqueID())) {
            sendError(killer, "You can't claim a bounty on yourself.");
            return;
        }

        UUID targetId = target.getUniqueID();
        if (!activeBounties.containsKey(targetId)) return;

        Bounty bounty = activeBounties.remove(targetId);
        giveCreditsToPlayer(killer, bounty.getAmount());
        sendSuccess(killer, "You have claimed a bounty of " + bounty.getAmount() + " credits for eliminating " + target.getCommandSenderName() + "!");
        broadcast(killer.getCommandSenderName() + " claimed the bounty on " + target.getCommandSenderName() + "!", EnumChatFormatting.AQUA);

        // Determine top contributor
        String topContributorName = null;
        int highest = -1;
        for (Map.Entry<String, Integer> entry : bounty.getContributors().entrySet()) {
            if (entry.getValue() > highest) {
                highest = entry.getValue();
                topContributorName = entry.getKey();
            }
        }

        if (topContributorName != null) {
            EntityPlayerMP topContributor = MinecraftServer.getServer().getConfigurationManager().func_152612_a(topContributorName);
            if (topContributor != null) {
                ItemStack head = createPlayerHead(target.getCommandSenderName());
                topContributor.inventory.addItemStackToInventory(head);
                sendSuccess(topContributor, "You received " + target.getCommandSenderName() + "'s head for funding the bounty!");
            }
        }
    }

    public static void removeBounty(EntityPlayerMP sender, String targetName) {
        UUID targetId = fetchRealUUIDFromPlayerDB(targetName);
        if (targetId == null) {
            sendError(sender, "Could not fetch player UUID. Make sure the username is correct.");
            return;
        }

        Bounty bounty = activeBounties.get(targetId);
        if (bounty == null) {
            sendError(sender, "No active bounty found on that player.");
            return;
        }

        boolean isAdmin = MinecraftServer.getServer().getConfigurationManager().func_152596_g(sender.getGameProfile());
        String senderName = sender.getCommandSenderName();
        Integer contributed = bounty.getContributors().get(senderName);

        if (!isAdmin && (contributed == null || contributed <= 0)) {
            sendError(sender, "You have not contributed to this bounty.");
            return;
        }

        long age = System.currentTimeMillis() - bounty.getCreatedTime();
        if (!isAdmin && age < MIN_REMOVE_DELAY) {
            sendError(sender, "You must wait before removing your contribution. Try again later.");
            return;
        }

        if (isAdmin) {
            // Refund everyone before removal
            for (Map.Entry<String, Integer> entry : bounty.getContributors().entrySet()) {
                EntityPlayerMP contributor = MinecraftServer.getServer().getConfigurationManager().func_152612_a(entry.getKey());
                if (contributor != null) {
                    giveCreditsToPlayer(contributor, entry.getValue());
                }
            }
            activeBounties.remove(targetId);
            return;
        }

        bounty.getContributors().remove(senderName);
        bounty.decreaseAmount(contributed);
        giveCreditsToPlayer(sender, contributed);
        sendSuccess(sender, "You have been refunded " + contributed + " credits from the bounty on " + targetName + ".");

        if (bounty.getAmount() <= 0 || bounty.getContributors().isEmpty()) {
            activeBounties.remove(targetId);
            broadcast("The bounty on " + bounty.getTargetName() + " has been removed.", EnumChatFormatting.YELLOW);
        } else if (senderName.equalsIgnoreCase(bounty.getOwner())) {
            bounty.owner = bounty.getContributors().keySet().stream().findFirst().orElse(null);
            if (bounty.owner != null) {
                broadcast("The bounty on " + bounty.getTargetName() + " is now owned by " + bounty.owner + ".", EnumChatFormatting.GRAY);
            }
        }
    }

    private static UUID fetchRealUUIDFromPlayerDB(String username) {
        try {
            URL url = new URL("https://playerdb.co/api/player/minecraft/" + username);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "SownModding/1.0");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder responseBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                responseBuilder.append(line);
            }
            reader.close();

            String json = responseBuilder.toString();
            JsonObject obj = new Gson().fromJson(json, JsonObject.class);

            if (obj.get("success").getAsBoolean()) {
                String rawId = obj.getAsJsonObject("data").getAsJsonObject("player").get("id").getAsString();
                return UUID.fromString(formatUUID(rawId));
            }

        } catch (Exception e) {
            System.err.println("[BountyManager] Failed to fetch UUID from PlayerDB for: " + username);
            e.printStackTrace();
        }
        return null;
    }

    private static String formatUUID(String raw) {
        if (raw.contains("-")) return raw;
        return raw.replaceFirst(
            "(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})",
            "$1-$2-$3-$4-$5"
        );
    }

    public static boolean removeCreditsFromInventory(EntityPlayerMP player, int amount) {
        int[] values = {1000, 100, 10, 1}; // ordered largest to smallest for payment
        Item[] items = {CREDIT_1000, CREDIT_100, CREDIT_10, CREDIT_1};

        Map<Item, Integer> available = new HashMap<>();
        int total = 0;

        for (int i = 0; i < items.length; i++) {
            int count = countItem(player, items[i]);
            available.put(items[i], count);
            total += count * values[i];
        }

        if (total < amount) return false;

        Map<Item, Integer> toRemove = new HashMap<>();
        int remaining = amount;

        for (int i = 0; i < values.length; i++) {
            Item item = items[i];
            int value = values[i];
            int maxUsable = Math.min(available.get(item), remaining / value);

            if (maxUsable > 0) {
                toRemove.put(item, maxUsable);
                remaining -= maxUsable * value;
            }
        }

        if (remaining > 0) {
            for (int i = 0; i < values.length; i++) {
                Item item = items[i];
                int value = values[i];

                if (available.get(item) > toRemove.getOrDefault(item, 0) && value > remaining) {
                    toRemove.put(item, toRemove.getOrDefault(item, 0) + 1);
                    remaining -= value;
                    break;
                }
            }
        }

        for (Map.Entry<Item, Integer> entry : toRemove.entrySet()) {
            removeItem(player, entry.getKey(), entry.getValue());
        }

        if (remaining < 0) {
            giveCreditsToPlayer(player, -remaining);
        }

        return true;
    }

    private static void removeItem(EntityPlayerMP player, Item item, int count) {
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            if (count <= 0) break;
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                int toRemove = Math.min(stack.stackSize, count);
                stack.stackSize -= toRemove;
                if (stack.stackSize <= 0) {
                    player.inventory.setInventorySlotContents(i, null);
                }
                count -= toRemove;
            }
        }
    }

    public static void giveCreditsToPlayer(EntityPlayerMP player, int amount) {
        int[] values = {1000, 100, 10, 1};
        Item[] items = {CREDIT_1000, CREDIT_100, CREDIT_10, CREDIT_1};

        for (int i = 0; i < values.length; i++) {
            int count = amount / values[i];
            amount %= values[i];
            if (count > 0) {
                player.inventory.addItemStackToInventory(new ItemStack(items[i], count));
            }
        }
    }

    private static int countItem(EntityPlayerMP player, Item item) {
        int count = 0;
        for (int i = 0; i < player.inventory.getSizeInventory(); i++) {
            ItemStack stack = player.inventory.getStackInSlot(i);
            if (stack != null && stack.getItem() == item) {
                count += stack.stackSize;
            }
        }
        return count;
    }

    public static void saveBounties() {
        File saveFile = getSaveFile();
        try (FileWriter writer = new FileWriter(saveFile)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            Map<String, Bounty> saveMap = new HashMap<>();
            for (Map.Entry<UUID, Bounty> entry : activeBounties.entrySet()) {
                saveMap.put(entry.getKey().toString(), entry.getValue());
            }
            gson.toJson(saveMap, writer);
        } catch (IOException e) {
            System.err.println("[BountyManager] Failed to save bounties:");
            e.printStackTrace();
        }
    }

    public static void loadBounties() {
        File saveFile = getSaveFile();
        if (!saveFile.exists()) return;

        try (FileReader reader = new FileReader(saveFile)) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Bounty>>() {}.getType();
            Map<String, Bounty> loaded = gson.fromJson(reader, type);
            for (Map.Entry<String, Bounty> entry : loaded.entrySet()) {
                activeBounties.put(UUID.fromString(entry.getKey()), entry.getValue());
            }
        } catch (Exception e) {
            System.err.println("[BountyManager] Failed to load bounties:");
            e.printStackTrace();
        }
    }

    public static Bounty getBounty(EntityPlayerMP player) {
        return activeBounties.get(player.getUniqueID());
    }

    public static void listBounties(EntityPlayerMP viewer, int page) {
        final int perPage = 5;

        if (activeBounties.isEmpty()) {
            sendInfo(viewer, "No active bounties.");
            return;
        }

        List<Bounty> sorted = new ArrayList<>(activeBounties.values());
        sorted.sort((a, b) -> Integer.compare(b.getAmount(), a.getAmount()));

        int totalPages = (int) Math.ceil(sorted.size() / (double) perPage);
        page = Math.max(1, Math.min(page, totalPages));

        int startIndex = (page - 1) * perPage;
        int endIndex = Math.min(startIndex + perPage, sorted.size());

        ChatComponentText header = new ChatComponentText("Active Bounties - Page " + page + "/" + totalPages + ":");
        header.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GOLD));
        viewer.addChatMessage(header);

        long now = System.currentTimeMillis();

        for (int i = startIndex; i < endIndex; i++) {
            Bounty bounty = sorted.get(i);
            int amount = bounty.getAmount();
            String creditLabel = amount == 1 ? "credit" : "credits";

            ChatComponentText line = new ChatComponentText(bounty.getTargetName() + " - " + amount + " " + creditLabel);
            line.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GRAY));

            StringBuilder hoverText = new StringBuilder("Contributors:");
            for (Map.Entry<String, Integer> entry : bounty.getContributors().entrySet()) {
                int val = entry.getValue();
                hoverText.append("\n").append(entry.getKey()).append(": ").append(val).append(" ").append(val == 1 ? "credit" : "credits");
            }

            long ageMillis = now - bounty.getCreatedAt();
            String ageStr = formatDuration(ageMillis);
            hoverText.append("\n\n").append("Posted ").append(ageStr).append(" ago");

            line.getChatStyle().setChatHoverEvent(
                new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ChatComponentText(hoverText.toString()))
            );

            viewer.addChatMessage(line);
        }
    }
    
    private static ItemStack createPlayerHead(String playerName) {
        ItemStack skull = new ItemStack(Item.getItemById(397), 1, 3);
        skull.setStackDisplayName(playerName + "'s Head");

        NBTTagCompound tag = new NBTTagCompound();
        NBTTagCompound skullOwner = new NBTTagCompound();
        skullOwner.setString("Name", playerName);
        tag.setTag("SkullOwner", skullOwner);
        skull.setTagCompound(tag);

        return skull;
    }
    
    private static String formatDuration(long millis) {
        long seconds = millis / 1000;
        long minutes = seconds / 60;
        long hours = minutes / 60;
        long days = hours / 24;

        if (days > 0) return days + " day" + (days > 1 ? "s" : "");
        if (hours > 0) return hours + " hour" + (hours > 1 ? "s" : "");
        if (minutes > 0) return minutes + " minute" + (minutes > 1 ? "s" : "");
        return seconds + " second" + (seconds != 1 ? "s" : "");
    }

    public static void broadcast(String msg, EnumChatFormatting color) {
        ChatComponentText chat = new ChatComponentText(msg);
        chat.setChatStyle(new ChatStyle().setColor(color));
        for (Object o : MinecraftServer.getServer().getConfigurationManager().playerEntityList) {
            ((EntityPlayerMP) o).addChatMessage(chat);
        }
    }

    public static void sendError(EntityPlayerMP player, String msg) {
        ChatComponentText chat = new ChatComponentText(msg);
        chat.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.RED));
        player.addChatMessage(chat);
    }

    private static void sendInfo(EntityPlayerMP player, String msg) {
        ChatComponentText chat = new ChatComponentText(msg);
        chat.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.YELLOW));
        player.addChatMessage(chat);
    }

    public static void sendSuccess(EntityPlayerMP player, String msg) {
        ChatComponentText chat = new ChatComponentText(msg);
        chat.setChatStyle(new ChatStyle().setColor(EnumChatFormatting.GREEN));
        player.addChatMessage(chat);
    }
    
    public static Map<UUID, Bounty> getActiveBounties() {
        return activeBounties;
    }
    
    public static void clearBounty(EntityPlayerMP player) {
        UUID id = player.getUniqueID();
        activeBounties.remove(id);
    }
}
