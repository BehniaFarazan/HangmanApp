package com.example.behnia.s165203superhangman;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class ChooseWordActivity extends AppCompatActivity {
    private final Mylogic mylogic = Mylogic.getInstance();
    private List<String> WordList;
    private ArrayAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_word);
        WordList = mylogic.getPossibleWord();
        ListView WordListView = (ListView) findViewById(R.id.wordlist);
        WordListView.removeAllViewsInLayout();
        listViewAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, mylogic.getPossibleWord());
        WordListView.setAdapter(listViewAdapter);
    //  WordListView.removeAllViewsInLayout();
        WordListView.setOnItemClickListener((AdapterView.OnItemClickListener)(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Object var =adapterView.getItemAtPosition(i);
                String choosenWord = (String)var;
                mylogic.setTheWord(choosenWord);
                Intent intent = new Intent((Context)ChooseWordActivity.this, PlayMultiModeActivity.class);
                ChooseWordActivity.this.startActivity(intent);
            }
        }));

}
}