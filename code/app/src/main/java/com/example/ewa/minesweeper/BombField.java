package com.example.ewa.minesweeper;

public class BombField extends Field implements IUncoverable {

    public BombField(Position position) {
        super(position);
    }

    public String explode() {
        String result = "";
        if (getUncovered()) {
            result = "Game over";
        }
        return result;
    }

    public Boolean isBomb() {
        return true;
    }
}
