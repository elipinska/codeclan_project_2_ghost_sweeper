package com.example.ewa.minesweeper;

public class BombField extends Field {

    public BombField(Position position) {
        super(position, FieldType.BOMB);
    }

    @Override
    public String getTextForButton() {
        return (isUncovered()) ? "Bomb" : "";
    }

    public String explode() {
       return "Game Over";
    }

}
