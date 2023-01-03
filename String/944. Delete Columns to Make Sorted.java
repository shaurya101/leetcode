/*
944. Delete Columns to Make Sorted

You are given an array of n strings strs, all of the same length.
The strings can be arranged such that there is one on each line, making a grid. For example, strs = ["abc", "bce", "cae"] can be arranged as:

abc
bce
cae
You want to delete the columns that are not sorted lexicographically. In the above example (0-indexed), columns 0 ('a', 'b', 'c') and 2 ('c', 'e', 'e') are sorted while column 1 ('b', 'c', 'a') is not, so you would delete column 1.
Return the number of columns that you will delete.

Example 1:
Input: strs = ["cba","daf","ghi"]
Output: 1
Explanation: The grid looks as follows:
  cba
  daf
  ghi
Columns 0 and 2 are sorted, but column 1 is not, so you only need to delete 1 column.

*/

============


class Solution {
    public int minDeletionSize(String[] strs) {
        int count = 0;

        int wordLength = strs[0].length();
        // for each index of a word we will check the order for all words for the particular index
        // traversing a char in a word
        for(int i = 0; i<wordLength; i++) {
            // traversing the words and checking for the particular index
            for(int j = 1; j<strs.length; j++) {
                if(strs[j-1].charAt(i) > strs[j].charAt(i)) {
                    count++;
                    break;
                }
            }
        }

        return count;
    }
}

// Approach
// Since all words are of same length, we will traverse the chars of a word in outer loop
// In inner loop, we will check if order is correct for that particular index for all words
