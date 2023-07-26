/*
852. Peak Index in a Mountain Array

An array arr a mountain if the following properties hold:

arr.length >= 3
There exists some i with 0 < i < arr.length - 1 such that:
arr[0] < arr[1] < ... < arr[i - 1] < arr[i] 
arr[i] > arr[i + 1] > ... > arr[arr.length - 1]
Given a mountain array arr, return the index i such that arr[0] < arr[1] < ... < arr[i - 1] < arr[i] > arr[i + 1] > ... > arr[arr.length - 1].

You must solve it in O(log(arr.length)) time complexity.

Example 1:
Input: arr = [0,1,0]
Output: 1

Example 2:
Input: arr = [0,2,1,0]
Output: 1

*/


-------------

// T : O(log n)
// S : O(1)

class Solution {
    public int peakIndexInMountainArray(int[] arr) {
        int low = 0;
        int high = arr.length;
        while(low<high) {
            int mid = low + (high-low)/2;
            if(arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1])
                return mid;
            if(arr[mid]>arr[mid+1])
                high = mid;
            else
                low = mid;
        }
        return low;
    }
}
