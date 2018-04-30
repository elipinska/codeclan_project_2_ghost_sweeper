package com.example.ewa.minesweeper;

public class BombField extends Field {

    public BombField(Position position) {
        super(position);
    }

    @Override
    public String getTextForButton() {
        if(getIsLongPressed() && !isUncovered()) {
            return "Long";
        } else {
            return (isUncovered()) ? "Bomb" : "";
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
