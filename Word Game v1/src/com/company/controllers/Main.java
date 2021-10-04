package com.company.controllers;

import com.company.models.WordList;
import com.company.views.CmdLineView;

public class Main {

    public static String[] letters;
    public static int numLetters = 0;
    public static int numIncGuesses;
    public static String theGuess;
    public static String[] hints;
    public static  CmdLineView view;

    public static void main(String[] args) {

        numIncGuesses = 0;
//        String[] hints = new String[letters.length];

        GetWord getWord = new GetWord();
        String theWord = getWord.getTheWord();

        WordList word = new WordList(theWord);

        //WordList word = new WordList(getWord.getTheWord());

        letters = calculateLetters(word.getTheWord());

        view = new CmdLineView(letters);
        String[] hints = new String[letters.length];
        view.startGame();
        view.cheat(word.getTheWord());

        while(numIncGuesses < 7){
            hints = guess(hints);
            view.displayHints(hints);
            //display
            //System.out.println(hints);
            int eureka = 1;
            for(String wordFound: hints){
                if (wordFound.equals("_")){
                    eureka = 0;
                    break;
                }

            }
            if (eureka == 1) {
                System.out.println("Word Found");
                break;
            }
            numIncGuesses++;
        }
    }

    private static String[] calculateLetters(String theWord){
        String[] letters = theWord.split("");
        numLetters = letters.length;
        return letters;
    }

    private static String[] guess(String[] hints){

        theGuess = view.makeAGuess();
        System.out.println("Guess: " + theGuess);
        String msg = "";
        int found = 0;


        for(int i = 0; i < letters.length; i++) {
            if (letters[i].equals(theGuess)) {
                    hints[i] = theGuess;
                    found = 1;
            }
            else {
                if (hints[i] == null   )
                    hints[i] = "_";

            }

        }
        if (found == 0 )
            System.out.println("Incorrect Guess");

        return hints;
    }
}