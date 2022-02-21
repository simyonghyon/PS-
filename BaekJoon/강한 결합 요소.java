#include<cstdio>
#include<vector>
#include<stack>
#include<algorithm>
#include<utility>
#define din2(a,b) scanf("%d%d", &a,&b)
#define pb(a) push_back(a)
using namespace std;
 
typedef vector<int> vi;
typedef vector<bool> vb;
typedef vector<pair<int, int> > vpii;
typedef stack<int> si;
const int S = 11111;
vi scc[S]; // Strongly Connected Component
int num_scc; // # of SCC
vb VIS; // vertex visit check
vi DG[S];  // 1: Directed Graph
vi RDG[S]; // 2: Reverse-Directed Graph
si STACK;  // 3: Stack
int V, E;
 
void DG_dfs(int vertex) {
    if (VIS[vertex]) return;
    VIS[vertex] = true;
    for (int i = 0; i < DG[vertex].size(); i++) {
        int here = DG[vertex][i];
        if (VIS[here]) continue;
        DG_dfs(here);
    }
    STACK.push(vertex);
    return;
}
 
void RDG_dfs(int vertex) {
    if (VIS[vertex]) return;
    VIS[vertex] = true;
    for (int i = 0; i < RDG[vertex].size(); i++) {
        int here = RDG[vertex][i];
        if (VIS[here]) continue;
        RDG_dfs(here);
    }
    scc[num_scc].pb(vertex);
    return;
}
 
void make_scc() {
    VIS.clear(); 
    VIS = vb(V + 1, false);
    while (!STACK.empty()) {
        int here = STACK.top(); STACK.pop();
        if (VIS[here]) continue;
        RDG_dfs(here);    
        num_scc++;
    }
    for (int i = 0; i < num_scc; i++) {
        sort(scc[i].begin(), scc[i].end());
    }
    return;
}
 
void kosaraju() {
    for(int vertex = 1 ; vertex <= V ; vertex++) DG_dfs(vertex);
    make_scc();
}
 
void answer() {
    printf("%d\n", num_scc);
    vpii scc_index; // first low-vertex second scc-group index
    for (int group = 0; group < num_scc; group++) {
        scc_index.pb(make_pair(scc[group][0], group));
    }
    sort(scc_index.begin(), scc_index.end() );
    for (int group = 0; group < scc_index.size() ; group++) {
        for (auto p : scc[scc_index[group].second]) {
            printf("%d ",p);
        }
        puts("-1");
    }
}
 
int main() {
    din2(V, E);
    VIS = vb(V+1, false);
    for (int i = 0; i < E; i++) {
        int U, V;
        din2(U, V);
        DG[U].pb(V);
        RDG[V].pb(U);
    } // input 
 
    kosaraju();
    answer();
 
    return 0;
}


//출처: https://wondy1128.tistory.com/28 [잡블로그]