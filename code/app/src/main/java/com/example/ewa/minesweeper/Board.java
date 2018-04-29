package com.example.ewa.minesweeper;

import java.lang.reflect.Array;
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
        calculateHints();

    }

    public ArrayList<ArrayList<Field>> getBoard() {
        return fields;
    }

    public int getRowNo() {
        return rowNo;
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
            int y = rand.nextInt(rowNo);
            int x= rand.nextInt(10);
            IUncoverable newBombField = (IUncoverable) fields.get(y).get(x);
            if (!(newBombField.isBomb())) {
                fields.get(y).set(x, new BombField(new Position(x, y)));
            } else {
                bombNo +=1;
            }

        }
    }

    public ArrayList<Position> getBombPositions() {
        ArrayList<Position> bombPositions = new ArrayList<>();

        for (int y = 0; y < rowNo; y++) {
            for (int x = 0; x < 10; x++) {
                if (((IUncoverable) fields.get(y).get(x)).isBomb()) {
                    bombPositions.add(fields.get(y).get(x).getPosition());
                }
            }
        }
        return bombPositions;

    }

    public void calculateHints() {
        ArrayList<Position> allBombPositions = getBombPositions();

        for (Position position: allBombPositions) {
            int bombY = position.getY();
            int bombX = position.getX();

            for (int i = bombY - 1; i <= bombY + 1; i++) {
                if ((i >= 0) && (i < rowNo)) {
                    for (int j = bombX - 1; j<= bombX + 1; j++) {
                        if ((j >= 0) && (j < 10)) {
                            IUncoverable neighbour = (IUncoverable) fields.get(i).get(j);
                            if (!(neighbour.isBomb())) {
                                ((HintField) neighbour).addToBombCount();
                            }
                        }
                    }
                }
            }
        }
    }

    public Field getFieldAtIndex(int index) {
        int y = index/10;
        int x = index - 10*y;

        return fields.get(y).get(x);
    }

    public Field getFieldAtPosition(Position position) {
        int y = position.getY();
        int x = position.getX();
        return fields.get(y).get(x);
    }

    public Field setFieldAtPosition(Position position, Field field) {
        int y = position.getY();
        int x = position.getX();
        return fields.get(y).set(x, field);
    }


}
