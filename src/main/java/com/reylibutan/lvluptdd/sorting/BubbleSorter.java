package com.reylibutan.lvluptdd.sorting;

public class BubbleSorter implements Sorter {

  @Override
  public void sort(int[] intArr) {
    for (int i = 0; i < intArr.length; i++) {
      for (int j = 0; j < intArr.length - i - 1; j++) {
        if (intArr[j] > intArr[j + 1]) {
          swap(intArr, j, j + 1);
        }
      }
    }
  }

}
