package com.example.ewa.ghostsweeper;

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
}
