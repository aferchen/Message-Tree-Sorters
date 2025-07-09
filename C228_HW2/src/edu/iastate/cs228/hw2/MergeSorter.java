package edu.iastate.cs228.hw2;

import java.io.FileNotFoundException;
import java.lang.NumberFormatException; 
import java.lang.IllegalArgumentException; 
import java.util.InputMismatchException;

/**
 *  
 * @author Alec Ferchen 
 *
 */

/**
 * 
 * This class implements the mergesort algorithm.   
 *
 */

public class MergeSorter extends AbstractSorter
{
	// Other private instance variables if needed
	private Point[] newPoints;
	/** 
	 * Constructor takes an array of points.  It invokes the superclass constructor, and also 
	 * set the instance variables algorithm in the superclass.
	 *  
	 * @param pts   input array of integers
	 */
	public MergeSorter(Point[] pts) 
	{
		super(pts);
	}


	/**
	 * Perform mergesort on the array points[] of the parent class AbstractSorter. 
	 * 
	 */
	@Override 
	public void sort()
	{
		points = mergeSortRec(points);
		
	}

	
	/**
	 * This is a recursive method that carries out mergesort on an array pts[] of points. One 
	 * way is to make copies of the two halves of pts[], recursively call mergeSort on them, 
	 * and merge the two sorted subarrays into pts[].   
	 * 
	 * @param pts	point array 
	 * @return 
	 */
	private Point[] mergeSortRec(Point[] pts)
	{		
		if (pts.length <= 1){ // Termination condition
	    return pts;
		}
	  int m = pts.length/2; // Has to be int
	  Point[] pt_left = new Point[m];
	  for(int i = 0; i < m; i++) {
		  pt_left[i] = pts[i];
	  }
	  Point[] pt_right = new Point[pts.length - m];
	  for(int i = m; i < pts.length; i++) {
		  pt_right[i - m] = pts[i];
	  }
	  pt_left = mergeSortRec(pt_left);
	  pt_right =mergeSortRec(pt_right);
	  pts = Merge(pt_left, pt_right);
	  return pts;
	  
	}
	/*
	 * MERGESORT (A)
n = A.length
if n â¤ 1 return
m = n/2
B = [A_0......A_m]
C = [A_m+1.....A_n-1]
B = MERGESORT(B) //produces sorted B.
C = MERGESORT(C) //produces sorted C
A = MERGE(B, C) //need to know Merge's
//big-O. O(n)
return A
	 */
	
	private Point[] Merge(Point[] pt_left, Point[] pt_right) {
		int leftLen = pt_left.length;
		int rightLen = pt_right.length;
		Point[] D = new Point[leftLen + rightLen];
		int i = 0;
		int j = 0;
		int Dindex = 0;
		while (i < leftLen && j < rightLen) { // There are elements in either array
			if (pointComparator.compare(pt_left[i], pt_right[j]) <= 0) {
				D[Dindex] = pt_left[i];
				i++;
				Dindex++;
			}
			else {
				D[Dindex] = pt_right[j];
				j++;
				Dindex++;
			}
		}
		if (i >= leftLen) {
			for(int u = j; u < pt_right.length; u++) {
				D[Dindex] = pt_right[u];
				Dindex++;
			}
		}
		else {
			for(int u = i; u < pt_left.length; u++) {
				D[Dindex] = pt_left[u]; 
				Dindex++;
			}
		}
		
		
		return D;
	}
}
	/*
	 MERGE(B,C)
p = B.length, q = C.length
create an empty array D of length p+q
i=0, j=0, k=0
while i < p && j < q linear time
if B[i] â¤ C[j]
D[i+j] = B[i]
i++
else
D[i+j] = C[j]
j++
if i â¥ p
append C[j],...,C[q-1] to D linear
else
append B[i],...,B[p-1] to D
return D
	 */
	// Other private methods if needed ...

