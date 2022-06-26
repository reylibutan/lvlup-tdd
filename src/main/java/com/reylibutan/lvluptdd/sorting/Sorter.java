package com.reylibutan.lvluptdd.sorting;

// TODO: no need to entertain other data types for now
public interface Sorter {
  void sort(int[] intArr);

  default void swap(int[] intArr, int index1, int index2) {
    int length = intArr.length;
    if (length == 0 || index1 >= length || index2 >= length) {
      throw new IllegalArgumentException("Array cannot be empty. Please also check if the indices are valid.");
    }

    int temp = intArr[index1];
    intArr[index1] = intArr[index2];
    intArr[index2] = temp;
  }
}
