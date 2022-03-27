#include <bits/stdc++.h>
#include <deque>
#include <queue>
using namespace std;

int main() {
    cout << "// 1) Dynamic arrays" << '\n';
    vector<int> v;
    v.push_back(1);
    v.push_back(2);
    v.push_back(3);

    for (auto x : v) {
        cout << x << '\n';
    }


    cout << "// 2) Set structures" << '\n'; // collection of elements maintained
    set<int> s; // alt: multiset (can have duplicates)
    s.insert(2);
    s.insert(1);

    cout << s.count(3) << '\n';
    cout << s.count(2) << '\n';

    s.erase(2);
    cout << s.count(2) << '\n';


    cout << "// 3) Map structures" << '\n';
    map<string, int> m; // has key-value pairs
    m["monkey"] = 4;
    m["banana"] = 3;
    m["harpsichord"] = 9;

    if (m.count("sussy imposter!!!")) {
        // check if val exists
    }

    for (auto x : m) cout << x.first << ' ' << x.second << '\n';
    

    cout << "// 4) Iterators + ranges" << '\n'; // it> points to an element
    // you can use auto instead of having to typedef container::iterator


    cout << "// 5) Other structures" << '\n';
    bitset<10> bs(string("1010010110")); // right to left
    // bs[1] = 1; bs[2] = 1; bs[4] = 1; bs[7] = 1;
    for (int i = 0; i < 10; i++) {
        cout << bs[i];
    }
    cout << '\n' << bs.count() << '\n';

    deque<int> d; // this is basically a vector but it has pop / push at the front

    stack<int> sk; // can only push to the top, remove from the top

    queue<int> q; // can only push to the top, remove from the bottom

    priority_queue<int> pq; // elements sorted in decreasing order, EZ removing largest element

}
