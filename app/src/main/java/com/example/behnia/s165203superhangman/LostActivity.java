package com.example.behnia.s165203superhangman;

import android.app.AlertDialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.behnia.s165203superhangman.utils.Util;

public class LostActivity extends AppCompatActivity {
    public static TextView theLostWordShow, theHighScoreShow;
    String theLostWord;
    private final Mylogic mylogic = Mylogic.getInstance();
    private final Util util = Util.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost);
        final MediaPlayer loserSound = MediaPlayer.create(this, R.raw.sound);
        loserSound.start();
        theLostWord = mylogic.getTheWord();
        theLostWordShow = (TextView) findViewById(R.id.textViewTheLostWord);
        theLostWordShow.setText(theLostWord);
        theHighScoreShow = (TextView) findViewById(R.id.textViewHighscored);
        theHighScoreShow.setText(String.valueOf(mylogic.getHighScore()));

//PlayAgain Button function
        Button buttonPlay = (Button) findViewById(R.id.buttonPlayAgain);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mylogic.roundReset();
                Intent i = new Intent(LostActivity.this, PlaySingleModeActivity.class);
                startActivity(i);
            }
        });
        //GOBACKTOMENU BUTTON FUNCTION
        Button buttonGoBack = (Button) findViewById(R.id.buttonGoToMenu);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LostActivity.this, MainMenu.class);
                startActivity(i);


            }
        });

        //SaveHighscore BUTTON FUNCTION
        Button buttonSaveHighScore = (Button) findViewById(R.id.buttonSaveTheHighscore);
        buttonSaveHighScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(LostActivity.this, HighsScoreActivity.class);
                startActivity(i);
                util.createMatchAndReset(getApplicationContext());
            }
        });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Back to the lost game? U cheater?")
                .setMessage("Not possible!")
                .setPositiveButton("I am not cheater", null)
                .setNegativeButton("OK", null).create().show();

    }
}
