package com.example.cogsquizmaster;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class QuizComplete extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    TextView tvPoints;

    private TextView textViewPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_complete);
        textViewPlayer = findViewById(R.id.textViewPlayer);

        String en = getIntent().getStringExtra("keyenterName");

        textViewPlayer.setText(en);

        // Receive the extra data from Intent that is sent from StartGame Activity.
        int points = getIntent().getExtras().getInt("points");
        // Get the handles of the TextViews for points and personal best
        tvPoints = findViewById(R.id.tvPoints);
        // Set tvPoints with the value of points
        tvPoints.setText("" + points);
        // Instantiate the SharedPreferences object reference
        sharedPreferences = getSharedPreferences("pref", 0);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        // Check, if points is greater than pointsSP
    }

    public void exit(View view) {
        // Call finish() method to finish GameOver Activity
        finish();
    }

    public void backMainMenu(View view) {
        Intent intent = new Intent(QuizComplete.this, MainActivity.class);
        startActivity(intent);
    }
}