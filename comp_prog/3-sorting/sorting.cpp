#include <bits/stdc++.h>
#include <string>
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

template <size_t size>
string binary_search(array<int, size>& arr, int x) {
  int a = 0, b = arr.size()-1;
  while (a <= b) {
    int k = (a+b)/2;
    if (arr[k] == x) {
      return "found at arr[" + to_string(k) + "]";
    }
    if (arr[k] > x) b = k-1;
    else a = k+1;
  }
  return "not found :(";
}

int main() {
  array<int, 10> arr;
  for (int i = 0; i < arr.size(); i++) {
    arr[i] = i;
  }
  
  print_arr(arr);
  cout << binary_search(arr, 8);
}
