package com.hunter.movie532_02.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by zxt on 2016/2/16.
 */
public class VersionUtil {
    public static String getVersionName(Context context){
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packInfo = null;
            packInfo = packageManager.getPackageInfo(context.getPackageName(),0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
