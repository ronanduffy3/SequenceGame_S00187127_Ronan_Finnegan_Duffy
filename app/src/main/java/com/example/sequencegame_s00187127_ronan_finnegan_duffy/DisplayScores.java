package com.example.sequencegame_s00187127_ronan_finnegan_duffy;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DisplayScores extends AppCompatActivity {

    // Create variable to hold the listview
    ListView scoresLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_scores);

        // Find the LV in the app view and append it to the ScoresLV variable
        scoresLV = findViewById(R.id.lv_Scores);

        // Create an instance of the database handler
        DatabaseHandler dbHandler = new DatabaseHandler(this);

        // Add a dummy score
        dbHandler.addHiScore(new HighScore("20 OCT 2020", 12, "John"));

        // Read in the scores into a list
        List<HighScore> highScoreList = dbHandler.allHighScores();

        // Create a scores array
        List<String> scoresArray = new ArrayList<>();

        // Populate the array
        for(HighScore : highScoreList){
            scoresArray.add(x++ + " : "  +
                    HighScore. + "\t" +
                    hs.getScore());)
        }

        // Create an adaptor

        ArrayAdapter<String> scoresAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, scoresStr);
        listView.setAdapter(itemsAdapter);


    }
}