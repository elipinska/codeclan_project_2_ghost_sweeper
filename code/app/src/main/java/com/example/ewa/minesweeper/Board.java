package com.example.ewa.minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private ArrayList<ArrayList<Field>> fields;
    private int rowNo;

    public Board(int rowNo) {
        this.fields = new ArrayList<>();
        this.rowNo = rowNo;
        createFields();
        addBombs();

    }

    public void createFields() {
        for (int y = 0; y < rowNo; y++) {
            fields.add(new ArrayList<Field>());
            for (int x = 0; x < 10; x++) {
                fields.get(y).add(new HintField(new Position(x, y)));
            }
        }
    }

    public void addBombs() {
        int bombNo = rowNo; //One tenth of all fields

        for (int i = 0; i< bombNo; i++) {
            Random rand = new Random();
            int y = rand.nextInt(rowNo - 1);
            int x= rand.nextInt(9);
            IUncoverable newBombField = (IUncoverable) fields.get(y).get(x);
            if (!(newBombField.isBomb())) {
                fields.get(y).set(x, new BombField(new Position(x, y)));
            } else {
                bombNo +=1;
            }

        }
    }


    public ArrayList<ArrayList<Field>> getBoard() {
        return fields;
    }

    public int getRowNo() {
        return rowNo;
    }
}
