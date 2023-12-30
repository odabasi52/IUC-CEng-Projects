#include <bits/stdc++.h>
using namespace std;

#define ROW 4
#define COL 4

int rowOp[4] = {-1, 0, 1, 0};
int colOp[4] = {0, 1, 0, -1};
int visited[ROW][COL];

bool isValid(int col, int row)
{
    return ( col >= 0 && row >= 0 && col < COL && row < ROW && !visited[row][col]);
}

void DFS(int arr[][COL], int sCol, int sRow)
{
    visited[sRow][sCol] = true;
    cout << arr[sRow][sCol] << " ";

    for (int i = 0; i < 4; i++)
    {
        int nRow = sRow + rowOp[i];
        int nCol = sCol + colOp[i];
        if (isValid(nCol, nRow))
            DFS(arr, nCol, nRow);
    }
}

int main(){
    int grid[ROW][COL] = { { 1, 2, 3, 4 },
                           { 5, 6, 7, 8 },
                           { 9, 10, 11, 12 },
                           { 13, 14, 15, 16 } };
    DFS(grid, 0, 0);
}