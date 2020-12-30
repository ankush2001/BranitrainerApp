package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    Button goButton;
    TextView writeAnswer;
    TextView textView;
    TextView timerTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playGame;
    ConstraintLayout gameLayout;
    int locationOfCorrectAnswer;
    int score = 0;
    int numberOfQuestion = 0;
    TextView scoreTextView ;
    ArrayList<Integer> answer = new ArrayList<Integer>();

    public void goButton(View view) {
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.scoreTextView));

    }

    public void choseAnswer(View view) {
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())) {
            Log.i("Correct!", "You got it");
            writeAnswer.setText("Correct!");
            score++;

        } else {
            Log.i("Incorrect!", ":/");
            writeAnswer.setText("Incorrect!");
        }
        writeAnswer.setVisibility(View.VISIBLE);
        numberOfQuestion++;
        scoreTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestion));
        newQuestion();

    }
    public void playAgain(View play){
         score = 0 ;
         numberOfQuestion = 0;
        timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + " / " + Integer.toString(numberOfQuestion));
        newQuestion();
        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000 )+"s");
            }

            @Override
            public void onFinish() {
                writeAnswer.setText("Done");
                writeAnswer.setVisibility(View.VISIBLE);
                playGame.setVisibility(View.VISIBLE);
            }
        }.start();
        playGame.setVisibility(View.INVISIBLE);
        writeAnswer.setVisibility(View.INVISIBLE);
    }

    public void newQuestion(){
        Random random = new Random();

        int a = random.nextInt(21);
        int b = random.nextInt(21);
        textView.setText(Integer.toString(a) + " + " + Integer.toString(b));
        locationOfCorrectAnswer = random.nextInt(4);
        answer.clear();
        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answer.add(a + b);
            } else {
                int wrongAnswer = random.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = random.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        writeAnswer = findViewById(R.id.resetTextView);
        button0 = findViewById(R.id.button3);
         button1 = findViewById(R.id.button4);
         button2 = findViewById(R.id.button5);
         button3 = findViewById(R.id.button6);
         textView = findViewById(R.id.textView3);
         playGame = findViewById(R.id.playGame);
         gameLayout = findViewById(R.id.gameLayout);
         timerTextView = findViewById(R.id.timerTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        playGame.setVisibility(View.INVISIBLE);
        goButton.setVisibility(View.VISIBLE);
        writeAnswer.setVisibility(View.INVISIBLE);
        playAgain(findViewById(R.id.scoreTextView));
        gameLayout.setVisibility(View.INVISIBLE);
    }
}