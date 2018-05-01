package com.example.ewa.minesweeper;

import android.graphics.drawable.Drawable;

public class BombField extends Field {

    private Boolean activatedByPlayer;

    public BombField(Position position) {
        super(position);
        this.activatedByPlayer = false;

    }

    public Boolean isActivatedByPlayer() {
        return activatedByPlayer;
    }

    public void activate() {
        this.activatedByPlayer = true;
    }

    @Override
    public String getTextForButton() {
        return "";
    }






    @Override
    public FieldType getFieldType() {
        return FieldType.BOMB;
    }
}
