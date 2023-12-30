// O(log n) time complexity - O(1) space complexity
#include <vector>
using std::vector;

int binary_search(vector<int>& nums, int target) {
    int mid;
    int high = nums.size() - 1;
    int low = 0;
        
    while (low <= high && target >= nums[low] && target <= nums[high]) {
        mid = low + (high - low) / 2;

        if (nums[mid] == target)
            return mid;
        else if (nums[mid] > target)
            high = mid - 1;
        else
            low = mid + 1;
    }
    return -1;
}
