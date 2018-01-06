package com.example.behnia.s165203superhangman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Mylogic {

    private static Mylogic instance;
    private ArrayList<String> UsedLetters = new ArrayList<String>();
    private List<String> possibleWord = new ArrayList<String>();
    private String theWord;
    private String xWord;
    private int thisIsnrWrongGuess, thisIsnrCorrectGuess, thisIsnrTotGuess;
    private int letterScore = 1000;
    private boolean thisGameIsWon;
    private boolean theGameIsLost;
    private final Random random = new Random();
    private int wordScore;
    private int round;
    char thisLetter;
    private int highScore;
    private boolean IsCorrectGuess = false;

    public void setPossibleWord(List<String> list) {
        this.possibleWord = list;
    }
    public List<String> getPossibleWord() {
        return possibleWord;

    }
    public ArrayList<String> getUsedLetters() {

        return UsedLetters;
    }

    public int getHighScore() {
        return highScore;
    }

    public String getxWord() {
        return xWord;
    }

    public String getTheWord() {

        return theWord.toUpperCase();
    }

    public int getRound() {
        return round;
    }

    public int getThisIsnrWrongGuess() {

        return thisIsnrWrongGuess;
    }

    public int getThisIsnrCorrectGuess() {

        return thisIsnrCorrectGuess;
    }

    public int getThisIsnrTotGuess() {

        return thisIsnrTotGuess;
    }

    public boolean isItIsCorrectGuess() {
        return IsCorrectGuess;
    }

    public boolean isThisGameIsWon() {

        return thisGameIsWon;
    }

    public boolean isTheGameLost() {

        return theGameIsLost;
    }

    public boolean isTheGameOver() {

        return theGameIsLost || thisGameIsWon;
    }


    public int getwordScore() {
        return wordScore;
    }


    private Mylogic() {
        UsedLetters = new ArrayList<>();
    }


    static {
        try {
            instance = new Mylogic();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static synchronized Mylogic getInstance() {
        return instance;
    }


    //WordlistAddToPossibleWordArray
    public void add() {
        possibleWord.add("BUSTICKET");
        possibleWord.add("TRAIN");
        possibleWord.add("LONDON");
        possibleWord.add("GOALLIST");
        possibleWord.add("DTU");
        possibleWord.add("FRANCE");
        possibleWord.add("DANMARK");
        possibleWord.add("CPU");
        possibleWord.add("ROAD");
        possibleWord.add("HEART");
        possibleWord.add("BARCELONA");
        possibleWord.add("LIVERPOOL");
        possibleWord.add("FRANCE");
        possibleWord.add("ANDROIDSTUDIO");
        possibleWord.add("MATCH");
        possibleWord.add("COPENHAGEN");
        possibleWord.add("KEYBOARD");

    }

    private String getRandomPossibleWord() {
        return possibleWord.get(random.nextInt(possibleWord.size()));
    }
 public void setTheWord(String ChoosenWord) {
      this.theWord=ChoosenWord;
    }

    public void roundReset() {
        round = 0;
        highScore = 0;
    }

    public void reset() {
        thisIsnrWrongGuess = 0;
        wordScore = 0;
        thisIsnrCorrectGuess = 0;
        thisIsnrTotGuess = 0;
        thisGameIsWon = false;
        theGameIsLost = false;
        IsCorrectGuess = false;
    }
    public void updateChoosenWord() {
        wordScore = theWord.length() * 1000;
        showXWord();
        round++;
    }
    public void updateWord() {
        add();
        theWord = getRandomPossibleWord();
        wordScore = theWord.length() * 1000;
        showXWord();
        round++;
    }

    public void calcHighscore() {
        highScore = highScore + (((theWord.length() * letterScore) - (letterScore * thisIsnrWrongGuess)));
        if (highScore <= 0) {
            highScore = 0;
        }
    }

    private void showXWord() {
        xWord = "";
        for (int n = 0; n < getTheWord().length(); n++) {
            String letter = getTheWord().substring(n, n + 1);
            if (getUsedLetters().contains(letter)) {
                xWord = xWord + letter;
            } else {
                xWord = xWord + "#";
            }
        }
    }

    public void guessLetter(String letter) {
        thisIsnrTotGuess++;
        if (getTheWord().contains(letter)) {
            IsCorrectGuess = true;
            updateXWord(letter);
            if (thisIsnrCorrectGuess == getTheWord().length()) {
                thisGameIsWon = true;
            }

        } else {
            IsCorrectGuess = false;
            wordScore -= letterScore;
            if (wordScore < 0) {
                wordScore = 0;
            }
            thisIsnrWrongGuess = thisIsnrWrongGuess + 1;
            if (thisIsnrWrongGuess > 6) {
                theGameIsLost = true;
            }
        }
    }

    public void updateXWord(String letter) {
        final StringBuilder replacement = new StringBuilder(xWord);
        for (int n = 0; n < theWord.length(); n++) {
            thisLetter = getTheWord().charAt(n);
            if (thisLetter == letter.charAt(0)) {
                replacement.setCharAt(n, thisLetter);
                xWord = String.valueOf(replacement);
                thisIsnrCorrectGuess++;
            }
        }
    }


    public static String hentURL(String url) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
        StringBuilder sb = new StringBuilder();
        String line = br.readLine();
        while (line != null) {
            sb.append(line + "\n");
            line = br.readLine();
        }
        return sb.toString();
    }
}


