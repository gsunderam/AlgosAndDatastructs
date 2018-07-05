package com.gs.home.sorting;

import static com.gs.home.log.Logger.stdout;

import java.util.Arrays;


/**
 * First the pivot element is at a[end]. Then pivot moves to a place such that elements to its left
 * are < than pivot. Elements to the right are > pivot. Then the left and right portions are sorted
 * recursively until whole array is sorted. QS alogorithm is "in place" sorting i.e. no additional memory is needed
 * like merge sort to create temporary arrays. Avg. Perf. is O(n log b2 n). Worst case perf. O(n ^ 2)
 * 
 * @author chandrashekar
 *k
 */
public class QuickSort {
	public static void main(String[] args) {
		int [] numbers = {23, 17, 11, 5, 19, 21, 20, 2, 17, 5, 11};
		new QuickSort().sort(numbers);
		print(numbers);
	}

	private void sort(int[] numbers) {
		sort(numbers, 0, numbers.length - 1);
	}

	private void sort(int[] numbers, int start, int end) {
		if (start < end) {
			int pivot = partition(numbers, start, end);
			sort(numbers, start, pivot - 1);
			sort(numbers, pivot + 1, end);
		}
	}

	/**
	 * This does the neavy lifting. Sorting actually takes place here.
	 * 
	 * @param numbers original array
	 * @param start begin pos to sort
	 * @param end End pos to sort
	 * @return the new pivot
	 */
	private int partition(int[] numbers, int start, int end) {
		int pivot = end, i = start, j = end - 1;
		
		while (i <= j) {
			while(numbers[i] < numbers[pivot]) 	i++;
			
			while(j >= 0 && numbers[j] >= numbers[pivot]) j--;
			
			if (i < j) { 
				swap(numbers, i, j);
				i++;j--;
			}
		}
		
		swap(numbers, i, pivot);
		return i;
	}

	private void swap(int[] numbers, int i, int pivot) {
		int temp = numbers[i];
		numbers[i] = numbers[pivot];
		numbers[pivot] = temp;
	}

	private static void print(int[] numbers) {
		stdout(Arrays.toString(numbers));
	}
}
