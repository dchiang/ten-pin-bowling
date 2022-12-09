package com.dchiang.bowling;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import com.dchiang.bowling.app.Game;
import com.dchiang.bowling.factories.*;
import com.dchiang.bowling.utils.*;

public class App {

	private static void printGameSelectionMenu(HashMap<Integer, String> gamesMenuOptions) {
		System.out.println("Type the number of the game you would like to play (0 to exit):");
		gamesMenuOptions.forEach((menuOption, gameName) -> {
			System.out.println(menuOption + " -> " + gameName);
		});
	}

	private static HashMap<Integer, String> getGamesMenuOptions() {
		List<String[]> records = null;
		String filename = "/games.txt";
		HashMap<Integer, String> gamesMenuOptions = new HashMap<>();
		try {
			records = FileHandler.readFile(App.class.getResourceAsStream(filename), "\\t");
			if (records != null) {
				records.stream().forEach((menuOption) -> {
					gamesMenuOptions.put(Integer.valueOf(menuOption[0]), menuOption[1]);
				});
			}
		} catch (IOException e) {
			System.out.println("Error loading file " + filename + " "
					+ e.getMessage() + " " + e.getClass().getName());
		}
		return gamesMenuOptions;
	}

	private static Integer getGameSelection() {
		Integer selection = null;
		try {
			ConsoleHandler cli = ConsoleHandler.getInstance();
			selection = Integer.valueOf(cli.readLine());
		} catch (NumberFormatException e) {
			System.out.println("Not a valid input: " + selection
					+ ", enter a valid option (0 to exit)");
		} catch (IOException e) {
			System.out.println("Error reading the console "
					+ e.getMessage() + " " + e.getClass().getName());
		}
		return selection;
	}

	private static Integer selectGame() {
		Integer selection = null;
		HashMap<Integer, String> gamesMenuOptions = getGamesMenuOptions();
		if (gamesMenuOptions != null) {
			printGameSelectionMenu(gamesMenuOptions);
			while (selection == null) {
				selection = getGameSelection();
				if (selection != null && selection != 0
						&& !gamesMenuOptions.containsKey(selection)) {
					System.out.println("Not a valid input: " + selection
							+ ", enter a valid option (0 to exit)");
					selection = null;
				}
			}
		}
		return selection;
	}

	private static Game configureGame() {
		Game game;
		GamesFactory factory;
		int selectedGame = selectGame();
		switch (selectedGame) {
			case 1:
				factory = new TenPinsTraditionalBowlingFactory();
				break;
			default:
				return null;
		}
		game = new Game(factory);
		return game;
	}

	public static void main(String[] args) {
		Game game = configureGame();
		if (game != null) {
			try {
				game.execute();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		ConsoleHandler cli = ConsoleHandler.getInstance();
		try {
			cli.closeReader();
		} catch (IOException e) {
			System.out.println("Error closing the cli reader "
					+ e.getMessage() + " " + e.getClass().getName());
		}
		System.exit(0);
	}
}
