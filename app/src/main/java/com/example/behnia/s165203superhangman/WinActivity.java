package com.example.behnia.s165203superhangman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;


public class WinActivity extends AppCompatActivity {
    public static TextView theScoreScoredShow;
    public static TextView allRoundScoreShow;
    public static TextView theWrongGuessShow;
    public static TextView theRoundShow;
    private final Mylogic mylogic = Mylogic.getInstance();
    KonfettiView kf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_won);

        //Den nedenstående konfeti er implementiret ved hjælp af library/Tutorial fundet på nettet
        kf = (KonfettiView) findViewById(R.id.viewKonfetti);
        kf.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA)
                .setDirection(0.0, 359.0)
                .setSpeed(1f, 5f)
                .setFadeOutEnabled(true)
                .setTimeToLive(2000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(12, 5f))
                .setPosition(-50f, kf.getWidth() + 50f, -50f, -50f)
                .stream(300, 5000L);


        theWrongGuessShow = (TextView) findViewById(R.id.textView7);
        theWrongGuessShow.setText(String.valueOf(mylogic.getThisIsnrWrongGuess()));
        theScoreScoredShow = (TextView) findViewById(R.id.textViewScoreScored);
        theScoreScoredShow.setText(String.valueOf(mylogic.getwordScore()));


        theRoundShow = (TextView) findViewById(R.id.textViewRound);
        theRoundShow.setText(String.valueOf(mylogic.getRound()));
        mylogic.calcHighscore();

        allRoundScoreShow = (TextView) findViewById(R.id.highScoreView);
        allRoundScoreShow.setText(String.valueOf(mylogic.getHighScore()));

        //PlayNextRound Button function
        Button buttonPlay = (Button) findViewById(R.id.buttonPlayNextRound);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(WinActivity.this, PlayActivity.class);
                startActivity(i);
            }
        });
        //GOBACKTOMENU BUTTON FUNCTION
        Button buttonGoBack = (Button) findViewById(R.id.buttonGoToMenu);
        buttonGoBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(WinActivity.this)
                        .setTitle("Going back to menu in the middle of the game?")
                        .setMessage("The game will be lost! Are you sure?")
                        .setNegativeButton("No, lets win the next round", null)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface arg0, int arg1) {
                                Intent i = new Intent(WinActivity.this, MainMenu.class);
                                startActivity(i);
                            }
                        }).create().show();

            }
        });

    }

    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Back to the won game?")
                .setMessage("Not possible!")
                .setNegativeButton("OK", null).create().show();
    }
}
