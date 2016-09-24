package ru.spbau.eshcherbin.homework.hw3;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import static org.junit.Assert.*;

public class TrieTest {

    private Trie trie = null;

    @org.junit.Before
    public void setUp() throws Exception {
        trie = new Trie();
    }

    @org.junit.Test
    public void addAndContains() throws Exception {
        assertTrue(trie.add("testString"));
        assertTrue(trie.contains("testString"));
        assertFalse(trie.add("testString"));
        assertTrue(trie.contains("testString"));

        assertTrue(trie.add(";123O,.La_  asd1⊆◌⇶"));
        assertTrue(trie.contains(";123O,.La_  asd1⊆◌⇶"));
        assertFalse(trie.contains(";123O,.La_ asd1⊆◌⇶"));
    }

    @org.junit.Test
    public void remove() throws Exception {
        trie.add("testString");
        trie.add("anotherTestString");
        assertFalse(trie.remove(""));
        assertTrue(trie.remove("testString"));
        assertFalse(trie.contains("testString"));
        assertFalse(trie.remove("testString"));
    }

    @org.junit.Test
    public void size() throws Exception {
        assertEquals(0, trie.size());
        trie.add("testString");
        assertEquals(1, trie.size());
        trie.add("anotherTestString");
        assertEquals(2, trie.size());
        trie.remove("testString");
        assertEquals(1, trie.size());
        trie.remove("testString");
        assertEquals(1, trie.size());
        trie.remove("anotherTestString");
        assertEquals(0, trie.size());
    }

    @org.junit.Test
    public void howManyStartsWithPrefix() throws Exception {
        trie.add("endless");
        trie.add("endfull");
        trie.add("endian");
        trie.add("endianness");
        assertEquals(4, trie.howManyStartsWithPrefix("e"));
        assertEquals(4, trie.howManyStartsWithPrefix("end"));
        assertEquals(1, trie.howManyStartsWithPrefix("endl"));
        assertEquals(1, trie.howManyStartsWithPrefix("endf"));
        assertEquals(2, trie.howManyStartsWithPrefix("endi"));
        assertEquals(2, trie.howManyStartsWithPrefix("endian"));
        assertEquals(1, trie.howManyStartsWithPrefix("endianness"));
        assertEquals(0, trie.howManyStartsWithPrefix("testString"));
    }

    @org.junit.Test
    public void serializationTest() throws Exception {
        trie.add("testString");
        trie.add("anotherTestString");
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        trie.serialize(out);
        ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
        Trie newTrie = new Trie();
        newTrie.deserialize(in);
        assertEquals(2, newTrie.size());
        assertTrue(newTrie.contains("testString"));
        assertTrue(newTrie.contains("anotherTestString"));
        in.close();
        out.close();
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void addNullTest() throws Exception {
        trie.add(null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void containsNullTest() throws Exception {
        trie.contains(null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void removeNullTest() throws Exception {
        trie.remove(null);
    }

    @org.junit.Test(expected = IllegalArgumentException.class)
    public void howManyStartsWithPrefixNullTest() throws Exception {
        trie.howManyStartsWithPrefix(null);
    }
}