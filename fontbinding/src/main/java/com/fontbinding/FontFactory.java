package com.fontbinding;

import android.content.Context;
import android.graphics.Typeface;

import java.util.HashMap;

/**
 * Created by vivek on 29/04/16.
 */
public class FontFactory {
    private static FontFactory fontFactory;
    private HashMap<String, Typeface> fontMap = new HashMap<String, Typeface>();
    public static FontFactory getInstance() {
        if (fontFactory == null)
            fontFactory = new FontFactory();
        return fontFactory;
    }

    public Typeface getFont(Context mActivity, String fontName) {
        Typeface typeface = fontMap.get(fontName);
        if (typeface == null && mActivity != null) {
            typeface = Typeface.createFromAsset(mActivity.getResources().getAssets(), "fonts/" + fontName);
            fontMap.put(fontName, typeface);
        }
        return typeface;
    }

    public static Typeface getFont(String fontName) {
        return getInstance().getFont(Application.getAppContext(), fontName);
    }

//    public static Typeface getProximaNovaSemiBold(Context activity) {
//        return getInstance().getFont(activity, "ProximaNova-Semibold.otf");
//    }
}
