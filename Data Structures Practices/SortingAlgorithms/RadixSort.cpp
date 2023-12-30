// O (maxDigit* (n + maxValue - MinValue)) time complexity - O(n + (maxValue - minValue)) space complexity

#include <bits/stdc++.h>
using std::vector;

void countSort(vector<int>& nums, int div, int size)
{
    vector<int> res(size, 0);
    int i; 
    int count[10] = {0};

    for (i = 0; i < size; i++)
        count[(nums[i]/div) % 10]++;
    for (i = 1; i < 10; i++)
        count[i] += count[i - 1];
    for (i = size - 1; i >= 0; i--)
    {
        res[count[(nums[i]/div) % 10] - 1] = nums[i];
        count[(nums[i]/div) % 10]--;
    }
    for (i = 0; i < size; i++)
        nums[i] = res[i];
}
void radixSort(vector<int>& nums)
{
    int a = *max_element(nums.begin(), nums.end());
    int size = nums.size();
    for (int div = 1; a / div > 0; div *= 10)
        countSort(nums, div, size);
}