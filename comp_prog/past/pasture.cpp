#include <bits/stdc++.h>
#include <cstdio>
using namespace std;

struct Rect {
    int x1, y1, x2, y2;
};

int main() {
    freopen("pasture.in", "r", stdin);
    freopen("pasture.out", "w", stdout);
    Rect a, b;

    cin >> a.x1 >> a.y1 >> a.x2 >> a.y2;
    cin >> b.x1 >> b.y1 >> b.x2 >> b.y2;

    int xDim = max(max(b.x2, a.x1)-min(b.x2, a.x1), max(b.x1, a.x2)-min(b.x1, a.x2));
    int yDim = max(max(b.y2, a.y1)-min(b.y2, a.y1), max(b.y1, a.y2)-min(b.y1, a.y2));
    cout << max(xDim, yDim)*max(xDim, yDim);
}
