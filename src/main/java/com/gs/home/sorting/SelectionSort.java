package com.gs.home.sorting;

import com.gs.home.log.Logger;

/**
 * Find the smallest element in the array and put it at index = 0. then compare
 * and find the next smallest. put it at index = 1 and so on till index = len - 1
 * The outer loop does this. Inner loop does the actual compare
 * 
 * @author chandrashekar
 *
 */
public class SelectionSort {
	public static void main(String[] args) {
		final int [] numbers = {25, 15, 10, 7, 23, 8, 45, 11};
		new SelectionSort().sort(numbers);
	}

	private void sort(int[] numbers) {
		int minIndex = 0;
		
		for(int i = 0; i <= numbers.length - 2; i++) {
			minIndex = i;
			
			for (int j = i + 1; j <= numbers.length - 1; j++) {
				if (numbers[minIndex] > numbers[j]) {
					minIndex = j;
				}
			}
			swap(i, minIndex, numbers);
		}
		
		print(numbers);
	}

	private void swap(int i, int minIndex, int[] numbers) {
		int temp = numbers[minIndex];
		numbers[minIndex] = numbers[i];
		numbers[i] = temp;
	}

	private void print(int[] numbers) {
		for (int n : numbers) Logger.stdout(n);		
	}
}
