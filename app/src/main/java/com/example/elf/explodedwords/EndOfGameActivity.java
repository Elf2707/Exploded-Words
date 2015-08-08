package com.example.elf.explodedwords;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class EndOfGameActivity extends Activity {
    public static final String USER_NAME = "user_name";

    //Edit Text View with user name new or from prefs
    EditText userNameEdit;
    private int points;
    //Quize word
    private String word;
    private String userName;
    //Result of the game win or loose
    private boolean wasWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_loose);

        //Figure out what was that win or not win and be optimistic by default
        wasWin = getIntent().getBooleanExtra(GameActivity.RESULT, true);

        if (wasWin) {
            //Load win layout
            setContentView(R.layout.layout_win);

            points = getIntent().getIntExtra(GameActivity.POINTS, 0);
            //Set up points
            TextView pointsView = (TextView) findViewById(R.id.txt_points);
            pointsView.setText(getString(R.string.lbl_points) + " " + String.valueOf(points));

            //Set up user name to edit text
            userName = PreferenceManager.getDefaultSharedPreferences(getApplication()).getString(USER_NAME, getString(R.string.unknown_user));
            userNameEdit = (EditText) findViewById(R.id.edtxt_user_name);
            userNameEdit.setText(userName);

            //Set up quize word for score sring
            word = getIntent().getStringExtra(GameActivity.WORD);
        } else {
            //Load loose layout
            setContentView(R.layout.layout_loose);
        }
    }

    public void newGame(View v) {
        if (wasWin) {
            SharedPreferences prefs = getSharedPreferences(getString(R.string.prefs_name), MODE_PRIVATE);
            String scores = prefs.getString(getString(R.string.pref_score), getString(R.string.no_score));

            //Figure out user name
            StringBuffer appendScore = new StringBuffer();

            //If it not a first time add delimiter
            if (scores.equals(getString(R.string.no_score))) {
                //Clrear string
                scores = "";
            }

            if (!scores.isEmpty()) {
                appendScore.append(ScoresActivity.SCORE_DELIMITER);
            }

            //Check if the user enter new Name not that he/she have at prefs
            String newUserName = userNameEdit.getText().toString();
            if (userName.equals(newUserName)) {
                appendScore.append(userName);
            } else {
                appendScore.append(newUserName);
            }

            appendScore.append(" " + word + " " + String.valueOf(points));

            SharedPreferences.Editor prefsEditor = prefs.edit();
            //Saving Prefs
            prefsEditor.putString(getString(R.string.pref_score), scores + appendScore);
            prefsEditor.apply();
        }
        finish();
    }
}
