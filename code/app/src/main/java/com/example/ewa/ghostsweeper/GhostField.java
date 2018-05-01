package com.example.ewa.ghostsweeper;

public class GhostField extends Field {

    private Boolean activatedByPlayer;

    public GhostField(Position position) {
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
        return "";
    }


    @Override
    public FieldType getFieldType() {
        return FieldType.BOMB;
    }
}
