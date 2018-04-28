package com.example.ewa.minesweeper;

public class BombField extends Field implements IUncoverable {

    public BombField(Position position) {
        super(position);
    }

    public String explode() {
       return "Game Over";
    }

    public Boolean isBomb() {
        return true;
    }
}
