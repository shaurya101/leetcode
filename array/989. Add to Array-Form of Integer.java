/*
989. Add to Array-Form of Integer

The array-form of an integer num is an array representing its digits in left to right order
For example, for num = 1321, the array form is [1,3,2,1].
Given num, the array-form of an integer, and an integer k, return the array-form of the integer num + k.

Example 1:
Input: num = [1,2,0,0], k = 34
Output: [1,2,3,4]
Explanation: 1200 + 34 = 1234

*/

-------------

// Approach 1
  
class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        List<Integer> list = new LinkedList<>();
        int sum=0;
        int carry=0;

        // traversing num[] from end and adding k's last digit and updating k
        for(int i=num.length-1; i>=0; i--) {
            int a = num[i];
            int b = k%10;
            k/=10;
            sum = (a+b+carry)%10;
            carry = (a+b+carry)/10;
            list.add(0, sum);
        }
        // if k is not zero here, add k and carry and put in list
        while(k!=0) {
            int b = k%10;
            k/=10;
            sum = (b+carry)%10;
            carry = (b+carry)/10;
            list.add(0, sum);
        }

        // incase carry is not zero, it means an extra digit would be there in list
        if(carry != 0)
            list.add(0, carry);
        return list;
    }
}


----------------
  
// Approach 2
  
class Solution {
    public List<Integer> addToArrayForm(int[] num, int k) {
        LinkedList<Integer> answer = new LinkedList<>();
        
        int i=num.length-1; // iterator
        while(i>=0 || k!=0){ // iterates till both num[] is traversed and k is 0
            if(i>=0){ // incase num[] still has aome elements 
                k += num[i];
                i--;
            }
            answer.addFirst(k%10); // add from head of linkedlist
            k /= 10;
        }
        return answer;
    }
}

// we do not want to extract num[] into a number (to add k and change it back to array) as converting an array to a number might go out of range for int or even long!

// APPROACH
// We will iterate num from end till it is traversed completely and we will also consider that k should become 0 when we extract its digits in the loop
// we will add num[i] to k and add it to a linkedList from the head side as we do not want the number to be reversed.
// we will do i-- and k /= 10 respectively in the loop to prepare for next iteration

// Eg: 168+24 = 160+32 (we have added num[i] to k, ie 24+8); we add k%=10 to list, then updating k/=10; Now after i--,we have num[i] at 6, so 6+3 = 9, so add 9 to list. Then add 1. Hence 192 will be added in list
