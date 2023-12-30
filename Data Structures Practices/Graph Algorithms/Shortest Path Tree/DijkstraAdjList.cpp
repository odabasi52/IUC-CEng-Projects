#include <bits/stdc++.h>
using namespace std;

class vertex{
    public:
        bool visited;
        vector<pair<vertex*, int>> adjs;
        string label;

        vertex(string label)
        {
            this->label = label;
        }
};

class Graph{
    vector<vertex*> graph;
    public:
        void addNode(string label)
        {
            vertex* v = new vertex(label);
            graph.push_back(v);
        }

        void addEdge(string s, string t, int w)
        {
            vertex* target;
            vertex* source;

            for (vertex* v: graph)
            {
                if (v->label == s)
                    source = v;
                if (v->label == t)
                    target = v;
            }
            target->adjs.push_back({source, w});
            source->adjs.push_back({target, w});
        }

        int getWeight(vertex* s, vertex* t)
        {
            for (auto& p: s->adjs)
                if (p.first->label == t->label)
                    return p.second;
            return -1; 
        }

        void djikstra(string source)
        {
            int n = graph.size();
            int min;
            vertex* min_v;
            map<string, bool> visited;
            map<string, int> distance;

            for (vertex* v: graph)
                distance[v->label] = INT_MAX;
            distance[source] = 0;
            for (int i = 0; i < n - 1; i++)
            {
                min = INT_MAX;
                for (vertex* v: graph)
                    if (!visited[v->label] && distance[v->label] <= min)
                        min = distance[v->label], min_v = v;
                visited[min_v->label] = true;
                
                for (vertex* v: graph)
                {
                    int w = getWeight(v, min_v);
                    if (!visited[v->label] && w != -1 && w + distance[min_v->label] < distance[v->label])
                        distance[v->label] = w + distance[min_v->label];
                }
            }

            printer(distance, source);
        }

        void printer(map<string, int> distance, string source)
        {
            for (vertex* v: graph)
                cout << source << " - " << v->label << " ===== " << distance[v->label] << endl;
        }
};

int main()
{
    Graph g;
    g.addNode("A");
    g.addNode("B");
    g.addNode("C");
    g.addNode("D");
    g.addNode("E");
    g.addEdge("A", "D", 4);
    g.addEdge("A", "C", 2);
    g.addEdge("D", "C", 6);
    g.addEdge("E", "D", 4);
    g.addEdge("B", "D", 7);
    g.addEdge("B", "E", 2);
    g.djikstra("A");
}