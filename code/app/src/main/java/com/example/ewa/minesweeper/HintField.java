package com.example.ewa.minesweeper;

public class HintField extends Field implements IUncoverable {

    private Integer bombCount;

    public HintField(Position position) {
        super(position);
        bombCount = 0;
    }

    public Boolean isBomb() {
        return false;
    }

    public Integer getBombCount() {
        return bombCount;
    }

    public void setBombCount(int bombNo) {
        this.bombCount = bombNo;
    }

    public void addToBombCount() {
        bombCount++;
    }
}
