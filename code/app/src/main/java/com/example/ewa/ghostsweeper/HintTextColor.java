package com.example.ewa.ghostsweeper;

import java.util.HashMap;
import java.util.Map;

public class HintTextColor {

    public static int getHintColor(String hint) {
        Map<String, Integer> hintColors = new HashMap<String, Integer>() {{
            put("", R.color.colorPrimary);
            put("1", R.color.hint_color_one);
            put("2", R.color.hint_color_two);
            put("3", R.color.hint_color_three);
            put("4", R.color.hint_color_four);
            put("5", R.color.hint_color_five);
            put("6", R.color.hint_color_six);
            put("7", R.color.hint_color_seven);
            put("8", R.color.hint_color_eight);
        }};
        return hintColors.get(hint);
    }
}
