/*
1921. Eliminate Maximum Number of Monsters

You are playing a video game where you are defending your city from a group of n monsters. You are given a 0-indexed integer array 
dist of size n, where dist[i] is the initial distance in kilometers of the ith monster from the city.

The monsters walk toward the city at a constant speed. The speed of each monster is given to you in an integer array speed of size n,
where speed[i] is the speed of the ith monster in kilometers per minute.

You have a weapon that, once fully charged, can eliminate a single monster. However, the weapon takes one minute to charge. The weapon
is fully charged at the very start.

You lose when any monster reaches your city. If a monster reaches the city at the exact moment the weapon is fully charged, it counts
as a loss, and the game ends before you can use your weapon.

Return the maximum number of monsters that you can eliminate before you lose, or n if you can eliminate all the monsters before they
reach the city.

 

Example 1:
Input: dist = [1,3,4], speed = [1,1,1]
Output: 3
Explanation:
In the beginning, the distances of the monsters are [1,3,4]. You eliminate the first monster.
After a minute, the distances of the monsters are [X,2,3]. You eliminate the second monster.
After a minute, the distances of the monsters are [X,X,2]. You eliminate the thrid monster.
All 3 monsters can be eliminated.

Example 2:
Input: dist = [1,1,2,3], speed = [1,1,1,1]
Output: 1
Explanation:
In the beginning, the distances of the monsters are [1,1,2,3]. You eliminate the first monster.
After a minute, the distances of the monsters are [X,0,1,2], so you lose.
You can only eliminate 1 monster.

Example 3:
Input: dist = [3,2,4], speed = [5,3,2]
Output: 1
Explanation:
In the beginning, the distances of the monsters are [3,2,4]. You eliminate the first monster.
After a minute, the distances of the monsters are [X,0,2], so you lose.
You can only eliminate 1 monster.

*/


---------------

// Approach 1 - with Double
// T: O(NlogN), S: O(N)

import java.util.PriorityQueue;

class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        int n = dist.length;
        PriorityQueue<Double> attackTimes = new PriorityQueue<>();

        // Calculate and store the attack times for all monsters
        for (int i = 0; i < n; i++) {
            double attackTime = (double) dist[i] / speed[i];
            attackTimes.offer(attackTime); // Store attack times in the priority queue
        }

        int count = 0;
        double currentTime = 0;

        // Process monsters based on their attack times
        while (!attackTimes.isEmpty()) {
            double nextAttackTime = attackTimes.poll();

            // If the monster reaches you, break out of the loop
            if (nextAttackTime <= currentTime) {
                break;
            }

            // Increment time and count for each monster eliminated
            currentTime++;
            count++;
        }

        return count; // Return the maximum number of eliminated monsters
    }
}

------------

// Approach 1 - with int
// T: O(NlogN), S: O(N)

class Solution {
    public int eliminateMaximum(int[] dist, int[] speed) {
        PriorityQueue<Integer> timeToAtk = new PriorityQueue<>();
        for(int i=0; i<dist.length; i++)
            timeToAtk.offer((int)Math.ceil((double)dist[i]/speed[i]));
            // timeToAtk.offer((int) Math.ceil(dist[i] / speed[i])); This is wrong
        
        int time=0;
        int count=0;
        while(!timeToAtk.isEmpty()) {
            int nextAtk = timeToAtk.poll();
            if(time >= nextAtk)
                return count;
            count++;
            time++;
        }
        return count;
    }
}
