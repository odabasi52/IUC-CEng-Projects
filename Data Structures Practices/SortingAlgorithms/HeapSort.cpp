// O ( n log(n)) time complexity - O(1) space complexity

#include <bits/stdc++.h>
using std::vector;

void heapify(vector<int>& arr, int n, int i)  
{
    int biggest = i;
    int left = (2*i) + 1;
    int right = (2*i) + 2;
      
    //if there is child node and it is bigger than root
    if (left < n && arr[biggest] < arr[left])
        biggest = left;
    if (right < n && arr[biggest] < arr[right])
        biggest = right;
      
    // if root is not biggest swap and sort them
    if (biggest != i)
    {
        int temp = arr[i];
        arr[i] = arr[biggest];
        arr[biggest] = temp;
        heapify(arr, n, biggest);
    }
}

void buildHeap(vector<int>& arr, int n)  
{
    for (int i = (n / 2) - 1; i >= 0; i--)
        heapify(arr, n, i);
}

void heapSort(vector<int>& arr, int n)
{
    buildHeap(arr, n);
    for (int i = n - 1; i >= 0; i--)
    {
        int temp = arr[0];
        arr[0] = arr[i];
        arr[i] = temp;
        heapify(arr, i, 0);
    }
}