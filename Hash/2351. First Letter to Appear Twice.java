/*
2351. First Letter to Appear Twice


*/

-----------------------

class Solution {
    public char repeatedCharacter(String s) {
        HashSet<Character> set = new HashSet<>();
        
        for(int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if(set.contains(c))
                return c;
            set.add(c);
        }
        return 'a'; // as it is guaranteed there will be a repeated char, this line will never run.
    }
}
// Add char in set
// check if it already exists and return
