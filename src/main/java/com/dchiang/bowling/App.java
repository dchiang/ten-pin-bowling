package com.dchiang.bowling;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.dchiang.bowling.app.Game;
import com.dchiang.bowling.factories.GamesFactory;
import com.dchiang.bowling.factories.TenPinsTraditionalBowlingFactory;
import com.dchiang.bowling.factories.TwelveFramesBowlingFactory;
import com.dchiang.bowling.factories.WorldBowlingFactory;
import com.dchiang.bowling.utils.ConsoleHandler;
import com.dchiang.bowling.utils.FileHandler;

public class App {

	private static final String GAMES_FILE = "/games.txt";

	private static void printGameSelectionMenu(HashMap<Integer, String> gamesMenuOptions) {
		ConsoleHandler.println("Type the number of the game you would like to play (0 to exit):");
		gamesMenuOptions.forEach((menuOption, gameName) -> ConsoleHandler.println(menuOption + " -> " + gameName));
	}

	private static HashMap<Integer, String> getGamesMenuOptions() {
		List<String[]> records = null;
		HashMap<Integer, String> gamesMenuOptions = new HashMap<>();
		records = FileHandler.readFile(App.class.getResourceAsStream(GAMES_FILE), "\\t");
		if (records != null) {
			records.stream()
					.forEach(menuOption -> gamesMenuOptions.put(Integer.valueOf(menuOption[0]), menuOption[1]));
		}
		return gamesMenuOptions;
	}

	private static Integer getGameSelection() throws IOException {
		Integer selection = null;
		String input = null;
		try {
			input = ConsoleHandler.readLine();
			selection = Integer.valueOf(input);
		} catch (NumberFormatException e) {
			ConsoleHandler.println("Not a valid input: \"" + input
					+ "\", enter a valid option (0 to exit)");
		}
		return selection;
	}

	private static Integer selectGame() throws IOException {
		Integer selection = null;
		HashMap<Integer, String> gamesMenuOptions = getGamesMenuOptions();
		if (gamesMenuOptions.size() > 0) {
			printGameSelectionMenu(gamesMenuOptions);
			while (selection == null) {
				selection = getGameSelection();
				if (selection != null && selection != 0
						&& !gamesMenuOptions.containsKey(selection)) {
					ConsoleHandler.println("Not a valid input: \"" + selection
							+ "\", enter a valid option (0 to exit)");
					selection = null;
				}
			}
		}
		return selection;
	}

	private static Game configureGame(Integer gameSelection) throws IOException {
		Game game = null;
		GamesFactory factory;
		Integer selectedGame = gameSelection != null ? gameSelection : selectGame();
		if (selectedGame != null) {
			switch (selectedGame) {
				case 1:
					factory = new TenPinsTraditionalBowlingFactory();
					break;
				case 2:
					factory = new TwelveFramesBowlingFactory();
					break;
				case 3:
					factory = new WorldBowlingFactory();
					break;
				default:
					return null;
			}
			game = new Game(factory);
		}
		return game;
	}

	public static void main(String[] args) {
		Integer gameSelection = args.length > 0 ? Integer.valueOf(args[0]) : null;
		String scoreFile = args.length > 1 ? args[1] : null;
		try {

			Game game = configureGame(gameSelection);
			if (game != null) {
				game.execute(scoreFile);
			} else {
				ConsoleHandler.println("Exiting...");
			}
		} catch (Exception e) {
			ConsoleHandler.println(e.toString());
		}
	}
}
