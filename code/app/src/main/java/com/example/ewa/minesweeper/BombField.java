package com.example.ewa.minesweeper;

public class BombField extends Field {

    private Boolean activatedByPlayer;

    public BombField(Position position) {
        super(position);
        this.activatedByPlayer = false;

    }

    public Boolean isActivatedByPlayer() {
        return activatedByPlayer;
    }

    public void activate() {
        this.activatedByPlayer = true;
    }

    @Override
    public String getTextForButton() {
        if(getIsLongPressed() && !isUncovered()) {
            return "Long";
        } else {
            return (isUncovered()) ? "Bomb" : "";
        }
    }

    public String explode() {
       return "Game Over";
    }

    @Override
    public FieldType getFieldType() {
        return FieldType.BOMB;
    }
}
