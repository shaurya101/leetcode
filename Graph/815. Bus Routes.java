/*
815. Bus Routes

You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
You will start at the bus stop source (You are not on any bus initially), and you want to go to the bus stop target. You can travel 
between bus stops by buses only.

Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.
 

Example 1:
Input: routes = [[1,2,7],[3,6,7]], source = 1, target = 6
Output: 2
Explanation: The best strategy is take the first bus to the bus stop 7, then take the second bus to the bus stop 6.

Example 2:
Input: routes = [[7,12],[4,5,15],[6],[15,19],[9,12,13]], source = 15, target = 12
Output: -1

*/


-----------

// Graph of buses connected eith each other

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        // adjList Map to store the buses for each stop
        // <bus, buses connected to this bus>
        Map<Integer, Set<Integer>> stopToBuses = new HashMap<>();
        
        // Populate the stopToBuses map
        for (int i = 0; i < routes.length; i++) {
            for (int stop : routes[i]) {
                stopToBuses.computeIfAbsent(stop, k -> new HashSet<>()).add(i);
            }
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(source);
        int count = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            count++;

            for (int i = 0; i < size; i++) {
                int currentStop = queue.poll();

                // Iterate over buses that visit the current stop
                for (int bus : stopToBuses.getOrDefault(currentStop, Collections.emptySet())) {
                    if (visited.contains(bus)) continue;
                    visited.add(bus);

                    // Add stops of the current bus to the queue
                    for (int nextStop : routes[bus]) {
                        if (nextStop == target) return count;
                        queue.offer(nextStop);
                    }
                }
            }
        }

        // If no valid path is found
        return -1;
    }
}

------------------

// GIVES TLE
// Graph has bus stops connected to other bus stops

class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;

        // create adjacency list for stops
        // <bus stop, set of connected bus stops>
        Map<Integer, Set<Integer>> adjList = new HashMap<>();

        // Populate the adjacency list based on bus routes.
        for (int[] route : routes) {
            for (int i = 0; i < route.length - 1; i++) {
                for (int j = i + 1; j < route.length; j++) {
                    // Add an edge between different bus stops in the same route.
                    adjList.computeIfAbsent(route[i], k -> new HashSet<>()).add(route[j]);
                    adjList.computeIfAbsent(route[j], k -> new HashSet<>()).add(route[i]);
                }
            }
        }

        Set<Integer> visited = new HashSet<>();
        Queue<Integer> q1 = new ArrayDeque<>();
        q1.offer(source);
        visited.add(source);
        int count = 0;

        // Perform BFS traversal
        while (!q1.isEmpty()) {
            int size = q1.size();

            for (int i = 0; i < size; i++) {
                int curr = q1.poll();

                if (curr == target)
                    return count;

                for (int neighbor : adjList.getOrDefault(curr, Collections.emptySet())) {
                    if (visited.contains(neighbor))
                        continue;

                    visited.add(neighbor);
                    q1.offer(neighbor);
                }
            }
            count++; // Increase the distance for each level of BFS traversal.
        }

        return -1; // If the target is not reachable.
    }
}
