// O ( n log(n)) time complexity - O(log n) space complexity

void quickSort(int arr[], int low, int high)
{
    if (low > high)
        return ;
    int ref = arr[high];
    int temp;
    int i = low - 1;
    for (int j = low; j < high; j++)
    {
        if (arr[j] < ref)
        {
            i++;
            temp = arr[j];
            arr[j] = arr[i];
            arr[i] = temp;
        }
    }
    i++;
    temp = arr[high];
    arr[high] = arr[i];
    arr[i] = temp;
    quickSort(arr, low, i - 1);
    quickSort(arr, i + 1, high);
}