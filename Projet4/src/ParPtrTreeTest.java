import student.TestCase;

/**
 * ParPtrTree Test Class.
 * 
 * @author Connor Marks (connorm20)
 * @version 11.28.2023
 */
public class ParPtrTreeTest extends TestCase {

    /**
     * Tests setup.
     */
    public void setUp() {
        // do nothing.
    }


    /**
     * Tests 1
     */
    public void test1() {
        ParPtrTree tree = new ParPtrTree(10);

        assertEquals(tree.find(0), 0);

        tree.union(0, 1);
        assertEquals(tree.find(0), tree.find(1));
        assertEquals(tree.find(0), tree.find(1));

        assertNotSame(tree.find(2), tree.find(3));

        tree.union(1, 2);
        assertEquals(tree.find(0), tree.find(2));

        tree.union(4, 5);
        assertEquals(tree.find(4), tree.find(5));
        assertNotSame(tree.find(4), tree.find(3));

        tree.union(4, 3);
        assertEquals(tree.find(3), tree.find(5));

        tree.union(0, 3);
        assertEquals(tree.find(0), tree.find(4));

    }


    /**
     * tests roots.
     */
    public void testRoots() {
        ParPtrTree tree = new ParPtrTree(10);

        for (int i = 0; i < 10; i++) {
            assertEquals(i, tree.find(i));
        }
    }


    /**
     * tests union / find
     */
    public void testUnion() {
        ParPtrTree tree = new ParPtrTree(10);

        tree.union(0, 1);
        assertEquals(tree.find(0), tree.find(1));
        assertEquals(tree.find(0), tree.find(1));

        assertNotSame(tree.find(2), tree.find(3));

        tree.union(1, 2);
        assertEquals(tree.find(0), tree.find(2));

        tree.union(4, 5);
        assertEquals(tree.find(4), tree.find(5));
        assertNotSame(tree.find(4), tree.find(3));

        tree.union(4, 3);
        assertEquals(tree.find(3), tree.find(5));

        tree.union(0, 3);
        assertEquals(tree.find(0), tree.find(4));
    }


    /**
     * tests union / find
     */
    public void testUnion2() {
        ParPtrTree tree = new ParPtrTree(10);

        tree.union(1, 2);
        assertEquals(tree.find(1), tree.find(2));

        tree.getWeights()[2] = 20;
        tree.getWeights()[1] = 10;
        tree.union(1, 3);
        assertEquals(tree.find(1), tree.find(3));

        tree.getWeights()[4] = 10;
        tree.getWeights()[5] = 20;
        tree.union(4, 5);
        assertEquals(tree.find(4), tree.find(5));

    }


    /**
     * tests union weights.
     */
    public void testUnion5() {
        ParPtrTree tree = new ParPtrTree(10);

        tree.getArray()[0] = 0;
        tree.getArray()[1] = 1;
        tree.getWeights()[0] = 5;
        tree.getWeights()[1] = 5;

        tree.union(0, 1);

        assertEquals(tree.find(0), 0);

        assertEquals(tree.getWeights()[1], 5);
    }


    /**
     * Test same roots union / find.
     */
    public void testSameRoots() {
        ParPtrTree tree = new ParPtrTree(10);

        tree.getArray()[0] = 0;
        tree.getArray()[1] = 0;
        tree.getWeights()[0] = 5;
        tree.getWeights()[1] = 10;

        tree.union(0, 1);

        assertEquals(tree.find(0), 0);
        assertEquals(tree.find(1), 0);
        assertEquals(tree.getWeights()[0], 5);
        assertEquals(tree.getWeights()[1], 10);
    }


    /**
     * Test union
     */
    public void testUnion6() {
        ParPtrTree tree = new ParPtrTree(10);

        tree.getArray()[0] = 0;
        tree.getArray()[1] = 1;
        tree.getWeights()[0] = 5;
        tree.getWeights()[1] = 10;

        tree.union(0, 1);
        assertEquals(tree.find(0), 1);
    }


    /**
     * tests updated parptrtree
     */
    public void testUpdate() {
        ParPtrTree tree = new ParPtrTree(10);

        tree.getArray()[0] = 0;
        tree.getArray()[1] = 1;
        tree.getWeights()[0] = 5;
        tree.getWeights()[1] = 10;

        tree.union(0, 1);

        assertEquals(tree.find(0), tree.find(1));
        assertEquals(tree.getWeights()[1], 15);
    }


