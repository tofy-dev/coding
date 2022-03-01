#include <bits/stdc++.h>
#include "libs/structs.h"
using namespace std;

template <size_t size>
array<int, size> bubble_sort(array<int, size> arr) {
  int n = arr.size();
  for (int i = 0; i < n-1; i++) {
    int counter = 0;
    for (int j = 0; j < n-1; j++) {
      if (arr[j] > arr[j+1]) {
        swap(arr[j],arr[j+1]);
        print_arr(arr);
      }
      counter++;
    }
    cout << counter << "===================================\n";
  }
  return arr;
}

template <size_t size>
array<int, size> counting_sort(array<int, size>& arr) {
  map<int, int> omap;
  typedef typename array<int, size>::const_iterator cter;
  for (cter it = arr.cbegin(); it < arr.cend(); it++) {
    if (omap.count(*it) == 0) {
      omap[*it] = 1;
    } else {
      omap[*it]++;
    }
  }

  array<int, size> sorted;
  return conv_map(omap, sorted);
}

int main() {
  array<int, 500> unsorted;
  for (int i = 0; i < unsorted.size(); i++) {
    unsorted[i] = rand() % 100;
  }

  print_arr(unsorted);
  print_arr(counting_sort(unsorted));
}
