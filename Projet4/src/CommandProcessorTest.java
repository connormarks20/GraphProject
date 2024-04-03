import student.TestCase;

/**
 * @author Connor Marks (connorm20)
 * @version 11.28.2023
 * 
 *          Class responsible for testing the
 *          Command Processor/Parser functionality.
 */
public class CommandProcessorTest extends TestCase {

    /**
     * empty setup constructor.
     */
    public void setUp() {
        // do nothing
    }


    /**
     * This tests the processCommand method
     * when there is a non empty file.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testNonEmptyFile() throws Exception {

        String name = "commandTest.txt";

        CommandProcessor process = new CommandProcessor(10, name);

        process.processCommand(name);

        assertEquals(systemOut().getHistory(), "");
    }


    /**
     * This will test the print branch
     * in the switch statement within
     * the class.
     * 
     * @throws Exception
     *             if there is an error.
     */
    public void testProcessCommand2() throws Exception {
        String file = "printhashtable.txt";
        Graph graph = new Graph(16);
        Hash hash = new Hash(16, graph);
        CommandProcessor command = new CommandProcessor(16, file);
        command.processCommand(file);
        assertEquals(systemOut().getHistory(), "total songs: 0\r\n"
            + "total artists: 0\r\n"
            + "|Blind Lemon Jefferson| is added to the Artist database.\r\n"
            + "|Long Lonesome Blues| is added to the Song database.\r\n"
            + "|Ma Rainey| is added to the Artist database.\r\n"
            + "|Ma Rainey's Black Bottom| is added to the Song database.\r\n"
            + "8: |Ma Rainey's Black Bottom|\r\n"
            + "11: |Long Lonesome Blues|\r\n" + "total songs: 2\r\n"
            + "0: |Blind Lemon Jefferson|\r\n" + "7: |Ma Rainey|\r\n"
            + "total artists: 2\r\n" + "There are 2 connected components\r\n"
            + "The largest connected component has 2 elements\r\n"
            + "The diameter of the largest component is 1\r\n" + "");

    }


    /**
     * Tests the remove functionality for a nonempty file.
     * 
     * @throws Exception
     *             if the file doesn't exist, or some other
     *             error.
     */
    public void testProcessRemoveNonEmpty() throws Exception {
        String removeFile = "testRemove.txt";
        CommandProcessor process = new CommandProcessor(10, removeFile);
        process.processCommand(removeFile);
        assertEquals(systemOut().getHistory(),
            "|a| does not exist in the Song database.\r\n"
                + "|b| does not exist in the Song database.\r\n"
                + "|a| does not exist in the Artist database.\r\n"
                + "|song a| is added to the Artist database.\r\n"
                + "|b| is added to the Song database.\r\n"
                + "|a| does not exist in the Song database.\r\n"
                + "|b| is removed from the Song database.\n");
    }


    /**
     * Tests remove for the sample input file.
     * 
     * @throws Exception
     *             if the file doesn't exist, or
     *             some other error.
     */
    public void testRemoveSampleInput() throws Exception {
        String otherFile = "P4sampleInput.txt";
        CommandProcessor process = new CommandProcessor(10, otherFile);
        process.processCommand(otherFile);
        assertEquals(systemOut().getHistory(),
            "|When Summer's Through| does not exist in the Song database.\r\n"
                + "total songs: 0\r\n" + "total artists: 0\r\n"
                + "There are 0 connected components\r\n"
                + "The largest connected component has 0 elements\r\n"
                + "The diameter of the largest component is 0\r\n"
                + "|Blind Lemon Jefferson| is added to the Artist database.\r\n"
                + "|Long Lonesome Blues| is added to the Song database.\r\n"
                + "|Blind Lemon Jefferson<SEP>Long Lonesome Blues| "
                + "duplicates a record already in the database.\r\n"
                + "|Long   Lonesome Blues| is added to the Song database.\r\n"
                + "|long Lonesome Blues| is added to the Song database.\r\n"
                + "|Ma Rainey| is added to the Artist database.\r\n"
                + "|Ma Rainey's Black Bottom| is added to "
                + "the Song database.\r\n"
                + "|Mississippi Boweavil Blues| is added to "
                + "the Song database.\r\n" + "Song hash table size doubled.\r\n"
                + "|Fixin' To Die Blues| is added to the Song database.\r\n"
                + "0: |Blind Lemon Jefferson|\r\n" + "7: |Ma Rainey|\r\n"
                + "total artists: 2\r\n" + "1: |Fixin' To Die Blues|\r\n"
                + "2: |Mississippi Boweavil Blues|\r\n"
                + "7: |long Lonesome Blues|\r\n"
                + "15: |Long Lonesome Blues|\r\n"
                + "16: |Ma Rainey's Black Bottom|\r\n"
                + "19: |Long   Lonesome Blues|\r\n" + "total songs: 6\r\n"
                + "There are 1 connected components\r\n"
                + "The largest connected component has 8 elements\r\n"
                + "The diameter of the largest component is 4\r\n"
                + "|Sleepy| does not exist in the Song database.\r\n"
                + "|ma rainey| does not exist in the Artist database.\r\n"
                + "|Ma Rainey| is removed from the Artist database.\r\n"
                + "0: |Blind Lemon Jefferson|\r\n" + "7: TOMBSTONE\r\n"
                + "total artists: 1\n");
    }

}
