# Prefix Sum
```
It simply works by instead of applying operations to all elements,
applying it to first element and apply opposite operations to 
next elemenet after last element.
By doing these we are simply storing differences 
between consecutive two elements

Example:
    - [0, 0, 0, 0, 0, 0, 0]
    - add +2 to index 1 to 4
    - [0, 2, 2, 2, 2, 0, 0]
    - or use prefix sum to store differences
    - [0, 2, 0, 0, 0, -2, 0]
    - [1] is ([0] + 2) and [5] is ([4] - 2)
```
