package com.xyang;

import java.util.Arrays;

public class SoreTest {

	public static void main(String[] args) {
		int[] score = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15,
				35, 25, 53, 51 };
		// bubble_sort(score);
		bubble_sort_2(score);
		quickly_sort_1(score, 0, score.length - 1);
		// quickly_sort_2(score, 0, score.length - 1);
		System.out.println(Arrays.toString(score));
	}

	@SuppressWarnings("unused")
	private static void bubble_sort(int[] arr) {
		int tmp;
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - 1 - i; j++) {
				if (arr[j] > arr[j + 1]) {
					tmp = arr[j + 1];
					arr[j + 1] = arr[j];
					arr[j] = tmp;
				}
			}
		}
	}

	private static int quickly_sort_1(int[] arr, int low, int high) {
		int middleIndex = low;
		if (low < high) {
			middleIndex = getMiddle(arr, low, high);
			quickly_sort_1(arr, low, middleIndex - 1);
			quickly_sort_1(arr, middleIndex + 1, high);
			System.out.printf("index[%d] num[%d]。\n", middleIndex, arr[middleIndex]);
		}
		return middleIndex;
	}

	private static int getMiddle(int[] arr, int low, int high) {
		int tmp = arr[low]; // 数组的第一个作为中轴
		while (low < high) {
			while (low < high && arr[high] >= tmp) {
				high--;
			}
			arr[low] = arr[high]; // 比中轴小的记录移到低端
			while (low < high && arr[low] <= tmp) {
				low++;
			}
			arr[high] = arr[low]; // 比中轴大的记录移到高端
		}
		arr[low] = tmp; // 中轴记录到尾
		return low; // 返回中轴的位置
	}

	public static void bubble_sort_2(int[] arr) {
		if(null==arr|| arr.length<=1){
			return;
		}
		for(int i=0;i<arr.length-1;i++){
			for(int j=0;j<arr.length-1-i;j++){
				int tmp = arr[j];
				if(arr[j+1]<arr[j]){
					arr[j]=arr[j+1];
					arr[j+1]=tmp;
				}
			}
		}
	}

	@SuppressWarnings("unused")
	private static void quickly_sort_2(int[] score, int low, int hight) {
		if (low < hight) {
			int result = middlePartition(score, low, hight);
			quickly_sort_2(score, low, result - 1);
			quickly_sort_2(score, result + 1, hight);
		}
	}

	private static int middlePartition(int arr[], int low, int hight) {
		int key = arr[low];
		while (low < hight) {
			while (low < hight && arr[hight] >= key) {
				hight--;
			}
			arr[low] = arr[hight];
			while (low < hight && arr[low] <= key) {
				low++;
			}
			arr[hight] = arr[low];
		}
		arr[low] = key;
		return low;
	}
}
