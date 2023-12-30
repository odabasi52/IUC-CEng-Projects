#include <bits/stdc++.h>
using namespace std;

class Graph{
    int data;
    vector<vector<int>> adjs;

    public:
        void createGraph(int v)
        {
            adjs.resize(v);
        }

        void addEdge(int v, int t)
        {
            adjs[v].push_back(t);
        }

        void BFS(int start)
        {
            vector<bool> visited(adjs.size(), false);
            vector<int> queue;
            queue.push_back(start);
            visited[start] = true;

            while(!queue.empty()){
                int currentNode = queue.front();
                cout << currentNode << " ";
                queue.erase(queue.begin());

                for (int adj_num : adjs[currentNode]){
                    if (!visited[adj_num]){
                        queue.push_back(adj_num);
                        visited[adj_num] = true;
                    }
                }
            }
        }
};

int main(){
    Graph g;
    g.createGraph(5);
    g.addEdge(0, 1);
    g.addEdge(0, 2);
    g.addEdge(1, 2);
    g.addEdge(1, 3);
    g.addEdge(2, 1);
    g.addEdge(2, 3);
    g.addEdge(3, 1);
    g.addEdge(3, 4);
    g.addEdge(4, 2);
    g.addEdge(4, 3);

 
    cout << "Following is Breadth First Traversal\n";
    g.BFS(0);
}