package com.dchiang.bowling.scoreboards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.StringJoiner;

import com.dchiang.bowling.exceptions.ExtraScoreException;
import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.player.BowlingPlayer;
import com.dchiang.bowling.utils.ConsoleHandler;
import com.dchiang.bowling.utils.FileHandler;
import com.dchiang.bowling.utils.Validator;

public abstract class TenPinBowlingScoreboard implements Scoreboard {

    protected int framesNumber;
    protected int maxRolls;
    protected ArrayList<BowlingPlayer> players = new ArrayList<>();
    protected LinkedHashMap<String, List<String>> scoreboard = new LinkedHashMap<>();

    protected abstract BowlingPlayer createPlayer(String playerName, List<String> rolls) throws Exception;

    protected String requestScoreFile() {
        String scoresPath = null;
        while (scoresPath == null) {
            System.out.println("Enter the absolute path to the score file:");
            try {
                scoresPath = ConsoleHandler.readLine();
                if (!FileHandler.fileExists(scoresPath)) {
                    scoresPath = null;
                    System.out.println("Not a valid file");
                }
            } catch (IOException e) {
                System.out.println("Error reading the file "
                        + e.getMessage() + " " + e.getClass().getName());
            }
        }
        return scoresPath;
    }

    protected void loadScores(String scoresPath) throws Exception {
        List<String[]> records;
        records = FileHandler.readFile(scoresPath, "\\t");
        if (records != null && records.size() > 0) {
            for (String[] record : records) {
                if (record.length != 2) {
                    throw new FileContentException("Invalid score file, missing column");
                }
                String playerName = record[0];
                String knockedDownPins = record[1];
                scoreboard.computeIfAbsent(playerName, k -> new ArrayList<>()).add(knockedDownPins);
            }
        } else {
            throw new FileContentException("Empty score file");
        }
    }

    private void addPlayers() throws Exception {
        for (HashMap.Entry<String, List<String>> set : scoreboard.entrySet()) {
            String playerName = set.getKey();
            List<String> rolls = set.getValue();
            if (Validator.hasValidFormat(playerName, "^[A-Z]{1}[a-z]+$")) {
                if (rolls.size() > this.maxRolls) {
                    throw new ExtraScoreException();
                }
                players.add(this.createPlayer(playerName, rolls));
            } else {
                throw new FileContentException("Invalid player name, found " + playerName);
            }
        }
    }

    private void printPlayerScore(BowlingPlayer player) {
        String playerScore = String.join("\n", player.getName(), player.getRollsString(), player.getScores());
        System.out.println(playerScore);
    }

    private void printScoreboard() {
        StringJoiner frames = new StringJoiner("\t\t");
        frames.add("Frame");
        for (int i = 1; i <= this.framesNumber; i++) {
            frames.add(String.valueOf(i));
        }
        System.out.println(frames);
        players.forEach((player) -> {
            this.printPlayerScore(player);
        });
    }

    public void execute(String scoreFile) throws Exception {
        if (scoreFile == null) {
            while (this.scoreboard.size() == 0) {
                String scoresPath = this.requestScoreFile();
                this.loadScores(scoresPath);
            }
        } else {
            this.loadScores(scoreFile);
        }
        this.addPlayers();
        this.printScoreboard();
    }
}