# Java Challenge

This is a proposed java solution for Jobsity's Ten-pin bowling coding challenge. I have also considered and implemented the World Bowling and Twelve Frame variants of the game. You can find the necessary specifications in this [wiki](https://en.wikipedia.org/wiki/Ten-pin_bowling 'Ten-pin bowling').

---

## Software Requiriments

- Java JDK 11
- Maven

---

## Installation

1. Clone or download and unzip this repository in any local directory.
2. Open your terminal or command line console and navigate to the root folder of the project.
3. Compile and generate the .jar package executing the following command:
   ```console
   mvn clean package
   ```

---

## Run

This project is launch through the cli, for which you can pass the necessary input parameters or just run the program and follow the instructions that will appear on your cli.

#### Synopsis

Use the following command:

```console
java -jar target/JavaChallenge-1.0-SNAPSHOT.jar [gameSelection] [scoreFile]
```

#### Description

The generated jar file has two modes: interactive and non-interactive.

- Interactive mode allows the user to query the list of supported games and choose the one for which the scoreboard will be printed.
- Non-interactive mode is used when both input parameters are send and it directly prints the scoreboard for the specified game selection and score file.

#### Arguments

Interactive mode is entered in the following cases:

- When no arguments are given.
  ```console
  java -jar target/JavaChallenge-1.0-SNAPSHOT.jar
  ```
- When only the first argument is given e.g.
  ```console
  java -jar target/JavaChallenge-1.0-SNAPSHOT.jar 1
  ```

Non-interactive mode e.g.

```console
java -jar target/JavaChallenge-1.0-SNAPSHOT.jar 1 ./src/test/resources/positive/scores.txt
```

---

#### Files

This projects uses 2 types of files:

1. The game menu file that is stored in `src/java/main/resources/games.txt` with the format [ game selection (space-tab) game name ]. e.g.:

   <code>1&emsp;Traditional Ten Frames Bowling</code>

2. The score files with the format [ player name (space-tab) pins fallen ], that are in two separate folders under `src/java/test/resources/` as:
   - `negative` contains invalid score files to use when testing validations and exception handling.
   - `positive` contains valid score file to use the testins success cases. I have added a suffix in the file name to identify which files can be used to test each game.

---

## Supported Features

- [x] The code should handle the bowling scores rules described in the specs.
- [x] The program should run from the command-line and take a text file as input.
- [x] The program should read the input text file and parse its content, which should have the results for several players bowling 10 frames each, written according to these guidelines:

  1.  Each line represents a player and a chance with the subsequent number of pins knocked down.
  2.  An 'F' indicates a foul on that chance and no pins knocked down (identical for scoring to a roll of 0).
  3.  The columns in each row are tab-separated.

- [x] The program should handle bad input like more than ten throws (i.e., no chance will produce a negative number of knocked down pins or more than 10, etc), invalid score value or incorrect format.
- [x] The program should output the scoring for the associated game according to these guidelines:

  1.  For each player, print their name on a separate line before printing that player's pinfalls and score.
  2.  All values are tab-separated.
  3.  The output should calculate if a player scores a strike ('X'), a spare ('/') and allow for extra chances in the tenth frame.

- [x] Handle all possible cases of a game both including a game where all rolls are 0, all rolls are fouls (F) and a perfect game, where all rolls are strikes.
- [x] Unit test: Tests should cover at least the non-trivial classes and methods.
- [x] Integration test: At least cover the three main cases: Sample input (2 players), perfect score, zero score.

---

## Bonus Features

- [x] Use Java 8 streams and lambdas.
- [x] Support variants of the game:

  - World Bowling
  - Twelve Frame

- [x] The program can handle score files with less frames than needed to complete the game.
- [x] Accept only players name that starts with one capital letter followed by lower case letters only e.g. `Carl`.

---

## Considerations Requested and Applied

- [x] SRP: Single Responsibility Principle (Classes are self contained. They do the task they need to do and nothing else).
- [x] Liskov’s Substitution Principle: Interfaces (OOP, Swap principle.. Makes the program able to be extended).
- [x] Dependency Inversion Principle: Code should depend on interfaces, no concrete implementations.
- [x] Include well-known libraries and good use of the JDK API.
- [x] Build mechanism (maven, NO IDE). Program compile and run out of the box.
- [x] Readme file explaining how to compile the project, and contain the test text file to check the output.
- [x] Project structure: standard Maven project layout, no IDE specific or custom.

---

## Developer Notes

I have used test coverage reports using [JaCoCo](https://www.eclemma.org/jacoco/ 'Java Code Coverage Library'). To generate the coverage report use the command `mvn jacoco:prepare-agent clean package jacoco:report`. To see the results just open the file `/target/site/jacoco/index.html` using any web browser.

I have also used [SonarQube](https://www.sonarqube.org/ "SonarQube's Home Page") for QC. If you have an instance of your own you can use the command `mvn clean package sonar:sonar -Dsonar.login=[your sonarqube authentication token]` to see SonarQube's report in your instance.

SonarQube supports JaCoCo integration. In order to use it, excecute the command `mvn jacoco:prepare-agent clean package jacoco:report sonar:sonar -Dsonar.login=[your sonarqube authentication token]`

---

## Last Sonarqube Quality Gate Status

![SonarQube Project Overview](images/sonar.png 'SonarQube Project Overview')
