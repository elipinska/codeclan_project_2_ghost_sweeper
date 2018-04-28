package com.example.ewa.minesweeper;

public class HintField extends Field implements IUncoverable {

    private int bombNo;

    public HintField(Position position) {
        super(position);
        bombNo = 0;
    }

    public Boolean isBomb() {
        return false;
    }
}
