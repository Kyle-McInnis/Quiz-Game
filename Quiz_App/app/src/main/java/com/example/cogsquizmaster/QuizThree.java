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

public class QuizThree extends AppCompatActivity {

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
        setContentView(R.layout.activity_quiz_three);

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

        techList.add("DBAS1007");
        techList.add("INET2005");
        techList.add("MOBI3002");
        techList.add("NETW1700");
        techList.add("OSYS1000");
        techList.add("OSYS1200");
        techList.add("PROG2007");
        techList.add("PROG2100");
        techList.add("PROG2200");
        techList.add("SAAD1001");


        // Put all the technology names with technology image resource ids in map.
        map.put(techList.get(0), R.drawable.dbas1007);
        map.put(techList.get(1), R.drawable.inet2005);
        map.put(techList.get(2), R.drawable.mobi3002);
        map.put(techList.get(3), R.drawable.netw1700);
        map.put(techList.get(4), R.drawable.osys1000);
        map.put(techList.get(5), R.drawable.osys1200);
        map.put(techList.get(6), R.drawable.prog2007);
        map.put(techList.get(7), R.drawable.prog2100);
        map.put(techList.get(8), R.drawable.prog2200);
        map.put(techList.get(9), R.drawable.saad1001);

        Collections.shuffle(techList);
        millisUntilFinished = 20000;
        points = 0;
        quizThree();
    }

    private void quizThree() {

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
                    Intent intent = new Intent(QuizThree.this, QuizComplete.class);
                    intent.putExtra("points", points);
                    startActivity(intent);
                    // Finish StartGame Activity
                    finish();
                } else {
                    countDownTimer = null;
                    quizThree();
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
            Intent intent = new Intent(QuizThree.this, QuizComplete.class);
            String en1 = getIntent().getStringExtra("keyenterName");
            intent.putExtra("keyenterName", en1);
            intent.putExtra("points", points);
            startActivity(intent);
            // Finish StartGame Activity
            finish();
        } else {
            countDownTimer = null;
            quizThree();
        }
    }

    public void answerSelected(View view) {

        MediaPlayer correct = MediaPlayer.create(QuizThree.this,R.raw.correct);
        MediaPlayer wrong = MediaPlayer.create(QuizThree.this,R.raw.wrong);

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