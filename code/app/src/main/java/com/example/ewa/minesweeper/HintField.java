package com.example.ewa.minesweeper;

public class HintField extends Field {

    private Integer bombCount;

    public HintField(Position position) {
        super(position, FieldType.EMPTY);
        bombCount = 0;
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

    @Override
    public FieldType getFieldType() {
        return (bombCount == 0) ? FieldType.EMPTY : FieldType.HINT;
    }
}
