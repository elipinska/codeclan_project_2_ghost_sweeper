package com.example.ewa.ghostsweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {

    private TextView aboutInfoTextView;
    private TextView creditsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        aboutInfoTextView = findViewById(R.id.aboutInfoTextView);
        creditsTextView = findViewById(R.id.creditsInfoTextView);

        aboutInfoTextView.setMovementMethod(LinkMovementMethod.getInstance());
        creditsTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
