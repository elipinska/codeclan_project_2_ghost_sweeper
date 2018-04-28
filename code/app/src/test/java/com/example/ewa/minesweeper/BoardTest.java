package com.example.ewa.minesweeper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BoardTest {

    private Board board;

    @Before
    public void before() {
        board = new Board(5);
    }

    @Test
    public void boardHasRowNo() {
        assertEquals(5, board.getRowNo());
    }

    @Test
    public void hasBoardIn10ColumnsAnd5Rows() {
        assertEquals(5, board.getBoard().size());
        assertEquals(10, board.getBoard().get(0).size());
    }

    @Test
    public void boardHasBombs__ShouldHave5() {
        int bombsFound = 0;

        for (int y = 0; y < board.getRowNo(); y++) {
            for (int x = 0; x < 10; x++) {
                if (((IUncoverable) board.getBoard().get(y).get(x)).isBomb()) {
                    bombsFound += 1;
                }
            }
        }
        assertEquals(5, bombsFound);
    }
}
