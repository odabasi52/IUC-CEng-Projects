#include <bits/stdc++.h>
using std::vector;

long ft_max(long a, long b)
{
    return (a > b?a:b);
}

long arrayManipulation(int n, vector<vector<int>> queries) {
    vector<int> prefixSum(n + 1, 0);
    long currentVal = 0;
    long maxVal     = 0;
    
    for (vector<int> q: queries)
    {
        prefixSum[q[0] - 1] += q[2];
        prefixSum[q[1]] -= q[2];
    }
    for (int i: prefixSum)
    {
        currentVal += i;
        maxVal = ft_max(currentVal, maxVal);
    }
    return (maxVal);
}