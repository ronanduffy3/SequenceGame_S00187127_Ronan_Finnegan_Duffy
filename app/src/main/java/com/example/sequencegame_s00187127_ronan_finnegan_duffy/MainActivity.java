package com.example.sequencegame_s00187127_ronan_finnegan_duffy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // Create ints to pick random colors
    private final int BLUE = 1;
    private final int RED = 2;
    private final int YELLOW = 3;
    private final int GREEN = 4;

    // Create button instances to be filled with active buttons on screen
    Button bttnRed, bttnBlue, bttnGreen, bttnYellow;
    Button bttnFlash;


    // Create a sequence count variabke
    int sequenceCount = 4;
    int n = 0;
    private Object mutex = new Object();
    int[] gameSequence = new int[120];
    int arrayIndex;

    CountDownTimer timer = new CountDownTimer(6000, 1500) {
        public void onTick(long millisUntilFinished) {
            oneButton();
        }

        @Override
        public void onFinish() {
            for (int i = 0; i< arrayIndex; i++)
                Log.d("game sequence", String.valueOf(gameSequence[i]));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Fill variables with buttons from the view
        bttnBlue = findViewById(R.id.buttonBlueRight);
        bttnRed = findViewById(R.id.buttonRedBottom);
        bttnYellow = findViewById(R.id.buttonYellowLeft);
        bttnGreen = findViewById(R.id.buttonGreenTop);
    }

    public void doStart(View view){
        // start the timer when the button is started
        timer.start();
    }

    public void doHighScores(View view){
        Intent intent = new Intent(this, DisplayScores.class);
        startActivity(intent);
    }

    private void oneButton(){
        n = getRandom(sequenceCount);

        switch(n){
            case 1:
                flashButton(bttnBlue);
                gameSequence[arrayIndex++] = BLUE;
                break;
            case 2:
                flashButton(bttnRed);
                gameSequence[arrayIndex++] = RED;
                break;
            case 3:
                flashButton(bttnYellow);
                gameSequence[arrayIndex++] = YELLOW;
                break;
            case 4:
                flashButton(bttnGreen);
                gameSequence[arrayIndex++] = GREEN;
                break;
            default:
                break;

        }
    }

    private void flashButton(Button buttonToFlash) {
        bttnFlash = buttonToFlash;
        Handler handler = new Handler();
        Runnable r = new Runnable() {
            public void run() {
                bttnFlash.setPressed(true);
                bttnFlash.invalidate();
                bttnFlash.performClick();
                Handler handler1 = new Handler();
                Runnable r1 = new Runnable() {
                    public void run() {
                        bttnFlash.setPressed(false);
                        bttnFlash.invalidate();
                    }
                };
                handler1.postDelayed(r1, 600);
            }
        };

    }

    private int getRandom(int maxValue) {
        return ((int) ((Math.random() * maxValue) + 1));
    }
}