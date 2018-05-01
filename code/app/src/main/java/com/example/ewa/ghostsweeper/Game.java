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

        if (uncoveredFieldsCount == allFieldsCount) {
            return GameStatusType.LOST;
        } else if (uncoveredFieldsCount == allFieldsCount - bombCount) {
            return GameStatusType.WON;
        } else {
            return GameStatusType.IN_PROGRESS;
        }
    }

    public void uncoverAll() {
        for(Field field: board.getSimpleFieldsArray()) {
            field.markAsUncovered();
        }


    }


    public void uncoverFieldAndNeighbours(Field field) {

        if (!field.getIsLongPressed()) {

            if (field.getFieldType() == FieldType.BOMB) {
                if (!field.isUncovered()) {
                    ((GhostField) field).activate();
//                    Toast.makeText(mContext, R.string.lost_message,
//                            Toast.LENGTH_LONG).show();
                }
                uncoverAll();
            }

            field.markAsUncovered();



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
