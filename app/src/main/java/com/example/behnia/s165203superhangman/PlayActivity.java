package com.example.behnia.s165203superhangman;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.behnia.s165203superhangman.R;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    private final Mylogic mylogic = Mylogic.getInstance();
    TextView theWordShow, theScoreShow, theRoundShow;
    ImageView galgePic;
    private final int BTN_AMOUNT = 26;
    private final Button[] btnArray = new Button[BTN_AMOUNT];

    //setting_up A-Z buttons AND SHOW BUTTON
    int[] btnIdArray = {R.id.buttonA, R.id.buttonB, R.id.buttonC, R.id.buttonD, R.id.buttonE, R.id.buttonF, R.id.buttonG, R.id.buttonH, R.id.buttonI, R.id.buttonJ, R.id.buttonK, R.id.buttonL, R.id.buttonM, R.id.buttonN, R.id.buttonO, R.id.buttonP, R.id.buttonQ, R.id.buttonR, R.id.buttonS, R.id.buttonT, R.id.buttonU, R.id.buttonV, R.id.buttonW, R.id.buttonX, R.id.buttonY, R.id.buttonZ
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        mylogic.reset();

      //FIX IT(diffrent ways to update for diiffrent player modes)  mylogic.updateWord();


        galgePic = (ImageView) findViewById(R.id.imageView);
        theWordShow = (TextView) findViewById(R.id.theWord);
        theScoreShow = (TextView) findViewById(R.id.TextViewScore);

        theRoundShow = (TextView) findViewById(R.id.textViewRound);

        theWordShow.setText(mylogic.getxWord());
        theScoreShow.setText(String.valueOf(mylogic.getwordScore()));
        theRoundShow.setText(String.valueOf(mylogic.getRound()));
        for (int i = 0; i < btnArray.length; i++) {
            btnArray[i] = (Button) findViewById(btnIdArray[i]);
            btnArray[i].setOnClickListener(this);
        }

        final Button buttonSHOW = (Button) findViewById(R.id.buttonShow);
        buttonSHOW.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                theWordShow.setText(mylogic.getTheWord());
            }
        });
    }


    public void updateDisplay() {
        theWordShow.setText(mylogic.getxWord());

        theScoreShow.setText(Integer.toString(mylogic.getwordScore()));

        switch (mylogic.getThisIsnrWrongGuess()) {
            case 0:
                galgePic.setImageResource(R.drawable.galge);
                break;
            case 1:
                galgePic.setImageResource(R.drawable.forkert1);
                break;
            case 2:
                galgePic.setImageResource(R.drawable.forkert2);
                break;
            case 3:
                galgePic.setImageResource(R.drawable.forkert3);
                break;
            case 4:
                galgePic.setImageResource(R.drawable.forkert4);
                break;
            case 5:
                galgePic.setImageResource(R.drawable.forkert5);
                break;
            case 6:
                galgePic.setImageResource(R.drawable.forkert6);
                Intent i = new Intent(PlayActivity.this, LostActivity.class);
                startActivity(i);
                break;
        }
    }

    private void guess(String guessLetter) {

        mylogic.guessLetter(guessLetter);

        updateDisplay();

        if (mylogic.isTheGameLost()) {
            Intent initLostGameActivity = new Intent(this, LostActivity.class);
            startActivity(initLostGameActivity);
        }

        if (mylogic.isThisGameIsWon()) {
            Intent initWonGameActivity = new Intent(this, WinActivity.class);
            startActivity(initWonGameActivity);
        }
    }

    @Override
    public void onClick(View v) {
        if (v instanceof Button) {
            String guessLetter = ((Button) v).getText().toString();
            guess(guessLetter);
            if (mylogic.isItIsCorrectGuess()) {
                correctGuess(v);
            } else {
                wrongGuess(v);
            }
            v.setEnabled(false);
        }

    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Going back to menu in the middle of the game?")
                .setMessage("The game will be lost! Are you sure?")
                .setNegativeButton("No, lets win this game", null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(PlayActivity.this, MainMenu.class);
                        startActivity(i);
                    }
                }).create().show();
    }


    public void correctGuess(View v) {
        ((Button) v).setTextColor(Color.GREEN);
    }

    public void wrongGuess(View v) {
        ((Button) v).setTextColor(Color.RED);
    }
}

