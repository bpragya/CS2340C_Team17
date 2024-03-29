package com.example.myapplication;

import java.util.ArrayList;
import java.util.Collections;

import com.example.myapplication.Model.Player;
import com.example.myapplication.Model.Score;

/**
 * Stores scores from the leaderboard in the a list, called scoreEntry
 */
public class Leaderboard {

    private ArrayList<Score> scoreEntry = new ArrayList<>(); //list to store scores

    private Leaderboard() {

    }

    public void addScore(String player, int score, String time) {
        String playerName = Player.getInstance().getName();

        Score scoreActual = new Score(playerName, score, time);

        scoreEntry.add(scoreActual);
    } //adding scores to list

    public ArrayList<Score> getScoresSorted() {
        Collections.sort(scoreEntry);
        return scoreEntry;
    } //sorting scores

    private static Leaderboard instance = null;
    public static Leaderboard getInstance() {
        if (instance == null) {
            instance = new Leaderboard();
        }
        return instance;
    }
}

