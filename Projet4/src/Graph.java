
import java.util.LinkedList;

/**
 * Class responsible for representing the graph
 * structure obtained from the artist and song
 * hash tables. Nodes are represented using the
 * Node class and an adjacency list is used to
 * represent edges between nodes in the graph.
 * Implements the Union / Find method for computing
 * connected components, and Floyds algorithm for
 * computing the diameter of the largest connected
 * component of a given graph.
 * 
 * @author Connor Marks (connorm20)
 * @version 11.28.2023
 */
public class Graph {
    private Node[] nodes;
    private ParPtrTree unionFind;
    private int graphSize;
    private int numNodes;

    /**
     * Graph constructor
     * 
     * @param size
     *            for the size of the graph
     *            (max number of nodes)
     */
    public Graph(int size) {
        this.graphSize = size;
        this.nodes = new Node[size];
        this.unionFind = new ParPtrTree(size);
        this.numNodes = 0;
    }


    /**
     * Inserts a node into the graph. Expands
     * the capacity of the graph once the number
     * of the nodes exceeds half the capacity.
     * 
     * @param node
     *            for the node to be inserted.
     */
    public void insertNode(Node node) {

        // If the number of nodes reaches half capacity, expand.
        if (numNodes >= graphSize) {
            int newSize = graphSize * 2;
            graphSize = newSize;
            // Create the resized nodes array and copy back info.
            Node[] newNodes = new Node[newSize];

            System.arraycopy(nodes, 0, newNodes, 0, nodes.length);
            nodes = newNodes;

            // Rebuild the ParPtrTree for the new capacity.
            unionFind.reset(newSize);
            unionFind.rebuild(this);
        }

        // Simply loop through and insert into the first null
        // entry.
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i] == null) {
                nodes[i] = node;
                numNodes++;
                break;
            }
        }
    }


    /**
     * Adds an edge between two nodes.
     * 
     * @param node1
     *            for the first node
     * @param node2
     *            for the second node.
     */
    public void addEdge(Node node1, Node node2) {
        // As long as the two nodes arent null,
        // we can add an edge between them.
        if (node1 != null && node2 != null) {
            node1.addEdge(node2);
            node2.addEdge(node1);

            int index1 = getNodeIndex(node1);
            int index2 = getNodeIndex(node2);

            if (index1 != -1 && index2 != -1) {
                int root1 = unionFind.find(index1);
                int root2 = unionFind.find(index2);

                unionFind.union(root1, root2);
            }
        }
    }


    /**
     * Gets a node with the specified name from the graph.
     * 
     * @param name
     *            for either the artist name or song name
     *            associated with a particular node.
     * @return the node if it exists, null if it doesn't.
     */
    public Node getNode(String name) {
        for (Node node : nodes) {
            if (node != null && node.getName().equals(name)) {
                return node;
            }
        }
        return null;
    }


    /**
     * Prints the number of components,
     * the number of elements in the largest
     * connected component, and the diameter
     * of the largest connected component.
     */
    public void printGraph() {

        int numComponents = computeComponents();

        System.out.println("There are " + numComponents
            + " connected components");

        int largest = largestComponent();

        System.out.println("The largest connected component has " + largest
            + " elements");

        int diameter = floyd();

        System.out.println("The diameter of the largest component is "
            + diameter);

    }


    /**
     * Checks if the graph is empty.
     * 
     * @return true if it is empty, false if it's not.
     */
    public boolean isEmptyGraph() {
        for (Node node : nodes) {
            if (node != null && !node.getEdges().isEmpty()) {
                return false;
            }
        }
        return true;
    }


    /**
     * Computes the largest connected component in the graph.
     * 
     * @return the largest connected component.
     */
    public int largestComponent() {

        // Stores an array of all the component sizes.
        int[] componentSizes = new int[graphSize];
        int largest = 0;

        // Iterates through the graph and finds non-null nodes
        // and their union (the component they are a part of).
        for (int i = 0; i < graphSize; i++) {
            if (nodes[i] != null) {
                int root = unionFind.find(i);
                // Increment and store the result in the componentSizes array.
                componentSizes[root]++;
                // Update "largest" to the largest component size value stored
                // in the array.
                largest = Math.max(largest, componentSizes[root]);
            }
        }

        return largest;
    }


    /**
     * Finds the number of components in the graph.
     * 
     * @return the number of connected components.
     */
    public int computeComponents() {

        int components = 0;
        // Tracks the roots of connected components.
        boolean[] roots = new boolean[graphSize];

        // Iterates through all nodes, mark the root nodes,
        // update the component count.
        for (int i = 0; i < graphSize; i++) {
            if (nodes[i] != null) {
                int root = unionFind.find(i);
                if (!roots[root]) {
                    roots[root] = true;
                    components++;
                }
            }
        }

        return components;
    }


    /**
     * Gets the index of a specified node.
     * 
     * @param node
     *            for the node being searched for.
     * @return the index where that node is found.
     */
    public int getNodeIndex(Node node) {
        String nodeName = node.getName();
        for (int i = 0; i < graphSize; i++) {
            if (nodes[i] != null && nodes[i].getName().equals(nodeName)) {
                return i;
            }
        }
        return -1;
    }


    /**
     * Gets the node from graph
     * 
     * @param name
     *            for the name associated with the node.
     */
    public void remove(String name) {
        Node node = getNode(name);

        if (node != null) {
            removeNode(node);
            numNodes--;

        }
    }


    /**
     * Removes node itself from the graph.
     * 
     * @param node
     *            for the node to be removed.
     */
    public void removeNode(Node node) {
        // Finds the node, sets it to null.
        for (int i = 0; i < graphSize; i++) {
            if (nodes[i] == node) {
                nodes[i] = null;
                break;
            }
        }

        // For each node n in nodes, if the node matches
        // the one being removed, remove all edges connected
        // to that node.
        for (Node n : nodes) {
            if (n != null) {
                LinkedList<Node> edges = n.getEdges();
                for (int i = 0; i < edges.size(); i++) {
                    if (edges.get(i).equals(node)) {
                        edges.remove(i);
                        i--;
                    }
                }
            }
        }

        // Update the ParPtrTree with the new graph
        // resulting from the removal.
        unionFind.rebuild(this);

    }


    /**
     * Checks if a node is in the node array.
     * 
     * @param name
     *            for the name associated with the node.
     * @return true or false depending if it is in the array
     *         or not.
     */
    public boolean contains(String name) {
        for (Node node : nodes) {
            if (node != null && node.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if an edge exists between two nodes.
     * 
     * @param artistName
     *            for the name of the artist.
     * @param songName
     *            for the name of the song.
     * @return true if the edge exists, false if not.
     */
    public boolean edgeExists(String artistName, String songName) {
        // Gets the two nodes.
        Node artistNode = getNode(artistName);
        Node songNode = getNode(songName);

        // Searches the adjacency list for an edge.
        if (artistNode != null && songNode != null) {
            LinkedList<Node> artistEdges = artistNode.getEdges();
            for (Node edge : artistEdges) {
                // If a songNode exists in the list of edges
                // for the artist adjacency list, this is true.
                // Works the other way around as well.
                if (edge == songNode) {
                    return true;
                }
            }
        }
        return false;
    }


    /**
     * all-pairs shortest path floyd
     * implementation.
     * 
     * @return the diameter of the largest connected
     *         component.
     */
    public int floyd() {
        int diameter = 0;
        // Assigns nodeCount to # of nodes in largest
        // connected component.
        int nodeCount = largestComponent();

        int[][] dist = new int[nodeCount][nodeCount];

        //
        Node[] largestComponentNodes = new Node[nodeCount];
        int index = 0;

        // Gets the nodes belonging to the largest component.
        for (int i = 0; i < graphSize; i++) {
            if (nodes[i] != null && !containsNode(largestComponentNodes,
                nodes[i]) && index < dist.length) {

                largestComponentNodes[index++] = nodes[i];

            }
        }

        // Initializes the distance matrix based on existent edges
        // with a weight of 1.
        for (int i = 0; i < nodeCount; i++) {
            for (int j = 0; j < nodeCount; j++) {
                if (largestComponentNodes[i] != null
                    && largestComponentNodes[j] != null && edgeExists(
                        largestComponentNodes[i].getName(),
                        largestComponentNodes[j].getName())) {
                    // Edge exists, so weight is assigned to 1.
                    dist[i][j] = 1;
                }
                else {
                    // If the two nodes are the same, assign the value 0.
                    // Otherwise, there is no edge and max_value represents
                    // the weight of this edge.
                    dist[i][j] = (i == j) ? 0 : Integer.MAX_VALUE;
                }
            }
        }

        // Floyd shortest path algorithm.
        for (int k = 0; k < nodeCount; k++) {
            for (int i = 0; i < nodeCount; i++) {
                for (int j = 0; j < nodeCount; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE
                        && dist[k][j] != Integer.MAX_VALUE) {
                        // Updates the shortest path.
                        dist[i][j] = Math.min(dist[i][j], dist[i][k]
                            + dist[k][j]);
                        // Updates the diameter if a higher-valued
                        // path is found.
                        diameter = Math.max(diameter, dist[i][j]);
                    }
                }
            }
        }

        return diameter;
    }


    /**
     * Helper method to determine if a node is present in the nodes array.
     * 
     * @param nodesArray
     *            for the array of nodes in the graph.
     * @param node
     *            for the node being searched for.
     * @return true if it is contained in the graph, false if not.
     */
    private boolean containsNode(Node[] nodesArray, Node node) {
        for (Node n : nodesArray) {
            if (n != null && n.equals(node)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Public helper method to retrieve the number of nodes in the graph.
     * 
     * @return the number of nodes in the graph.
     */
    public int getNumNodes() {
        return numNodes;
    }


    /**
     * Returns the graph size.
     * 
     * @return the size of the graph.
     */
    public int getGraphLength() {
        return graphSize;
    }


    /**
     * Gets the array representation of nodes in the graph.
     * 
     * @return the nodes array.
     */
    public Node[] getNodes() {
        return this.nodes;
    }

}
