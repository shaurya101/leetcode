/*
1701. Average Waiting Time

There is a restaurant with a single chef. You are given an array customers, where customers[i] = [arrivali, timei]:
arrivali is the arrival time of the ith customer. The arrival times are sorted in non-decreasing order.
timei is the time needed to prepare the order of the ith customer.
When a customer arrives, he gives the chef his order, and the chef starts preparing it once he is idle. The customer waits till the chef finishes preparing his order. The chef does not prepare food for more than one customer at a time. The chef prepares food for customers in the order they were given in the input.
Return the average waiting time of all customers. Solutions within 10-5 from the actual answer are considered accepted.

Example 1:
Input: customers = [[1,2],[2,5],[4,3]]
Output: 5.00000
Explanation:
1) The first customer arrives at time 1, the chef takes his order and starts preparing it immediately at time 1, and finishes at time 3, so the waiting time of the first customer is 3 - 1 = 2.
2) The second customer arrives at time 2, the chef takes his order and starts preparing it at time 3, and finishes at time 8, so the waiting time of the second customer is 8 - 2 = 6.
3) The third customer arrives at time 4, the chef takes his order and starts preparing it at time 8, and finishes at time 11, so the waiting time of the third customer is 11 - 4 = 7.
So the average waiting time = (2 + 6 + 7) / 3 = 5.

*/

------------

class Solution {
    public double averageWaitingTime(int[][] customers) {
        double totalWaitingTime=0;
        
        int oldOrderFinishedTime=0; // time when all previous orders are served and latest order starts to cook
        
        for(int i=0; i<customers.length; i++){
            int startTime = customers[i][0];
            int endTime;
            // if customer came when all prev orders were already served his endTime would be his entry time+his food cooking time,
            // else if he came when previous order was being cooked, his order would take prevOrder+his food cooking time
            if(startTime>oldOrderFinishedTime){
                endTime = startTime+customers[i][1];
            }
            else{
                endTime = oldOrderFinishedTime+customers[i][1];
            }
            oldOrderFinishedTime = endTime;
            totalWaitingTime+= endTime-startTime;
        }
        return totalWaitingTime/customers.length;
        
        // simpy traverse the array and keep track of the time till the latest order will be completed. That way we can find the waiting time of all 
        
    }
}

// Approach
// average waiting time = totalWaitingTime/number of customers
// totalWaitingTime = sum of waiting time of all customers
// waiting time of 1 customer = endTime - startTime
// endTime = time when his order was delivered
// startTime = time he entered the restaurant ie customer[i][0].
// for endTime, there can be two scenarios.
// 1. customer entered when all previous orders were already served and his order can begin to be cooked immediately.
// 2. customer enters when previous orders are still being cooked and his order will be cooked after previous orders  are done.

