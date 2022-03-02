package com.example.cogsquizmaster;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class QuizTwo extends AppCompatActivity {

    // Declare a TextView for showing Timer
    TextView tvTimer;
    // A TextView for showing Result
    TextView tvResult;
    // An ImageView for showing an image in question
    ImageView ivShowImage;
    // Instantiate a HashMap to store technology names and corresponding image resource ids
    HashMap<String, Integer> map = new HashMap<>();
    // An ArrayList for storing technology names only
    ArrayList<String> techList = new ArrayList<>();
    // Declare an index variable. We'll keep incrementing it as the quiz proceeds to
    // the next questions.
    int index;
    // Declare four button object references for displaying four options to choose from
    Button btn1, btn2, btn3, btn4;
    // A TextView for displaying points
    TextView tvPoints;
    // An integer variable to store points
    int points;
    // A CountDownTimer object reference
    CountDownTimer countDownTimer;
    // And a long integer to store the time limit for each question to be used
    // with the CountDownTimer.
    long millisUntilFinished;

    TextView tvQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_two);

        tvTimer = findViewById(R.id.tvTimer);

        tvQuestion = findViewById(R.id.tvQuestion);

        tvResult = findViewById(R.id.tvResult);
        ivShowImage = findViewById(R.id.ivShowImage);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        tvPoints = findViewById(R.id.tvPoints);

        index = 0;

        techList.add("C");
        techList.add("C++");
        techList.add("C#");
        techList.add("Python");
        techList.add("Swift");
        techList.add("JavaScript");
        techList.add("English");
        techList.add("Java");
        techList.add("Objective-C");
        techList.add("Ruby");


        // Put all the technology names with technology image resource ids in map.
        map.put(techList.get(0), R.drawable.clan);
        map.put(techList.get(1), R.drawable.cplusplus);
        map.put(techList.get(2), R.drawable.csharp);
        map.put(techList.get(3), R.drawable.python);
        map.put(techList.get(4), R.drawable.swift);
        map.put(techList.get(5), R.drawable.javascript);
        map.put(techList.get(6), R.drawable.english);
        map.put(techList.get(7), R.drawable.javalan);
        map.put(techList.get(8), R.drawable.objc);
        map.put(techList.get(9), R.drawable.ruby);

        Collections.shuffle(techList);
        millisUntilFinished = 20000;
        points = 0;
        quizTwo();
    }

    private void quizTwo() {

        millisUntilFinished = 20000;
        tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
        tvPoints.setText(points + " / " + techList.size());
        generateQuestions(index);
        countDownTimer = new CountDownTimer(millisUntilFinished, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                // Update tvTimer every 1 second to show the number of seconds remaining.
                tvTimer.setText("" + (millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                index++;
                // When timer is finished check if all questions are being asked.
                if (index > techList.size() - 1){
                    // If true, hide the ImageView and Buttons.
                    ivShowImage.setVisibility(View.GONE);
                    btn1.setVisibility(View.GONE);
                    btn2.setVisibility(View.GONE);
                    btn3.setVisibility(View.GONE);
                    btn4.setVisibility(View.GONE);


                    // Go to GameOver screen with points using an Intent
                    Intent intent = new Intent(QuizTwo.this, QuizComplete.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    // Finish StartGame Activity
                    finish();
                } else {
                    countDownTimer = null;
                    quizTwo();
                }
            }
        }.start(); // Call start() method to start the timer.
    }

    private void generateQuestions(int index) {
        ArrayList<String> techListTemp = (ArrayList<String>) techList.clone();
        String correctAnswer = techList.get(index);
        techListTemp.remove(correctAnswer);
        Collections.shuffle(techListTemp);
        ArrayList<String> newList = new ArrayList<>();
        newList.add(techListTemp.get(0));
        newList.add(techListTemp.get(1));
        newList.add(techListTemp.get(2));
        newList.add(correctAnswer);
        Collections.shuffle(newList);
        btn1.setText(newList.get(0));
        btn2.setText(newList.get(1));
        btn3.setText(newList.get(2));
        btn4.setText(newList.get(3));
        // Also, set the ImageView with current image from map
        ivShowImage.setImageResource(map.get(techList.get(index)));
    }

    public void nextQuestion(View view) {
        // Enable the buttons
        btn1.setEnabled(true);
        btn2.setEnabled(true);
        btn3.setEnabled(true);
        btn4.setEnabled(true);
        // Cancel the countDownTimer
        countDownTimer.cancel();
        index++;
        // Check if all questions have been asked.
        if (index > techList.size() - 1){
            // If true, hide the ImageView and Buttons.
            ivShowImage.setVisibility(View.GONE);
            btn1.setVisibility(View.GONE);
            btn2.setVisibility(View.GONE);
            btn3.setVisibility(View.GONE);
            btn4.setVisibility(View.GONE);

            // Go to GameOver screen with points
            Intent intent = new Intent(QuizTwo.this, QuizComplete.class);
            String en1 = getIntent().getStringExtra("keyenterName");
            intent.putExtra("keyenterName", en1);
            intent.putExtra("points", points);
            startActivity(intent);
            // Finish StartGame Activity
            finish();
        } else {
            countDownTimer = null;
            quizTwo();
        }
    }

    public void answerSelected(View view) {

        MediaPlayer correct = MediaPlayer.create(QuizTwo.this,R.raw.correct);
        MediaPlayer wrong = MediaPlayer.create(QuizTwo.this,R.raw.wrong);

        view.setBackgroundColor(Color.parseColor("#17243e"));

        btn1.setEnabled(false);
        btn2.setEnabled(false);
        btn3.setEnabled(false);
        btn4.setEnabled(false);

        countDownTimer.cancel();
        String answer = ((Button) view).getText().toString().trim();
        String correctAnswer = techList.get(index);
        if(answer.equals(correctAnswer)){
            points++;
            tvPoints.setText(points + " / " + techList.size());
            tvResult.setText("Correct");
            correct.start();
        } else {
            tvResult.setText("Wrong");
            wrong.start();
        }
    }
}