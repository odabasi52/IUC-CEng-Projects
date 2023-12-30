// O(n) time complexity - O(1) space complexity
#include <vector>
using std::vector;

int linear_search(vector<int>& nums, int target) {
    for (int i = 0; i < nums.size(); i++)
        if (nums[i] == target)
            return (i);
    return (-1);
}