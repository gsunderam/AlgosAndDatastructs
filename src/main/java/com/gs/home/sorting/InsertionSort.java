package com.gs.home.sorting;

import java.util.Arrays;

import com.gs.home.log.Logger;

/**
 * Works well for small sized lists. My version
 * @author chandrashekar
 *
 */
public class InsertionSort {
	public static void main(String[] args) {
		final int numbers [] = {13, 2, 11, 20, 17, 15, 12};
		new InsertionSort().sort(numbers);
	}

	private void sort(int[] numbers) {
		for (int i = 1; i < numbers.length ; i++) {
			int index = i;
			
			for (int j = i - 1; j >= 0; j--) {
				if (numbers[j] > numbers[index]) {
					swap(j, index, numbers);
					index = j;
				}
			}
		}
		
		print(numbers);
	}

	private void swap(int j, int index, int[] numbers) {
		int temp = numbers[j];
		numbers[j] = numbers[index];
		numbers[index] = temp;
	}

	private void print(int[] numbers) {
		Logger.stdout(Arrays.toString(numbers));
	}
}
