package com.example.ewa.ghostsweeper;

import android.content.Context;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GhostSweeperActivity extends AppCompatActivity {

    private Game game;
    private TextView trapsLeftTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_sweeper);

        game = new Game(2);

        final BoardAdapter boardAdapter = new BoardAdapter(this, game);

        GridView gridview = findViewById(R.id.gameBoardGridView);
        gridview.setAdapter(boardAdapter);

        trapsLeftTextView = findViewById(R.id.trapsLeftTextView);
        trapsLeftTextView.setText(Integer.toString(game.getTrapsLeft()));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Field field = (Field) view.getTag();

                if (!field.getIsLongPressed()) {
                    game.uncoverFieldAndNeighbours(field);

                    GameStatusType gameStatus = game.checkIfGameWonOrLost();

                    if (gameStatus == GameStatusType.LOST) {
                        Toast.makeText(GhostSweeperActivity.this, R.string.lost_message, Toast.LENGTH_LONG).show();
                    } else if (gameStatus == GameStatusType.WON) {
                        Toast.makeText(GhostSweeperActivity.this, R.string.won_message, Toast.LENGTH_LONG).show();
                    }

                    TextView tile = view.findViewById(R.id.singleTileTextView);

                    String buttonText = field.getTextForButton();
                    tile.setText(buttonText);

                    boardAdapter.notifyDataSetChanged();

                }
            }
        });

        gridview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            public boolean onItemLongClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Vibrator vibrator = (Vibrator) GhostSweeperActivity.this.getSystemService(Context.VIBRATOR_SERVICE);
                TextView button = view.findViewById(R.id.singleTileTextView);
                Field field = (Field) view.getTag();

                if (!field.isUncovered()){
                    if (field.getIsLongPressed()) {
                        button.setBackgroundColor(ContextCompat.getColor(GhostSweeperActivity.this, R.color.coveredTiles));
                        button.setText("");
                        game.addToTrapsLeft();
                        field.toggleLongPressed();
                        vibrator.vibrate(25);
                    } else if (game.getTrapsLeft() > 0){
                        button.setBackgroundResource(R.drawable.trap);
                        game.subtractFromTrapsLeft();
                        field.toggleLongPressed();
                        vibrator.vibrate(25);
                    }

                }
                return true;
            }
        });

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





}
