package com.reylibutan.lvluptdd.sorting;

public class SelectionSorter implements Sorter {

  @Override
  public void sort(int[] intArr) {
    for (int i = 0; i < intArr.length; i++) {
      int min = intArr[i];
      int minIndex = i;

      for (int j = i; j < intArr.length; j++) {
        if (intArr[j] < min) {
          min = intArr[j];
          minIndex = j;
        }
      }

      if (min < intArr[i]) {
        int temp = intArr[i];
        intArr[i] = min;
        intArr[minIndex] = temp;
      }
    }
  }

}
