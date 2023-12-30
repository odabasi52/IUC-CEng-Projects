#include <bits/stdc++.h>
using namespace std;

class vertex{
    public:  
        bool visited = false;
        string label;
        vector<vertex*> adjs;
};

class Graph{
    public:
        vector<vertex*> vertices;

        void addVertex(string s)
        {
            vertex *v = new vertex();
            v->label = s;
            vertices.push_back(v);
        }

        void addEdge(string v, string t)
        {
            vertex* v1 = nullptr;
            vertex* t1 = nullptr;

            for (vertex *ver: vertices){
                if (ver->label == v)
                    v1 = ver;
                if (ver->label == t)
                    t1 = ver;
            }
            v1->adjs.push_back(t1);
            t1->adjs.push_back(v1);
        }

        void DFS(vertex* v)
        {
            v->visited = true;
            cout << v->label << " ";
            for (vertex *adj: v->adjs){
                if (!adj->visited)
                    DFS(adj);
            }
        }
};

int main()
{
    Graph g;
    g.addVertex("0");
	g.addVertex("1");
	g.addVertex("2");
	g.addVertex("3");
	g.addVertex("4");
	g.addEdge("0","1");
	g.addEdge("0", "2");
	g.addEdge("0", "3");
	g.addEdge("1", "0");
    g.addEdge("2", "0");
    g.addEdge("2","4");
	g.addEdge("2","3");
    g.addEdge("3","0");
    g.addEdge("3","2");
    g.addEdge("4","2");

	cout << "Deep First Search: ";
	g.DFS(g.vertices[0]);
}