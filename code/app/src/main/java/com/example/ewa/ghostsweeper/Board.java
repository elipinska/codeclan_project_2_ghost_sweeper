package com.example.ewa.ghostsweeper;

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
        int bombNo = rowNo * 2; //One fifth of all fields

        for (int i = 0; i< bombNo; i++) {
            Random rand = new Random();
            int y = rand.nextInt(rowNo);
            int x= rand.nextInt(10);
            Field newBombField = fields.get(y).get(x);
            if (!(newBombField.getFieldType() == FieldType.BOMB)) {
                fields.get(y).set(x, new GhostField(new Position(x, y)));
            } else {
                bombNo +=1;
            }

        }
    }

    public ArrayList<Position> getBombPositions() {
        ArrayList<Position> bombPositions = new ArrayList<>();

        for (int y = 0; y < rowNo; y++) {
            for (int x = 0; x < 10; x++) {
                if ((fields.get(y).get(x)).getFieldType() == FieldType.BOMB) {
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
                            Field neighbour = fields.get(i).get(j);
                            if (!(neighbour.getFieldType() == FieldType.BOMB)) {
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
    

    public ArrayList<Field> getSimpleFieldsArray() {
        ArrayList<Field> simpleArrayOfFields = new ArrayList<>();

        for (ArrayList<Field> row:fields) {
            for (Field field:row) {
                simpleArrayOfFields.add(field);
            }
        }

        return simpleArrayOfFields;
    }


    public ArrayList<Field> getAllNeighboursForField(Field field) {
        Position position = field.getPosition();
        ArrayList<Field> neighbours = new ArrayList<>();

        int fieldY = position.getY();
        int fieldX = position.getX();

        for (int i = fieldY - 1; i <= fieldY + 1; i++) {
            if ((i >= 0) && (i < rowNo)) {
                for (int j = fieldX - 1; j<= fieldX + 1; j++) {
                    if ((j >= 0) && (j < 10)) {
                        Field neighbour = fields.get(i).get(j);
                            if (neighbour != field) {
                                neighbours.add(neighbour);
                            }
                        }
                    }
                }
            }
        return neighbours;
    }






}
