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


    public void addToBombCount() {
        bombCount++;
    }

    @Override
    public String getTextForButton() {
        return (isUncovered()) ? getBombCount().toString() : "";
    }
}
