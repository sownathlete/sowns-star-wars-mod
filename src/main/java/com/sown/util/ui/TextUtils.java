/*
 * Decompiled with CFR 0.148.
 *
 * Could not load the following classes:
 *  net.minecraft.util.EnumChatFormatting
 */
package com.sown.util.ui;

import java.util.StringTokenizer;
import net.minecraft.util.EnumChatFormatting;

public class TextUtils {
    public static String addEffect(String p1, EnumChatFormatting effect) {
        return effect + p1 + EnumChatFormatting.RESET;
    }

    public static String makeBold(String p1) {
        return EnumChatFormatting.BOLD + p1 + EnumChatFormatting.RESET;
    }

    public static String makeItalic(String p1) {
        return EnumChatFormatting.ITALIC + p1 + EnumChatFormatting.RESET;
    }

    public static String makeObfuscated(String p1) {
        return EnumChatFormatting.OBFUSCATED + p1 + EnumChatFormatting.RESET;
    }

    public static String makeStrikethrough(String p1) {
        return EnumChatFormatting.STRIKETHROUGH + p1 + EnumChatFormatting.RESET;
    }

    public static String makeUnderline(String p1) {
        return EnumChatFormatting.UNDERLINE + p1 + EnumChatFormatting.RESET;
    }

    public static String[] splitIntoLine(String input, int maxCharInLine) {
        StringTokenizer tok = new StringTokenizer(input, " ");
        StringBuilder output = new StringBuilder(input.length());
        int lineLen = 0;
        while (tok.hasMoreTokens()) {
            String word = tok.nextToken();
            while (word.length() > maxCharInLine) {
                output.append(word, 0, maxCharInLine - lineLen).append("\n");
                word = word.substring(maxCharInLine - lineLen);
                lineLen = 0;
            }
            if (lineLen + word.length() > maxCharInLine) {
                output.append('\n');
                lineLen = 0;
            }
            output.append(word).append(' ');
            lineLen += word.length() + 1;
        }
        return output.toString().split("\n");
    }

    public static String upperFirst(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}

