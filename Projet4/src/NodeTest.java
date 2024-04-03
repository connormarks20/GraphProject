
import student.TestCase;

/**
 * Tests the node class.
 * 
 * @author Connor Marks (connorm20)
 * @version 11.28.2023
 */
public class NodeTest extends TestCase {

    /**
     * Empty setup.
     */
    public void setUp() {
        // nothing to do.
    }


    /**
     * Tests the getName() method for the node class.
     */
    public void testGetName() {
        Node node = new Node("a");

        assertEquals(node.getName(), "a");

        Node node2 = new Node("");
        assertEquals(node2.getName(), "");
    }


    /**
     * Tests the getEdges method.
     * Ensures that the adjacency list is properly
     * constructed.
     */
    public void testGetEdges() {
        Node node = new Node("a");

        node.addEdge(node);
        assertEquals(node.getEdges().get(0), node);

        node.removeEdge(node);
        assertTrue(node.getEdges().isEmpty());
    }

}
