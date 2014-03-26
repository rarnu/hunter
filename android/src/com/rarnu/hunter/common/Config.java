package com.rarnu.hunter.common;

import android.content.Context;
import com.rarnu.utils.ConfigUtils;

public class Config {
    private static final String KEY_ADMIN_ENABLED = "key_admin_enabled";
    private static final String KEY_ADMIN_ACCOUNT = "key_admin_account";
    private static final String KEY_ADMIN_ID = "key_admin_id";

    public static boolean isAdminEnabled(Context context) {
        return ConfigUtils.getBooleanConfig(context, KEY_ADMIN_ENABLED, false);
    }

    public static void setAdminEnabled(Context context, boolean value) {
        ConfigUtils.setBooleanConfig(context, KEY_ADMIN_ENABLED, value);
    }

    public static String getAdminAccount(Context context) {
        return ConfigUtils.getStringConfig(context, KEY_ADMIN_ACCOUNT, "");
    }

    public static void setAdminAccount(Context context, String value) {
        ConfigUtils.setStringConfig(context, KEY_ADMIN_ACCOUNT, value);
    }

    public static int getAdminId(Context context) {
        return ConfigUtils.getIntConfig(context, KEY_ADMIN_ID, -1);
    }

    public static void setAdminId(Context context, int value) {
        ConfigUtils.setIntConfig(context, KEY_ADMIN_ID, value);
    }
}
