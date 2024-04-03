import student.TestCase;

/**
 * @author Connor Marks (connorm20)
 * @version 11.28.2023
 */
public class HashTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing Here
    }


    /**
     * Tests the insertSong method in the hash class.
     */
    public void testInsert() {

        Graph graph1 = new Graph(10);
        // create a new song hashtable of size 10.
        Hash songTable = new Hash(10, graph1);
        String key = "key";

        songTable.insertSong("a", "ab", new Node(key));
        assertEquals(systemOut().getHistory(),
            "|a| is added to the Song database.\r\n" + "");
        assertEquals(songTable.getSongCount(), 1);

        assertTrue(songTable.search1("a"));
        assertEquals(songTable.getTable()[7].getKey().toString(), "a");
        songTable.insertSong("a", "ab", new Node(key));
        assertNull(songTable.getTable()[8]);
        songTable.printSongs();
        assertEquals(songTable.getTable().length, 10);

        Graph graph2 = new Graph(6);

        Hash secondTable = new Hash(6, graph2);

        secondTable.insertSong("a", "ab", new Node(key));
        secondTable.insertSong("b", "bc", new Node(key));
        assertEquals(secondTable.getTable().length, 6);
        assertEquals(secondTable.getSongCount(), 2);
        secondTable.insertSong("c", "cd", new Node(key));
        assertEquals(secondTable.getSongCount(), 3);
        secondTable.insertSong("d", "de", new Node(key));
        assertEquals(secondTable.getTable().length, 12);
        systemOut().clearHistory();
        Graph graph3 = new Graph(10);
        Hash artistTable = new Hash(10, graph3);
        artistTable.insertArtist("a", "ab", new Node(key));

        assertEquals(systemOut().getHistory(),
            "|a| is added to the Artist database.\r\n" + "");
        assertEquals(artistTable.getArtistCount(), 1);

        assertTrue(artistTable.search1("a"));
        assertEquals(artistTable.getTable()[7].getKey().toString(), "a");
        artistTable.insertArtist("a", "ab", new Node(key));

        artistTable.printArtists();
        assertEquals(artistTable.getTable().length, 10);

        systemOut().clearHistory();

        Hash secondArtist = new Hash(6, graph2);

        secondArtist.insertArtist("a", "ab", new Node(key));
        secondArtist.insertArtist("b", "bc", new Node(key));
        assertEquals(secondArtist.getTable().length, 6);
        assertEquals(secondArtist.getArtistCount(), 2);
        secondArtist.insertArtist("c", "cd", new Node(key));
        assertEquals(secondArtist.getArtistCount(), 3);
        secondArtist.insertArtist("d", "de", new Node(key));
        assertEquals(systemOut().getHistory(),
            "|a| is added to the Artist database.\r\n"
                + "|b| is added to the Artist database.\r\n"
                + "|c| is added to the Artist database.\r\n"
                + "Artist hash table size doubled.\r\n"
                + "|d| is added to the Artist database.\r\n" + "");
        assertEquals(secondArtist.getTable().length, 12);
        systemOut().clearHistory();

    }


    /**
     * Tests remove for artist and song tables.
     */
    public void testRemove() {
        Graph graph = new Graph(10);
        Hash artists = new Hash(10, graph);
        String key = "key";
        assertEquals(artists.getArtistCount(), 0);
        artists.removeArtist("a");
        assertEquals(systemOut().getHistory(),
            "|a| does not exist in the Artist database.\n");
        artists.insertArtist("a", "ab", new Node(key));
        assertEquals(artists.getTable()[7].getKey(), "a");
        systemOut().clearHistory();
        assertEquals(artists.getArtistCount(), 1);
        artists.removeArtist("a");
        assertEquals(artists.getArtistCount(), 0);
        assertEquals(systemOut().getHistory(),
            "|a| is removed from the Artist database.\r\n" + "");

        assertNotNull(artists.getTable()[7]);
        assertEquals(artists.getTable()[7].getKey(), "TOMBSTONE");
        artists.insertArtist("a", getName(), new Node(key));
        assertEquals(artists.getTable()[7].getKey(), "a");

        systemOut().clearHistory();
        Hash songs = new Hash(10, graph);

        assertEquals(songs.getSongCount(), 0);
        songs.removeSong("a");
        assertEquals(systemOut().getHistory(),
            "|a| does not exist in the Song database.\n");
        songs.insertSong("a", "ab", new Node(key));
        assertEquals(songs.getTable()[7].getKey(), "a");
        systemOut().clearHistory();
        assertEquals(songs.getSongCount(), 1);
        songs.removeSong("a");
        assertEquals(songs.getSongCount(), 0);
        assertEquals(systemOut().getHistory(),
            "|a| is removed from the Song database.\r\n" + "");

        assertNotNull(songs.getTable()[7].getKey());
        assertEquals(songs.getTable()[7].getKey(), "TOMBSTONE");
        songs.insertSong("a", getName(), new Node(key));
        assertEquals(songs.getTable()[7].getKey(), "a");

    }


    /**
     * Tests the expand method with a few inserts.
     * Triggers the expansion.
     */
    public void testExpand() {
        Graph graph = new Graph(5);
        Hash hashTable = new Hash(5, graph);
        String key = "key";
        hashTable.insertSong("a", "ab", new Node(key));
        hashTable.insertSong("b", "bc", new Node(key));
        hashTable.insertSong("c", "cd", new Node(key));
        hashTable.insertSong("d", "de", new Node(key));
        hashTable.insertSong("e", "ef", new Node(key));

        assertEquals(hashTable.getTable().length, 10);

        assertTrue(hashTable.search1("a"));
        assertTrue(hashTable.search1("b"));
        assertTrue(hashTable.search1("c"));
        assertTrue(hashTable.search1("d"));
        assertTrue(hashTable.search1("e"));
    }


    /**
     * Tests expand when we insert enough elements
     * to trigger the expansion.
     */
    public void testExpandInsert() {
        Graph graph = new Graph(5);
        Hash hashTable = new Hash(5, graph);
        String key = "key";
        hashTable.insertSong("a", "ab", new Node(key));
        hashTable.insertSong("b", "bc", new Node(key));

        assertEquals(hashTable.getTable().length, 5);

        hashTable.insertSong("c", "cd", new Node(key));

        assertEquals(hashTable.getTable().length, 10);
    }


    /**
     * Tests the expand method when we insert elements
     * but it does not expand.
     */
    public void testNoExpand() {
        Graph graph = new Graph(10);
        Hash hashTable = new Hash(10, graph);
        String key = "key";
        assertEquals(hashTable.getTable().length, 10);

        hashTable.insertSong("a", "ab", new Node(key));
        hashTable.insertSong("b", "bc", new Node(key));

        assertEquals(hashTable.getTable().length, 10);
    }


    /**
     * Tests the remove method again.
     */
    public void testRemove2() {
        Graph graph = new Graph(10);
        String key = "key";
        Hash artistTable = new Hash(10, graph);
        artistTable.insertArtist("a", "ab", new Node(key));

        systemOut().clearHistory();
        artistTable.removeArtist("a");
        assertEquals(artistTable.getArtistCount(), 0);
        assertEquals(systemOut().getHistory(),
            "|a| is removed from the Artist database.\r\n");
    }


    /**
     * Tests nonexistent entry in the artist table.
     */
    public void testNonExistent() {
        Graph graph = new Graph(10);
        Hash artistTable = new Hash(10, graph);

        systemOut().clearHistory();
        artistTable.removeArtist("a");
        assertEquals(artistTable.getArtistCount(), 0);
        assertEquals(systemOut().getHistory(),
            "|a| does not exist in the Artist database.\n");
    }


    /**
     * Tests single insert single remove.
     */
    public void testRemove3() {
        Graph graph = new Graph(10);
        String key = "key";
        Hash songTable = new Hash(10, graph);
        songTable.insertSong("a", "ab", new Node(key));

        systemOut().clearHistory();
        songTable.removeSong("a");
        assertEquals(songTable.getSongCount(), 0);
        assertEquals(systemOut().getHistory(),
            "|a| is removed from the Song database.\r\n");
    }


    /**
     * Tests insert then remove.
     */
    public void testRemove4() {
        Graph graph = new Graph(10);
        Hash songTable = new Hash(10, graph);

        systemOut().clearHistory();
        songTable.removeSong("a");
        assertEquals(songTable.getSongCount(), 0);
        assertEquals(systemOut().getHistory(),
            "|a| does not exist in the Song database.\n");
    }


    /**
     * Removal test
     */
    public void testRemove5() {
        Graph graph = new Graph(10);
        Hash songTable = new Hash(10, graph);
        Hash artistTable = new Hash(10, graph);
        String key = "key";
        songTable.insertSong("a", getName(), new Node(key));
        artistTable.insertArtist("a", getName(), new Node(key));

        songTable.removeSong("a");
        artistTable.removeArtist("a");

        assertFalse(songTable.search1("a"));
        assertFalse(artistTable.search1("a"));

        songTable.printSongs();
        artistTable.printArtists();

        assertEquals(systemOut().getHistory(),
            "|a| is added to the Song database.\r\n"
                + "|a| is added to the Artist database.\r\n"
                + "|a| is removed from the Song database.\r\n"
                + "|a| is removed from the Artist database.\r\n"
                + "7: TOMBSTONE\r\n" + "total songs: 0\r\n" + "7: TOMBSTONE\r\n"
                + "total artists: 0\r\n" + "");

    }


    /**
     * Insert/remove with tombstones.
     */
    public void testInsRem() {
        Graph graph = new Graph(10);
        String key = "key";
        Hash hashTable = new Hash(10, graph);
        hashTable.insertSong("a", getName(), new Node(key));
        hashTable.insertSong("b", getName(), new Node(key));
        hashTable.insertSong("c", getName(), new Node(key));
        hashTable.insertSong("d", getName(), new Node(key));
        hashTable.removeSong("a");
        assertEquals(hashTable.getTable()[7].getKey(), "TOMBSTONE");
        hashTable.insertSong("e", getName(), new Node(key));
        assertEquals(hashTable.getTable()[7].getKey(), "TOMBSTONE");

        hashTable.insertSong("f", getName(), new Node(key));
        assertEquals(hashTable.getTable().length, 10);
        assertEquals(hashTable.getTable()[7].getKey(), "TOMBSTONE");
        hashTable.insertSong("g", getName(), new Node(key));
        assertEquals(hashTable.getTable().length, 20);
        assertEquals(hashTable.search1("TOMBSTONE"), false);

    }


    /**
     * Insert/remove with tombstones 2.
     */
    public void testRemove7() {
        Graph graph = new Graph(10);
        Hash hashTable = new Hash(10, graph);
        String key = "key";
        hashTable.insertSong("a", getName(), new Node(key));
        hashTable.insertSong("b", getName(), new Node(key));
        hashTable.insertSong("c", getName(), new Node(key));
        hashTable.insertSong("d", getName(), new Node(key));

        hashTable.removeSong("a");
        assertEquals(hashTable.getTable()[7].getKey(), "TOMBSTONE");

        hashTable.insertSong("e", getName(), new Node(key));
        hashTable.insertSong("f", getName(), new Node(key));
        hashTable.insertSong("g", getName(), new Node(key));

        assertEquals(hashTable.getTable().length, 20);
        assertFalse(hashTable.search1("TOMBSTONE"));
    }


    /**
     * Tests insert, length, and search.
     */
    public void testInsert2() {
        Graph graph = new Graph(10);
        Hash hashTable = new Hash(10, graph);
        String key = "key";
        hashTable.insertSong("a", "ab", new Node(key));
        hashTable.insertSong("b", "bc", new Node(key));
        hashTable.insertSong("c", "cd", new Node(key));
        hashTable.insertSong("d", "de", new Node(key));
        hashTable.insertSong("e", "ef", new Node(key));

        assertEquals(hashTable.getTable().length, 10);

        hashTable.insertSong("f", "fg", new Node(key));

        assertEquals(hashTable.getTable().length, 20);
        assertTrue(hashTable.search1("f"));
    }


    /**
     * Tests table expansion.
     */
    public void testExpand2() {
        Graph graph = new Graph(5);
        Hash hashTable = new Hash(5, graph);
        String key = "key";
        hashTable.insertSong("a", "ab", new Node(key));
        hashTable.insertSong("b", "bc", new Node(key));
        hashTable.insertSong("c", "cd", new Node(key));
        hashTable.insertSong("d", "de", new Node(key));
        hashTable.insertSong("e", "ef", new Node(key));

        assertEquals(hashTable.getTable().length, 10);

        hashTable.insertSong("f", "fg", new Node(key));
        hashTable.insertSong("g", "gh", new Node(key));
        hashTable.insertSong("z", "zg", new Node(key));
        hashTable.insertSong("y", "yh", new Node(key));
        hashTable.insertSong("x", "xg", new Node(key));
        hashTable.insertSong("w", "wh", new Node(key));

        assertEquals(hashTable.getTable().length, 40);
        assertTrue(hashTable.search1("f"));
        assertTrue(hashTable.search1("g"));
    }


    /**
     * Tests table expansion with entry removal.
     */
    public void testExpand3() {
        Graph graph = new Graph(5);
        Hash hashTable = new Hash(10, graph);
        String key = "key";
        hashTable.insertSong("a", "ab", new Node(key));
        hashTable.insertSong("b", "bc", new Node(key));
        hashTable.insertSong("c", "cd", new Node(key));
        hashTable.insertSong("d", "de", new Node(key));
        hashTable.insertSong("e", "ef", new Node(key));
        hashTable.removeSong("a");
        hashTable.removeSong("b");
        hashTable.removeSong("c");
        hashTable.removeSong("d");
        hashTable.removeSong("ef");

        assertEquals(hashTable.getTable().length, 10);

        /// insert when all elements are tombstones
        hashTable.insertSong("e", "ef", new Node(key));

        assertEquals(hashTable.getTable().length, 10);
        assertTrue(hashTable.search1("e"));
    }


    /**
     * Tests song table inserts then expansion.
     */
    public void testExpand4() {
        Graph graph = new Graph(5);
        Hash hashTable = new Hash(5, graph);
        String key = "key";
        hashTable.insertSong("a", "ab", new Node(key));
        hashTable.insertSong("b", "bc", new Node(key));
        hashTable.insertSong("c", "cd", new Node(key));
        hashTable.insertSong("d", "de", new Node(key));

        assertEquals(hashTable.getTable().length, 10);

        hashTable.insertSong("e", "ef", new Node(key));

        assertEquals(hashTable.getTable().length, 10);
        assertTrue(hashTable.search1("e"));
    }


    /**
     * Tests remove and valid tombstone.
     */
    public void testRemove6() {
        String key = "key";
        Graph graph = new Graph(10);
        Hash artistTable = new Hash(10, graph);

        artistTable.insertArtist("a", "ab", new Node(key));
        artistTable.removeArtist("a");

        assertFalse(artistTable.search1("a"));
        assertEquals(artistTable.getTable()[7].getKey(), "TOMBSTONE");
        assertEquals(graph.getNumNodes(), 0);
    }


    /**
     * Tests quadratic probing.
     */
    public void testProbing() {
        Graph graph = new Graph(10);
        Hash artistTable = new Hash(10, graph);
        String key = "key";
        artistTable.insertArtist("a", "ab", new Node(key));
        artistTable.insertArtist("k", "kl", new Node(key));

        artistTable.removeArtist("k");

        assertFalse(artistTable.search1("k"));
        assertEquals(artistTable.getTable()[8].getKey(), "TOMBSTONE");
        assertEquals(graph.getNumNodes(), 0);
    }


    /**
     * Tests null removal.
     */
    public void testHashRemoveNull() {
        Graph g = new Graph(10);
        Hash artistTable = new Hash(10, g);

        artistTable.removeArtist(null);
        artistTable.removeArtist("TOMBSTONE");
        assertEquals(artistTable.getArtistCount(), 0);
    }
    
    /**
     * This tests the remove method
     */
    public void testRemove10() 
    {
        Graph graph = new Graph(10);
        Hash songTable = new Hash(10, graph);
        Hash artistTable = new Hash(10, graph);
        
        songTable.insertSong("key", "artist", new Node("key"));
        assertEquals(songTable.getSongCount(), 1);
    }


    /**
     * Check out the sfold method
     *
     * @throws Exception
     *             either a IOException or FileNotFoundException
     */
    public void testSfold() throws Exception {
        assertEquals(Hash.h("a", 10), 7);
        assertEquals(Hash.h("b", 10), 8);
        assertEquals(Hash.h("a", 10000), 97);
        assertEquals(Hash.h("b", 10000), 98);
        assertEquals(Hash.h("aaaa", 10000), 1873);
        assertEquals(Hash.h("aaab", 10000), 9089);
        assertEquals(Hash.h("baaa", 10000), 1874);
        assertEquals(Hash.h("aaaaaaa", 10000), 3794);
        assertEquals(Hash.h("Long Lonesome Blues", 10000), 4635);
        assertEquals(Hash.h("Long   Lonesome Blues", 10000), 4159);
        assertEquals(Hash.h("long Lonesome Blues", 10000), 4667);
    }

}
