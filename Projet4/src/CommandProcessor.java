import java.util.Scanner;
import java.io.File;

/**
 * The class responsible for parsing the input file
 * using a Scanner. It will communicate with the
 * WorldDataBase class in order to let the
 * HashTable know which command is called.
 * It uses a simple switch statement to seperate
 * the different cases of possible command calls.
 * 
 * @author Connor Marks (connorm20)
 * @version 11/10/2023
 */

public class CommandProcessor {

    //private String artistName;
    //private String space;
    //private String songName;
    private String nameOfFile;
    private WorldDataBase worldDataBase;

    /**
     * This is the constructor for the CommandProcessor class.
     * It takes in a file name from the command line passed
     * by SemManager and also a HashTable size.
     * 
     * @param filename
     *            the name of the file to create
     *            the command processor with.
     * @param hashSize
     *            is a HashTable object representing
     *            the size.
     */
    public CommandProcessor(int hashSize, String filename) {
        this.nameOfFile = filename;
        this.worldDataBase = new WorldDataBase(hashSize);

    }


    /**
     * This method processes the commands from
     * the file using a scanner and a switch
     * statement. It takes in a file name which
     * represents the input file to parse, and
     * it also takes in the arguments from
     * the command line.
     * 
     * @param fileName
     *            the name of the file to parse.
     *            are the arguments from the
     *            commandline.
     * @throws Exception
     *             if the file is empty
     *             or cannot be found. Or if the
     *             arguments are incorrect.
     */
    public void processCommand(String fileName) throws Exception {
        this.nameOfFile = fileName;
        // Creates a new Scanner Object to
        // Scan the input file.
        Scanner scan = new Scanner(new File(nameOfFile));
        // While the input file still has lines to read
        // Continue scanning the file.
        while (scan.hasNext()) {
            // This gets the type of command
            // Insert, delete, search, and print.
            String commandType = scan.next();
            switch (commandType) {
                case "insert":
                    String artistAndSong = scan.nextLine().trim();
                    String[] separate = artistAndSong.split("<SEP>");

                    String artist = separate[0].trim();
                    String song = separate[1].trim();

                    worldDataBase.insert(artist, song, artistAndSong);

                    break;
                case "remove":
                    String removeType = scan.next();
                    if (removeType.equals("artist")) {
                        String nameOfArtist = scan.nextLine().trim();
                        worldDataBase.removeArtist(nameOfArtist);
                    }
                    else if (removeType.equals("song")) {
                        String nameOfSong = scan.nextLine().trim();
                        worldDataBase.removeSong(nameOfSong);
                    }
                    break;

                case "print":
                    String printType = scan.nextLine().trim();
                    if (printType.equals("artist")) {
                        worldDataBase.printArtistTable();
                    }
                    else if (printType.equals("song")) {
                        worldDataBase.printSongTable();
                    }
                    else if (printType.equals("graph")) {
                        worldDataBase.printGraph();
                    }

                    break;
                default: 
                    System.out.println("File is empty");
            }
        }
    }
}
