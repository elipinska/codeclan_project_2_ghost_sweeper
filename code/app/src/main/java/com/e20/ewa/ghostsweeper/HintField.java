package com.e20.ewa.ghostsweeper;

public class HintField extends Field {

    private Integer ghostCount;

    public HintField(Position position) {
        super(position);
        ghostCount = 0;
    }

    public Integer getGhostCount() {
        return ghostCount;
    }

    public void addToGhostCount() {
        ghostCount++;
    }

    @Override
    public String getTextForButton() {
        if (getIsLongPressed() && !isUncovered()) {
            return "";
        } else if (getIsLongPressed() && isUncovered()) {
            return "X";
        } else {
            return (!getIsLongPressed() && isUncovered() && ghostCount > 0) ? ghostCount.toString() : "";
        }
    }

    @Override
    public FieldType getFieldType() {
        return (ghostCount == 0) ? FieldType.EMPTY : FieldType.HINT;
    }
}
