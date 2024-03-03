package com.example.racing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
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

    private ImageView player1ImageView, player2ImageView, player3ImageView;
    private int player1Position = 0, player2Position = 0, player3Position = 0;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final long COUNT_DOWN_TICKS = 100l;
    private static final long COUNT_DOWN_FINISH = 5000l;
    private long countdownElapsed = 0l;
    private Handler mCountDownHandler = new Handler();
    private TextView timer;
    private TextView next_button;
    private ImageView playerone;

    MediaPlayer countdownPlayer,bgPlayer;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_second);


        ImageView imageView = findViewById(R.id.leaf);
        ImageView imageView4 = findViewById(R.id.leaf2);
        ImageView imageView5 = findViewById(R.id.leaf3);
        ImageView imageView1 = findViewById(R.id.bird1);
        ImageView imageView2 = findViewById(R.id.bird2);
        ImageView imageView3 = findViewById(R.id.animal);
        // Get the screen width
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        // Create ObjectAnimator to move the ImageView across the screen LEAFFFFFFFFFFFFFF
        ObjectAnimator animator = ObjectAnimator.ofFloat(imageView, "translationX", -1500f, 1500f); // Move from 0 to 200 pixels horizontally
        animator.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator.setDuration(10000); // Set animation duration (3 seconds)
        animator.start(); // Start the animation

        // Create ObjectAnimator to move the ImageView across the screen LEAFFFFFFFFFFFFFF
        ObjectAnimator animator4 = ObjectAnimator.ofFloat(imageView4, "translationX", -1500f, 1500f); // Move from 0 to 200 pixels horizontally
        animator4.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator4.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator4.setDuration(10000); // Set animation duration (3 seconds)
        animator4.start(); // Start the animation

        // Create ObjectAnimator to move the ImageView across the screen LEAFFFFFFFFFFFFFF
        ObjectAnimator animator5 = ObjectAnimator.ofFloat(imageView5, "translationX", 1500f, -1500f); // Move from 0 to 200 pixels horizontally
        animator5.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator5.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator5.setDuration(10000); // Set animation duration (3 seconds)
        animator5.start(); // Start the animation

        // Create ObjectAnimator to move the ImageView across the screen BIRD1111111111111
        ObjectAnimator animator1 = ObjectAnimator.ofFloat(imageView1, "translationX", -10f, 10f); // Move from 0 to 200 pixels horizontally
        animator1.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator1.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator1.setDuration(3000); // Set animation duration (3 seconds)
        animator1.start(); // Start the animation

        // Create ObjectAnimator to move the ImageView across the screen BIRD22222222222
        ObjectAnimator animator2 = ObjectAnimator.ofFloat(imageView2, "translationX", -10f, 10f); // Move from 0 to 200 pixels horizontally
        animator2.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator2.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator2.setDuration(3000); // Set animation duration (3 seconds)
        animator2.start(); // Start the animation

        // Create ObjectAnimator to move the ImageView across the screen BIRD3333333333333333
        ObjectAnimator animator3 = ObjectAnimator.ofFloat(imageView3, "translationX", -10f, 10f); // Move from 0 to 200 pixels horizontally
        animator3.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator3.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator3.setDuration(3000); // Set animation duration (3 seconds)
        animator3.start(); // Start the animation



        // Initialize player ImageViews
        player1ImageView = findViewById(R.id.player1ImageView);
        player2ImageView = findViewById(R.id.player2ImageView);
        player3ImageView = findViewById(R.id.player3ImageView);

        timer = findViewById(R.id.timer);
        next_button = findViewById(R.id.next_button);
        playerone = findViewById(R.id.player1ImageView);
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
        // Method called when Player 1 taps the buttonZ
        public void onPlayer1Tap(View view) {
            if(timer.getVisibility() == View.GONE){
                    player1Position += 10; // Increase player 1's position
                    updatePlayerPosition(player1ImageView, player1Position);
                    checkWinner();

        }}

        // Method called when Player 2 taps the button
        public void onPlayer2Tap(View view) {
            if(timer.getVisibility() == View.GONE){player2Position += 10; // Increase player 2's position
            updatePlayerPosition(player2ImageView, player2Position);
            checkWinner();
        }}

        public void onPlayer3Tap(View view) {
            if(timer.getVisibility() == View.GONE){player3Position += 10; // Increase player 3's position
            updatePlayerPosition(player3ImageView, player3Position);
            checkWinner();
        }}

        // Update player's position on the screen
        private void updatePlayerPosition(ImageView playerImageView, int position) {
            playerImageView.setY(position); // Set X-coordinate of the player ImageView
        }

        // Check if any player has reached the end of the track
        private void checkWinner() {
            int trackEnd = 1910; // Adjust this value based on your track size
            if (player1Position >= trackEnd) {
                // Player 1 wins
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("PLAYER 1 WINS")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent i = new Intent(second.this, Winner.class);
                                    startActivity(i);
                                    finish();
                                    bgPlayer.stop();
                                }
                            });
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                    dialog.show();

            } else if (player2Position >= trackEnd) {
                // Player 2 wins
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("PLAYER 2 WINS")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(second.this, Winner.class);
                                startActivity(i);
                                finish();
                                bgPlayer.stop();
                            }
                        });
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
            } else if (player3Position >= trackEnd) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("PLAYER 3 WINS")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                Intent i = new Intent(second.this, Winner.class);
                                startActivity(i);
                                finish();
                                bgPlayer.stop();
                            }
                        });
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        }

    }


