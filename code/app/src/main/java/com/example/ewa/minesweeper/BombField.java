package com.example.ewa.minesweeper;

public class BombField extends Field implements IUncoverable {

    public BombField(Position position) {
        super(position);
    }

    @Override
    public String getTextForButton() {
        return (isUncovered()) ? "Bomb" : "";
    }

    public String explode() {
       return "Game Over";
    }

    public Boolean isBomb() {
        return true;
    }
}
