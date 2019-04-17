package com.gs.home.datastructures;

import static com.gs.home.log.Logger.stdout;
import java.util.Arrays;

/**
 * Implementation of heap using arrays
 */
public class Heap {
	private Integer[] heapData;
	private int currentPosition = -1;
	
	public Heap(int capacity) {
		this.heapData = new Integer[capacity];
	}
	
	public void insert(int item) {
		if (isFull()) throw new RuntimeException("Heap is full");
		this.heapData[++currentPosition] = item;
		fixUp(currentPosition);
	}
	
	public int deleteRoot() {
		int result = heapData[0];
		heapData[0] = heapData[currentPosition--];
		heapData[currentPosition+1] = null;
		fixDown(0, -1);
		return result;
	}
	
	private void fixDown(int index, int upto) {
		if (upto < 0) upto = currentPosition;
		while (index <= upto) {
			int leftChild = 2 * index + 1;
			int rightChild = 2 * index + 2;
			if (leftChild <= upto) { //checking for left boundary
				int childToSwap;
				if (rightChild > upto) {//if right child index > size - 1, then just take the left child
					childToSwap = leftChild;
					stdout("End of heap reached: RC: " + rightChild);
				} else {
					childToSwap = (heapData[leftChild] > heapData[rightChild]) ? leftChild : rightChild;
				}
				
				if (heapData[index] < heapData[childToSwap]) {
					stdout("Swapping data at " + index + " with " + childToSwap);
					swap(childToSwap, index);
				} else {
					break;
				}
				index = childToSwap;
			} else {
				break;
			}
		}
	}
	
	private void fixUp(int index) {
		int pi = (index-1)/2; //parent index
		while (pi >= 0 && heapData[pi] < heapData[index]) {
//			stdout("Exchanging data at " + pi + "= " + heapData[pi] + " with " + index + " = " + heapData[index]);
			swap(index, pi);
			index = pi;
			pi = (index-1)/2;
		}
	}
	
	private boolean isFull() {
		return size() == heapData.length;
	}
	
	/**
	 * Heap Sort could be called in a heap array, so we assume that this heap was
	 * built up by calling insert repeatedly, and then we call heapSort on it.
	 */
	public void heapSort() {
		for (int i=0; i < currentPosition; i++) {
			int tmp = heapData[0]; // max element
			heapData[0] = heapData[currentPosition-i]; // bring last element to the root
			heapData[currentPosition-i] = tmp; // put max at the last of unsorted part
			fixDown(0, currentPosition-i-1);
		}
	}
	
	@Override
	public String toString() {
		return Arrays.deepToString(this.heapData);
	}
	
	//This builds a heap from the given data array
	public static Heap heapify(int[] data) {
		Heap heap = new Heap(data.length);
		for (int i=0; i < data.length; i++) {
			heap.insert(data[i]);
		}
		return heap;
	}
	
	private void swap(int index, int i) {
		int tmp = heapData[i];
		heapData[i] = heapData[index];
		heapData[index] = tmp;
	}
	
	private int size() {
		return currentPosition + 1;
	}
	
	public static void main(String[] args) {
		Heap heap = new Heap(10);
		heap.insert(10);
		heap.insert(15);
		heap.insert(27);
		heap.insert(5);
		heap.insert(2);
		heap.insert(21);
		heap.insert(14);
		stdout("Size of the heap: " + heap.size());
		stdout(heap);
		stdout(heap.deleteRoot());
		stdout(heap);
		heap.heapSort();
		stdout(heap);
		stdout("Size of the heap: " + heap.size());
//		Heap another = Heap.heapify(new int[]{73,16,40,1,46,28,12,21,22,44,66,90,7});
//		another.heapSort();
//		System.out.println(another);
	}
}
