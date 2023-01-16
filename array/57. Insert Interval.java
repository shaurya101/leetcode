/*
57. Insert Interval

You are given an array of non-overlapping intervals intervals where intervals[i] = [starti, endi] represent the start and the end of the ith interval and intervals is sorted in ascending order by starti. You are also given an interval newInterval = [start, end] that represents the start and end of another interval.
Insert newInterval into intervals such that intervals is still sorted in ascending order by starti and intervals still does not have any overlapping intervals (merge overlapping intervals if necessary).
Return intervals after the insertion.
 
Example 1:
Input: intervals = [[1,3],[6,9]], newInterval = [2,5]
Output: [[1,5],[6,9]]

Example 2:
Input: intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
Output: [[1,2],[3,10],[12,16]]
Explanation: Because the new interval [4,8] overlaps with [3,5],[6,7],[8,10].

*/

===========

// Approach
// We traverse the intervals array one time
// We will add interval's blocks in the list till we reach where interval's end is greater than newIntervals start.
// When this condition is met, it means that our newInterval block will be starting from or before this interval's block; as our start point is before this intervals block's end
// Find the start block from among the two possibilities above
// Now we just need to find the end block. We traverse the intervals blocks forward till our intervals block's becomes >= newInterval's end.
// There are three possibilities - Either we have reached the end of intervals blocks and newInterval's end is > intervals last block's end; or if we have reached a block where newInterval's end = intervals block end; or if it is greater than intervals block end;
// If ends are equal then we have found our end of block; but if interval's end is greater we also need to compare interval's start value for that block and newIntervels end value. If start is smaller than or equal t0 newIntervals end then blocks can be merged else a additional block will have to added whose end will be newInterval's end and then the usual interval block will be added

// Edge cases
// 1. If interval is empty, return newInterval after adding it in a [][]
// 2. If newInterval is ahead of range of interval, it will have to be added as a new block 



class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if(intervals.length == 0)
            return new int[][] {newInterval};

        List<int[]> list = new ArrayList<>();
        
        int start = 0, end = 0;
        int i=0;
        int flag = 0;

        while(i<intervals.length) {
            // if ending is greater than newInterval start
            if(intervals[i][1] >= newInterval[0] && flag == 0) {
                // only want to run this loop once
                flag = 1;

                // selecting start point
                if(intervals[i][0] < newInterval[0])
                    start = intervals[i][0];
                else
                    start = newInterval[0];

                // selecting end point.
                // Now we will stop when intervals's end >= newInterval's end
                while(i<intervals.length && intervals[i][1] < newInterval[1])
                    i++;
                    
                // if intervals last end < newIntervals end or interval's end == newInterval end. We found our end point
                if(i == intervals.length || intervals[i][1] == newInterval[1]) {
                    end = newInterval[1];
                }
                // if interval's start > newInterval end. There will be a new block where end will be newInterval's end. 
                else if(intervals[i][0] > newInterval[1]) {
                    end = newInterval[1];
                    i--; // as start and this end will be added as a new block, we will have to add interval[i] too seperately
                }
                else // when interval's start < newInterval start
                    end = intervals[i][1];
            }
            // else continue

            if(flag == 1) {
                // Now we have found start and end points. Insert these
                list.add(new int[]{start, end});
                // we only want to skip adding intervals once when we add newInterval
                flag = 2;
            }
            else
                list.add(new int[] {intervals[i][0], intervals[i][1]});

            i++;
        }

        // if newInterval start > intervals last block's end
        if(flag == 0)
             list.add(new int[] {newInterval[0], newInterval[1]});

        return list.toArray(new int[list.size()][]);
    }
}
