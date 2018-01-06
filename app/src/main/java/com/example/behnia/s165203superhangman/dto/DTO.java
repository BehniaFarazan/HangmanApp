package com.example.behnia.s165203superhangman.dto;

import java.io.Serializable;
import java.util.Locale;

/**
 * Created by Behnia on 12-11-2017.
 */

public class DTO implements Serializable {
    private String lostWord;
    private int score;
    private int winCount;

    public DTO( int score, int winCount,String lostWord) {
        this.lostWord = lostWord;
        this.score = score;
        this.winCount = winCount;
    }



    public String getLastWord() {
        return lostWord;
    }

    public int getScore() {
        return score;
    }

    public int getWinCount() {
        return winCount;
    }


    @Override
    public String toString() {
        return "Score: " + score +
                ", Round lost in: " + winCount +
                "\nLost unfortunately on the word: " + lostWord;
    }


}
