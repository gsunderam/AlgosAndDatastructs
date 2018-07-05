package com.gs.home.sorting;

import java.util.Arrays;

public class InsertSort2 {
	public static void main(String[] args) {
		Integer[] data = {7,3,6,8,2, 13, 5};
		new InsertSort2().sort(data);
		System.out.println(Arrays.toString(data));
	}
	
	public void sort(Integer[] data) {
		for (int i = 1; i < data.length; i++) {
			int current = data[i];
			int j = i-1;
			while (j >=0 && data[j] >= current) {
				data[j+1] = data[j];
				j--;
			}
			data[j+1] = current;
		}
	}
}
