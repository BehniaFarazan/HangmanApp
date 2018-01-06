package com.example.behnia.s165203superhangman;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainMenu extends AppCompatActivity {
    private final Mylogic mylogic = Mylogic.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        //PLAY BUTTON FUNCTION
        Button buttonPlay = (Button) findViewById(R.id.playButton);
        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mylogic.roundReset();
                Intent i = new Intent(MainMenu.this,PlayerMode.class);
                startActivity(i);

            }
        });

//HIGH SCORE BUTTON FUNCTION
        Button buttonHighscore = (Button) findViewById(R.id.highscoreButton);
        buttonHighscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this,HighsScoreActivity.class);
                startActivity(i);
            }
        });


//Help BUTTON FUNCTION
        Button buttonHelp = (Button) findViewById(R.id.helpButton);
        buttonHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainMenu.this,HelpActivity.class);
                startActivity(i);
            }
        });
    }
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setTitle("Going back to introduction?")
                .setMessage("Are you sure?")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface arg0, int arg1) {
                        Intent i = new Intent(MainMenu.this,Welcome.class);
                        startActivity(i);
                    }
                }).create().show();
    }
}

