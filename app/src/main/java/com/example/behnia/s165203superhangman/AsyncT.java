package com.example.behnia.s165203superhangman;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Behnia on 08-11-2017.
 */


public class AsyncT extends AsyncTask<String,String,List<String>> {
    private final Mylogic mylogic = Mylogic.getInstance();
    ArrayList<String> words;

    @Override
    protected List<String> doInBackground(String... strings) {

        words = new ArrayList<>();

        try {
            String data = Mylogic.hentURL("http://www.dtu.dk/english");

            // Removes all the special characters.
            data = data.replaceAll("<.+?>", " ").toLowerCase().replaceAll("[^a-z]", " ");

            // Remove words consisting of 1 letter.
            data.replaceAll(" [a-z] "," ");

            // Remove words consisting of 2 letters.
            data.replaceAll(" [a-z][a-z] "," ");

            // Removes æ, ø and å.
            data.replaceAll("&#198;", "æ"); // erstat HTML-tegn
            data.replaceAll("&#230;", "æ"); // erstat HTML-tegn
            data.replaceAll("&#216;", "ø"); // erstat HTML-tegn
            data.replaceAll("&#248;", "ø"); // erstat HTML-tegn
            data.replaceAll("&oslash;", "ø"); // erstat HTML-tegn
            data.replaceAll("&#229;", "å"); // erstat HTML-tegn

            words.addAll(new HashSet<>(Arrays.asList(data.split(" "))));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return words;
    }
    @Override
    protected void onPostExecute(List<String> strings) {
        super.onPostExecute(strings);
        mylogic.setPossibleWord(strings);

        for (int i = 0; i < strings.size(); i++)
            System.out.println("Word [" + i + "]: " + strings.get(i));
    }
}

