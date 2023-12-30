#include <bits/stdc++.h>
using namespace std;

#define ROW 4
#define COL 4

int rowOps[4] = {-1, 0, 1, 0};
int colOps[4] = {0, 1, 0, -1};
bool visited[ROW][COL];

bool isValid(int row, int col)
{
    return (row >= 0 && col >= 0 && row < ROW && col < COL && !visited[row][col]);
}

void BFS(int sCol, int sRow, int arr[][COL]){
    vector<int*> que;
    que.push_back(new int[2]{sCol, sRow});
    visited[sRow][sCol] = true;

    while (!que.empty())
    {
        int col = que.front()[0];
        int row = que.front()[1];
        cout << arr[row][col] << " ";
        que.erase(que.begin());
        
        for (int i = 0; i < 4; i++){
            int new_row = row + rowOps[i];
            int new_col = col + colOps[i];
            if (isValid(new_row, new_col)){
                que.push_back(new int[2]{new_col, new_row});
                visited[new_row][new_col] = true;
            }
        }
    }

}


int main(){
    int grid[ROW][COL] = { { 1, 2, 3, 4 },
                           { 5, 6, 7, 8 },
                           { 9, 10, 11, 12 },
                           { 13, 14, 15, 16 } };
    BFS(0, 0, grid);
}