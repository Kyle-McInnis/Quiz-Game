package com.example.cogsquizmaster;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class QuizSelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_select);
    }

    public void quizOne(View view) {
        Intent intent = new Intent(QuizSelect.this, QuizOne.class);
        String en = getIntent().getStringExtra("keyenterName");
        intent.putExtra("keyenterName", en);
        startActivity(intent);
        finish();
    }

    public void quizTwo(View view) {
        Intent intent = new Intent(QuizSelect.this, QuizTwo.class);
        startActivity(intent);
        finish();
    }

    public void quizThree(View view) {
        Intent intent = new Intent(QuizSelect.this, QuizThree.class);
        startActivity(intent);
        finish();
    }
}
