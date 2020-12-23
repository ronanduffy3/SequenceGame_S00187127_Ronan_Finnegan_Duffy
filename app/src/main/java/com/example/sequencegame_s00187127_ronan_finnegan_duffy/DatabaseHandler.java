package com.example.sequencegame_s00187127_ronan_finnegan_duffy;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    // Fields for the database
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "project3game";
    private static final String TABLE_HI_SCORES = "hi_scores";
    private static final String KEY_SCORE_ID = "score_id";
    private static final String KEY_PLAYER_NAME = "player_name";
    private static final String KEY_GAME_DATE = "game_date";
    private static final String KEY_SCORE = "score";

    // Create a constructor for the database
    public DatabaseHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Create applicable fields in onCreate
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_HISCORES_TABLE = "CREATE TABLE " + TABLE_HI_SCORES + "(" +
                KEY_SCORE_ID + " INTEGER PRIMARY KEY," +
                KEY_GAME_DATE + " TEXT NOT NULL," +
                KEY_PLAYER_NAME + " TEXT NOT NULL," +
                KEY_SCORE + " INTEGER NOT NULL" +
                ")";
        db.execSQL(CREATE_HISCORES_TABLE);
    }


    // Clear the table and recreate the DB (Give the db a full wipe)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HI_SCORES);
        // Create tables again
        onCreate(db);

    }

    // Method to remove all scores from DB
    public void emptyHighScores(){
        // GET AN INSTANCE OF THE DB
        SQLiteDatabase db = this.getWritableDatabase();
        // EXECUTE SQL QUERY TO DROP TABLE AND THEN RECREATE WITH ONCREATE
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_HI_SCORES);

        // RUN AN ONCREATE TO CREATE TABLES
        onCreate(db);
    }

    void addHiScore(HighScore highscoreToAdd){
        // Create an instance of the writable database
        SQLiteDatabase db = this.getWritableDatabase();

        // Create a value holder (Content values in this case)
        ContentValues values = new ContentValues();

        // Put the values into the holder
        values.put(KEY_GAME_DATE, highscoreToAdd.getGemDate());
        values.put(KEY_PLAYER_NAME, highscoreToAdd.getPlayerName());
        values.put(KEY_SCORE, highscoreToAdd.getScore());

        // Insert the row into the database

        db.insert(TABLE_HI_SCORES, null, values);

        // Close the connection the databse

        db.close();


    }

    HighScore getHighScore(int id){
        // Create an instance of the database
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_HI_SCORES, new String[]{
                        KEY_SCORE_ID,
                        KEY_GAME_DATE,
                        KEY_PLAYER_NAME,
                        KEY_SCORE},
                KEY_SCORE_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        HighScore hiScore = new HighScore(Integer.parseInt(
                cursor.getString(0)),
                cursor.getString(1),
                cursor.getString(2),
                cursor.getInt(3));
        // return hi score
        return hiScore;
    }

    // Create a list of the high scores

    public List<HighScore> allHighScores(){
        // Create a list to hold the highscores
        List<HighScore> hiScoreList = new ArrayList<HighScore>();

        // Create query to ask db
        String selectQUERY = "SELECT * FROM " + TABLE_HI_SCORES;

        // Create db instance
        SQLiteDatabase db = this.getWritableDatabase();

        // Create cursor instance
        Cursor cursor = db.rawQuery(selectQUERY, null);

        // Loop through the rows in database and add to the list
        if (cursor.moveToFirst()) {
            do {
                HighScore hiScore = new HighScore();
                hiScore.setScoreID(Integer.parseInt(cursor.getString(0)));
                hiScore.setGameDate(cursor.getString(1));
                hiScore.setPlayerName(cursor.getString(2));
                hiScore.setScore(cursor.getInt(3));
                // Adding hi score to list
                hiScoreList.add(hiScore);
            } while (cursor.moveToNext());
        }

        return hiScoreList;





    }

}
