package edu.iastate.cs228.hw2;

/**
 *  
 * @author Alec Ferchen
 *
 */

/**
 * 
 * This class executes four sorting algorithms: selection sort, insertion sort, mergesort, and
 * quicksort, over randomly generated integers as well integers from a file input. It compares the 
 * execution times of these algorithms on the same input. 
 *
 */

import java.io.FileNotFoundException;
import java.util.Scanner;

import java.util.Random; 


public class CompareSorters 
{
	/**
	 * Repeatedly take integer sequences either randomly generated or read from files. 
	 * Use them as coordinates to construct points.  Scan these points with respect to their 
	 * median coordinate point four times, each time using a different sorting algorithm.  
	 * 
	 * @param args
	 **/
	public static void main(String[] args) throws FileNotFoundException
	{		
		Point[] pointList;
		PointScanner[] scanners = new PointScanner[4]; 
		
		int option = 4;
		Scanner input = new Scanner(System.in);
		
		while (option > 3 || option < 1) {
			System.out.println("keys: 1 (random integers) 2 (file input) 3 (exit)");
		    option = input.nextInt();
		    
		}
	    if(option == 1) {
	    	
            System.out.println("Please enter number of points");
            int numPoints = input.nextInt();
            
            
            System.out.println("Would you like it seeded? (1 Yes) (Anything else No)");
            int seeded = input.nextInt();
            if(seeded == 1) {
            	System.out.println("Enter seed");
            	
            	Random seed = new Random(input.nextInt());
            	pointList = generateRandomPoints(numPoints, seed);
            }
            else {
            	Random rand = new Random();
            	pointList = generateRandomPoints(numPoints, rand);
            }
            input.close();
            
            PointScanner Sel = new PointScanner(pointList, Algorithm.SelectionSort);
            PointScanner Ins = new PointScanner(pointList, Algorithm.InsertionSort);
            PointScanner Qui = new PointScanner(pointList, Algorithm.QuickSort);
            PointScanner Mer = new PointScanner(pointList, Algorithm.MergeSort);
            scanners[0] = Sel;
    	    scanners[1] = Ins;
    	    scanners[2] = Qui;
    	    scanners[3] = Mer;
            
            
	    }
	    else if(option == 2) {
            System.out.println("Please enter file path:");
            String filename = input.nextLine();
            try {
            	PointScanner Sel = new PointScanner(filename, Algorithm.SelectionSort);
                PointScanner Ins = new PointScanner(filename, Algorithm.InsertionSort);
                PointScanner Qui = new PointScanner(filename, Algorithm.QuickSort);
                PointScanner Mer = new PointScanner(filename, Algorithm.MergeSort);
                scanners[0] = Sel;
        	    scanners[1] = Ins;
        	    scanners[2] = Qui;
        	    scanners[3] = Mer;
            	
            	input.close();
            } catch (FileNotFoundException e) {
                input.close();
            	e.printStackTrace(); //print exception for user to make happy
            }
	    }
	    else {
	    	input.close();
	    	return;
	    }
	    for(int i = 0; i< 4; i++) {
	    	scanners[i].scan();
	    }
	    for(int i = 0; i< 4; i++) {
	    	scanners[i].stats();
	    	System.out.println(scanners[i].toString());
	    }
	    
	    
	    
		// 
		// Conducts multiple rounds of comparison of four sorting algorithms.  Within each round, 
		// set up scanning as follows: 
		// 
		//    a) If asked to scan random points, calls generateRandomPoints() to initialize an array 
		//       of random points. 
		// 
		//    b) Reassigns to the array scanners[] (declared below) the references to four new 
		//       PointScanner objects, which are created using four different values  
		//       of the Algorithm type:  SelectionSort, InsertionSort, MergeSort and QuickSort. 
		// 
		// 	
		
		
		
		
		// For each input of points, do the following. 
		// 
		//     a) Initialize the array scanners[].  
		//
		//     b) Iterate through the array scanners[], and have every scanner call the scan() 
		//        method in the PointScanner class.  
		//
		//     c) After all four scans are done for the input, print out the statistics table from
		//		  section 2.
		//
		// A sample scenario is given in Section 2 of the project description. 
		
	}
	
	
	/**
	 * This method generates a given number of random points.
	 * The coordinates of these points are pseudo-random numbers within the range 
	 * [-50,50] � [-50,50]. Please refer to Section 3 on how such points can be generated.
	 * 
	 * Ought to be private. Made public for testing. 
	 * 
	 * @param numPts  	number of points
	 * @param rand      Random object to allow seeding of the random number generator
	 * @throws IllegalArgumentException if numPts < 1
	 */
	public static Point[] generateRandomPoints(int numPts, Random rand) throws IllegalArgumentException
	{ 
		if(numPts < 1) {
			throw new IllegalArgumentException();
		}
		
		
		Point[] newPOINTS = new Point[numPts];
		for(int i = 0; i < numPts; i++) {
			
			rand = new Random();
			int x = rand.nextInt(101);
			x -= 50;
			int y = rand.nextInt(101);
			y -= 50;
			Point newPt = new Point(x, y);
			newPOINTS[i] = newPt;
		}
		return newPOINTS;
	}
	
}
