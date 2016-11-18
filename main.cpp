#include <iostream>
#include<fstream>
#include <vector>
#include <unordered_map>
#include <string>
#include <set>
#include <utility>

using std::unordered_map;
using std::vector;
using std::string;
using std::pair;
using std::endl;
using std::set;
using std::cin;
using std::cout;

int main() {

    size_t amount;
    cin >> amount;
    vector<pair<string, set<char>>> langs;

    string name, line;
    set<char> alph;
    size_t size;

    for (size_t i = 0; i < amount; ++i) {
        alph.clear();
        cin >> name;
        cin >> line;
        size = line.size();

        for (size_t j = 0; j < size; ++j) {
            alph.insert(line[j]);
        }

        langs.push_back(pair<string, set<char>>(name, alph));
    }

    cin >> line;
    size = line.size();
    unordered_map<char, int> scroll;

    for (size_t i = 0; i < size; ++i) {
        auto iter = scroll.find(line[i]);
        if (iter == scroll.end())
            scroll.insert(pair<char, int>(line[i], 1));
        else
            ++scroll[iter->first];
    }

    double laxity = 0.1 * size;
    double diff;
    bool flag = true;

    for (size_t i = 0; i < amount; ++i) {
        diff = 0;
        for (pair<char, int> curr_pair : scroll) {
            if (diff <= laxity && langs[i].second.find(curr_pair.first) == langs[i].second.end()) {
                diff += curr_pair.second;
            }
        }
        if (diff <= laxity) {
            cout << langs[i].first << endl;
            flag = false;
        }
    }
    if (flag) {
        cout << "NO SOLUTION" << endl;
    }

    return 0;

}
