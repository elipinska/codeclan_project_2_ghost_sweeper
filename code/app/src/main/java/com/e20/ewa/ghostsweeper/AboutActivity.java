package com.e20.ewa.ghostsweeper;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

import com.example.ewa.ghostsweeper.R;

public class AboutActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        TextView aboutInfoTextView = findViewById(R.id.aboutInfoTextView);
        TextView creditsTextView = findViewById(R.id.creditsInfoTextView);

        aboutInfoTextView.setMovementMethod(LinkMovementMethod.getInstance());
        creditsTextView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
