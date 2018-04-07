package game;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * Demonstrates how high-score data can be read from a text
 * file and printed to the terminal.
 */
public class HighScoreReader {

    private String fileName;
    public ArrayList<Player> playerScores;
    /**
     * Initialise a new HighScoreReader
     * @param fileName the name of the high-score file
     */
    public HighScoreReader(String fileName) {
        this.fileName = fileName;
        playerScores = new ArrayList<Player>();
    }

    /**
     * Read the high-score data from the high-score file and print it to
     * the terminal window.
     */
    //TODO read highscores in ascending order, list out only top 3
    public void readScores() throws IOException {
        FileReader fr = null;
        BufferedReader reader = null;
        try {
           // System.out.println("Reading " + fileName + " ...");
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();
            while (line != null) {
                // file is assumed to contain one name, score pair per line
                String[] tokens = line.split(",");
                String name = tokens[0];
                int score = Integer.parseInt(tokens[1]);
                playerScores.add(new Player(name, score));
                //System.out.println("Name: " + name + ", Score: " + score);
                line = reader.readLine();
            }
            Collections.sort(playerScores, new ScoreComparator());
            //System.out.println("...done.");       
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }

    }

    public ArrayList<Player> getPlayerScores() {
        return playerScores;
    }
        
}
