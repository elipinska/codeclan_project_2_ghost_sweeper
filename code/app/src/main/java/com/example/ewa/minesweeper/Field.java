package com.example.ewa.minesweeper;

public abstract class Field {

    private Boolean isUncovered;
    private Position position;

    public Field(Position position) {
        this.isUncovered = false;
        this.position = position;
    }

    public Boolean getUncovered() {
        return isUncovered;
    }

    public Position getPosition() {
        return position;
    }
}
