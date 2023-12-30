// O ( n^2 ) time complexity - O(1) space complexity

void bubbleSort(int arr[], int n)
{  
    bool swap = true;
    for (int i = 0; i < n - 1; i++)
    {
        swap = false;			
        for (int j = 0; j < n - i - 1; j++)
        {
            if (arr[j] > arr[j + 1])
            {
                swap = true;
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
        if (!swap)
            break;
    }
}