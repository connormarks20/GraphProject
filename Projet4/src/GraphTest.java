import student.TestCase;

/**
 * @author Connor Marks (connorm20)
 * @version 11.23.2023
 *          This class is responsible for testing
 *          the graph functionality. Tests insert,
 *          remove, print, duplicate entries, etc.
 */
public class GraphTest extends TestCase {

    /**
     * Empty set up method.
     */
    public void setUp() {
        // nothing to do here.
    }


    /**
     * 
     */
    public void testInsertingNodes2() {
        Graph graph = new Graph(10);
        Hash artistTable = new Hash(10, graph);
        Hash songTable = new Hash(10, graph);

        WorldDataBase worldDataBase = new WorldDataBase(10);

        worldDataBase.insert("a", "b", "a<SEP>b");
        worldDataBase.insert("c", "d", "c<SEP>d");
        worldDataBase.insert("a", "f", "a<SEP>f");
        worldDataBase.insert("z", "b", "z<SEP>b");

        worldDataBase.getGraph().printGraph();

        assertTrue(worldDataBase.getGraph().edgeExists("a", "b"));

        assertTrue(worldDataBase.getGraph().edgeExists("b", "a"));

        assertTrue(worldDataBase.getGraph().edgeExists("c", "d"));

        assertTrue(worldDataBase.getGraph().edgeExists("d", "c"));

        assertTrue(worldDataBase.getGraph().edgeExists("a", "f"));

        assertTrue(worldDataBase.getGraph().edgeExists("f", "a"));

        assertTrue(worldDataBase.getGraph().edgeExists("z", "b"));

        worldDataBase.insert("f", "c", "f<SEP>c");

        assertTrue(worldDataBase.getGraph().edgeExists("f", "c"));

        assertTrue(worldDataBase.getGraph().edgeExists("c", "f"));

        assertFalse(worldDataBase.getGraph().edgeExists("z", "f"));

        assertTrue(worldDataBase.getGraph().edgeExists("z", "b"));

    }


    /**
     * Tests multiple disjoint inserts.
     */
    public void testInsertingNodesAgain() {
        WorldDataBase worldDataBase = new WorldDataBase(10);

        worldDataBase.insert("a", "b", "a<SEP>b");
        worldDataBase.insert("c", "d", "c<SEP>d");
        worldDataBase.insert("e", "f", "a<SEP>f");
        worldDataBase.insert("g", "h", "z<SEP>b");

        worldDataBase.getGraph().printGraph();

        assertTrue(worldDataBase.getGraph().edgeExists("a", "b"));
        assertTrue(worldDataBase.getGraph().edgeExists("c", "d"));
        assertTrue(worldDataBase.getGraph().edgeExists("e", "f"));

        worldDataBase.getGraph().remove("a");

        assertFalse(worldDataBase.getGraph().edgeExists("a", "b"));
        assertFalse(worldDataBase.getGraph().edgeExists("b", "a"));
    }


    /**
     * Tests edge removal for removing a single node in the graph.
     */
    public void testRemoveGraphNodes() {
        WorldDataBase worldDataBase = new WorldDataBase(10);

        worldDataBase.insert("a", "b", "a<SEP>b");
        assertTrue(worldDataBase.getGraph().edgeExists("a", "b"));
        assertTrue(worldDataBase.getGraph().edgeExists("b", "a"));

        worldDataBase.removeSong("a");

        assertFalse(worldDataBase.getGraph().edgeExists("a", "b"));

    }


    /**
     * Tests the print functionality of the graph.
     * Simply prints out an empty graph with 0 components,
     * 0 elements in the largest connected component,
     * and 0 for the diameter of the component (there are no
     * components).
     */
    public void testPrintGraph() {
        WorldDataBase db = new WorldDataBase(10);

        db.printGraph();
        assertEquals(systemOut().getHistory(),
            "There are 0 connected components\r\n"
                + "The largest connected component has 0 elements\r\n"
                + "The diameter of the largest component is 0\r\n" + "");
    }

