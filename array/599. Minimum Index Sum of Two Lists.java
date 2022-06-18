/*
599. Minimum Index Sum of Two Lists

Suppose Andy and Doris want to choose a restaurant for dinner, and they both have a list of favorite restaurants represented by strings.
You need to help them find out their common interest with the least list index sum. If there is a choice tie between answers, output all of them with no order requirement. You could assume there always exists an answer.

Example 1:
Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["Piatti","The Grill at Torrey Pines","Hungry Hunter Steakhouse","Shogun"]
Output: ["Shogun"]
Explanation: The only restaurant they both like is "Shogun".

Example 2:
Input: list1 = ["Shogun","Tapioca Express","Burger King","KFC"], list2 = ["KFC","Shogun","Burger King"]
Output: ["Shogun"]
Explanation: The restaurant they both like and have the least index sum is "Shogun" with index sum 1 (0+1).

*/
------------------------------

class Solution {
    public String[] findRestaurant(String[] list1, String[] list2) {
        if (list1.length > list2.length) {
            return findRestaurant(list2, list1);
        } // to use lesser space in HashMap
        
        HashMap<String, Integer> map = new HashMap<>(list1.length);
        ArrayList<String> list3 = new ArrayList<>();
        
        // list1 to hashmap
        for(int i=0; i<list1.length; i++)
            map.put(list1[i], i);
        
        int minSum = Integer.MAX_VALUE; // please dont take it as 0. if we want to find min value, assume it to be the max value initially
        
        for(int i=0; i<list2.length; i++){
            if(map.containsKey(list2[i]))
            {
                int sum = map.get(list2[i]) + i;
                if(sum<minSum){
                    list3 = new ArrayList<>();
                    // Instead of clearing any previous arraylist(list3.clear()), we just form a new arraylist
                    minSum = sum; // the next loop will also run so we dont have to write add statement here 
                }
                 if(sum == minSum)
                    list3.add(list2[i]); 
            }
        }
        // String[] list = list3.toArray(new String[list3.size()]); Arraylist to array
        return list3.toArray(new String[list3.size()]);
    }
}
