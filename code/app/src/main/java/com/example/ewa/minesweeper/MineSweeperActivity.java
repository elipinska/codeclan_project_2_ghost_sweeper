package com.example.ewa.minesweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.Toast;

public class MineSweeperActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_sweeper);

        newGame();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //This will get called when the menu item gets clicked
        if(item.getItemId() == R.id.new_game) {
            newGame();

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void newGame() {
        Board board = new Board(20);

        BoardAdapter boardAdapter = new BoardAdapter(this, board);

        GridView gridview = findViewById(R.id.gameBoardGridView);
        gridview.setAdapter(boardAdapter);
    }



}
