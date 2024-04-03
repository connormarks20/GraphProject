import java.util.LinkedList;

/**
 * Represents the Parent Pointer Tree
 * used for the Union / Find method
 * in representing and computing the
 * connected components of the graph.
 * 
 * @author Connor Marks (connorm20)
 * @version 11.28.2023
 * 
 */
public class ParPtrTree {
    /**
     * array
     */
    private int[] array;
    /**
     * weights
     */
    private int[] weights;

    /**
     * Contructs the ParPtrTree
     * 
     * @param size
     *            for the size of the tree.
     */
    public ParPtrTree(int size) {
        array = new int[size];
        weights = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = i;
            weights[i] = 1;
        }
    }


    /**
     * Resets the tree to its initial conditions.
     * 
     * @param size
     *            for the size to be reset to.
     */
    public void reset(int size) {
        array = new int[size];
        weights = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = i;
            weights[i] = 1;
        }
    }


    /**
     * Rebuilds the graph and relevant
     * components after a node/edge removal.
     * 
     * @param graph
     *            for the graph to be rebuilt.
     */
    public void rebuild(Graph graph) {
        reset(graph.getGraphLength());

        Node[] nodes = graph.getNodes();
        for (Node node : nodes) {
            if (node != null) {
                LinkedList<Node> edges = node.getEdges();
                int nodeIndex = graph.getNodeIndex(node);

                if (edges != null && !edges.isEmpty()) {
                    for (Node edge : edges) {
                        int edgeIndex = graph.getNodeIndex(edge);
                        union(nodeIndex, edgeIndex);
                    }
                }
            }
        }
    }


    /**
     * Unifies graph components.
     * 
     * @param a
     *            for root at array location a.
     * @param b
     *            for root at array location b.
     */
    public void union(int a, int b) {
        int root1 = find(a);
        int root2 = find(b);

        if (weights[root1] == 0 || weights[root2] == 0) {
            return;
        }

        if (root1 != root2) {
            if (weights[root2] > weights[root1]) {
                array[root1] = root2;
                weights[root2] += weights[root1];
            }
            else {
                array[root2] = root1;
                weights[root1] += weights[root2];
            }
        }
    }


    /**
     * Finds root node.
     * 
     * @param curr
     *            for the current position.
     * @return the array at that position.
     */
    public int find(int curr) {
        if (array[curr] != curr) {
            array[curr] = find(array[curr]);
        }
        return array[curr];
    }


    /**
     * Gets the ParPtrTree Array.
     * 
     * @return the parptrtree array.
     */
    public int[] getArray() {
        return array;
    }


    /**
     * Gets the weights array.
     * 
     * @return the weights array.
     */
    public int[] getWeights() {
        return weights;
    }

}
