package com.example.racing;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.racing.MainActivity;

public class Credits extends AppCompatActivity {

    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        mediaPlayer = MediaPlayer.create(this, R.raw.credit);
        mediaPlayer.setLooping(true);
        mediaPlayer.start();

        TextView creditsText = findViewById(R.id.creditsText);
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.blink);
        creditsText.startAnimation(animation);

        TextView textView = findViewById(R.id.carl);
        TextView textView1 = findViewById(R.id.venus);

        // Create ObjectAnimator to move the TextView sideways
        ObjectAnimator animator = ObjectAnimator.ofFloat(textView, "translationX", 0f, 50f); // Move by 200 pixels horizontally
        animator.setDuration(3000); // Set animation duration (3 seconds)
        animator.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator.start(); // Start the animation

        // Create ObjectAnimator to move the TextView sideways
        ObjectAnimator animator1= ObjectAnimator.ofFloat(textView1, "translationX", 0f, 50f); // Move by 200 pixels horizontally
        animator1.setDuration(3000); // Set animation duration (3 seconds)
        animator1.setRepeatCount(ObjectAnimator.INFINITE); // Repeat animation infinitely
        animator1.setRepeatMode(ObjectAnimator.REVERSE); // Reverse animation direction when repeating
        animator1.start(); // Start the animation

        Button returnToMenuButton = findViewById(R.id.returnToMenuButton);
        returnToMenuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Navigate back to the main menu activity
                playSoundEffect(R.raw.click);
                Intent intent = new Intent(Credits.this, MainActivity.class);
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
