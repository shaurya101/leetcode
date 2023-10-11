/*
990. Satisfiability of Equality Equations

You are given an array of strings equations that represent relationships between variables where each string equations[i] is of
length 4 and takes one of two different forms: "xi==yi" or "xi!=yi".Here, xi and yi are lowercase letters (not necessarily different) 
that represent one-letter variable names.

Return true if it is possible to assign integers to variable names so as to satisfy all the given equations, or false otherwise. 


Example 1:
Input: equations = ["a==b","b!=a"]
Output: false
Explanation: If we assign say, a = 1 and b = 1, then the first equation is satisfied, but not the second.
There is no way to assign the variables to satisfy both equations.

*/

------------

// Approach - T: O(N + M * α(N)) , S: O(N)
// The time complexity of your code is O(N + M * α(N)) where N is the number of variables (26 in this case) and M is the number of equations.
// α(N) is the inverse Ackermann function, which grows very slowly and is considered nearly constant for practical purposes.
// Therefore, you can simplify the time complexity to O(N + M).
// The space complexity is O(N), where N is the number of variables (26 in this case), for storing the parent and size arrays in the DisjointSet data structure.

// In summary, the code is efficient and has a linear time complexity with respect to the number of equations 
// and a linear space complexity with respect to the number of variables.

class Solution {
    public boolean equationsPossible(String[] equations) {
        // Create a DisjointSet data structure to manage connected components.
        DisjointSet ds = new DisjointSet(26);
        
        // Separate equalities and inequalities into different lists.
        List<String> equalities = new ArrayList<>();
        List<String> inequalities = new ArrayList<>();
        
        for (String s : equations) {
            if (s.charAt(1) == '=') {
                equalities.add(s);
            } else {
                inequalities.add(s);
            }
        }

        // Handle equality equations to establish connected components.
        for (String s : equalities) {
            int nodeA = s.charAt(0) - 'a';
            int nodeB = s.charAt(3) - 'a';
            ds.unionBySize(nodeA, nodeB);
        }

        // Check inequalities to ensure there are no contradictions in the connected components.
        for (String s : inequalities) {
            int nodeA = s.charAt(0) - 'a';
            int nodeB = s.charAt(3) - 'a';
            if (ds.findUPar(nodeA) == ds.findUPar(nodeB)) {
                // If an inequality contradicts established equalities, return false.
                return false;
            }
        }
        return true;
    }
}

class DisjointSet {
    int[] parent = new int[26];
    int[] size = new int[26];

    public DisjointSet(int n) {
        // Initialize the DisjointSet with n elements.
        for (int i = 0; i < 26; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    public int findUPar(int node) {
        // Find the root parent of a node using path compression.
        if (node == parent[node]) {
            return node;
        }
        parent[node] = findUPar(parent[node]); // Path compression
        return parent[node];
    }

    public void unionBySize(int u, int v) {
        // Union two nodes by size to optimize tree depth.
        int rootU = findUPar(u);
        int rootV = findUPar(v);
        if (rootU == rootV) {
            return;
        }
        if (size[rootU] < size[rootV]) {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
        } else {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
        }
    }
}
