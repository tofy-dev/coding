#include <bits/stdc++.h>
#include <cstdio>
using namespace std;

// macros
#define PB push_back;
#define REP(a,b) for (int i = a; i <= b; i++)

int main() {
    // makes I/O faster
    ios::sync_with_stdio(0);
    cin.tie(0);

    // alt: c-styled I/O
    int a, b;
    scanf("%d %d", &a, &b);
    printf("%d %d\n", a, b);
    
    // using files for I/O
    // freopen("input.txt", "r", stdin);
    // freopen("output.txt", "w", stdout);
    
    // cool modulus trick
    // (a + b) mod m = (a mod m + b mod m) mod m
    // (a − b) mod m = (a mod m − b mod m) mod m
    // (a · b) mod m = (a mod m · b mod m) mod m
    
    // c++ sometimes returns negative moduluses
    int x = -11, m = 7;
    x %= m; 
    if (x < 0) x += m;
    printf("%d\n", x);
    
    REP(0, 4) {
        printf("%d|", i);
    }
}
