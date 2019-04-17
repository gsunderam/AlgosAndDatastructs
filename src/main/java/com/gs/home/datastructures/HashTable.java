package com.gs.home.datastructures;

import static com.gs.home.log.Logger.stdout;

/**
 * A very simple implementation of a Hash Table DS.
 * 
 */
public class HashTable {
	private static final int TABLE_SIZE = 100;
	private Record[] tableData = new Record[TABLE_SIZE]; // say that we won't get more than 100 records
	
	public void put(Object key, Object value) {
		stdout("Key: " + key);
		int keyCode = key.hashCode();
		int step = 0;
		int slot = hash(keyCode, step);
		while (!isEmpty(slot)) {
			slot = hash(keyCode, ++step);
		}
		tableData[slot] = new Record(key, value);
	}
	
	public boolean keyExists(Object key) {
		int keyCode = key.hashCode();
		int step = 0;
		int slot = hash(keyCode, step);
		while (tableData[slot] != null && !tableData[slot].getKey().equals(key)) {
			slot = hash(keyCode, ++step);
		}
		if (tableData[slot] != null) return true;
		return false;
	}
	
	public Object get(Object key) {
		int keyCode = key.hashCode();
		int step = 0;
		int slot = hash(keyCode, step);
		while (tableData[slot] != null && !tableData[slot].getKey().equals(key)) {
			slot = hash(keyCode, ++step);
		}
		if (tableData[slot] != null) return tableData[slot].getData();
		return null;
	}
	
	private int hash(int keyCode, int step) {
		int hash = keyCode % TABLE_SIZE;
		if (step == 0) {
			stdout("hash: " + hash + " Key code: " + keyCode);
			return hash;
		}
		
		hash = (hash + step*step) % TABLE_SIZE;
		stdout("Collision so ++step: " + step + " hash: " + hash + " Key code: " + keyCode);
		return hash;
	}
	
	private boolean isEmpty(int slot) {
		return tableData[slot] == null;
	}
	
	private class Record {
		Object key;
		Object data;
		
		public Record(Object key, Object data) {
			this.key = key;
			this.data = data;
		}
		
		public Object getKey() {
			return this.key;
		}
		
		public Object getData() {
			return this.data;
		}
	}
	
	public static void main(String[] args) {
		HashTable ht = new HashTable();
		ht.put("4", 40);
		ht.put("6", 60);
		ht.put("7", 70);
		ht.put("8", 80);
		ht.put("9", 90);
		ht.put("5", 50);
		ht.put("23", 23);
		ht.put(1, 41);
//		stdout("---------------------");
//		stdout("key 2 exists: " + ht.keyExists("2"));
//		stdout("Key 4 exists: " + ht.keyExists("4"));
//		stdout(ht.get(2));
//		stdout(ht.get("4"));
//		stdout(ht.get(4));
//		stdout(ht.get(1));
	}
}
