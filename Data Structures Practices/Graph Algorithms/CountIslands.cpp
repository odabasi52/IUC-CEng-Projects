#include <bits/stdc++.h>
using namespace std;

int rowOps[4] = {-1, 0, 1, 0};
int colOps[4] = {0, 1, 0, -1};

bool isValid(int row, int col, vector<vector<char>>& grid, vector<vector<bool>>& visited)
{
    int R = grid.size();
    int C = grid[0].size();
    return (row >= 0 && row < R && col >= 0 && col < C && !visited[row][col] && grid[row][col] == '1');
}

void DFS(vector<vector<char>>& grid, vector<vector<bool>>& visited, int row, int col)
{
    visited[row][col] = true;
    for (int i = 0; i < 4; i++)
    {
        int nRow = row + rowOps[i];
        int nCol = col + colOps[i];
        if (isValid(nRow, nCol, grid, visited))
            DFS(grid, visited, nRow, nCol);
    }
}

void BFS(vector<vector<char>>& grid, vector<vector<bool>>& visited, int row, int col)
{
    vector<int*> que;
    que.push_back(new int[2]{row, col});
    visited[row][col] = true;
    
    while (!que.empty())
    {
        int cur_row = que.front()[0];
        int cur_col = que.front()[1];
        que.erase(que.begin());

        for (int i = 0; i < 4; i++)
        {
            int next_row = rowOps[i] + cur_row;
            int next_col = colOps[i] + cur_col;
            if (isValid(next_row, next_col, grid, visited))
            {
                visited[next_row][next_col] = true;
                que.push_back(new int[2]{next_row, next_col});
            }
        }
    }
}

int countBFS(vector<vector<char>>& grid)
{
    int rows = grid.size();
    int cols = grid[0].size();
    vector<vector<bool>> visited(rows, vector<bool>(cols, false));     

    int count = 0;
    for (int i = 0; i < grid.size(); i++)
    {
        for (int j = 0; j < grid[0].size(); j++)
        {
            if (isValid(i, j, grid, visited))
            {
                BFS(grid, visited, i, j);
                count++;
            }
        }
    }
    return count;
}

int countDFS(vector<vector<char>>& grid) {
    int rows = grid.size();
    int cols = grid[0].size();
    vector<vector<bool>> visited(rows, vector<bool>(cols, false));     

    int count = 0;
    for (int i = 0; i < grid.size(); i++)
    {
        for (int j = 0; j < grid[0].size(); j++)
        {
            if (isValid(i, j, grid, visited))
            {
                DFS(grid, visited, i, j);
                count++;
            }
        }
    }
    return count;
}

int main()
{
    vector<vector<char>> grid = {
        {'1','0','1','1','0'},
        {'1','1','0','0','0'},
        {'0','1','0','0','0'},
        {'0','0','0','1','1'}
    };

    cout << countDFS(grid) << endl << countBFS(grid);
}