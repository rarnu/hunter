package com.rarnu.hunter.common;

import android.os.Environment;

import java.io.File;

public class LocalDir {

    public static String LOCALDIR = Environment.getExternalStorageDirectory().getAbsolutePath() + "/.vicky/";

    static {
        if (!new File(LOCALDIR).exists()) {
            new File(LOCALDIR).mkdir();
        }
    }
}
