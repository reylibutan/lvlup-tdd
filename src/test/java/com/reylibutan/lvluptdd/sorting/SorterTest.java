package com.reylibutan.lvluptdd.sorting;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SorterTest {
  private static int[] DEFAULT_SOLUTION;

  @BeforeAll
  public static void setup() {
    DEFAULT_SOLUTION = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  }

  @ParameterizedTest
  @MethodSource(value = "getSorterImplementations")
  @DisplayName("Sorting an empty array should succeed")
  public void sort_withAnEmptyArray_shouldPass(Sorter sorter) {
    int[] empty = new int[]{};

    sorter.sort(empty);

    assertThat(empty).containsExactly(empty);
  }

  @ParameterizedTest
  @MethodSource(value = "getSorterImplementations")
  @DisplayName("Sorting an already sorted array (best case) should succeed")
  public void sort_withAnAlreadySortedArray_shouldPass(Sorter sorter) {
    int[] alreadySorted = DEFAULT_SOLUTION;

    sorter.sort(alreadySorted);

    assertThat(alreadySorted).containsExactly(DEFAULT_SOLUTION);
  }

  @ParameterizedTest
  @MethodSource(value = "getSorterImplementations")
  @DisplayName("Sorting a short unsorted array should succeed")
  public void sort_withAShortUnsortedArray_shouldPass(Sorter sorter) {
    int[] shortUnsorted = new int[]{99, 88};

    sorter.sort(shortUnsorted);

    assertThat(shortUnsorted).containsExactly(88, 99);
  }

  @ParameterizedTest
  @MethodSource(value = "getSorterImplementations")
  @DisplayName("Sorting a randomly arranged array (normal case) should succeed")
  public void sort_withRandomlyArrangedArray_shouldPass(Sorter sorter) {
    int[] randomlyArranged1 = new int[]{1, 10, 9, 4, 3, 2, 5, 8, 7, 6};
    int[] randomlyArranged2 = new int[]{6, 7, 8, 9, 10, 1, 2, 3, 4, 5};
    int[] randomlyArranged3 = new int[]{1, 10, 2, 9, 3, 8, 4, 7, 5, 6};

    sorter.sort(randomlyArranged1);
    sorter.sort(randomlyArranged2);
    sorter.sort(randomlyArranged3);

    assertThat(randomlyArranged1).containsExactly(DEFAULT_SOLUTION);
    assertThat(randomlyArranged2).containsExactly(DEFAULT_SOLUTION);
    assertThat(randomlyArranged3).containsExactly(DEFAULT_SOLUTION);
  }

  @ParameterizedTest
  @MethodSource(value = "getSorterImplementations")
  @DisplayName("Sorting reverse sorted array (worst case) should succeed")
  public void sort_withReverseSortedArray_shouldPass(Sorter sorter) {
    int[] reverseSorted = new int[]{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    sorter.sort(reverseSorted);

    assertThat(reverseSorted).containsExactly(DEFAULT_SOLUTION);
  }

  private static List<Sorter> getSorterImplementations() {
    return List.of(new BubbleSorter(), new SelectionSorter(), new InsertionSorter());
  }
}