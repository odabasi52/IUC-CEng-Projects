#include <bits/stdc++.h>
using namespace std;


void printer(vector<int>& distance, int s)
{
    for (int i = 0; i < distance.size(); i++)
    {
        cout <<  s << " - " << i << " ----- " << distance[i] << endl;
    }
}

void dijkstra(vector<vector<int>>& graph, int source)
{
    int n = graph.size();
    int m;
    int min;
    vector<bool> visited(n, false);
    vector<int> distance(n, INT_MAX);

    distance[source] = 0;
    for (int i = 0; i < n - 1; i++)
    {
        min = INT_MAX;
        for (int v = 0; v < n; v++)
            if (!visited[v] && distance[v] <= min)
                min = distance[v], m = v;
        visited[m] = true;

        for (int v = 0; v < n; v++)
            if (!visited[v] && graph[m][v] != 0 && (distance[m] + graph[m][v]) < distance[v])
                distance[v] = (distance[m] + graph[m][v]);
    }
    printer(distance, source);
}

int main()
{
    vector<vector<int>> graph = {
        {0, 2, 6, 0, 0, 0, 0},
        {2, 0, 0, 5, 0, 0, 0},
        {6, 0, 0, 8, 0, 0, 0},
        {0, 5, 8, 0, 10, 15, 0},
        {0, 0, 0, 10, 0, 6, 2},
        {0, 0, 0, 15, 6, 0, 6},
        {0, 0, 0, 0, 2, 6, 0}
    };

    dijkstra(graph, 0);
}