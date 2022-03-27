#include <bits/stdc++.h>
#include <bitset>
using namespace std;

int main() {
    bitset<10> bit;
    int n = bit.size();
    for (int b = 0; b < (1<<n); b++) {
        vector<int> subset;
        for (int i = 0; i < n; i++) {
            if (b&(1<<i)) subset.push_back(i);
        }
        cout << string(subset.begin(), subset.end()) << '\n';
    }
}
