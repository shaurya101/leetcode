/*
1207. Unique Number of Occurrences

Given an array of integers arr, return true if the number of occurrences of each value in the array is unique, or false otherwise.

Example 1:
Input: arr = [1,2,2,1,1,3]
Output: true
Explanation: The value 1 has 3 occurrences, 2 has 2 and 3 has 1. No two values have the same number of occurrences.

Example 2:
Input: arr = [1,2]
Output: false

*/

-------------------------------
  
class Solution {
    public boolean uniqueOccurrences(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        // Key = Number in array, Value = Number of occurence
        
        for(int i=0;i<arr.length; i++){
            if(!map.containsKey(arr[i]))
                map.put(arr[i], 1);
            else
                map.put(arr[i], map.get(arr[i])+1);
        }
        
        HashSet<Integer> set = new HashSet<>();
        // Iterate the HashMap
        for(int values:map.values()){
            if(set.contains(values)) // if any value already present, then it is not unique
                return false;
            set.add(values);
        }
        return true;
    }
}

// Another way to compare if values in HashMap are unique-
//  Set<Integer> set = new HashSet<>(map.values());
//  return map.size() == set.size();
