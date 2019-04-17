package com.gs.home.datastructures;

import java.util.concurrent.atomic.AtomicInteger;
import static com.gs.home.log.Logger.stdout;

public class DoublyLinkedList {
	private static AtomicInteger size = new AtomicInteger(1);
	private static LinkNode<Integer> first;
	
	public static void main(String[] args) {
		LinkNode<Integer> head = new LinkNode<>(33, null);
		createLinkedList(head);
		iterateLinkedList(head);
		insertAfter(45, 46, head);
		iterateLinkedList(head);
		
		insertAfter(11, 46, head); //not found
		reverse(head, null);
		iterateLinkedList(first);
	}

	/**
	 * head is merely pointed to various new references at line 32. Original "head" object is not modified.
	 * That's why head doesn't change in the calling function - main method above. So Objects
	 * are passed by reference
	 * @param head
	 */
	private static void createLinkedList(LinkNode<Integer> head) {
		for (int i = 35; i < 55; i += 2) {
			LinkNode<Integer> next = new LinkNode<>(i, head);
			head.next = next;
			head = next;
			size.incrementAndGet();
		}
		
		stdout("Size of the list " + size.get());
	}
	
	private static void iterateLinkedList(LinkNode<Integer> head) {
		while (head != null) {
			stdout("Node Value: " + head.t + " Prev: " + head.prev + " Next: " + head.next);
			head = head.next;
		}
		
		stdout("Size of the list " + size.get());
	}
	
	private static void insertAfter(int oldval, int newval, LinkNode<Integer> head) {
		while (head != null) {
			if (head.t == oldval) {
				LinkNode<Integer> node = new LinkNode<>(newval, head);
				node.next = head.next;
				head.next = node;
				size.getAndIncrement();
				return;
			}
			
			head = head.next;
		}
		
		stdout("Value " + oldval + " not found in the linked list!");
	}
	
	/**
	 * Reverses using recursion
	 * @param head
	 * @param prev
	 * @return
	 */
	private static LinkNode<Integer> reverse(LinkNode<Integer> head, LinkNode<Integer> prev) {
		if (head == null) return null;
		
		//Terminating condition for recursion
		if (head.next == null) {
			first = head;
			return head;
		}
		
		LinkNode<Integer> node = reverse(head.next, head);
		node.prev = node.next; //Reset prev pointer first before doing it for next
		node.next = prev; //Then set the next pointer
		return prev;
	}
}
