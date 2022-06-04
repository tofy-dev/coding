#include <bits/stdc++.h>
using namespace std;

struct Rect {
    int x1, y1, x2, y2;
    int area() {
        return (x2-x1)*(y2-y1);
    }
};

int intersect(Rect a, Rect b) {
    int x = max(min(a.x2, b.x2) - max(a.x1, b.x1), 0);
    int y = max(min(a.y2, b.y2) - max(a.y1, b.y1), 0);
    return x*y;
}

int main() {
    freopen("board.in", "r", stdin);
	freopen("board.out", "w", stdout);

    Rect a, b, t;
    cin >> a.x1 >> a.y1 >> a.x2 >> a.y2;
    cin >> b.x1 >> b.y1 >> b.x2 >> b.y2;
    cin >> t.x1 >> t.y1 >> t.x2 >> t.y2;

    int area = a.area() + b.area();
    cout << area - intersect(a, t) - intersect(b, t);
}
