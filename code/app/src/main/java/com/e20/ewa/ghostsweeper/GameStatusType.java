package com.e20.ewa.ghostsweeper;

import com.example.ewa.ghostsweeper.R;

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
