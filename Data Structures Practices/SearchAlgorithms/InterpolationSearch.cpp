// O(log(log n)) time complexity - O(1) space complexity
#include <vector>
using std::vector;

int interpolation_search(vector<int>& nums, int target) {
    int high = nums.size() - 1;
    int low = 0;
    int probe = 0;
    
    while (target >= nums[low] && target <= nums[high] && low <= high)
    {
        if (low != high)
            probe = low + ((high - low) * (target - nums[low]) / (nums[high] - nums[low]));
        if (nums[probe] == target)
            return (probe);
        else if (nums[probe] < target)
            low = probe + 1;
        else
            high = probe - 1;
    }
    return (-1);
}