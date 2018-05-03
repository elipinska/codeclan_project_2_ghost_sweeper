package com.example.ewa.ghostsweeper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.HashMap;

public class SettingsActivity extends AppCompatActivity {

    private RadioGroup difficultyRadioGroup;
    private RadioButton modeRadioButton;
    private Button saveBtn;
    private Integer rowNo;
    private Integer bombCount;
    private Context context;
    private ApplicationState applicationState;
    private RadioButton radioEasy;
    private RadioButton radioMedium;
    private RadioButton radioHard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        context = this;
        radioEasy = findViewById(R.id.radioEasy);
        radioMedium = findViewById(R.id.radioMedium);
        radioHard = findViewById(R.id.radioHard);

        applicationState = SharedPreferencesHelper.loadApplicationState(context);

        HashMap<String, Integer> currentDifficulty = applicationState.getGameDifficulty();

        switch (currentDifficulty.get("rowNo")) {
            case 10:
                radioEasy.setChecked(true);
                break;
            case 20:
                radioMedium.setChecked(true);
                break;
            case 30:
                radioHard.setChecked(true);
                break;
        }


        addListenerOnButton();
    }

    public void addListenerOnButton() {

        difficultyRadioGroup = findViewById(R.id.difficultyRadioGroup);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(new View.OnClickListener() {

            private Intent difficultyIntent;

            @Override
            public void onClick(View v) {


                int selectedId = difficultyRadioGroup.getCheckedRadioButtonId();
                modeRadioButton = findViewById(selectedId);

                HashMap<String, Integer> gameDifficulty = new HashMap<>();


                if ((modeRadioButton.getText()).equals("Easy")) {
                    rowNo = 10;
                    bombCount = 8;
                } else if ((modeRadioButton.getText()).equals("Medium")) {
                    rowNo = 20;
                    bombCount = 20;

                } else if ((modeRadioButton.getText()).equals("Hard")) {
                    rowNo = 30;
                    bombCount = 40;

                }

                gameDifficulty.put("rowNo", rowNo);
                gameDifficulty.put("ghostCount", bombCount);

                applicationState.setGameDifficulty(gameDifficulty);
                SharedPreferencesHelper.saveApplicationState(context, applicationState);

                difficultyIntent = new Intent(context, GhostSweeperActivity.class);
                startActivity(difficultyIntent);

            }

        });
    }
}
