package com.example.ewa.ghostsweeper;

import java.util.ArrayList;

public class Game {

    private Board board;
    private int uncoveredFieldsCount;
    private GameStatusType gameStatus;

    public Game(int rowNo) {
        this.board = new Board(rowNo);
        this.uncoveredFieldsCount = 0;
        this.gameStatus = GameStatusType.IN_PROGRESS;
    }

    public Board getBoard() {
        return board;
    }

    public int getUncoveredFieldsCount() {
        return uncoveredFieldsCount;
    }

    public GameStatusType getGameStatus() {
        return gameStatus;
    }

    public void addToUncoveredFieldsCount() {
        uncoveredFieldsCount++;
    }

    public GameStatusType checkIfGameWonOrLost() {
        int allFieldsCount = board.getSimpleFieldsArray().size();
        int bombCount = board.getBombPositions().size();

        if (uncoveredFieldsCount >= allFieldsCount) {
            gameStatus = GameStatusType.LOST;
        } else if (uncoveredFieldsCount == allFieldsCount - bombCount) {
            gameStatus = GameStatusType.WON;
        }
        return gameStatus;
    }

    public void uncoverAll() {
        for(Field field: board.getSimpleFieldsArray()) {
            if (!field.isUncovered()) {
                field.markAsUncovered();
                addToUncoveredFieldsCount();
            }
        }


    }


    public void uncoverFieldAndNeighbours(Field field) {

        if (!field.getIsLongPressed()) {

            if (field.getFieldType() == FieldType.BOMB) {
                if (!field.isUncovered()) {
                    ((GhostField) field).activate();
                }
                uncoverAll();
            } else {

                field.markAsUncovered();
                addToUncoveredFieldsCount();


                if (field.getFieldType() == FieldType.EMPTY) {
                    ArrayList<Field> neighbours = board.getAllNeighboursForField(field);
                    for (Field neighbour : neighbours) {
                        if (neighbour.getFieldType() != FieldType.BOMB && !neighbour.isUncovered()) {
                            uncoverFieldAndNeighbours(neighbour); //recursion
                        }
                    }

                }
            }
        }
    }
}
