package com.example.ewa.minesweeper;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    //Potentially redundant?
    public int asIndex() {
        //Based on a grid with 10 columns, calculate the index in GridView based on a 2d array
        return (y * 10) + x;
    }
}
