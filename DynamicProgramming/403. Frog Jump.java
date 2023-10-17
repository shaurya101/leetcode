/*
403. Frog Jump

A frog is crossing a river. The river is divided into some number of units, and at each unit, there may or may not exist a stone. 
The frog can jump on a stone, but it must not jump into the water.

Given a list of stones positions (in units) in sorted ascending order, determine if the frog can cross the river by landing on the 
last stone. Initially, the frog is on the first stone and assumes the first jump must be 1 unit.

If the frog's last jump was k units, its next jump must be either k - 1, k, or k + 1 units. The frog can only jump in the forward direction.

 
Example 1:
Input: stones = [0,1,3,5,6,8,12,17]
Output: true
Explanation: The frog can jump to the last stone by jumping 1 unit to the 2nd stone, then 2 units to the 3rd stone, then 2 units to the 
4th stone, then 3 units to the 6th stone, 4 units to the 7th stone, and 5 units to the 8th stone.

Example 2:
Input: stones = [0,1,2,3,4,8,9,11]
Output: false
Explanation: There is no way to jump to the last stone as the gap between the 5th and 6th stone is too large.

*/


-------------------

// Approach : T: O(n^2), S: O(n^2)
// The time complexity of the provided dynamic programming code with memoization for the "Frog Jump" problem is O(n^2), where 'n' is 
// the number of stones. This is because for each stone, the code explores up to three jump possibilities (jumpSize - 1, jumpSize, 
// and jumpSize + 1), and for each jump, it may iterate through the memoization sets. In the worst case, it can explore up to O(n)
// possibilities for each stone, resulting in O(n^2) time complexity.

// The space complexity is O(n^2) as well. This is due to the memoization map, which may contain entries for each stone with associated 
// jump sizes. In the worst case, it could have up to O(n) keys, and each key could have a set of jump sizes of size O(n), leading to 
// O(n^2) space complexity.


// DP memoization

class Solution {
    public boolean canCross(int[] stones) {
        // Step 1: Convert the array of stones into a set for efficient lookup.
        Set<Integer> stoneSet = new HashSet<>();
        for (int stone : stones)
            stoneSet.add(stone);

        // Step 2: Create a memoization map to track valid jumps to each stone.
        Map<Integer, Set<Integer>> memo = new HashMap<>();
        memo.put(stones[0], new HashSet<>());
        memo.get(stones[0]).add(0);

        // Step 3: Iterate through the stones and check possible jumps.
        for (int i = 0; i < stones.length; i++) {
            int stone = stones[i];

            if (!memo.containsKey(stone)) {
                continue;  // Skip stones that cannot be reached to avoid null pointer exception in for loop.
            }

            for (int jumpSize : memo.get(stone)) {
                for (int nextJump = jumpSize - 1; nextJump <= jumpSize + 1; nextJump++) {
                    // Move forward if there's a stone at the stone + nextJump position.
                    if (nextJump > 0 && stoneSet.contains(stone + nextJump)) {
                        int nextStone = stone + nextJump;

                        if (stones[stones.length - 1] == nextStone)
                            return true;

                        if (!memo.containsKey(nextStone)) {
                            memo.put(nextStone, new HashSet<>());
                        }
                        memo.get(nextStone).add(nextJump);
                    }
                }
            }
        }
        return false;
    }
}
