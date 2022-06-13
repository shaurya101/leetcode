/*

345. Reverse Vowels of a String

Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u', and they can appear in both cases.

Example 1:

Input: s = "hello"
Output: "holle"
Example 2:

Input: s = "leetcode"
Output: "leotcede"

*/

----------------------

class Solution {
    public String reverseVowels(String s) {
        char[] s1 = s.toCharArray();
        int i=0;
        int j = s1.length-1;
        // i and j are two pointers which we use to iterate char[]
        
        while(i<j){
            while(i<s1.length && !isVowel(s1[i])) // aviod ArrayIndexOutOfBound exception here
                i++;
            while(j>=0 && !isVowel(s1[j])) // // aviod ArrayIndexOutOfBound exception here
                j--;
            if(i<j) // After the 2 while loops above i may be > j now. To avoid double swapping(essentially = no swap) we use check here.
                swap(s1, i, j);
            i++;
            j--;
        }
        return new String(s1);
    }
    private static boolean isVowel(char c){ // tocheck if vowel
        if(c == 'a' || c == 'A' ||
           c == 'e' || c == 'E' ||
           c == 'i' || c == 'I' ||
           c == 'o' || c == 'O' ||
           c == 'u' || c == 'U' )
            return true;
        return false;
    }
    private static void swap(char[] s1, int i, int j){ // swap
        char temp = s1[j];
        s1[j] = s1[i];
        s1[i] = temp;
    }
}
