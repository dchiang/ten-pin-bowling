package com.dchiang.bowling.scoreboards;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import com.dchiang.bowling.exceptions.FileContentException;
import com.dchiang.bowling.player.BowlingPlayer;
import com.dchiang.bowling.utils.ConsoleHandler;
import com.dchiang.bowling.utils.FileHandler;

public abstract class TenPinBowlingScoreboard implements Scoreboard {

    protected ArrayList<BowlingPlayer> players = new ArrayList<>();
    protected LinkedHashMap<String, List<String>> scoreboard = new LinkedHashMap<>();

    protected abstract BowlingPlayer createPlayer(String playerName, List<String> rolls) throws FileContentException;

    protected String requestScoreFile() throws IOException {
        String scoresPath = null;
        while (scoresPath == null) {
            ConsoleHandler.println("Enter the absolute path to the score file:");
            scoresPath = ConsoleHandler.readLine();
            if (!FileHandler.fileExists(scoresPath)) {
                scoresPath = null;
                ConsoleHandler.println("Not a valid file");
            }
        }
        return scoresPath;
    }

    protected void loadScores(String scoresPath) throws FileContentException {
        List<String[]> records;
        records = FileHandler.readFile(scoresPath, "\\t");
        if (records != null && !records.isEmpty()) {
            for (String[] line : records) {
                if (line.length != 2) {
                    throw new FileContentException("Invalid score file, missing column");
                }
                String playerName = line[0];
                String knockedDownPins = line[1];
                scoreboard.computeIfAbsent(playerName, k -> new ArrayList<>()).add(knockedDownPins);
            }
        } else {
            throw new FileContentException("Empty score file");
        }
    }

    private void addPlayers() throws FileContentException {
        for (Map.Entry<String, List<String>> set : scoreboard.entrySet()) {
            String playerName = set.getKey();
            List<String> rolls = set.getValue();
            players.add(this.createPlayer(playerName, rolls));
        }
    }

    private void printPlayerScore(BowlingPlayer player) {
        String playerScore = String.join("\n", player.getName(), player.getRollsString(), player.getScores());
        ConsoleHandler.println(playerScore);
    }

    private void printScoreboard() {
        StringJoiner frames = new StringJoiner("\t\t");
        frames.add("Frame");
        int framesNumber = this.players.get(0).getFramesNumber();
        for (int i = 1; i <= framesNumber; i++) {
            frames.add(String.valueOf(i));
        }
        ConsoleHandler.println(frames.toString());
        this.players.forEach(this::printPlayerScore);
    }

    public void execute(String scoreFile) throws IOException, FileContentException {
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