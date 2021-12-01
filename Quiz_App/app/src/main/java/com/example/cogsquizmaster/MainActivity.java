package com.example.cogsquizmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText enterName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        enterName = findViewById(R.id.enterName);

    }

    public void startGame(View view) {

        String en = enterName.getText().toString();

        Intent intent = new Intent(MainActivity.this, QuizSelect.class);
        intent.putExtra("keyenterName", en);
        startActivity(intent);
        finish();
    }
}