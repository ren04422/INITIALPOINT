package com.example.racing;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Winner extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_winner);

            mediaPlayer = MediaPlayer.create(this, R.raw.credit);
            mediaPlayer.setLooping(true);
            mediaPlayer.start();


              TextView play= findViewById(R.id.winner);
              Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink);
              play.startAnimation(animation);

              play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Navigate back to the main menu activity
                    playSoundEffect(R.raw.click);
                    Intent intent = new Intent(Winner.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Optional: finish current activity to prevent going back to it using back button
                    mediaPlayer.stop();
                }
            });
        }
        private void playSoundEffect(int soundId) {
            MediaPlayer mediaPlayer = MediaPlayer.create(this, soundId);
            mediaPlayer.start();
        }
    }




