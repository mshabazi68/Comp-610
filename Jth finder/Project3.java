
//Mohammadreza shahbazi
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class Project3 extends Jfinder {
/* 
 * This method taake the input file read the file and passing to each array of size n after all of the we call the delete() method */
	public static void main(String[] args) throws URISyntaxException {
		// Getting path from the directory
		try {
			URL path = ClassLoader.getSystemResource("input3.txt");
			// Warning if the file is not available !
			if (path == null) {
				System.out.println("File is not available!");
			}
			// passing the input file and store it in an array list
			File myfile = new File(path.toURI());

			Scanner myscan = new Scanner(myfile);
			ArrayList<Integer> input = new ArrayList<Integer>();
			while (myscan.hasNextLine()) {
				input.add(myscan.nextInt());
			}
			int size = input.size();
			// passing the size of each array and the jth pos we want to find.
			int arrsize = input.get(0);
			int jth = input.get(1);
			jth= jth+1;
			
		//	System.out.println("the input of Jth is "+ jth);
			
			input.subList(0, 2).clear();
			// intitaling the two arrays 
			ArrayList<Integer> arr1 = new ArrayList<Integer>(input.subList(0, arrsize));
			ArrayList<Integer> arr2 = new ArrayList<Integer>(input.subList(arrsize, size - 2));
			//System.out.println("arr1" + arr1 );
			//System.out.println("arr2"+ arr2);
			System.out.println("The Answer will be " + delete(arr1, arr2, jth));
			

		} catch (IOException e) {
			System.out.println("Something went wrong in your reading file! ");
		}

	}

}
