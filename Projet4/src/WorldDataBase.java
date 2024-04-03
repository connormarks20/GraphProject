/**
 * WorldDataBase Class responsible for
 * mediating the interaction between
 * the HashTable and Graph classes to
 * ensure encapsulation.
 * 
 * @author Connor Marks (connorm20)
 * @version 11.13.2023
 */
public class WorldDataBase {
    /**
     * fields for the different hashtables.
     */
    private Hash artistTable;
    private Hash songTable;
    private Graph graph;

    /**
     * Constructor for WorldDataBase.
     * Creates the artist and song tables
     * as well as the adjacency list of edges.
     * 
     * @param size
     *            for the size of the hashtable(s).
     */
    public WorldDataBase(int size) {

        this.graph = new Graph(size);
        this.artistTable = new Hash(size, graph);
        this.songTable = new Hash(size, graph);
    }


    /**
     * prints the artist table.
     */
    public void printArtistTable() {

        artistTable.printArtists();

    }


    /**
     * Prints the song table.
     */
    public void printSongTable() {

        songTable.printSongs();
    }


    /**
     * Removes a song from the song table.
     * 
     * @param song
     *            for the song to be removed.
     */
    public void removeSong(String song) {
        songTable.removeSong(song);
        graph.remove(song);
    }


    /**
     * Removes an artist from the artist table.
     * 
     * @param artist
     *            for the artist to be removed.
     */
    public void removeArtist(String artist) {
        artistTable.removeArtist(artist);
        graph.remove(artist);
    }


    /**
     * Inserts an artist and song into the hashtable as well
     * as nodes in the graph. Adds an edge between the two nodes.
     * 
     * @param artist
     *            for the artist to be inserted.
     * @param song
     *            for the song to be inserted.
     * @param artistAndSong
     *            for the full Artist<SEP>Song entry.
     */
    public void insert(String artist, String song, String artistAndSong) {
        if (graph.contains(artist) && graph.contains(song) && graph.edgeExists(
            artist, song)) {
            System.out.println("|" + artistAndSong
                + "| duplicates a record already in the database.");
            return;
        }

        Node artistNode = graph.getNode(artist);
        Node songNode = graph.getNode(song);

        if (artistNode == null) {
            artistNode = new Node(artist);
            graph.insertNode(artistNode);
            artistTable.insertArtist(artist, artistAndSong, artistNode);
        }

        if (songNode == null) {
            songNode = new Node(song);
            graph.insertNode(songNode);
            songTable.insertSong(song, artistAndSong, songNode);
        }

        graph.addEdge(artistNode, songNode);

    }


    /**
     * Prints the graph.
     * # Connected Components,
     * # Elements in largest connected component,
     * Diameter of largest connected component.
     */
    public void printGraph() {
        graph.printGraph();
    }


    /**
     * Gets the song hash table.
     * 
     * @return the song hash table.
     */
    public Hash getSongTable() {
        return songTable;
    }


    /**
     * Gets the artist hash table.
     * 
     * @return the artist hash table.
     */
    public Hash getArtistTable() {
        return artistTable;
    }


    /**
     * Gets the graph.
     * 
     * @return the graph.
     */
    public Graph getGraph() {
        return graph;
    }

}
