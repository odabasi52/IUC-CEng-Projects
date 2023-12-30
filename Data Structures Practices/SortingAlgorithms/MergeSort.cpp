// O ( n log(n)) time complexity - O(n) space complexity

void merge(int left[], int right[], int mai[], int size)
{
    int l = 0;
    int r = 0;
    int i = 0;
    int lmax = size / 2;
    int rmax = size - lmax;
    while (l < lmax && r < rmax)
    {
        if (left[l] < right[r])
            mai[i++] = left[l++];
        else
            mai[i++] = right[r++];
    }
    while (l < lmax)
        mai[i++] = left[l++];
    while (r < rmax)
        mai[i++] = right[r++];
}

void merge_sort(int mai[], int size)
{
    int mid = size / 2;
    int left[mid];
    int right[size - mid];

    if (size <= 1) return ;
    for (int i = 0; i < size; i++)
    {
        if (i < mid)
            left[i] = mai[i];
        else
            right[i - mid] = mai[i];
    }
    merge_sort(left, mid);
    merge_sort(right, size - mid);
    merge(left, right, mai, size);
}