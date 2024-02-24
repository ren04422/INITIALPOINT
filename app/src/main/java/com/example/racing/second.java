package com.example.racing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class second extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final long COUNT_DOWN_TICKS = 100l;
    private static final long COUNT_DOWN_FINISH = 5000l;
    private long countdownElapsed = 0l;
    private Handler mCountDownHandler = new Handler();
    private TextView timer,next_button;
    private ImageView playerone;

    MediaPlayer countdownPlayer,bgPlayer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);

        timer = findViewById(R.id.timer);
        next_button = findViewById(R.id.next_button);
        playerone = findViewById(R.id.player1);
        countdownPlayer = MediaPlayer.create(this, R.raw.countdown);
        bgPlayer = MediaPlayer.create(this, R.raw.racingbg);
        bgPlayer.setLooping(true);
        bgPlayer.start();


        Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink);
        next_button.startAnimation(animation);

        next_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCountDown();
                countdownPlayer.start();
                next_button.clearAnimation();
                next_button.setVisibility(View.GONE);
                bgPlayer.pause();
            }
        });

    }

    public void onBackPressed() {

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Returning to Main Menu")
                .setMessage("Are you sure you want to end the race?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent i = new Intent(second.this, MainActivity.class);
                        startActivity(i);
                        finish();
                        bgPlayer.stop();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void startCountDown() {
        try {
            countdownElapsed = 0l;
            next_button.setEnabled(false);
            displayCountDown();
            mCountDownHandler.postDelayed(mCountDownRunnable, COUNT_DOWN_TICKS);
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    private Runnable mCountDownRunnable = new Runnable() {
        @Override
        public void run() {
            countdownElapsed = countdownElapsed + COUNT_DOWN_TICKS;
            if (countdownElapsed >= COUNT_DOWN_FINISH) {
                releaseCountDownHandler();
                next_button.setEnabled(true);
                timer.setVisibility(View.GONE);// Hide the TextView when countdown is done
                bgPlayer.start();
            } else {
                mCountDownHandler.postDelayed(mCountDownRunnable, COUNT_DOWN_TICKS);
            }
            long secFull = countdownElapsed % 1000;
            if (secFull == 0) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        displayCountDown();
                    }
                });
            }
        }
    };

    private void releaseCountDownHandler() {
        try {
            if (mCountDownRunnable != null) {
                mCountDownHandler.removeCallbacks(mCountDownRunnable);
            }
        } catch (Exception ex) {
            Log.e(TAG, ex.getMessage());
        }
    }

    private void displayCountDown() {
        long t = (COUNT_DOWN_FINISH - countdownElapsed) / 1000;
        String myTime = String.valueOf(t);
        timer.setText(myTime);
    }

}