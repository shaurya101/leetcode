/*
319. Bulb Switcher

There are n bulbs that are initially off. You first turn on all the bulbs, then you turn off every second bulb.
On the third round, you toggle every third bulb (turning on if it's off or turning off if it's on). For the ith round, you toggle every i bulb.
For the nth round, you only toggle the last bulb.
Return the number of bulbs that are on after n rounds.


*/


----------------

// Approach T: O(1), S: O(1)


class Solution {
    public int bulbSwitch(int n) {
        // For any bulb i, it is toggled once for each of its factors
        // So the number of times it is toggled is the number of its factors
        // For all i in range [1, n], only the bulbs with odd number of factors will remain on
        // Bulbs who are perfect square will have odd number of factors
        return (int) Math.sqrt(n);
    }
}


// The solution is based on the observation that a bulb will be toggled an even number of times (including zero) if it has an even number of divisors,
// and an odd number of times if it has an odd number of divisors. A bulb will have an odd number of divisors if and only if it is a perfect square, 
// because each divisor less than the square root of the number corresponds to a divisor greater than the square root of the number. 
// Therefore, the number of bulbs that are on after n rounds is equal to the number of perfect squares less than or equal to n, 
// which is equal to the floor of the square root of n.
