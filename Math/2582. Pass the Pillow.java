/*
2582. Pass the Pillow

There are n people standing in a line labeled from 1 to n. The first person in the line is holding a pillow initially. Every second, the person holding the pillow passes it to the next person standing in the line. Once the pillow reaches the end of the line, the direction changes, and people continue passing the pillow in the opposite direction.

For example, once the pillow reaches the nth person they pass it to the n - 1th person, then to the n - 2th person and so on.
Given the two positive integers n and time, return the index of the person holding the pillow after time seconds.

Example 1:
Input: n = 4, time = 5
Output: 2
Explanation: People pass the pillow in the following way: 1 -> 2 -> 3 -> 4 -> 3 -> 2.
Afer five seconds, the pillow is given to the 2nd person.

Example 2:
Input: n = 3, time = 2
Output: 3
Explanation: People pass the pillow in the following way: 1 -> 2 -> 3.
Afer two seconds, the pillow is given to the 3rd person.

*/

-------------------
  
// Approach 1: T: O(1), S: O(1)
// It takes 2*n-2 time to come back to initial index=1.
// So when we take mod of time with (2*n-2), we are assuming all possible round trips have been made and we are at the final round.
// If time < n, return time+1 which will be the answer index
// Else return n-(time-(n-1)). Here n-1 is time to travel on side, so this we subtract from time. Now our time has only one side trip seconds left. This new time is subtracted from n
  
class Solution {
    public int passThePillow(int n, int time) {
        time = time % (2*n-2);
        if(time < n)
            return time+1;
        else
            return n-(time-(n-1));
    }
}

  
-------------------
  
// Approach 2: T: O(1), S: O(1)
// if time<n, we return time+1 which will be the answer index
// To go from 1 to n, it takes n-1 time.
// So we say count time/n-1, and if this is even then we are at initial index=1, else if i=this is odd we are at index = n.
// If count is even, we return time%(n-1); else for odd n-time%(n-1)

class Solution {
    public int passThePillow(int n, int time) {
        if(time<n)
            return time+1;
        int count = time/(n-1);
        int rem = time%(n-1);
        if(count%2 == 0)
            return rem+1;
        return n-rem;
    }
}
