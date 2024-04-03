/**
 * Hash table class used to insert
 * and remove songs and artists
 * into their respective hashtables.
 *
 * @author Connor Marks (connorm20)
 * @version 12.6.2023
 */

public class Hash {
    /**
     * Field variables for the hash table.
     */
    private int hashSize;
    private Record<String, Node>[] table;
    private int artistCount;
    private int songCount;

    /**
     * Constructor for the Hashtable
     * in progress. So far, takes in the size
     * from an input file that was parsed in the
     * commandprocessor and assigns the size.
     * 
     * @param graph
     *            for the graph associated with
     *            the hash table.
     * @param size
     *            for the size of a given hashtable.
     */
    public Hash(int size, Graph graph) {
        this.hashSize = size;
        this.table = new Record[size];
        this.songCount = 0;
        this.artistCount = 0;
    }


    /**
     * Inserts a song into the song hash table.
     * 
     * @param key
     *            for the song as the key.
     * @param artAndSong
     *            for the string representing both
     *            the artist and the song.
     * @param value
     *            is the node to insert
     */
    public void insertSong(String key, String artAndSong, Node value) {
        if (table.length / 2 <= songCount) {
            expand();

            System.out.println("Song hash table size doubled.");
        }
        int hashValue = h(key, hashSize);
        int originalHash = hashValue;
        int probe = 1;

        while (table[hashValue] != null && !table[hashValue].getKey().equals(
            "TOMBSTONE")) {
            hashValue = (originalHash + (probe * probe)) % hashSize;
            probe++;
        }

        if (search1(key)) {
            return;
        }

        // Create new record for the song
        Record<String, Node> songRecord = new Record<>(key, value);

        // Set the record in the hash table
        table[hashValue] = songRecord;
        System.out.println("|" + key + "|" + " is added to the Song database.");
        songCount++;
    }


    /**
     * Inserts an artist into the artists hashtable.
     * 
     * @param key
     *            for the artist being inserted.
     * @param artAndSong
     *            for the artist and song together.
     * @param value
     *            is the node to insert.
     */
    public void insertArtist(String key, String artAndSong, Node value) {
        if (table.length / 2 <= artistCount) {
            expand();

            System.out.println("Artist hash table size doubled.");
        }
        int hashValue = h(key, hashSize);
        int originalHash = hashValue;
        int probe = 1;

        while (table[hashValue] != null && !table[hashValue].getKey().equals(
            "TOMBSTONE")) {
            hashValue = (originalHash + (probe * probe)) % hashSize;
            probe++;
        }

        if (search1(key)) {
            return;
        }

        // Create new record for the artist
        Record<String, Node> artistRecord = new Record<>(key, value);

        // Set the record in the hash table
        table[hashValue] = artistRecord;

        System.out.println("|" + key + "| is added to the Artist database.");
        artistCount++;
    }


    /**
     * Expands the hashtable when half the capacity is reached.
     */
    public void expand() {
        int newSize = table.length * 2;
        Record<String, Node>[] newTable = new Record[newSize];

        for (int i = 0; i < table.length; i++) {
            Record<String, Node> record = table[i];
            if (record != null && record.getKey() != null && !record.getKey()
                .equals("TOMBSTONE")) {
                int newHash = h(record.getKey(), newSize);
                int probe = 1;
                while (newTable[newHash] != null && newTable[newHash]
                    .getKey() != null) {
                    newHash = (newHash + (probe * probe)) % newSize;
                    probe++;
                }

                record.getKey();
                newTable[newHash] = record;

            }
        }

        table = newTable;
        hashSize = newSize;

    }


