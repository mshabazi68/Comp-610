
import java.util.ArrayList;

/*mohammadreza shahbazi 
Project3 find the Jth pos of two sorted array size n */
public class Jfinder {
	
	//self explanatory finding the midpoint  
	public static int midFinder(ArrayList<Integer> array) {
		int mid = -1;
		if (array.size() % 2 == 0) {
			
			mid = array.get(array.size() / 2);
		}
		if (array.size() % 2 != 0) {
			mid = array.get(array.size() / 2);
			
		}
		return mid;
	}
// In this method we will get the two array and the Jth pos . then using the Algo to find the jth 
	public static Object delete(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int j) {
		
		int c = -1;

		int total = arr1.size() + arr2.size();
		ArrayList<Integer> large = maxfinder(arr1, arr2);
		ArrayList<Integer> small = minfinder(arr1, arr2);
		int s1 = large.size();
		int s2 = small.size();

		if (j > (total)|| j<=0) {
			System.out.println("Error ! Check your input for Jth position. ");
			System.exit(c);
		}else if (j == 1) {
			if (large.get(0) < small.get(0)) {
				//System.out.println("The Answer will be " + large.get(0));
				c= large.get(0);
				//return c;
			} else
			//	System.out.println("The Answer will be "  + small.get(0));
				c= small.get(0);
				//return c;
		} else {
			
			//here we checking which parth of array we need to delete 
			while (s1 > 1 && s2 > 1) {

				if (j <= (s1 + s2) / 2) {

					large.subList(s1 / 2, s1).clear();

					return delete(large, small, j);

				} else {
					// if (j > (s1 + s2) / 2)
					small.subList(0, s2 / 2).clear();
					j = j - (s2 / 2);
					return delete(large, small, j);

				}

			}
			/*if(j==1 && s1==1) {
				if ( small.get(j)<large.get(0)&& small.get(j-1)<large.get(0)) {
					System.out.println("the answer is : "+ small.get(j));
				}
			}*/
			 if (s1 == 1) {
				if (small.get(j - 2) < large.get(0) && small.get(j - 1) > large.get(0)) {
					//System.out.println("The value of Jth element is if1: "  + large.get(0));
					c=large.get(0);
				} else if (small.get(j - 2) > large.get(0)) {
					//System.out.println("The value of Jth element is: if2 "  + small.get(j - 2));
					c=small.get(j-2);
				} else if (small.get(j - 1) < large.get(0)) {
					//System.out.println("The value of Jth element is: if3 "  + small.get(j - 1));
					c=small.get(j-1);
				}
			}else {

			if (large.get(j - 2) < small.get(0) && large.get(j - 1) > small.get(0) ) {
			//	System.out.println("The value of Jth element is: else1 "  + small.get(0));
				c= small.get(0);
			} else if (large.get(j - 2) > small.get(0)) {
			//	System.out.println("The value of Jth element is: else2 "  + large.get(j - 2));
				c=large.get(j-2);
			} else if (large.get(j - 1) < small.get(0)) {
				//System.out.println("The value of Jth element is: else3 "  + large.get(j - 1));
				large.get(j-1);
			}

			}
			
			//System.out.println("the large: " + large);
		//	System.out.println("the small: " + small);
			//System.out.println("the jth index: " + j);
		}
		return c;

	}
	// Here in this method we will find the min of two mid point
	public static ArrayList<Integer> minfinder(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
		
		int num1 = midFinder(arr1);
		int num2 = midFinder(arr2);
		int min = Math.min(num1, num2);
		if (num1 == min) {

			return arr1;
		} else {

			return arr2;
		}
	}
// Here in this method we will find the max of two mid point
	public static ArrayList<Integer> maxfinder(ArrayList<Integer> arr1, ArrayList<Integer> arr2) {
		 
		int num1 = midFinder(arr1);
		int num2 = midFinder(arr2);
		int max = Math.max(num1, num2);

		if (num1 == max) {

			return arr1;
		} else {

			return arr2;
		}
	}

}
