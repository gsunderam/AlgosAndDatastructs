package com.gs.home.datastructures;

import java.util.Arrays;

public class AscendingHeap {
	private Integer[] heapData;
	private int currentPosition = -1;
	
	public AscendingHeap(int size) {
		this.heapData = new Integer[size];
	}
	
	public void insert(int item) {
		if (isFull()) 
			throw new RuntimeException("Heap is full");
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
			if (leftChild <= upto) {
				int childToSwap;
				if (rightChild > upto)
					childToSwap = leftChild;
				else
					childToSwap = (heapData[leftChild] < heapData[rightChild]) ? leftChild : rightChild;
				
				if (heapData[index] > heapData[childToSwap]) {
					swap(index, childToSwap);
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

		while (pi >= 0 && heapData[pi] > heapData[index]) {
			swap(pi, index);
			index = pi;
			pi = (index-1)/2;
		}
	}
	
	private boolean isFull() {
		return currentPosition == heapData.length-1;
	}
	
	public void heapSort() {
		for(int i = 0; i < currentPosition; i++) {
			swap(0, currentPosition - i); //put min at the end as sorted part
			fixDown(0, currentPosition - i -1);
		}
	}
	
	private void swap(int i, int j) {
		int tmp = heapData[i];
		heapData[i] = heapData[j];
		heapData[j] = tmp;
	}

	public static void main(String[] args) {
		AscendingHeap heap = new AscendingHeap(10);
		heap.insert(10);
		heap.insert(15);
		heap.insert(27);
		heap.insert(5);
		heap.insert(2);
		heap.insert(21);
		System.out.println(Arrays.deepToString(heap.heapData));
//		heap.heapSort();
		System.out.println(heap.deleteRoot());
		System.out.println(Arrays.deepToString(heap.heapData));
	}

}
