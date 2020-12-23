package com.example.sequencegame_s00187127_ronan_finnegan_duffy;

public class HighScore {

    // Create properties

    int score_id;
    int score;
    String player_name;
    String date_of_game;

    // Constructor for highscore classs

    public HighScore(int score_id, int score, String player_name, String dateOfGame){
        this.score_id = score_id;
        this.date_of_game = dateOfGame;
        this.score = score;
        this.player_name = player_name;
    }

    public HighScore(int score_id, String string, String string1, int anInt) {
    }

    public HighScore(String date, int score, String playerName ) {
        this.date_of_game = date;
        this.score = score;
        this.player_name = playerName;
    }

    public HighScore() {

    }

    // Getters

    public int getScoreId(){
        return score_id;
    }

    public String getPlayerName(){
        return player_name;
    }

    public String getGemDate(){
        return date_of_game;
    }

    public int getScore(){
        return score;
    }

    // Setters


    public void setGameDate(String date) {
        this.date_of_game = date;
    }

    public void setPlayerName(String name) {
        this.player_name = name;
    }

    public void setScoreID(int scoreID) {
        this.score_id = scoreID;
    }

    public void setScore(int score) {
        this.score = score;
    }






}
