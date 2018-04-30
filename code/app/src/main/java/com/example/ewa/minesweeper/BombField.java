package com.example.ewa.minesweeper;

public class BombField extends Field {

    public BombField(Position position) {
        super(position);
    }

    @Override
    public String getTextForButton() {
        if(!getIsLongPressed()) {
            return (isUncovered()) ? "Bomb" : "";
        } else {
            return "Long";
        }
    }

    public String explode() {
       return "Game Over";
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.BOMB;
    }
}
