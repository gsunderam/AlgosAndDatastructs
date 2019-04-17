package com.gs.home.datastructures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import static com.gs.home.log.Logger.stdout;
import static com.gs.home.log.Logger.print;

/**
 * Demo to execise the Trie data structure. Insert,delete and display. Go through
 * you tube videos on Tries before reviewing this. All methods use recursion
 * @author chandrashekar
 */
public class TrieDemo {
	private static TrieNode root = new TrieNode();
	private static List<String> words = Arrays.asList("abc", "def", "abgl", "abgk", "abcd");
	private static List<String> deleteWords = Arrays.asList("abc", "def", "abcd");
	static List<Character> holder = new ArrayList<>();
	private static int count;
	
	public static void main(String[] args) {
		//for (String word: words) 
		//	insert(root, word);
		
		//root = new TrieNode();
		for (String word: words)
			insertRecursive(root, word.toCharArray(), 0);
		
		printLetters(root);
		countNodes(root);
		stdout("No of nodes: " + count);
		
		char [] letters = new char[4];
		stdout("Words in the Trie are....");
		printWords(root, letters, 0);
		print("\n");
		
		//Delete few words
		for (String word: deleteWords)
			deleteWord(root, word.toCharArray(), 0);
		
		//Print again to see if deletes worked -:)
		stdout("Deleted " + deleteWords + "! Now the trie contains....");
		printWords(root, letters, 0);
	}
	
	/**
	 * Print all words in the Trie
	 * @param root Root TrieNode
	 * @param letters Temp char array to store the letters
	 * @param level Current depth of the tree
	 */
	private static void printWords(TrieNode root, char[] letters, int level) {
		if (root.isLastLetter) {
			char [] word = new char[level];
			System.arraycopy(letters, 0, word, 0, level);
			stdout(Arrays.toString(word).replaceAll("[\\s,]", ""));
		}
		
		Set<Entry<Character, TrieNode>> entries = root.map.entrySet();
		for (Entry<Character, TrieNode> entry : entries) {
			letters[level] = entry.getKey();
			printWords(entry.getValue(), letters, level + 1);
		}
	}
	
	/**
	 * print just the letters stored in the Trie
	 * @param root Root Node of the Trie tree
	 */
	private static void printLetters(TrieNode root) {
		Set<Entry<Character, TrieNode>> entrySet = root.map.entrySet();
		Iterator<Entry<Character, TrieNode>> it = entrySet.iterator();
		
		while (it.hasNext()) {
			Entry<Character, TrieNode> entry = it.next();
			holder.add(entry.getKey());
			if(!entry.getValue().map.isEmpty()) {
				printLetters(entry.getValue());
				if (entry.getValue().isLastLetter) {
					print(holder);
					stdout("\n-----------------\n");
					holder.clear();
				}
			}
		}
	}
	
	/** Iterative insert */
	@SuppressWarnings("unused")
	private static void insert(TrieNode root, String word) {
		char[] chars = word.toCharArray();
		int len = chars.length;
		for (int i = 0; i < len; i++) {
			TrieNode trieNode = root.map.get(chars[i]);
			if (trieNode == null) {
				trieNode = new TrieNode();
				root.map.put(chars[i], trieNode);
				stdout("inserted " + chars[i]);
			} 
			root = trieNode;
		}
		
		root.isLastLetter = true;
	}
	
	private static void insertRecursive(TrieNode root, char [] letters, int index) {
		if (index == letters.length) {
			root.isLastLetter = true;
			return;
		}
		
		TrieNode trieNode = root.map.get(letters[index]);
		if (trieNode == null) {
			trieNode = new TrieNode();
			root.map.put(letters[index], trieNode);
			stdout("inserted " + letters[index]);
		}
		
		insertRecursive(trieNode, letters, index + 1);
	}
	
	/**
	 * This is a little tricky to understand. Go through the web on Tries before reviewing this
	 * @param root
	 * @param letters
	 * @param index
	 * @return
	 */
	private static TrieNode deleteWord(TrieNode root, char [] letters, int index) {
		if (index == letters.length) {
			if (root.isLastLetter) {
				if (root.map.isEmpty()) root = null;
				else root.isLastLetter = false;
			}
			
			return root;
		}
		
		TrieNode trieNode = root.map.get(letters[index]);
		if (trieNode != null) {
			trieNode = deleteWord(trieNode, letters, index + 1);
			if (trieNode == null) {
				root.map.remove(letters[index]);
				if (!root.isLastLetter && root.map.isEmpty()) {
					root = null;
				}
			}
		}
		
		return root;
	}
	
	private static void countNodes(TrieNode root) {
		if (!root.visited) {
			root.visited = true;
			count++;
		}
		
		if (root.map.isEmpty()) return;
		
		Iterator<Entry<Character, TrieNode>> it = root.map.entrySet().iterator();
		while (it.hasNext())
			countNodes(it.next().getValue());
	}
}
