package com.example.elf.explodedwords;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startSingleGame(View view) {
        Intent singleGameIntent = new Intent(this, GameActivity.class);
        startActivity(singleGameIntent);
    }

    public void startMultiGame(View view) {
        Intent multiGameIntent = new Intent(this, MultiPlayerGame.class);
        startActivity(multiGameIntent);
    }

    public void seeScore(View view) {
        Intent scoresIntent = new Intent(this, ScoresActivity.class);
        startActivity(scoresIntent);
    }

    public void settings(View view) {
        Intent settingsIntent = new Intent(this, SettingsActivity.class);
        startActivity(settingsIntent);
    }
}