    /*
     * insert Blind Lemon Jefferson<SEP>Long Lonesome Blues
     * insert Blind Lemon Jefferson<SEP>Long Lonesome Blues
     * insert Blind Lemon Jefferson<SEP>Long Lonesome Blues
     * insert Blind Lemon Jefferson<SEP>long Lonesome Blues
     */


    /**
     * Tests duplicate entries in the database. (Present in the
     * artist table, song table, and an edge exists between the
     * two nodes in the graph).
     */
    public void testDuplicateRecord() {
        WorldDataBase worldDataBase = new WorldDataBase(10);

        worldDataBase.insert("Blind Lemon Jefferson", "Long Lonesome Blues",
            "Blind Lemon Jefferson<SEP>Long Lonesome Blues");

        worldDataBase.insert("Blind Lemon Jefferson", "Long Lonesome Blues",
            "Blind Lemon Jefferson<SEP>Long Lonesome Blues");

        worldDataBase.insert(" Blind Lemon Jefferson", "Long   Lonesome Blues",
            "insert Blind Lemon Jefferson<SEP>Long   Lonesome Blues");

        worldDataBase.insert("Blind Lemon Jefferson", "long Lonesome Blues",
            "Blind Lemon Jefferson<SEP>long Lonesome Blues");

        assertEquals(systemOut().getHistory(),
            "|Blind Lemon Jefferson| is added to the " + "Artist "
                + "database.\r\n"
                + "|Long Lonesome Blues| is added to the Song "
                + "database.\r\n" + "|Blind Lemon Jefferson<SEP>Long "
                + "Lonesome Blues| duplicates a record already "
                + "in the database.\r\n"
                + "| Blind Lemon Jefferson| is added to the "
                + "Artist database.\r\n"
                + "|Long   Lonesome Blues| is added to the "
                + "Song database.\r\n"
                + "|long Lonesome Blues| is added to the "
                + "Song database.\r\n" + "");

    }


    /**
     * Tests the graph when exceeding capacity.
     * (will need to go back to this one).
     */
    public void testGraphExceed() {
        Graph graph = new Graph(4);
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        graph.insertNode(a);
        assertEquals(graph.getGraphLength(), 4);
        graph.insertNode(b);
        assertEquals(graph.getGraphLength(), 4);
        graph.insertNode(c);
        assertEquals(graph.getGraphLength(), 4);
        graph.insertNode(d);
        assertEquals(graph.getGraphLength(), 4);

    }


    /**
     * Tests a single node insert for the graph.
     */
    public void testInsertOne() {
        Graph graph = new Graph(10);
        Node node = new Node("a");
        graph.insertNode(node);
        assertEquals(graph.getNode("a"), node);
    }


    /**
     * Tests expansion again.
     */
    public void testInsertNodes3() {
        Graph graph = new Graph(10000);
        Node[] nodes = { new Node("a"), new Node("b"), new Node("c"), new Node(
            "d") };
        for (Node node : nodes) {
            graph.insertNode(node);
        }
        assertEquals(graph.getGraphLength(), 10000);
    }


    /**
     * Tests expansion for the graph again.
     */
    public void testInsertNodes4() {
        Graph graph = new Graph(4);
        Node[] nodes = { new Node("a"), new Node("b"), new Node("c"), new Node(
            "d"), new Node("e") };
        for (Node node : nodes) {
            graph.insertNode(node);
        }
        assertEquals(graph.getGraphLength(), 8);
    }


    /**
     * tests expansion again.
     */
    public void testInsertNodes5() {
        Graph graph = new Graph(2);
        Node[] nodes = { new Node("a"), new Node("b"), new Node("c"), new Node(
            "d") };
        for (Node node : nodes) {
            graph.insertNode(node);
        }
        assertEquals(graph.getGraphLength(), 4);
    }


    /**
     * tests expansion again.
     */
    public void testInsertNodes6() {
        Graph graph = new Graph(2);
        Node[] nodes = { new Node("a"), new Node("b"), new Node("c"), new Node(
            "d"), new Node("e") };
        for (Node node : nodes) {
            graph.insertNode(node);
        }
        assertEquals(graph.getGraphLength(), 8);
    }


