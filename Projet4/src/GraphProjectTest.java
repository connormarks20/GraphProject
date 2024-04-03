import student.TestCase;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * This class was designed to test the GraphProject
 *
 * @author Connor Marks (connorm20)
 * @version 11.28.2023
 */
public class GraphProjectTest extends TestCase {
    // ----------------------------------------------------------
    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Set up the tests that follow.
     */
    public void setUp() { // Nothing needed yet

    }


    /**
     * Tests null arguments passed to Main.
     * 
     * @throws Exception
     *             for an exception.
     */
    public void testTest() {
        try {
            GraphProject.main(null);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(systemOut().getHistory(), "Exit\n");
        systemOut().clearHistory();

    }


    /**
     * Tests an incorrect number of arguments
     * for the input file.
     */
    public void testIncorrectArgs() {
        String[] args = { "10" };
        try {
            GraphProject.main(args);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        assertEquals(systemOut().getHistory(),
            "Number of arguments incorrect There should be an argument "
                + "for the hash size and an argument for the file name.");
    }


    /**
     * Tests the correct argument count for an input file.
     */
    public void testCorrectArgs() {
        String[] args = { "10", "input.txt" };
        try {
            GraphProject.main(args);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        assertEquals(systemOut().getHistory(), "");
    }


    /**
     * This method is simply to get code coverage of the class declaration.
     */
    public void testQInit() {
        GraphProject it = new GraphProject();
        assertNotNull(it);
    }
}
