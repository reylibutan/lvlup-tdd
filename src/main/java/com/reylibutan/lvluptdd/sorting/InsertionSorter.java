package com.reylibutan.lvluptdd.sorting;

public class InsertionSorter implements Sorter {

  @Override
  public void sort(int[] intArr) {
    for (int i = 1; i < intArr.length; i++) {
      for (int j = i; j > 0; j--) {
        if (intArr[j] < intArr[j - 1]) {
          swap(intArr, j, j - 1);
        } else {
          break;
        }
      }
    }
  }

  private void swap(int[] intArr, int index1, int index2) {
    int length = intArr.length;
    if (length == 0 || index1 >= length || index2 >= length) {
      throw new IllegalArgumentException("Array cannot be empty. Please also check if the indices are valid.");
    }

    int temp = intArr[index1];
    intArr[index1] = intArr[index2];
    intArr[index2] = temp;
  }
}
