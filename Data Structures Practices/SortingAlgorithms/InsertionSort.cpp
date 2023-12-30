// O ( n^2 ) time complexity - O(1) space complexity

void insertionSort(int arr[], int n)
{
    for (int i = 1; i < n; i++)
    {
        int j = i;
        int key = arr[i];
        while (key < arr[--j] && j >= 0)
            arr[j + 1] = arr[j];
        arr[j + 1] = key;
    }
}