package com.example.racing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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