package com.gs.home.datastructures;

import static com.gs.home.log.Logger.stdout;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementation of a singly linked list. Storing as byte so that memory foot print is less
 * Byte values are between -128 and 127
 * @author chandrashekar
 *
 */
public class SinglyLinkedList {
	private static AtomicInteger length = new AtomicInteger();
	private static Node<Byte> first;
	
	public static void main(String[] args) {
		Node<Byte> head = new Node<Byte>((byte)15);
		populateLinkedList(head); //create the list
		iterateLinkedList(head);
		
		insertAtEnd(head, 43); //insertions two below
		iterateLinkedList(head);
		
		head = insertAtBegin(head, 7);
		iterateLinkedList(head);
		
		Node<Byte> node = delete(head, 25); //delete values - existing and otherwise
		stdout("Deleted node is " + node);
		iterateLinkedList(head);
		
		node = delete(head, 1);
		stdout("Deleted node is " + node);
		stdout("Length : " + length);
		
		iterateLinkedList(reverse(head)); //reverse the list and print
		
		stdout("Reversing the list by method #2 Recursion!");
		
		recursiveReverse(head, null); //reverse the list and print
		iterateLinkedList(first);
	}
	
	private static void populateLinkedList(Node<Byte> head) {
		byte count = 1; //byte occupies less memory than an int or short
		
		for (byte i = 17; i < 30; i += 2) {
			Node<Byte> next = new Node<Byte>(i);
			head.next = next;
			head = next;
			count++;
		}
		
//		head.next = null;  //Its already null by default! Just for documentation purpose
		length.compareAndSet(0, count);
	}
	
	public static void iterateLinkedList(Node<Byte> head) {
		while (head != null) {
//			stdout("Node value: " + head.t + " Node: " + head + " Next Node: " + head.next);
			stdout("Node value: " + head.t);
			head = head.next;
		}
		
		stdout("Length of the List: " + length.get()); //Faster to use get. AtoInt.toString calls get internally!
	}

	public static void insertAtEnd(Node<Byte> head, int i) {
		while (head != null) {
			if (head.next == null) { 
				head.next = new Node<Byte>((byte) i, null);
				length.getAndIncrement();
				return;
			}
			
			head = head.next;
		}
	}
	
	private static Node<Byte> insertAtBegin(Node<Byte> head, int i) {
		length.getAndIncrement();
		return new Node<Byte>((byte) i, head);
	}
	
	private static Node<Byte> delete(Node<Byte> head, int i) {
		while (head != null) {
			if (head.next != null && head.next.t.equals((byte) i)) {
				Node<Byte> deletedNode = head.next;
				head.next = deletedNode.next;
				length.getAndDecrement();
				return deletedNode;
			}
			
			head = head.next;
		}
		
		return null;
	}
	
	private static Node<Byte> reverse(Node<Byte> head) {
		/**
		 * Memory efficient to use fixed length data structures as opposed open ended collections, when feasible.
		 * Note that primitive arrays are MORE memory efficient THAN their wrapper type counterparts. Per JavaOne
		 * Conference talk on memory footprint, Objects occupy MORE bytes to store METADATA than the actual DATA! 
		 * Leverage this idea in real world projects
		 */
		 byte [] elements = new byte[(byte) length.get()];
		 byte i = 0;
		 
		 while (head != null) {
			 elements[i++] = head.t;
			 head = head.next;
		 }
		 
		 Node<Byte> newhead = new Node<Byte>(elements[elements.length - 1]);
		 Node<Byte> first = newhead;
		 
		 for (byte j = (byte) (elements.length - 2); j >= 0;) {
			 Node<Byte> next = new Node<>(elements[j--]); //faster if done here I think -:)
			 newhead.next = next;
			 newhead = next;
		 }
		 
		 //newhead = null; //mark the last node's next as null. Not needed as NULL by default
		 return first;
	}
	
	
	private static Node<Byte> recursiveReverse(Node<Byte> head, Node<Byte> prev) {
		if (head == null) return null;
		
		if (head.next == null) {
			first = head;
			return head;
		}
		
		Node<Byte> node = recursiveReverse(head.next, head);
		node.next = prev;
		return prev;
	}
}
