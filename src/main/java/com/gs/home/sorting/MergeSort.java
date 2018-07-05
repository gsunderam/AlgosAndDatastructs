package com.gs.home.sorting;

import static com.gs.home.log.Logger.stdout;

import java.util.Arrays;

public class MergeSort {
	public static void main(String[] args) {
		int [] numbers = {23, 10, 11, 15, 12, 25, 9, 20};
		new MergeSort().sort(numbers, 0, numbers.length - 1);
		
		print(numbers);
	}

	private void sort(int[] numbers, int start, int end) {
		if (start < end) {
			int middle = (int)((start + end)/2);
			sort(numbers, start, middle);
			sort(numbers, middle + 1, end);
			merge(numbers, start, middle, end);
		}
		
//		print(numbers);
	}

	private int[] merge(int[] numbers, int start, int middle, int end) {
//		stdout("Merging from start: "+ start + " to middle: " + middle + " end: " + end);
		int [] left = new int[(middle - start + 1)];
		int [] right = new int[end - middle];
		
		for (int i = 0; i < left.length; i++) 
			left[i] = numbers[start + i];
		
		for (int j = 0; j < right.length; j++) 
			right[j] = numbers[middle + 1 + j];
		
		return merge(numbers, left, right, start, end);
	}

	private int[] merge(int[] numbers, int[] left, int[] right, int start, int end) {
//		stdout("Merging left: " + toString(left) + " with right: " + toString(right));
		
		int i = 0, j = 0, k = start;
		while (i < left.length && j < right.length) {
			if (left[i] > right[j]) {
				numbers[k++] = right[j++]; 
			} else if (left[i] <= right[j]) {
				numbers[k++] = left[i++];
			}
		}
		
		while (i < left.length) numbers[k++] = left[i++];
		while (j < right.length) numbers[k++] = right[j++];
		
		return numbers;
	}

	private static void print(int[] numbers) {
		stdout(Arrays.toString(numbers));
	} 
}
