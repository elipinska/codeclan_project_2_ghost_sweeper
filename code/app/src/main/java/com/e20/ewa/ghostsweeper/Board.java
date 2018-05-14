package com.e20.ewa.ghostsweeper;

import java.util.ArrayList;
import java.util.Random;

public class Board {
    private ArrayList<ArrayList<Field>> fields;
    private int rowNo;
    private int ghostCount;

    public Board(int rowNo, int ghostCount) {
        this.fields = new ArrayList<>();
        this.ghostCount = ghostCount;
        this.rowNo = rowNo;
        createFields();
        addGhostsToGhostSweeperBoard();
        calculateHints();

    }

    public int getGhostCount() {
        return ghostCount;
    }

    public ArrayList<ArrayList<Field>> getBoard() {
        return fields;
    }

    public int getRowNo() {
        return rowNo;
    }


    private void createFields() {
        for (int y = 0; y < rowNo; y++) {
            fields.add(new ArrayList<Field>());
            for (int x = 0; x < 10; x++) {
                fields.get(y).add(new HintField(new Position(x, y)));
            }
        }
    }


    private void addGhostsToGhostSweeperBoard() {

        int ghostsToAdd = ghostCount;

        for (int i = 0; i< ghostsToAdd; i++) {
            Random rand = new Random();
            int y = rand.nextInt(rowNo);
            int x= rand.nextInt(10);
            Field newGhostField = fields.get(y).get(x);
            if (!(newGhostField.getFieldType() == FieldType.GHOST)) {
                fields.get(y).set(x, new GhostField(new Position(x, y)));
            } else {
                ghostsToAdd +=1;
            }

        }
    }

    public ArrayList<Position> getGhostPositions() {
        ArrayList<Position> ghostPositions = new ArrayList<>();

        for (int y = 0; y < rowNo; y++) {
            for (int x = 0; x < 10; x++) {
                if ((fields.get(y).get(x)).getFieldType() == FieldType.GHOST) {
                    ghostPositions.add(fields.get(y).get(x).getPosition());
                }
            }
        }
        return ghostPositions;

    }

    private void calculateHints() {
        ArrayList<Position> allGhostPositions = getGhostPositions();

        for (Position position: allGhostPositions) {
            int ghostY = position.getY();
            int ghostX = position.getX();

            for (int i = ghostY - 1; i <= ghostY + 1; i++) {
                if ((i >= 0) && (i < rowNo)) {
                    for (int j = ghostX - 1; j<= ghostX + 1; j++) {
                        if ((j >= 0) && (j < 10)) {
                            Field neighbour = fields.get(i).get(j);
                            if (!(neighbour.getFieldType() == FieldType.GHOST)) {
                                ((HintField) neighbour).addToGhostCount();
                            }
                        }
                    }
                }
            }
        }
    }

    public Field getFieldAtIndex(int index) {
        int y = index/10;
        int x = index % 10;

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
