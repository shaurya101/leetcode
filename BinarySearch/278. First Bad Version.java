/*
278. First Bad Version

You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.
Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.
You are given an API bool isBadVersion(version) which returns whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Example 1:
Input: n = 5, bad = 4
Output: 4
Explanation:
call isBadVersion(3) -> false
call isBadVersion(5) -> true
call isBadVersion(4) -> true
Then 4 is the first bad version.

Example 2:
Input: n = 1, bad = 1
Output: 1

*/
----------------

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low=1;
        int high=n;
        int index=50;
        
        while(low<=high) {
            int mid = low+(high-low)/2;
            if(!isBadVersion(mid))
                low=mid+1;
            else {
                index=mid; // we store this latest index. This will only be updated once we come across another badVersion value. 
                high=mid-1;
            }
        }
        return index;
    }
}

-----------------
      
// Second approach
      
/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */

public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int low = 1;
        int high = n;
        int mid = 1;
        
        while(low<=high) {
            mid = low+(high-low)/2;
            if(!isBadVersion(mid)) {
                low = mid + 1;
                continue;
            }
            else if(!isBadVersion(mid-1)) // if prev condition isnt true then mid is not a bad version so we do not need to check for bad version now
                return mid;
            else
                high = mid - 1;
        }
        return -1;
    }
}

