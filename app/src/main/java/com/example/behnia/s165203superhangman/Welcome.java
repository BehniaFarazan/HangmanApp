package com.example.behnia.s165203superhangman;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        ProgressDialog  myProgressDialog = new ProgressDialog(Welcome.this);

        myProgressDialog.setMessage("Retrieving data (words) from www.dtu.dk" +
                "               So guess wisely");
        myProgressDialog.setCancelable(false);
        myProgressDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                new AsyncT().execute();
                dialog.dismiss();
            }
        });
        myProgressDialog.show();

        //StartApp BUTTON FUNCTION
        Button buttonStartApp = (Button) findViewById(R.id.buttonStartApp);
        buttonStartApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Welcome.this,MainMenu.class);
                startActivity(i);

            }
        });
    }
}
