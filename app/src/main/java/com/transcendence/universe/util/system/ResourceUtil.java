package com.transcendence.universe.util.system;

import android.content.Context;

/**
 * Created by liuf on 16/4/14.
 */
public class ResourceUtil {
    public static int getLayoutId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "layout",
                paramContext.getPackageName());
    }

    public static int getStringId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "string",
                paramContext.getPackageName());
    }

    public static int getDrawableId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString,
                "drawable", paramContext.getPackageName());
    }

    public static int getStyleId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "style",
                paramContext.getPackageName());
    }

    public static int getAnimId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "anim",
                paramContext.getPackageName());
    }

    public static int getId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "id",
                paramContext.getPackageName());
    }

    public static int getColorId(Context paramContext, String paramString) {
        return paramContext.getResources().getIdentifier(paramString, "color",
                paramContext.getPackageName());
    }

}
