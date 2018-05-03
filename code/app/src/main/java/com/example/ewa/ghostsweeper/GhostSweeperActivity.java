package com.example.ewa.ghostsweeper;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

public class GhostSweeperActivity extends AppCompatActivity {

    private Game game;
    private TextView trapsLeftTextView;
    private GridView gridview;
    private ApplicationState applicationState;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ghost_sweeper);

        applicationState = SharedPreferencesHelper.loadApplicationState(this);


        if (applicationState.getGameDifficulty() == null){
            applicationState = new ApplicationState();
            SharedPreferencesHelper.saveApplicationState(this, applicationState);
        }

        newGame();

        trapsLeftTextView = findViewById(R.id.trapsLeftTextView);
        refreshTrapsLeftTextField();

        gridview.setOnItemClickListener(onItemClick);
        gridview.setOnItemLongClickListener(onItemLongClick);



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

        switch (item.getItemId()) {
            case R.id.new_game:
                newGame();
                return true;
            case R.id.about:
                Intent aboutIntent = new Intent(this, AboutActivity.class);
                startActivity(aboutIntent);
                return true;
            case R.id.settings:
                Intent settingsIntent = new Intent(this, SettingsActivity.class);
                startActivity(settingsIntent);
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
        refreshTrapsLeftTextField();

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
        HashMap gameDifficulty = applicationState.getGameDifficulty();
        Integer rowNo = (Integer) gameDifficulty.get("rowNo");
        Integer ghostCount = (Integer) gameDifficulty.get("ghostCount");

        game = new Game(rowNo, ghostCount);

        BoardAdapter boardAdapter = new BoardAdapter(this, game);

        gridview = findViewById(R.id.gameBoardGridView);
        gridview.setAdapter(boardAdapter);
    }

    public void onNewGameButtonPressed(View button) {
        newGame();
        refreshTrapsLeftTextField();
    }

    public void refreshTrapsLeftTextField() {
        trapsLeftTextView.setText(Integer.toString(game.getTrapsLeft()));
    }

    AdapterView.OnItemClickListener onItemClick = new AdapterView.OnItemClickListener() {
        public void onItemClick(AdapterView<?> parent, View view,
                                int position, long id) {
            Field field = (Field) view.getTag();

            game.uncoverFieldAndNeighbours(field);

            GameStatusType gameStatus = game.checkIfGameWonOrLost();

            if (game.checkIfGameWonOrLost() != GameStatusType.IN_PROGRESS) {
                Toast.makeText(GhostSweeperActivity.this, gameStatus.getMessage(), Toast.LENGTH_LONG).show();
            }

            ((BoardAdapter)gridview.getAdapter()).notifyDataSetChanged();

        }
    };

    AdapterView.OnItemLongClickListener onItemLongClick = new AdapterView.OnItemLongClickListener() {
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
                    refreshTrapsLeftTextField();

                } else if (game.getTrapsLeft() > 0){
                    button.setBackgroundResource(R.drawable.trap);
                    game.subtractFromTrapsLeft();
                    field.toggleLongPressed();
                    vibrator.vibrate(25);
                    refreshTrapsLeftTextField();
                }

            }

            GameStatusType gameStatus = game.checkIfGameWonOrLost();

            if (game.checkIfGameWonOrLost() != GameStatusType.IN_PROGRESS) {
                Toast.makeText(GhostSweeperActivity.this, gameStatus.getMessage(), Toast.LENGTH_LONG).show();
            }

            return true;
        }
    };





}
