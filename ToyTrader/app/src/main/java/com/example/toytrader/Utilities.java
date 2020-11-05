package com.example.toytrader;

import android.text.TextUtils;
import android.util.Patterns;

public class Utilities {
    public static boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    public static boolean isValidPassword(CharSequence target) {
        return (!TextUtils.isEmpty(target) && target.length() > 6);
    }
}
