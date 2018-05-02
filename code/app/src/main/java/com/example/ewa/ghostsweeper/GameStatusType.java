package com.example.ewa.ghostsweeper;

public enum GameStatusType {
    IN_PROGRESS(0),
    WON(R.string.won_message),
    LOST(R.string.lost_message);

    private final int message;

    GameStatusType(int message) {
        this.message = message;
    }

    public int getMessage() {
        return message;
    }
}