    /**
     * tests insert for expansion again.
     */
    public void testInsertNodes7() {
        Graph graph = new Graph(3); //
        Node[] nodes = { new Node("a"), new Node("b"), new Node("c") };
        for (Node node : nodes) {
            graph.insertNode(node);
        }
        Node extraNode = new Node("d");
        graph.insertNode(extraNode);
        assertEquals(graph.getGraphLength(), 6);

    }


    /**
     * tests graph easy insert for single components.
     */
    public void testGraphEasyInsert() {

        WorldDataBase db = new WorldDataBase(10);

        db.insert("a", "b", "a<SEP>b");

        assertEquals(db.getGraph().computeComponents(), 1);

        // db.insert("c", "b", "c<SEP>b");

        assertEquals(db.getGraph().computeComponents(), 1);

        assertEquals(db.getGraph().largestComponent(), 2);

        assertTrue(db.getGraph().edgeExists("a", "b"));

        assertEquals(db.getGraph().floyd(), 1);

    }


    /**
     * tests simple delete for a single insert.
     * Ensures the proper number of components
     * and elements in the largest component.
     * No diameter testing.
     */
    public void testDelete() {
        WorldDataBase db = new WorldDataBase(10);
        db.insert("a", "b", "a<SEP>b");
        assertTrue(db.getGraph().edgeExists("a", "b"));

        db.removeSong("b");

        assertFalse(db.getGraph().edgeExists("a", "b"));

        assertTrue(db.getGraph().contains("a"));
        db.getGraph().remove("b");
        db.getGraph().remove("a");

        assertTrue(db.getGraph().isEmptyGraph());

    }


    /**
     * Tests potential null pointer exceptions
     * for null node entries.
     */
    public void testNullPointer() {
        WorldDataBase db = new WorldDataBase(10);
        db.insert("a", "b", "a<SEP>b");
        db.removeArtist(null);

        db.removeArtist("abbb");

        db.getGraph().remove("abbbb");

        Node node = new Node("abbb");
        db.getGraph().remove(node.getName());
        db.getGraph().remove(null);
        db.getGraph().remove("llll");

        assertTrue(db.getGraph().edgeExists("a", "b"));
        assertEquals(db.getGraph().floyd(), 1);
        db.getGraph().remove("a");
        assertEquals(db.getGraph().floyd(), 0);
        assertEquals(db.getGraph().computeComponents(), 1);
        assertTrue(db.getGraph().contains("b"));
        assertEquals(db.getGraph().getNumNodes(), 1);

        db.getGraph().remove("a");

        assertTrue(db.getGraph().isEmptyGraph());
        db.getGraph().remove(null);

    }


    /**
     * This method tests the insert method
     * when inserting things into the graph.
     */
    public void testInsertHashAndGraph() {
        WorldDataBase db = new WorldDataBase(4);

        db.insert("a", "b", "a<SEP>b");
        db.insert("b", "c", "b<SEP>c");

        assertEquals(db.getGraph().getGraphLength(), 4);

        db.insert("e", "f", "e<SEP>f");

        assertEquals(db.getGraph().getGraphLength(), 8);

    }


    /**
     * This tests the delete method when
     * the records aren't found and can't
     * be deleted.
     */
    public void testDeleteNoRecords() {
        WorldDataBase db = new WorldDataBase(10);
        String artist = "a";
        String song = "b";
        String both = "a<SEP>b";

        db.removeArtist("g");
        db.removeSong("g");

        db.getGraph().printGraph();

        db.getGraph().insertNode(new Node("a"));

        db.printGraph();
        assertEquals(db.getGraph().getNumNodes(), 1);
    }


    /**
     * This tests the insert method when
     * we insert things into the world.
     */
    public void testWorldInsert() {
        WorldDataBase db = new WorldDataBase(10);

        String artist = "a";
        String song = "b";
        String both = "a<SEP>b";
        System.out.println("--------------------");
        db.insert(artist, song, both);
        assertTrue(db.getGraph().edgeExists("a", "b"));
        db.printGraph();
        db.insert(artist, song, both);
        assertTrue(db.getGraph().contains(artist));
        assertTrue(db.getGraph().contains(song));
        assertTrue(db.getGraph().edgeExists("a", "b"));

    }
}
