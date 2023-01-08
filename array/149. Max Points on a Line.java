/*
149. Max Points on a Line

Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane, return the maximum number of points that lie on the same straight line.

Example 1:
Input: points = [[1,1],[2,2],[3,3]]
Output: 3

Example 2:
Input: points = [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
Output: 4

*/

============

// Approach - T-O(N^2), S-O(N)
// If we have a same slope of two points passing through the same point, then both points are in same line.
// Eg if (x1,y1) and (x2,y2) and (x1,y1) and (x3, y3) have same slope. Then they are in same line as they both pass through a common point (x1, y1).
// For every point in the array, we check the slope with the other points in the array. We check if any two points (x2, y2), (x3, y3) etc have same slope with respect to (x1, y1). If yes, then they will be in the same line.
// Same process for other points as well, hence O(n^2) soln

class Solution {
    public int maxPoints(int[][] points) {
        if(points.length < 2)
            return points.length;

        int max = 1;
        // for every ith point, we check from (i+1)th point to the last point in array.
        // Also, that means we do not need to check for the last element in the array, as it would have been already compared too all points before it in previous iterations   
        for(int i = 0; i < points.length-1; i++) {
            // to store all the slopes possible of ith point to other points, and how many times we get same slope.
            HashMap<Double, Integer> map = new HashMap<>();
            
            for(int j = i+1; j < points.length; j++) {
                // if point is same, check next points
                if(points[i][0]==points[j][0] && points[i][1]==points[j][1])
                    continue;
                double slope = 0;
                // if line parallel to x axis, then slope is infinity
                if(points[i][0]==points[j][0])
                    slope = Double.POSITIVE_INFINITY;
                else
                    slope = ((double) points[i][1]-points[j][1])/(points[i][0]-points[j][0]);
                
                // 0.0 and -0.0 are different (signed) so we make them equal.
                // IEEE754 specifies a signed zero. That is, -0.0 and +0.0 are represented individually.
                if(slope == -0.0)
                    slope = 0.0;
                map.put(slope, map.getOrDefault(slope, 1)+1); //+1 as we will also include the current point, that is ith point
                max = Math.max(max, map.get(slope));
            }
        }
        return max;
    }
}
