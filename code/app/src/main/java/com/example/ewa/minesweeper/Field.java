package com.example.ewa.minesweeper;

public abstract class Field {

    private Boolean isUncovered;
    private Position position;
    private FieldType fieldType;

    public Field(Position position, FieldType fieldType) {
        this.isUncovered = false;
        this.position = position;
        this.fieldType = fieldType;

    }

    public Boolean isUncovered() {
        return isUncovered;
    }

    public Position getPosition() {
        return position;
    }

    public abstract String getTextForButton();

    public void markAsUncovered(){
        this.isUncovered = true;
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public Boolean isBomb() {
        return false;
    }
}
