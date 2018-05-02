package com.example.ewa.ghostsweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

public class GhostSweeperActivity extends AppCompatActivity {

    private Game game;
    private TextView trapsLeftTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_sweeper);

        newGame();

        trapsLeftTextView = findViewById(R.id.trapsLeftTextView);
        trapsLeftTextView.setText(Integer.toString(game.getTrapsLeft()));

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

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("game", game);
    }

    @Override
    public void onResume(){
        super.onResume();
        BoardAdapter boardAdapter = new BoardAdapter(this, game);

        GridView gridview = findViewById(R.id.gameBoardGridView);
        gridview.setAdapter(boardAdapter);

    }

    @Override
    public void onPause(){
        super.onPause();
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        game = savedInstanceState.getParcelable("game");

    }

    public void newGame() {
        game = new Game(20);

        BoardAdapter boardAdapter = new BoardAdapter(this, game);

        GridView gridview = findViewById(R.id.gameBoardGridView);
        gridview.setAdapter(boardAdapter);
    }

    public void onNewGameButtonPressed(View button) {
        newGame();
    }

    public void refreshTrapsLeft() {
        TextView trapsLeftTextView = findViewById(R.id.trapsLeftTextView);
    }



}
