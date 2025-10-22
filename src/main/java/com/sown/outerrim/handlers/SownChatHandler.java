package com.sown.outerrim.handlers;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;

import java.util.*;

public class SownChatHandler {

    private static final UUID SOWN_UUID = UUID.fromString("5135dae1-7a0a-4915-85e1-c5a923e2d98a");

    private static final Map<String, String> TRIGGER_TO_SOUND = new HashMap<>();

    static {
        TRIGGER_TO_SOUND.put("liar", "outerrim:voice.anakin.liar");
        TRIGGER_TO_SOUND.put("you turned her against me", "outerrim:voice.anakin.turned_her_against_me");
        TRIGGER_TO_SOUND.put("you will not take her from me", "outerrim:voice.anakin.not_take_her");
        TRIGGER_TO_SOUND.put("dont lecture me obi wan", "outerrim:voice.anakin.dont_lecture_me");
        TRIGGER_TO_SOUND.put("i see through the lies of the jedi", "outerrim:voice.anakin.lies_of_the_jedi");
        TRIGGER_TO_SOUND.put("i do not fear the dark side as you do", "outerrim:voice.anakin.no_fear_dark_side");
        TRIGGER_TO_SOUND.put("i have brought peace freedom justice and security to my new empire", "outerrim:voice.anakin.brought_peace");
        TRIGGER_TO_SOUND.put("dont make me kill you", "outerrim:voice.anakin.dont_make_me_kill_you");
        TRIGGER_TO_SOUND.put("if youre not with me then youre my enemy", "outerrim:voice.anakin.if_not_with_me");
        TRIGGER_TO_SOUND.put("you will try", "outerrim:voice.anakin.you_will_try");
        TRIGGER_TO_SOUND.put("i should have known the jedi were plotting to take over", "outerrim:voice.anakin.should_have_known");
        TRIGGER_TO_SOUND.put("from my point of view the jedi are evil", "outerrim:voice.anakin.jedi_are_evil");
        TRIGGER_TO_SOUND.put("this is the end for you my master", "outerrim:voice.anakin.end_for_you");
        TRIGGER_TO_SOUND.put("you underestimate my power", "outerrim:voice.anakin.underestimate_power");
        TRIGGER_TO_SOUND.put("i hate you", "outerrim:voice.anakin.i_hate_you");

        TRIGGER_TO_SOUND.put("its not fair", "outerrim:voice.anakin.not_fair");
        TRIGGER_TO_SOUND.put("i want more", "outerrim:voice.anakin.want_more");
        TRIGGER_TO_SOUND.put("buzz droids", "outerrim:voice.anakin.buzz_droids");
        TRIGGER_TO_SOUND.put("its unfair", "outerrim:voice.anakin.unfair");
        TRIGGER_TO_SOUND.put("intoxicating", "outerrim:voice.anakin.intoxicating");
        TRIGGER_TO_SOUND.put("dont ask me to do that", "outerrim:voice.anakin.dont_ask");
        TRIGGER_TO_SOUND.put("youre shorter than i expected", "outerrim:voice.anakin.youre_shorter");
        TRIGGER_TO_SOUND.put("i should not have done that its not the jedi way", "outerrim:voice.anakin.not_jedi_way");
        TRIGGER_TO_SOUND.put("what have i done", "outerrim:voice.anakin.what_i_done");
        TRIGGER_TO_SOUND.put("i will be the most powerful jedi ever", "outerrim:voice.anakin.powerful_jedi");
        TRIGGER_TO_SOUND.put("if you are suffering as much as i am please tell me", "outerrim:voice.anakin.suffering");
        TRIGGER_TO_SOUND.put("the jedi turned against me dont you turn against me", "outerrim:voice.anakin.jedi_turned");
        TRIGGER_TO_SOUND.put("what did you say", "outerrim:voice.anakin.what_did_you_say");
    }
    
    private static final Map<String,String> OBIWAN_TRIGGER_TO_SOUND = new HashMap<>();
    static {
        OBIWAN_TRIGGER_TO_SOUND.put("may the force be with you", "outerrim:voice.obiwan.may_the_force");
        OBIWAN_TRIGGER_TO_SOUND.put("hello there",             "outerrim:voice.obiwan.hello_there");
    }

    @SubscribeEvent
    public void onPlayerChat(ServerChatEvent event) {
        if (!(event.player instanceof EntityPlayerMP)) return;
        EntityPlayerMP player = (EntityPlayerMP) event.player;

        // Only our Sown UUID can trigger
        if (!player.getUniqueID().equals(SOWN_UUID)) return;

        String normalized = normalize(event.message);
        Set<String> variants = generateVariants(normalized);

        // 1) Obi-Wan helmet check
        boolean wearingObiWan = false;
        try {
            ItemStack helm = player.inventory.armorInventory[3];
            if (helm != null && helm.getItem() != null) {
                String cls = helm.getItem().getClass().getName();
                if (cls.contains("ObiWan") || cls.contains("obiWanHelmet")) {
                    wearingObiWan = true;
                }
            }
        } catch (Throwable ignored) {}

        if (wearingObiWan) {
            for (String variant : variants) {
                if (OBIWAN_TRIGGER_TO_SOUND.containsKey(variant)) {
                    playVoiceLine(player, OBIWAN_TRIGGER_TO_SOUND.get(variant));
                    return;
                }
            }
        }

        // 2) Anakin lines as before
        for (String variant : variants) {
            if (TRIGGER_TO_SOUND.containsKey(variant)) {
                playVoiceLine(player, TRIGGER_TO_SOUND.get(variant));
                return;
            }
        }

        // 3) Fallback aaa leap yell
        if (normalized.matches("a{3,}")) {
            playVoiceLine(player, "outerrim:voice.anakin.leap_yell");
        }
    }


    private void playVoiceLine(EntityPlayerMP player, String sound) {
        player.worldObj.playSoundEffect(
            player.posX,
            player.posY,
            player.posZ,
            sound,
            1.0F,
            1.0F
        );
    }

    private String normalize(String message) {
        return message
            .trim()
            .toLowerCase()
            .replaceAll("[^a-z ]", "")
            .replaceAll("\\s+", " ")
            .trim();
    }

    private Set<String> generateVariants(String input) {
        Set<String> variants = new HashSet<>();

        variants.add(input);

        variants.add(expandAbbreviations(input, "your", false));
        variants.add(expandAbbreviations(input, "youre", false));
        variants.add(expandAbbreviations(input, "your", true));
        variants.add(expandAbbreviations(input, "youre", true));

        return variants;
    }

    private String expandAbbreviations(String input, String urReplacement, boolean collapseYouAre) {
        input = input
            .replaceAll("\\bu\\b", "you")
            .replaceAll("\\bur\\b", urReplacement)
            .replaceAll("\\bshouldve\\b", "should have")
            .replaceAll("\\bshouldnt\\b", "should not");

        if (collapseYouAre) {
            input = input.replaceAll("\\byou are\\b", "youre");
        }

        return input;
    }
}