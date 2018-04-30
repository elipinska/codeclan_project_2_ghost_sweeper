package com.example.ewa.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

public class MineSweeperActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_sweeper);

        Board board = new Board(10);

        BoardAdapter boardAdapter = new BoardAdapter(this, board);

        GridView gridview = (GridView) findViewById(R.id.gameBoardGridView);
        gridview.setAdapter(boardAdapter);

    }



}