    /**
     * graph tests
     */
    public void testGraph() {
        WorldDataBase db = new WorldDataBase(10);

        db.getGraph().remove("a");

        db.printGraph();
        Node a = new Node("a");
        db.getGraph().insertNode(a);

        assertTrue(db.getGraph().contains("a"));
        String str = "a";
        String str2 = "b";
        String both = str + "<SEP>" + str2;
        for (int i = 0; i < 10; i++) {

            db.insert(str, str2, both);
            str = str + "a";
            str2 = str2 + "b";

        }

        db.getGraph().printGraph();

        System.out.println("BREAK");

        db.removeArtist("a");
        db.removeSong("b");

        assertFalse(db.getGraph().contains("a"));

        assertFalse(db.getGraph().contains("b"));

        db.printGraph();

        db.removeArtist("aa");
        db.removeSong("bb");
        db.printGraph();

    }


    /**
     * Test remove2
     */
    public void testRemove2() {
        WorldDataBase db = new WorldDataBase(10);

        db.getGraph().remove("a");

        db.printGraph();
        Node a = new Node("a");
        db.getGraph().insertNode(a);

        assertTrue(db.getGraph().contains("a"));
        String str1 = "a";
        String str2 = "b";
        String both = "ab";

        String str3 = "b";
        String str4 = "c";
        String both2 = "bc";

        String str5 = "c";
        String str6 = "d";
        String both3 = "cd";

        db.insert(str1, str2, both);
        db.insert(str3, str4, both2);
        db.insert(str5, str6, both3);

        db.printGraph();

        db.removeArtist("a");

        db.printGraph();

        db.removeSong("b");
        db.printGraph();

    }


    /**
     * TEST ANOTHER
     */
    public void testAnother() {

        System.out.println("--------------------------");
        WorldDataBase db = new WorldDataBase(10);

        db.printGraph();

        db.insert("a", "b", "ab");

        db.insert("b", "c", "bc");

        db.insert("c", "d", "cd");

        db.insert("d", "f", "df");

        db.printGraph();

        assertTrue(db.getGraph().edgeExists("a", "b"));
        assertTrue(db.getGraph().edgeExists("b", "c"));
        assertTrue(db.getGraph().edgeExists("c", "d"));
        assertTrue(db.getGraph().edgeExists("d", "f"));

        db.removeArtist("c");

        assertTrue(db.getGraph().edgeExists("a", "b"));
        assertFalse(db.getGraph().edgeExists("b", "c"));
        assertFalse(db.getGraph().edgeExists("c", "d"));
        assertTrue(db.getGraph().edgeExists("d", "f"));

        db.printGraph();

    }


    /**
     * TEST ANOTHER 2
     */
    public void testAnother2() {

        System.out.println("--------------------------");
        WorldDataBase db = new WorldDataBase(10);

        db.printGraph();

        db.insert("a", "b", "ab");

        db.insert("b", "c", "bc");

        db.insert("c", "d", "cd");

        db.insert("d", "f", "df");
        System.out.println("--------------------------");

        db.printGraph();

        db.insert("y", "z", "yz");

        db.insert("z", "x", "zx");
        System.out.println("--------------------------");

        db.printGraph();

        db.removeArtist("c");

        System.out.println("--------------------------");

        db.printGraph();
        assertTrue(db.getGraph().edgeExists("a", "b"));
    }


    /**
     * Test easy delete.
     */
    public void testEasyDelete() {

        WorldDataBase db = new WorldDataBase(10);

        db.insert("a", "b", "ab");
        System.out.println("--------------------------");

        db.printGraph();

        db.removeArtist("a");

        db.printGraph();

        db.removeSong("b");

        db.printGraph();

        String artist = "a";
        String song = "s";

        String both = "as";

        for (int i = 0; i < 10; i++) {
            db.insert(artist, song, both);
            artist = artist + "a";
            song = song + "b";
            both = song + artist;
        }

        System.out.println("--------------------------");
        db.printGraph();
        assertEquals(db.getGraph().getNumNodes(), 20);
    }


    /**
     * Test again again.
     */
    public void testAgainAgain() {

        WorldDataBase db = new WorldDataBase(4);

        String artist = "a";
        String song = "b";
        String artistAndSong = "a<SEP>b";

        String artist2 = "c";
        String song2 = "b";

        // insert Artist<SEP>Song

        // insert the artist and song into their respective hash tables.

        db.insert(artist, song, artistAndSong);

        System.out.println("---------------------------------------");

        db.printArtistTable();
        db.printSongTable();
        db.printGraph();

        db.insert(artist2, song2, artistAndSong);

        String a = "f";
        String b = "g";
        String both = "f<SEP>g";

        db.insert(a, b, both);

        db.printGraph();

        assertEquals(db.getGraph().getGraphLength(), 8);

        assertTrue(db.getGraph().edgeExists("f", "g"));

        assertTrue(db.getGraph().edgeExists(artist2, song2));

        // number of components

    }
    
    
    
    public void testInsertHashAndGraph() 
    {
        Graph graph = new Graph(10);
        Hash songTable = new Hash(10, graph);
        
        songTable.insertSong("a", "s" , new Node("a"));   
        
        
    }

}
