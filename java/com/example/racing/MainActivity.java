package com.example.racing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);

        TextView play = findViewById(R.id.playButton);

        mediaPlayer = MediaPlayer.create(this, R.raw.bgmusic);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();


        ImageView imageView6 = findViewById(R.id.leafp);
        ImageView imageView7 = findViewById(R.id.leafp1);
        ImageView imageView8 = findViewById(R.id.leafp2);
        // Get the screen width
        int screenWidth = getResources().getDisplayMetrics().widthPixels;

        // Create ObjectAnimator to move the ImageView across the screen LEAFFFFFFFFFFFFFF
        ObjectAnimator animator6 = ObjectAnimator.ofFloat(imageView6, "translationX", -1500f, 1500f); // Move from 0 to 200 pixels horizontally
        animator6.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator6.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator6.setDuration(10000); // Set animation duration (3 seconds)
        animator6.start(); // Start the animation

        ObjectAnimator animator7 = ObjectAnimator.ofFloat(imageView7, "translationX", -1500f, 1500f); // Move from 0 to 200 pixels horizontally
        animator7.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator7.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator7.setDuration(10000); // Set animation duration (3 seconds)
        animator7.start(); // Start the animation

        ObjectAnimator animator8 = ObjectAnimator.ofFloat(imageView8, "translationX", 1500f, -1500f); // Move from 0 to 200 pixels horizontally
        animator8.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator8.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator8.setDuration(10000); // Set animation duration (3 seconds)
        animator8.start(); // Start the animation


        TextView playButton= findViewById(R.id.playButton);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink);
        playButton.startAnimation(animation);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play the sound effect
                playSoundEffect(R.raw.click);
                Intent i = new Intent(MainActivity.this, second.class);
                startActivity(i);
                finish();
                mediaPlayer.stop();

            }

        });
        Button button2 = findViewById(R.id.creditsButton);


        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Play the sound effect
                playSoundEffect(R.raw.click);
                Intent i = new Intent(MainActivity.this, Credits.class);
                startActivity(i);
                finish();
                mediaPlayer.stop();

            }

        });

    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Closing the App")
                .setMessage("Are you sure you want to close this activity?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                        mediaPlayer.stop();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }

    private void playSoundEffect(int soundId) {
        MediaPlayer mediaPlayer = MediaPlayer.create(this, soundId);
        mediaPlayer.start();
    }

}