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
}
