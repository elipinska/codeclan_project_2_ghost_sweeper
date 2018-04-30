package com.example.ewa.minesweeper;

public class HintField extends Field {

    private Integer bombCount;

    public HintField(Position position) {
        super(position);
        bombCount = 0;
    }

    public Integer getBombCount() {
        return bombCount;
    }

    public void addToBombCount() {
        bombCount++;
    }

    public String prepareTextForButton() {
        if (bombCount > 0) {
            return bombCount.toString();
        } else {
            return "";
        }
    }

    @Override
    public String getTextForButton() {
        if (!getIsLongPressed()) {
            return (isUncovered()) ? prepareTextForButton() : "";
        } else {
            return "Long";
        }
    }

    @Override
    public FieldType getFieldType() {
        return (bombCount == 0) ? FieldType.EMPTY : FieldType.HINT;
    }
}
