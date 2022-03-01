#include <bits/stdc++.h>
using namespace std;

int main() {
    // time complexity determined by O(...)
    // doesn't mean how many times code is executed; it's the order of magnitude
    // algorithms are polynomial if its time complexity is, at MOST
    // O(n^k) -- most are polynomial except for O(2^n) or O(n!)
    //      some problems don't have polynomial algorithms (NP-problems)


    
}

// LOOPS => O(n^k) where k is the amount of nested loops
// time complexity: O(n^2)
void loops() {
    int n = 10;
    for (int i = 1; i <= n; i++){
        for (int j = 1; j <= n; j++) {
            // code
        }
    }
}

// PHASES => total time complexity <=> slowest single phase
// time complexity: O(n^2)
void phases() {
    int n = 10;
    for (int i = 1; i <= n; i++) {
        // code
    }
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= n; j++) {
            // code
        }
    }
    for (int i = 1; i <= n; i++) {
        // code
    }
}

// SEVERAL VARIABLES => O(var*var)
// time complexity: O(n*m)
void mult_vars() {
    int n = 10;
    int m = 5;
    for (int i = 1; i <= n; i++) {
        for (int j = 1; j <= m; j++) {
            // code
        }
    }
}

// RECURSION => depends on how many times the function gets called 
//              each call is O(1) => total is O(n)
// time complexity: O(2^n)
//      g(n) : 1, g(n-1) : 2, g(n-2) : 4, g(1) : 2^(n-1)
//      TOTAL time complexity is (bk-a)/(k-1) or 2^n-1 or O(2^n)
void recursion(int n = 10) {
    if (n == 1) return;
    recursion(n-1);
    recursion(n-1);
}
