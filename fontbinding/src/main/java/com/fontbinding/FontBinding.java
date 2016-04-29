package com.fontbinding;

import android.databinding.BindingAdapter;
import android.widget.TextView;

/**
 * Created by vivek on 29/04/16.
 */
public class FontBinding {

    @BindingAdapter({"bind:font"})
    public static void setFont(TextView textView, String fontName) {
        textView.setTypeface(FontFactory.getInstance().getFont(fontName));
    }

    ;

}

