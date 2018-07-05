package com.gs.home.datastructures;

/**
 * Represents a Node in a Singly Linked List
 * @author chandrashekar
 *
 * @param <T>
 */
public class Node<T> {
	T t;
	Node<T> next;
	
	public Node(T t, Node<T> next) {
		this.t = t;
		this.next = next;
	}

	public Node(T t) {
		this.t = t;
	}
	
	public Node() {}
	
	@Override
	public String toString() {
		return Integer.toString(t.hashCode());
	}
}
