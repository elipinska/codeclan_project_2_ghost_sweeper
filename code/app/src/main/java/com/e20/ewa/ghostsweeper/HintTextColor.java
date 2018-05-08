package com.e20.ewa.ghostsweeper;

import com.example.ewa.ghostsweeper.R;

import java.util.ArrayList;
import java.util.Arrays;


public class HintTextColor {

    public static int getHintColor(Integer hintValue) {
        ArrayList<Integer> hintColors = new ArrayList<>();
        hintColors.addAll(Arrays.asList(R.color.colorPrimary,
                R.color.hint_color_one,
                R.color.hint_color_two,
                R.color.hint_color_three,
                R.color.hint_color_four,
                R.color.hint_color_five,
                R.color.hint_color_six,
                R.color.hint_color_seven,
                R.color.hint_color_eight
        ));

        return hintColors.get(hintValue);
    }
}
