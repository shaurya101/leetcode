/*
1061. Lexicographically Smallest Equivalent String

You are given two strings of the same length s1 and s2 and a string baseStr.
We say s1[i] and s2[i] are equivalent characters.

For example, if s1 = "abc" and s2 = "cde", then we have 'a' == 'c', 'b' == 'd', and 'c' == 'e'.
Equivalent characters follow the usual rules of any equivalence relation:
Reflexivity: 'a' == 'a'.
Symmetry: 'a' == 'b' implies 'b' == 'a'.
Transitivity: 'a' == 'b' and 'b' == 'c' implies 'a' == 'c'.

For example, given the equivalency information from s1 = "abc" and s2 = "cde", "acd" and "aab" are equivalent strings of baseStr = "eed", and "aab" is the lexicographically smallest equivalent string of baseStr.

Return the lexicographically smallest equivalent string of baseStr by using the equivalency information from s1 and s2.
 

Example 1:
Input: s1 = "parker", s2 = "morris", baseStr = "parser"
Output: "makkek"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [m,p], [a,o], [k,r,s], [e,i].
The characters in each group are equivalent and sorted in lexicographical order.
So the answer is "makkek".

Example 2:
Input: s1 = "hello", s2 = "world", baseStr = "hold"
Output: "hdld"
Explanation: Based on the equivalency information in s1 and s2, we can group their characters as [h,w], [d,e,o], [l,r].
So only the second letter 'o' in baseStr is changed to 'd', the answer is "hdld".

*/

======

// Approach
// we take a char array, where we will hold the lexically smallest char for that char.
// First we will be putting 'a' in char[0], 'b' in char[1]...'z'in char[26] and so on.
// Then as we traverse s1, s2; we will put the lexically smallest for that char
// Eg, char[0] will hold the lexSmall for 'a'.
// Note that, because of this we can be sure that char[0] either contains 'a' or the lexSmall for 'a'. But it cannot be lexically larger than 'a'.
// Hence we need not compare the chars in s1 or s2, but only the chars[s1.character..] and chars[s2.characters..].
// When s1 and s2 are traversed, we will have the lexically smallest charcters for a to z in chars[].
// Now we store lexically smallest characters of baseStr, by picking them up from chars[] 

class Solution {
    public String smallestEquivalentString(String s1, String s2, String baseStr) {
        char[] chars = new char[26];
        for(char i='a'; i<='z'; i++)
            chars[i-'a'] = i;

        for(int i=0; i<s1.length(); i++) {
            char c1 = chars[s1.charAt(i)-'a'];
            char c2 = chars[s2.charAt(i)-'a'];

            char lexSmall = c1<c2?c1:c2;
            char lexBig = c1<c2?c2:c1;

            for(int j=0; j<26; j++) {
                if(chars[j] == lexBig)
                    chars[j] = lexSmall;
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<baseStr.length(); i++) {
            sb.append(chars[baseStr.charAt(i)-'a']);
        }
        return sb.toString();
    }
}
