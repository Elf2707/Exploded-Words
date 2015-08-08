package com.example.elf.explodedwords;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ScoresActivity extends Activity {
    public static final String SCORE_DELIMITER = "%";
    SharedPreferences prefs;
    ListView listOfScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        prefs = getSharedPreferences(getString(R.string.prefs_name), MODE_PRIVATE);
        listOfScores = (ListView) findViewById(R.id.listScores);
        refresfScoreData();
    }

    //Clear score
    public void clearScore(View v) {
        SharedPreferences.Editor prefsEditor = prefs.edit();
        prefsEditor.remove(getString(R.string.pref_score));
        prefsEditor.apply();
        refresfScoreData();
    }

    private void refresfScoreData(){
        //Get scores from prefs
        String prefsString = prefs.getString(getString(R.string.pref_score), getString(R.string.no_score));
        String[] splitScores = prefsString.split(SCORE_DELIMITER);
        ArrayAdapter<String> scoreAdapter = new ArrayAdapter<String>(this, R.layout.list_scores_item, splitScores);
        listOfScores.setAdapter(scoreAdapter);
    }
}
