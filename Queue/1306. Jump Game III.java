/*
1306. Jump Game III

Given an array of non-negative integers arr, you are initially positioned at start index of the array. 
When you are at index i, you can jump to i + arr[i] or i - arr[i], check if you can reach any index with value 0.
Notice that you can not jump outside of the array at any time.


Example 1:
Input: arr = [4,2,3,0,3,1,2], start = 5
Output: true
Explanation: 
All possible ways to reach at index 3 with value 0 are: 
index 5 -> index 4 -> index 1 -> index 3 
index 5 -> index 6 -> index 4 -> index 1 -> index 3

Example 2:
Input: arr = [4,2,3,0,3,1,2], start = 0
Output: true 
Explanation: 
One possible way to reach at index 3 with value 0 is: 
index 0 -> index 4 -> index 1 -> index 3

*/


-------------

// Approach- T: O(N), S: O(N)


class Solution {
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n]; // To keep track of visited indices
        
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start); // Start the BFS from the given start index
        
        while (!queue.isEmpty()) {
            int currentIndex = queue.poll(); // Get the current index from the queue
            
            if (arr[currentIndex] == 0) {
                return true; // If we reach a zero, we can reach the target
            }
            
            visited[currentIndex] = true; // Mark the current index as visited
            
            // Calculate the left and right indices based on the current value
            int leftIndex = currentIndex - arr[currentIndex];
            int rightIndex = currentIndex + arr[currentIndex];
            
            // Check if the left index is valid and not visited
            if (leftIndex >= 0 && !visited[leftIndex]) {
                queue.offer(leftIndex); // Add it to the queue for exploration
            }
            
            // Check if the right index is valid and not visited
            if (rightIndex < n && !visited[rightIndex]) {
                queue.offer(rightIndex); // Add it to the queue for exploration
            }
        }
        
        return false; // If no zero is reached, we can't reach the target
    }
}
