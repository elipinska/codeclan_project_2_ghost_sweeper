package com.e20.ewa.ghostsweeper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.ewa.ghostsweeper.R;

import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup difficultyRadioGroup;
    private RadioButton modeRadioButton;
    private Integer rowNo;
    private Integer ghostCount;
    private Context context;
    private ApplicationState applicationState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        context = this;
        RadioButton radioEasy = findViewById(R.id.radioEasy);
        RadioButton radioMedium = findViewById(R.id.radioMedium);
        RadioButton radioHard = findViewById(R.id.radioHard);

        applicationState = SharedPreferencesHelper.loadApplicationState(context);

        HashMap<String, Integer> currentDifficulty = applicationState.getGameDifficulty();

        switch (currentDifficulty.get("rowNo")) {
            case 10:
                radioEasy.setChecked(true);
                break;
            case 15:
                radioMedium.setChecked(true);
                break;
            case 20:
                radioHard.setChecked(true);
                break;
        }


        addListenerOnButton();
    }

    public void addListenerOnButton() {

        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        Button saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            private Intent difficultyIntent;

            @Override
            public void onClick(View v) {


                int selectedId = difficultyRadioGroup.getCheckedRadioButtonId();
                modeRadioButton = findViewById(selectedId);

                HashMap<String, Integer> gameDifficulty = new HashMap<>();


                if ((modeRadioButton.getText()).equals("Easy")) {
                    rowNo = 10;
                    ghostCount = 10;
                } else if ((modeRadioButton.getText()).equals("Medium")) {
                    rowNo = 15;
                    ghostCount = 20;

                } else if ((modeRadioButton.getText()).equals("Hard")) {
                    rowNo = 20;
                    ghostCount = 35;

                }

                gameDifficulty.put("rowNo", rowNo);
                gameDifficulty.put("ghostCount", ghostCount);

                applicationState.setGameDifficulty(gameDifficulty);
                SharedPreferencesHelper.saveApplicationState(context, applicationState);

                difficultyIntent = new Intent(context, GhostSweeperActivity.class);
                startActivity(difficultyIntent);

            }

        });
    }
}
