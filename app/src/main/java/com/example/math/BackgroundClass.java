package com.example.math;

import android.content.Context;
import android.content.SharedPreferences;

public class BackgroundClass {
    public static void putBackground(int bg, Context c){
        int background = bg;
        Context mContext = c;

        SharedPreferences preferences = mContext.getSharedPreferences("fondos", Context.MODE_PRIVATE);

        if(background < 0) {
            background = preferences.getInt("sp_fondo", 0);
        }else{
            SharedPreferences.Editor E_background = preferences.edit();
            E_background.putInt("sp_fondo", background);
            E_background.apply();
        }

        switch (background){
            case 6:
                mContext.setTheme(R.style.ThemeMath);
                break;
            case 5:
                mContext.setTheme(R.style.ThemeBlue);
                break;
            case 4:
                mContext.setTheme(R.style.ThemeBrown);
                break;
            case 3:
                mContext.setTheme(R.style.ThemeRed);
                break;
            case 2:
                mContext.setTheme(R.style.ThemeAmber);
                break;
            case 1:
                mContext.setTheme(R.style.ThemeLime);
                break;
        }
    }
}
