/*
1700. Number of Students Unable to Eat Lunch

The school cafeteria offers circular and square sandwiches at lunch break, referred to by numbers 0 and 1 respectively. All students stand in a queue. Each student either prefers square or circular sandwiches.

The number of sandwiches in the cafeteria is equal to the number of students. The sandwiches are placed in a stack. At each step:

If the student at the front of the queue prefers the sandwich on the top of the stack, they will take it and leave the queue.
Otherwise, they will leave it and go to the queue's end.
This continues until none of the queue students want to take the top sandwich and are thus unable to eat.

You are given two integer arrays students and sandwiches where sandwiches[i] is the type of the ith sandwich in the stack (i = 0 is the top of the stack) and students[j] is the preference of the jth student in the initial queue (j = 0 is the front of the queue). Return the number of students that are unable to eat.

Example 1:

Input: students = [1,1,0,0], sandwiches = [0,1,0,1]
Output: 0 
Explanation:
- Front student leaves the top sandwich and returns to the end of the line making students = [1,0,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,0,1,1].
- Front student takes the top sandwich and leaves the line making students = [0,1,1] and sandwiches = [1,0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [1,1,0].
- Front student takes the top sandwich and leaves the line making students = [1,0] and sandwiches = [0,1].
- Front student leaves the top sandwich and returns to the end of the line making students = [0,1].
- Front student takes the top sandwich and leaves the line making students = [1] and sandwiches = [1].
- Front student takes the top sandwich and leaves the line making students = [] and sandwiches = [].
Hence all students are able to eat.
Example 2:

Input: students = [1,1,1,0,0,1], sandwiches = [1,0,0,0,1,1]
Output: 3

*/
----------------
// APPROACH 1
  
class Solution {
    public int countStudents(int[] students, int[] sandwiches) {
        Queue<Integer> q1 = new LinkedList<>(); // queue to store students
        int top = 0; // sandwich[] top
        
        for(int i=0; i<students.length; i++)
            q1.add(students[i]);
        
        int counterOfStudentsRotating=0; // counter of student rotating continuously without any removal
        while(!q1.isEmpty() && counterOfStudentsRotating<q1.size()){
            if(q1.peek() == sandwiches[top]){
                q1.remove(); // removing student from queue
                top++; // moving to next sandwich[] top
                counterOfStudentsRotating=0; // resetting counter
            }
            else{
                q1.add(q1.poll());
                counterOfStudentsRotating++;
            }
        }
        if(q1.isEmpty())
            return 0;
        return sandwiches.length-top;
    }
}

// Approach
// We store the students in a queue.
// We compare the q1 front and the sandwich[top], if they are equal we remove the student from the queue. If not we put him at the end of queue and increase the rotating counter by 1.
// If the rotating counter becomes equal to q.size(), it means all elements in queue are gettig rotated and no student will accept the sanwich[top] further.
