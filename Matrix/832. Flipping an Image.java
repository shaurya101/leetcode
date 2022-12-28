/*
832. Flipping an Image

Given an n x n binary matrix image, flip the image horizontally, then invert it, and return the resulting image.
To flip an image horizontally means that each row of the image is reversed.
For example, flipping [1,1,0] horizontally results in [0,1,1].
To invert an image means that each 0 is replaced by 1, and each 1 is replaced by 0.
For example, inverting [0,1,1] results in [1,0,0].

Example 1:
Input: image = [[1,1,0],[1,0,1],[0,0,0]]
Output: [[1,0,0],[0,1,0],[1,1,1]]
Explanation: First reverse each row: [[0,1,1],[1,0,1],[0,0,0]].
Then, invert the image: [[1,0,0],[0,1,0],[1,1,1]]

*/

========

class Solution {
    public int[][] flipAndInvertImage(int[][] image) {
        int n = image.length;
        for(int i=0; i<n; i++) {
            int left = 0;
            int right = n-1;
            while(left < right) {
                swap(image, i, left, right);
                image[i][left] = image[i][left]==0? 1: 0;
                image[i][right] = image[i][right]==0? 1: 0;
                left++;
                right--;
            }
            if(n%2 != 0) // if n is odd, middle element will not get inverted in above while loop
                image[i][left] = image[i][left]==0? 1: 0;
        }
        return image;
    }

    private void swap(int[][] image, int i, int left, int right) {
        int temp = image[i][left];
        image[i][left] = image[i][right];
        image[i][right] = temp;
    }
}
