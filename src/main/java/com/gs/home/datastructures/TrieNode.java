package com.gs.home.datastructures;

import java.util.HashMap;
import java.util.Map;
/**
 * Trie data structure used to store many strings. Efficient to store letters in the strings
 * especially if they are repeated. This is a tricky data structure. Go through the Web before 
 * reviewing this
 * @author chandrashekar
 * Apr 7 2019
 */
public class TrieNode {
	Map<Character, TrieNode> map = new HashMap<>();
	boolean isLastLetter;
	boolean visited;
}
