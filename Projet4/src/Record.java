/**
 * Record representing the Key,Value
 * pairs of the hashtable.
 * 
 * @author Connor Marks (connorm20)
 * @version 11.28.2023
 * @param <String>
 *            for the artist or song name.
 * @param <Node>
 *            for the associated graph node.
 */
public class Record<String, Node> {

    private String key;
    private Node value;
    //private Record<String, Node> next;

    /**
     * Constructor for record.
     * 
     * @param key
     *            representing artist/song name.
     * @param value
     *            representing associated node value.
     */
    public Record(String key, Node value) {
        this.key = key;
        this.value = value;
    }


    /**
     * Gets the artist/song name as key.
     * 
     * @return the key.
     */
    public String getKey() {
        return key;
    }


    /**
     * Gets the node associated with the key.
     * 
     * @return the value of the node.
     */
    public Node getValue() {
        return value;
    }


    /**
     * Sets the value of a node.
     * 
     * @param value
     *            for the node to be set.
     */
    public void setValue(Node value) {
        this.value = value;
    }

}
