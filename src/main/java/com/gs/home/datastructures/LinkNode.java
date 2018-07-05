package com.gs.home.datastructures;

public class LinkNode<T> {
	T t;
	LinkNode<T> prev;
	LinkNode<T> next;
	
	public LinkNode(T t, LinkNode<T> prev, LinkNode<T> next) {
		this.t = t;
		this.prev = prev;
		this.next = next;
	}
	
	public LinkNode(T t, LinkNode<T> prev) {
		this.t = t;
		this.prev = prev;
	}

	public LinkNode(T t) {
		this.t = t;
	}
	
	public LinkNode() {}
	
	@Override
	public String toString() {
		return Integer.toString(t.hashCode());
	}

}
