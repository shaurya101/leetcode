/*
1615. Maximal Network Rank

There is an infrastructure of n cities with some number of roads connecting these cities. 
Each roads[i] = [ai, bi] indicates that there is a bidirectional road between cities ai and bi.
The network rank of two different cities is defined as the total number of directly connected roads to either city. 
If a road is directly connected to both cities, it is only counted once.
The maximal network rank of the infrastructure is the maximum network rank of all pairs of different cities.
Given the integer n and the array roads, return the maximal network rank of the entire infrastructure.

Example 1:
Input: n = 4, roads = [[0,1],[0,3],[1,2],[1,3]]
Output: 4
Explanation: The network rank of cities 0 and 1 is 4 as there are 4 roads that are connected to either 0 or 1. The road between 0 and 1 is only counted once.

*/

----------------

// T: (n+m), S: (n); n=number of cities, m=number of roads
// https://leetcode.com/problems/maximal-network-rank/solutions/3924675/beat-100-o-v-e-most-efficient-solution-greedy-no-hash-no-double-loop/


class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] degrees = new int[n];

        // Calculate degrees of each city
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            degrees[a]++;
            degrees[b]++;
        }

        int maxDegree = 0;
        int secondMaxDegree = 0;

        // Find the max and second max degrees
        for (int degree : degrees) {
            if (degree < secondMaxDegree) {
                continue;
            }
            secondMaxDegree = degree;
            if (secondMaxDegree > maxDegree) {
                int temp = secondMaxDegree;
                secondMaxDegree = maxDegree;
                maxDegree = temp;
            }
        }

        boolean[] isCandidate = new boolean[n];
        int candidateCount = 0;
        int king = -1;

        // Identify candidates and the king (if exists)
        for (int i = 0; i < n; i++) {
            if (degrees[i] == secondMaxDegree) {
                isCandidate[i] = true;
                candidateCount++;
            }
            if (maxDegree > secondMaxDegree && degrees[i] == maxDegree) {
                king = i;
            }
        }

        if (maxDegree == secondMaxDegree) {
            // Case 1: Multiple candidates with the same max degrees
            if (candidateCount > maxDegree + 1) {
                return maxDegree * 2;
            }

            int connectionCount = 0;

            // Count connections between candidates
            for (int[] road : roads) {
                int a = road[0];
                int b = road[1];
                if (isCandidate[a] && isCandidate[b]) {
                    connectionCount++;
                }
            }

            if (connectionCount < candidateCount * (candidateCount - 1) / 2) {
                return maxDegree * 2;
            }

            return maxDegree * 2 - 1;
        }

        // Case 2: Single max degree (king) and multiple second max degree candidates
        int connectionCount = 0;

        // Count connections between king and candidates
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            if (a != king && b != king) {
                continue;
            }
            if (isCandidate[a] || isCandidate[b]) {
                connectionCount++;
            }
        }

        if (connectionCount < candidateCount) {
            return maxDegree + secondMaxDegree;
        }

        return maxDegree + secondMaxDegree - 1;
    }
}


----------------

// T: (n^2), S: (n)


class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        // Array to store the count of roads connected to each city
        int[] cityCount = new int[n];
        
        // Array of sets to store connected cities for each city
        Set<Integer>[] connectedCities = new HashSet[n];
        for (int i = 0; i < n; i++) {
            connectedCities[i] = new HashSet<>();
        }
        
        // Populate cityCount and connectedCities arrays based on the roads
        for (int[] road : roads) {
            int city1 = road[0];
            int city2 = road[1];
            
            cityCount[city1]++;
            cityCount[city2]++;
            
            // Adding connected cities to each city's set
            connectedCities[city1].add(city2);
            connectedCities[city2].add(city1);
        }
        
        int maxRank = 0;
        
        // Calculate the network rank for all pairs of cities
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = cityCount[i] + cityCount[j];
                
                // If the cities are connected, reduce one count as it was counted twice
                if (connectedCities[i].contains(j)) {
                    rank--;
                }
                
                // Update the maximum network rank
                maxRank = Math.max(maxRank, rank);
            }
        }
        
        return maxRank;
    }
}


----------------

//Approach - // T: (n^2), S: (n^2)
  
class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] cityCount = new int[n];
        boolean[][] isConnected = new boolean[n][n];
        
        // Count the number of roads connected to each city and build the connection matrix
        for (int[] road : roads) {
            cityCount[road[0]]++;
            cityCount[road[1]]++;
            isConnected[road[0]][road[1]] = true;
            isConnected[road[1]][road[0]] = true;
        }
        
        int maxRank = 0;
        
        // Calculate the maximal network rank by iterating through all pairs of cities
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int rank = cityCount[i] + cityCount[j];
                
                // If there's a direct road between the two cities, reduce the count by 1
                if (isConnected[i][j]) {
                    rank--; // Reduce one count as we counted the connection twice
                }
                
                // Update the maximum network rank
                maxRank = Math.max(maxRank, rank);
            }
        }
        
        return maxRank;
    }
}


// The code uses an adjacency matrix to efficiently check whether two cities are connected in O(1) time.
// If an adjacency list were used instead, the time complexity for checking connectivity between two cities would be O(n),
// where n is the total number of cities.


-----------------

//Approach - // T: (n^2), S: (n^2)

class Solution {
    public int maximalNetworkRank(int n, int[][] roads) {
        int[] degree = new int[n];
        int[][] adjMat = new int[n][n];

        for(int[] road : roads) {
            degree[road[0]]++;
            degree[road[1]]++;
            adjMat[road[0]][road[1]] = 1;
            adjMat[road[1]][road[0]] = 1;
        }

        int maxRank=0;
        for(int i=0; i<n; i++) {
            for(int j=0; j<i; j++) {
                int rank = degree[i]+degree[j];
                if(adjMat[i][j] == 1)
                    rank--;
                maxRank = Math.max(maxRank, rank);
            }
        }
        return maxRank;
    }
}
