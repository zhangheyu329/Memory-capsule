package com.zql.base.utils;

import android.util.Log;

public class BaseLog {

    private static boolean isDebug = true;
    private static  final String mDefault = "=======>ads----->";

    public static void setDebug(boolean debug){
        isDebug = debug;
    }

    public static  void D(String tag,String msg){
        if (isDebug){
            Log.d(mDefault+tag,msg);
        }
    }
}
