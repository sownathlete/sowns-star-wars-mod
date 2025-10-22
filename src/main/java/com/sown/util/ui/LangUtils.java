package com.sown.util.ui;

import net.minecraft.client.resources.I18n;

public class LangUtils {

    public static String translateKey(String input) {
        return I18n.format(input);
    }

    public static String translateKeyFormat(String input, Object... args) {
        return I18n.format(input, args);
    }
}