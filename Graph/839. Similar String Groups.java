/*
839. Similar String Groups

Two strings, X and Y, are considered similar if either they are identical or we can make them equivalent by swapping at most two 
letters (in distinct positions) within the string X.

For example, "tars" and "rats" are similar (swapping at positions 0 and 2), and "rats" and "arts" are similar, but "star" is not 
similar to "tars", "rats", or "arts".

Together, these form two connected groups by similarity: {"tars", "rats", "arts"} and {"star"}.  Notice that "tars" and "arts" are in 
the same group even though they are not similar.  Formally, each group is such that a word is in the group if and only if it is 
similar to at least one other word in the group.

We are given a list strs of strings where every string in strs is an anagram of every other string in strs. 
How many groups are there?
 

Example 1:
Input: strs = ["tars","rats","arts","star"]
Output: 2

Example 2:
Input: strs = ["omv","ovm"]
Output: 1
*/

--------------

// Approach - T: O(N^2 * M), S:O(N)
// N is the number of strings (length of the "strs" array), and M is the length of each string


class Solution {
    public int numSimilarGroups(String[] strs) {
        int n = strs.length;
        DisjointSet ds = new DisjointSet(n);

        // Iterate through the strings and check if they are similar, then union them in the DisjointSet.
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (areSimilar(strs[i], strs[j]))
                    ds.unionBySize(i, j);
            }
        }

        // Return the number of distinct groups using the DisjointSet.
        return ds.distinctRoots();
    }

    // Check if two strings are similar (differ by at most 2 characters).
    private boolean areSimilar(String a, String b) {
        int count = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != b.charAt(i))
                count++;
            if (count > 2)
                return false;
        }
        return count == 0 || count == 2;
    }
}

class DisjointSet {
    int[] parent;
    int[] size;

    public DisjointSet(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    // Find the root parent of a node with path compression.
    public int findUPar(int node) {
        if (node == parent[node])
            return node;
        parent[node] = findUPar(parent[node]); // Path compression
        return parent[node];
    }

    // Union two nodes based on size to optimize the tree structure.
    public void unionBySize(int u, int v) {
        int rootU = findUPar(u);
        int rootV = findUPar(v);
        if (rootU == rootV)
            return;
        // attaching smaller tree to larfer tree and updating larger tree size
        if (size[rootU] < size[rootV]) {
            parent[rootU] = rootV;
            size[rootV] += size[rootU];
        } else {
            parent[rootV] = rootU;
            size[rootU] += size[rootV];
        }
    }

    // Count the number of distinct root parents to determine the number of groups.
    public int distinctRoots() {
        int count = 0;
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == i)
                count++;
        }
        return count;
    }
}
