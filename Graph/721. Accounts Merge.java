/*
721. Accounts Merge

Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, 
and the rest of the elements are emails representing emails of the account.

Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both 
accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. 
A person can have any number of accounts initially, but all of their accounts definitely have the same name.

After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of
the elements are emails in sorted order. The accounts themselves can be returned in any order.
 

Example 1:

Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
Explanation:
The first and second John's are the same person as they have the common email "johnsmith@mail.com".
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'], 
['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.

*/

-----------

// Approach - T: O(n*m), S: O(m) ; n is the number of accounts, and m is the average number of emails per account.

class Solution {
    public List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();
        DisjointSet ds = new DisjointSet(n);
        // Map to represent emails with an integer and make their disjoint set
        Map<String, Integer> mapMailNode = new HashMap<>();

        // Step 1: Create disjoint sets for emails
        for (int i = 0; i < n; i++) {
            // Traverse only the emails; accounts[i][0] is always the name
            for (int j = 1; j < accounts.get(i).size(); j++) {
                String mail = accounts.get(i).get(j);
                if (!mapMailNode.containsKey(mail)) {
                    mapMailNode.put(mail, i);
                } else {
                    ds.unionBySize(mapMailNode.get(mail), i);
                }
            }
        }

        // Initialize an array of ArrayLists to store merged emails
        ArrayList<String>[] mergedMail = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            mergedMail[i] = new ArrayList<>();
        }

        // Step 2: Merge emails based on disjoint sets
        for (Map.Entry<String, Integer> entry : mapMailNode.entrySet()) {
            String mail = entry.getKey();
            int node = ds.findUPar(entry.getValue());
            mergedMail[node].add(mail);
        }

        List<List<String>> ans = new ArrayList<>();

        // Step 3: Create the final result
        for (int i = 0; i < n; i++) {
            if (mergedMail[i].size() == 0) {
                continue;
            }
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0)); // Add the name
            Collections.sort(mergedMail[i]);
            for (String mail : mergedMail[i]) {
                temp.add(mail);
            }
            ans.add(temp);
        }
        return ans;
    }
}

// Disjoint set class
class DisjointSet {
    List<Integer> parent = new ArrayList<>();
    List<Integer> size = new ArrayList();

    public DisjointSet(int n) {
        for (int i = 0; i < n; i++) {
            parent.add(i);
            size.add(1);
        }
    }

    // Find the representative parent of a node
    public int findUPar(int node) {
        if (node == parent.get(node))
            return node;
        int ulp = findUPar(parent.get(node));
        parent.set(node, ulp);
        return ulp;
    }

    // Union two sets by size
    public void unionBySize(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v)
            return;
        if (size.get(ulp_u) < size.get(ulp_v)) {
            parent.set(ulp_u, ulp_v);
            size.set(ulp_v, size.get(ulp_v) + size.get(ulp_u));
        } else {
            parent.set(ulp_v, ulp_u);
            size.set(ulp_u, size.get(ulp_u) + size.get(ulp_v));
        }
    }
}

/*

Time Complexity:
Creating the mapMailNode map: This involves iterating over the accounts and their emails, resulting in O(n * m) time complexity, where n is the number of accounts, and m is the average number of emails per account.
Initializing the mergedMail array: This operation has a time complexity of O(n) because it iterates over the number of accounts.
Merging emails based on disjoint sets: The loop that merges emails also takes O(n * m) time in the worst case.
Creating the final result: The last loop has a time complexity of O(n * m) in the worst case.
The dominant factor in the time complexity is the merging of emails and creating the final result, which are both O(n * m).

Space Complexity:
mapMailNode: This map stores a mapping between emails and integers, resulting in O(m) space complexity, where m is the total number of unique emails.
mergedMail: An array of ArrayLists to store merged emails. In the worst case, there can be a separate ArrayList for each email, so the space complexity is O(m).
DisjointSet: This class uses two lists, parent and size, each of size n, where n is the number of accounts. Thus, the space complexity is O(n).
Overall, the space complexity is dominated by the mapMailNode and mergedMail structures, resulting in O(m) space complexity.
                                                                                                        
  */