    /**
     * Searches the hash table for duplicate entries.
     * 
     * @param key
     *            for the key being searched for
     * @return true if it's found, false if not.
     */
    /**
     * Searches the hash table for duplicate entries, including tombstones.
     * 
     * @param key
     *            for the key being searched for
     * @return true if it's found, false if not.
     */
    public boolean search1(String key) {
        for (int i = 0; i < table.length; i++) {
            if (this.table[i] != null && this.table[i].getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }


    /**
     * 0: |Blind Lemon Jefferson|
     * 7: |Ma Rainey|
     */
    /**
     * Prints the contents of the song hashtable.
     */
    public void printSongs() {
        for (int i = 0; i < hashSize; i++) {
            if (table[i] != null) {
                if (!table[i].getKey().equals("TOMBSTONE")) {
                    System.out.println(i + ": " + "|" + table[i].getKey()
                        + "|");
                }
                else {
                    System.out.println(i + ": " + table[i].getKey());
                }
            }
        }
        System.out.println("total songs: " + songCount);
    }


    /**
     * Prints the contents of the artist hashtable.
     */
    public void printArtists() {
        for (int i = 0; i < hashSize; i++) {
            if (table[i] != null) {
                if (!table[i].getKey().equals("TOMBSTONE")) {
                    System.out.println(i + ": " + "|" + table[i].getKey()
                        + "|");
                }
                else {
                    System.out.println(i + ": " + table[i].getKey());
                }
            }
        }
        System.out.println("total artists: " + artistCount);
    }


    /**
     * Removes a given artist from the artist table.
     * 
     * @param key
     *            for the artist to be removed.
     */
    public void removeArtist(String key) {
        if (key == null) {
            return;
        }
        int hashValue = h(key, hashSize);
        int originalHash = hashValue;
        int probe = 1;

        while (table[hashValue] != null && !table[hashValue].getKey().equals(
            key)) {
            // Quadratic probing
            hashValue = (originalHash + (probe * probe)) % hashSize;
            probe++;
        }

        if (table[hashValue] != null && table[hashValue].getKey().equals(key)) {
            // Set the record as a tombstone
            Record<String, Node> tombstone = new Record<>("TOMBSTONE", null);
            table[hashValue] = tombstone;
            System.out.println("|" + key
                + "| is removed from the Artist database.");
            artistCount--;
        }
        else {
            System.out.println("|" + key
                + "| does not exist in the Artist database.");
        }
    }


    /**
     * Removes a song from the song hash table.
     * 
     * @param key
     *            for the song string to be removed.
     */
    public void removeSong(String key) {
        if (key == null) {
            return;
        }
        int hashValue = h(key, hashSize);
        int originalHash = hashValue;
        int probe = 1;

        while (table[hashValue] != null && !table[hashValue].getKey().equals(
            key)) {
            // Quadratic probing
            hashValue = (originalHash + (probe * probe)) % hashSize;
            probe++;
        }

        if (table[hashValue] != null && table[hashValue].getKey().equals(key)) {
            // Deletes the song from the hashtable.
            table[hashValue] = new Record<>("TOMBSTONE", null);
            System.out.println("|" + key
                + "| is removed from the Song database.");
            songCount--;
        }
        else {
            System.out.println("|" + key
                + "| does not exist in the Song database.");
        }
    }


    /**
     * Gets the array representation of table.
     * 
     * @return the hash table.
     */
    public Record<String, Node>[] getTable() {
        return table;
    }


    /**
     * Gets the number of songs
     * in the song hash table.
     * 
     * @return number of songs in song table.
     */
    public int getSongCount() {
        return this.songCount;
    }


    /**
     * Gets the number of artists
     * in the artist hash table.
     * 
     * @return number of artists in artist table.
     */
    public int getArtistCount() {
        return this.artistCount;
    }


    /**
     * Compute the hash function
     * 
     * @param s
     *            The string that we are hashing
     * @param length
     *            Length of the hash table (needed because this method is
     *            static)
     * @return
     *         The hash function value (the home slot in the table for this key)
     */
    public static int h(String s, int length) {
        int intLength = s.length() / 4;
        long sum = 0;
        for (int j = 0; j < intLength; j++) {
            char[] c = s.substring(j * 4, (j * 4) + 4).toCharArray();
            long mult = 1;
            for (int k = 0; k < c.length; k++) {
                sum += c[k] * mult;
                mult *= 256;
            }
        }

        char[] c = s.substring(intLength * 4).toCharArray();
        long mult = 1;
        for (int k = 0; k < c.length; k++) {
            sum += c[k] * mult;
            mult *= 256;
        }

        return (int)(Math.abs(sum) % length);
    }
}
