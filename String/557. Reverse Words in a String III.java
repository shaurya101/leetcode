/*
557. Reverse Words in a String III

Given a string s, reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.

Example 1:

Input: s = "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Example 2:

Input: s = "God Ding"
Output: "doG gniD"

*/

-----------------------------------

class Solution {
    public String reverseWords(String s) {
        char[] s1 = s.toCharArray();
        int i = 0;
        int startWordIndex = 0;
        
        while(i<s1.length){
            while(i<s1.length && s1[i] != ' ') // iterate till we met a space. Also keep in mind IndexOutOfBounds Exception here.
                i++;
            reverse(s1,startWordIndex, i-1);
            startWordIndex = i+1; // next word starts from index after the space
            i++;
        }
        return new String(s1);
    }
    private static void reverse(char[] s1, int i, int j){
        while(i<j){
            char temp = s1[j];
            s1[j--] = s1[i];
            s1[i++] = temp;
        }
    }
}

// keep track of word start index.
// iterate char[] till we meet a space.
// reverse the string
// if no space the entire string will be reversed as the entire string will be considered one word.

