// -------------------------------------------------------------------------
/**
 * Main for Graph project (CS3114/CS5040 Fall 2023 Project 4).
 * Usage: java GraphProject <init-hash-size> <command-file>
 * 
 * The goal of this program is to represent two data structures:
 * a HashTable and a Graph. These two data structures work together
 * to store music in a database where songs and artists are stored
 * in two separate HashTables where the name of a
 * song or artist acts as the key, and
 * a corresponding node in the graph is the value. The job of the graph
 * is to represent these nodes and the connections between them. These two
 * structures will support insertion, removal, and printing. The Graph
 * will be responsible for computing the connected components and the
 * diameter of the resulting graph after various inserts and removals.
 * The two HashTables are simply used to store the key,value pairs for
 * different artists and songs.
 *
 * @author Connor Marks (connorm20)
 * @version 12.6.2-23
 *
 */

// On my honor:sp
// - I have not used source code obtained from another current or
// former student, or any other unauthorized source, either
// modified or unmodified.
//
// - All source code and documentation used in my program is
// either my original work, or was derived by me from the
// source code published in the textbook for this course.
//
// - I have not discussed coding details about this project with
// anyone other than my partner (in the case of a joint
// submission), instructor, ACM/UPE tutors or the TAs assigned
// to this course. I understand that I may discuss the concepts
// of this program with other students, and that another student
// may help me debug my program so long as neither of us writes
// anything during the discussion or modifies any computer file
// during the discussion. I have violated neither the spirit nor
// letter of this restriction.

public class GraphProject {
    /**
     * @param args
     *            Command line parameters
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        // This is the main file for the program.
        if (args == null) {
            System.out.println("Exit");
            return;
        }
        if (args.length != 2) {
            System.out.print("Number of arguments incorrect"
                + " There should be an argument for the hash size"
                + " and an argument for the file name.");
        }

        int size = Integer.parseInt(args[0]);
        String filename = args[1];

        CommandProcessor processor = new CommandProcessor(size, filename);

        processor.processCommand(filename);

    }
}
