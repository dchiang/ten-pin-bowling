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

/**
 * Abstract class that provides default implementations and standard behaviors
 * of the {@link Scoreboard} interface.
 * <p>
 * Subclasses must implement the {@link #createPlayer(String, List)} method to
 * create BowlingPlayer objects from the loaded scores.
 */
public abstract class TenPinBowlingScoreboard implements Scoreboard {

    /**
     * List of BowlingPlayer objects representing the players in the game
     */
    protected ArrayList<BowlingPlayer> players = new ArrayList<>();

    /**
     * Map with player names as keys and a list of rolls as values representing the
     * game scoreboard
     */
    protected LinkedHashMap<String, List<String>> scoreboard = new LinkedHashMap<>();

    /**
     * Abstract method for creating BowlingPlayer objects from player name and rolls
     *
     * @param playerName the name of the player
     * @param rolls      a list of rolls for the player
     * @return a BowlingPlayer object
     * @throws FileContentException if there is an error with the file content
     */
    protected abstract BowlingPlayer createPlayer(String playerName, List<String> rolls) throws FileContentException;

    /**
     * Prompts the user for the absolute path to the score file, and reads the file
     * if it exists
     *
     * @return the absolute path to the score file
     * @throws IOException if there is an error reading the file
     */
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

    /**
     * Loads scores from a file and populates the scoreboard map with player names
     * as keys and a list of rolls as values
     *
     * @param scoresPath the path to the score file
     * @throws FileContentException if there is an error with the file content
     */
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

    /**
     * Executes the game by loading scores, creating players, and printing the
     * scoreboard. If a scoreFile is provided, it loads the scores from the file.
     * Otherwise, it prompts the user for the path to the score file.
     *
     * @param scoreFile the path to the score file, or null if the user should input
     *                  the path
     * @throws IOException          if there is an error reading the file
     * @throws FileContentException if there is an error with the file content
     */
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