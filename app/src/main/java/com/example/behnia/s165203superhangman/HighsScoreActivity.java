package com.example.behnia.s165203superhangman;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.behnia.s165203superhangman.dto.DTO;
import com.example.behnia.s165203superhangman.dao.IDAO;
import com.example.behnia.s165203superhangman.utils.Util;

import java.util.List;

public class HighsScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvCustomTitle;
    private Button Reset, Menu;

    private ListView highScoreListView;
    private ArrayAdapter listViewAdapter;

    private List<DTO> highScoreList;

    private IDAO DAO = com.example.behnia.s165203superhangman.dao.DAO.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_highscore);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.activity_bar);

        try {
            highScoreList = DAO.getAll();
        } catch (IDAO.DALException e) {
            e.printStackTrace();
        }

        tvCustomTitle = (TextView) findViewById(R.id.action_bar_title);
        Reset = (Button) findViewById(R.id.resethighscore);
        Menu = (Button) findViewById(R.id.gotomenu);
        highScoreListView = (ListView) findViewById(R.id.highscore_list );

        listViewAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, highScoreList);
        highScoreListView.setAdapter(listViewAdapter);

        tvCustomTitle.setText(R.string.highscoretitle_label);

        Reset.setOnClickListener(this);
        Menu.setOnClickListener(this);
        updateDisplay();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.resethighscore:

                resetHistory();
                updateDisplay();


                Intent i = new Intent(HighsScoreActivity.this, MainMenu.class);
                startActivity(i);

                break;

            case R.id.gotomenu:
                Intent intent = new Intent(HighsScoreActivity.this, MainMenu.class);
                startActivity(intent);


        }
    }

    private void updateDisplay() {
        try {
            highScoreList = DAO.getAll();
            highScoreListView.removeAllViewsInLayout();
        } catch (IDAO.DALException e) {
            e.printStackTrace();
        }
    }

    private void resetHistory() {
        try {
            DAO.removeAll();
            DAO.save(getApplicationContext());
        } catch (IDAO.DALException e) {
            Toast.makeText(getApplicationContext(), R.string.highscoreReset, Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }


}
