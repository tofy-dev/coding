#include <array>
#include <map>
#include <iostream>

using std::array;
using std::map;
using std::size_t;
using std::cout;

template <typename type, size_t size>
void print_arr(array<type, size> arr) {
  typedef typename array<type, size>::const_iterator iter; //http://www.cplusplus.com/forum/general/219039/#msg1009625
  for (iter it = arr.begin(); it < arr.end(); it++) {
    cout << *it << " ";
  }
  cout << '\n';
}

template <typename T, size_t size>
array<T, size> conv_map(map<T, T>& mp, array<T, size>& arr) {
  int idx = 0;
  for (auto x : mp) {
    for (int i = 0; i < x.second; i++) {
      arr[idx++] = x.first;
    }
  }
  return arr;
}
