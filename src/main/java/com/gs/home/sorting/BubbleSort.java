package com.gs.home.sorting;

import com.gs.home.log.Logger;

public class BubbleSort {
	public static void main(String[] args) {
		final int [] numbers = {25, 15, 10, 7, 23, 8, 45, 11};
		new BubbleSort().sort(numbers);
		//changed
	}
	
	public void sort(int[] numbers) {
		for (int i = numbers.length - 2; i >= 0; i--) {
			for (int j = 0; j <= i; j++) {
				if (numbers[j] > numbers[j + 1]) {
					swap(j, j + 1, numbers);
				}
			}
		}
		
		print(numbers);
	}

	public static void print(int[] numbers) {
		for (int n : numbers) Logger.stdout(n);
	}

	private static void swap(int i, int j, int [] numbers) {
		int temp = numbers[i];
		numbers[i] = numbers[i + 1];
		numbers[i + 1] = temp;
	}
}
