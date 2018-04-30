package com.example.ewa.minesweeper;

public abstract class Field {

    private Boolean isUncovered;
    private Position position;
    private Boolean isLongPressed;

    public Field(Position position) {
        this.isUncovered = false;
        this.isLongPressed = false;
        this.position = position;

    }

    public Boolean isUncovered() {
        return isUncovered;
    }

    public Boolean getIsLongPressed() {
        return isLongPressed;
    }

    public void toggleLongPressed() {
        if (isLongPressed) {
            isLongPressed = false;
        } else {
            isLongPressed = true;
        }
    }

    public Position getPosition() {
        return position;
    }

    public abstract String getTextForButton();

    public void markAsUncovered(){
        this.isUncovered = true;
    }

    public abstract FieldType getFieldType();

}
