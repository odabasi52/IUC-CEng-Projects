#include <bits/stdc++.h>
using std::vector;

int ft_max(int a, int b)
{
    return (a>b?a:b);
}

void    ft_fill(vector<vector<int>>&dt, vector<int> rows, vector<int> cols)
{
    for (int row = 1; row < rows.size() + 1; row++)
    {
        for (int col = 1; col < cols.size() + 1; col++)
        {
            if (rows[row - 1] == cols[col - 1])
                dt[row][col] = dt[row - 1][col - 1] + 1;
            else
                 dt[row][col] = ft_max(dt[row - 1][col], dt[row][col - 1]);
        }
    }
}

vector<int> longestCommonSubsequence(vector<int> a, vector<int> b) {
    vector<int> ar;
    int rs = a.size() + 1;
    int cs = b.size() + 1;

    vector<vector<int>>dt(rs, vector<int>(cs, 0));
    ft_fill(dt, a, b);
    rs--;
    cs--;
    while (rs > 0 && cs > 0)
    {
        if (dt[rs - 1][cs] == dt[rs][cs - 1] && dt[rs][cs] == (dt[rs - 1][cs - 1] + 1))
        {
            rs--;
            cs--;
            ar.push_back(a[rs]);
        }
        else if (dt[rs - 1][cs] > dt[rs][cs - 1])
            rs--;
        else
            cs--;
    }
    reverse(ar.begin(), ar.end());
    return (ar);
}
