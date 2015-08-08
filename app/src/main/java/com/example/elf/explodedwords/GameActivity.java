package com.example.elf.explodedwords;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;


public class GameActivity extends Activity implements FragmentKeyboard.OnLetterInput {
    public static final String POINTS = "points";
    public static final String RESULT = "result";
    public static final String WORD = "word";

    private final int MAX_MISS = 6;
    private final int MAX_WORD_GROUPS = 7;

    private char[] misses;
    private String quizeWord;
    private StringBuilder outString = new StringBuilder();
    private TextView wordTextView;
    private ImageView mainImg;
    private int wrongScore, rightScore, points;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        wordTextView = (TextView) findViewById(R.id.word);
        mainImg = (ImageView) findViewById(R.id.img_main_image);

        initGame();
    }

    /**
     * Check dose the word contain introduced letter
     * return true if letter was in word and false if letter wasn't
     *
     * @param letter - letter introdused by user
     */
    private boolean checkLetter(String letter) {
        char insChar = letter.charAt(0);
        boolean isLetterIn = false;

        //already exist in outstring
        if (outString.toString().indexOf(insChar) != -1) return true;

        int letterIndx = quizeWord.indexOf(insChar);
        while (letterIndx != -1) {
            points++;
            rightScore++;
            isLetterIn = true;
            outString.setCharAt(letterIndx * 2, insChar);
            letterIndx = quizeWord.indexOf(insChar, letterIndx + 1);
        }

        if (isLetterIn) {
            wordTextView.setText(outString);
            //Test to win of a game
            if (quizeWord.length() == rightScore) {

                //Call the win layout with some extras
                Intent winIntent = new Intent(this, EndOfGameActivity.class);
                winIntent.putExtra(POINTS, points);
                winIntent.putExtra(RESULT, true); //win
                winIntent.putExtra(WORD, quizeWord); //win
                startActivity(winIntent);
                finish();
            }
        } else {
            //Already missed with this letter
            if (Arrays.toString(misses).contains(String.valueOf(insChar))) {
                Toast.makeText(this, R.string.letter_used, Toast.LENGTH_SHORT).show();
                return false;
            }
            misses[wrongScore] = insChar;
            wrongScore++;
            points--;
            Resources res = getResources();
            switch (wrongScore) {
                case 1:
                    mainImg.setImageDrawable(res.getDrawable(R.drawable.bomba_0));
                    break;
                case 2:
                    mainImg.setImageDrawable(res.getDrawable(R.drawable.bomba_1));
                    break;
                case 3:
                    mainImg.setImageDrawable(res.getDrawable(R.drawable.bomba_2));
                    break;
                case 4:
                    mainImg.setImageDrawable(res.getDrawable(R.drawable.bomba_3));
                    break;
                case 5:
                    mainImg.setImageDrawable(res.getDrawable(R.drawable.bomba_4));
                    break;
                case 6:
                    mainImg.setImageDrawable(res.getDrawable(R.drawable.bomba_5));
                    break;
                case 7:
                    //Loose
                    Intent looseIntent = new Intent(this, EndOfGameActivity.class);
                    looseIntent.putExtra(RESULT, false); //loose
                    startActivity(looseIntent);
                    finish();
                default:
                    break;
            }
        }
        return isLetterIn;
    }

    /**
     * Initialize game
     */
    public void initGame() {
        wrongScore = points = 0;
        misses = new char[MAX_MISS + 1];

        //Test multi game or not
        String word = getIntent().getStringExtra(FragEnterWord.QUIZE_WORD);
        if (word == null) {
            //Single game
            quizeWord = getQuizeWord().toUpperCase();
        } else {
            //Multi game
            quizeWord = word.toUpperCase();
        }

        //Clear out tamplate
        for (int i = 0; i < quizeWord.length(); i++) {
            outString.append("_ ");
        }

        Resources res = getResources();
        mainImg.setImageDrawable(res.getDrawable(R.drawable.start)); //, this.getTheme()));
        wordTextView.setText(outString);

    }

    /**
     * Generate a random word from string resourses
     *
     * @return new random word from resources words group
     */
    private String getQuizeWord() {
        Random genRandom = new Random(System.currentTimeMillis());
        int wordsGroupIndx = genRandom.nextInt(MAX_WORD_GROUPS);

        //Get random words group
        String wordsGroup = "";
        switch (wordsGroupIndx) {
            case 0:
                wordsGroup = getString(R.string.words_group0);
                break;
            case 1:
                wordsGroup = getString(R.string.words_group1);
                break;
            case 2:
                wordsGroup = getString(R.string.words_group2);
                break;
            case 3:
                wordsGroup = getString(R.string.words_group3);
                break;
            case 4:
                wordsGroup = getString(R.string.words_group4);
                break;
            case 5:
                wordsGroup = getString(R.string.words_group5);
                break;
            case 6:
                wordsGroup = getString(R.string.words_group6);
                break;
        }

        if (wordsGroup != null) {
            //Split group on words
            String[] words = wordsGroup.split(" ");

            //Gen random word
            int wordIndx = genRandom.nextInt(words.length - 1);
            return words[wordIndx];
        }
        Log.d("MYTAG", "Error while generete word");
        return getString(R.string.default_word); //Word by default
    }

    @Override
    public boolean onLetterInput(View inputButton) {
        boolean isLetterRigth = false;
        if (inputButton != null) {
            isLetterRigth = checkLetter(((Button) inputButton).getText().toString());
        }
        return isLetterRigth;
    }
}
