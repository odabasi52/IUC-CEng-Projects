# LCS
```
Instead of getting all subsequences and check if it is
longest common subsequence, start from first element and
go one by one and get rid of elements.

Example:
LCS([3, 1, 4], [3, 4, 6])
If elements are equal we delete both and add + 1
1 + LCS([1, 4], [4, 6])
If not equal then we divide our problem
[1, 4], [4, 6]
1- [4], [4, 6] 
2- [1, 4], [6]
1st one returns 1 and second one returns 0 we get max of them
1 + max(1, 0) = 2

Table Method:
         3   1   4
      0  0   0   0
    3 0  1*  1   1
    4 0  1   1   2*
    6 0  1   1   2
If equals add + 1 to top left else get max of left and top.
Then start from end go to max side if both side are max go left.
After that if left and top is equal it is your element, go top left.
I put stars on our LCS elements. [3, 4]
```
