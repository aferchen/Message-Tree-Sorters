package edu.iastate.cs228.hw2;

/**
 * 
 * @author Alec Ferchen
 *
 */

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;


/**
 * 
 * This class sorts all the points in an array of 2D points to determine a reference point whose x and y 
 * coordinates are respectively the medians of the x and y coordinates of the original points. 
 * 
 * It records the employed sorting algorithm as well as the sorting time for comparison. 
 *
 */
public class PointScanner  
{
	private Point[] points; 
	
	private Point medianCoordinatePoint;  // point whose x and y coordinates are respectively the medians of 
	                                      // the x coordinates and y coordinates of those points in the array points[].
	private Algorithm sortingAlgorithm;    
	
		
	protected long scanTime; 	       // execution time in nanoseconds. 
	
	/**
	 * This constructor accepts an array of points and one of the four sorting algorithms as input. Copy 
	 * the points into the array points[].
	 * 
	 * @param  pts  input array of points 
	 * @throws IllegalArgumentException if pts == null or pts.length == 0.
	 */
	public PointScanner(Point[] pts, Algorithm algo) throws IllegalArgumentException
	{
		if (pts == null || pts.length == 0) {
			throw new IllegalArgumentException();
		}
		points = new Point[pts.length];
		for(int i = 0; i < pts.length; i++) {
			points[i] = pts[i];
		}
		sortingAlgorithm = algo;
	}

	
	/**
	 * This constructor reads points from a file. 
	 * 
	 * @param  inputFileName
	 * @throws FileNotFoundException 
	 * @throws InputMismatchException   if the input file contains an odd number of integers
	 */
	protected PointScanner(String inputFileName, Algorithm algo) throws FileNotFoundException, InputMismatchException
	{
		Scanner scanner = new Scanner(inputFileName);
		  
		ArrayList<Point> pts = new ArrayList<Point>();
		  while (scanner.hasNextLine())
		    {
		      // get the next line
		      String temp = scanner.nextLine();
		      Scanner line = new Scanner(temp);
		      
		      while(line.hasNextInt()) {
		    	  try {
		    	  int x = line.nextInt();
		    	  int y = line.nextInt();
		    	  pts.add(new Point(x,y));
		    	  }
		    	  catch (InputMismatchException e){
		    		  e.printStackTrace();
		    	  }
		      }
		      line.close();
		    }
		    // close the file
		    scanner.close();
		    
		    
		    points = new Point[pts.size()];
		    for(int i = 0; i < pts.size(); i++) {
		    	points[i] = pts.get(i);
		    }
		    sortingAlgorithm = algo;
	 }
		

	
	/**
	 * Carry out two rounds of sorting using the algorithm designated by sortingAlgorithm as follows:  
	 *    
	 *     a) Sort points[] by the x-coordinate to get the median x-coordinate. 
	 *     b) Sort points[] again by the y-coordinate to get the median y-coordinate.
	 *     c) Construct medianCoordinatePoint using the obtained median x- and y-coordinates.     
	 *  
	 * Based on the value of sortingAlgorithm, create an object of SelectionSorter, InsertionSorter, MergeSorter,
	 * or QuickSorter to carry out sorting.       
	 * @param algo
	 * @return
	 */
	public void scan()
	{
		AbstractSorter aSorter; 
		Point medX;
		Point medY;
		
		if(sortingAlgorithm == Algorithm.SelectionSort) {
			aSorter = new SelectionSorter(points);
			
			long startTime = System.nanoTime();
			aSorter.setComparator(0);
			aSorter.sort();
			medX = aSorter.getMedian();
			
			aSorter.setComparator(1);
			aSorter.sort();
			medY = aSorter.getMedian();
			scanTime = System.nanoTime() - startTime;
			medianCoordinatePoint = new Point(medX.getX(), medY.getY());
			
		}
		else if(sortingAlgorithm == Algorithm.InsertionSort) {
			aSorter = new InsertionSorter(points);
			
			long startTime = System.nanoTime();
			aSorter.setComparator(0);
			aSorter.sort();
			medX = aSorter.getMedian();
			
			aSorter.setComparator(1);
			aSorter.sort();
			medY = aSorter.getMedian();
			scanTime = System.nanoTime() - startTime;
			medianCoordinatePoint = new Point(medX.getX(), medY.getY());
		}
		else if(sortingAlgorithm == Algorithm.MergeSort) {
			aSorter = new MergeSorter(points);
			
			long startTime = System.nanoTime();
			aSorter.setComparator(0);
			aSorter.sort();
			medX = aSorter.getMedian();
			
			aSorter.setComparator(1);
			aSorter.sort();
			medY = aSorter.getMedian();
			scanTime = System.nanoTime() - startTime;
			medianCoordinatePoint = new Point(medX.getX(), medY.getY());
		}
		else if(sortingAlgorithm == Algorithm.QuickSort) {
			aSorter = new QuickSorter(points);
			
			long startTime = System.nanoTime();
			aSorter.setComparator(0);
			aSorter.sort();
			medX = aSorter.getMedian();
			
			aSorter.setComparator(1);
			aSorter.sort();
			medY = aSorter.getMedian();
			scanTime = System.nanoTime() - startTime;
			medianCoordinatePoint = new Point(medX.getX(), medY.getY());
		}
			
		
		
		// create an object to be referenced by aSorter according to sortingAlgorithm. for each of the two 
		// rounds of sorting, have aSorter do the following: 
		// 
		//     a) call setComparator() with an argument 0 or 1. 
		//
		//     b) call sort(). 		
		// 
		//     c) use a new Point object to store the coordinates of the medianCoordinatePoint
		//
		//     d) set the medianCoordinatePoint reference to the object with the correct coordinates.
		//
		//     e) sum up the times spent on the two sorting rounds and set the instance variable scanTime. 
		
		//medianCoordinatePoint = new Point(medX.getX(), medY.getY());
		
	}
	
	
	/**
	 * Outputs performance statistics in the format: 
	 * 
	 * <sorting algorithm> <size>  <time>
	 * 
	 * For instance, 
	 * 
	 * selection sort   1000	  9200867
	 * 
	 * Use the spacing in the sample run in Section 2 of the project description. 
	 */
	public String stats()
	{
		String rString = sortingAlgorithm + " " + points.length + " " + scanTime;
		System.out.println(rString);
		return rString;
	}
	
	
	/**
	 * Write MCP after a call to scan(),  in the format "MCP: (x, y)"   The x and y coordinates of the point are displayed on the same line with exactly one blank space 
	 * in between. 
	 */
	@Override
	public String toString()
	{
		return "MCP: (" + medianCoordinatePoint.getX() + " " + medianCoordinatePoint.getY() + ")";
	}

	
	/**
	 *  
	 * This method, called after scanning, writes point data into a file by outputFileName. The format 
	 * of data in the file is the same as printed out from toString().  The file can help you verify 
	 * the full correctness of a sorting result and debug the underlying algorithm. 
	 * 
	 * @throws FileNotFoundException
	 */
	public void writeMCPToFile() throws FileNotFoundException
	{
		Scanner scnr = new Scanner(System.in);
        System.out.println("Please enter file path:");
        String filename = scnr.nextLine();
		try {
		      FileWriter myWriter = new FileWriter(filename);
		      myWriter.write("MCP: (" + medianCoordinatePoint.getX() + " " + medianCoordinatePoint.getY() + ")");
		      myWriter.close();
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		finally {
			scnr.close();
		}
		
	}	

	

		
}
