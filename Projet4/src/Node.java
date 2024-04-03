import java.util.LinkedList;

/**
 * @author Connor Marks (connorm20)
 * @version 11.24.2023
 */
public class Node {
    /**
     * Fields for the name of the node.
     * and the adjacency list consisting of edges.
     */
    private String name;
    private LinkedList<Node> edges;

    /**
     * Constructor for the node.
     * 
     * @param name
     *            for the name of the artist or song
     *            the node will hold.
     */
    public Node(String name) {
        this.name = name;
        this.edges = new LinkedList<>();
    }


    /**
     * Gets the associated artist or song name
     * from the node.
     * 
     * @return the song name or artist name.
     */
    public String getName() {

        return name;
    }


    /**
     * Gets the adjacency list consisting of edges
     * for the nodes.
     * 
     * @return the adjacency list which represents
     *         the edges connecting nodes in the nodes array.
     */
    public LinkedList<Node> getEdges() {
        return edges;
    }


    /**
     * Adds an edge to the adjacency list
     * LinkedList.
     * 
     * @param node
     *            for the node to be added.
     */
    public void addEdge(Node node) {
        edges.add(node);
    }


    /**
     * Removes an edge between two nodes
     * in the adjacency list.
     * 
     * @param node
     *            for the node to be removed.
     */
    public void removeEdge(Node node) {
        edges.remove(node);
    }
}
