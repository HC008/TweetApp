/**
 * This QuickSort class is primarily used to sort a given array of numbers through the driver class.  The quicksort
 * method will call the partition method if the first index is less than the last.  Otherwise, a recursive quicksort
 * call will be made from the first element to the pivot index minus one, as well as another recursive quicksort call
 * from the pivot index plus one all the way until the last element of the array.  If only an array is given, 
 * the left endpoint is the first element of the array, and the right endpoint is the last element of the array. 
 * 
 * @author Original Author:  Jason Delos Reyes
 * @author Derivative Authors:  Hansen Cheng, Micah Angeles, Jason Delos Reyes	
 */
public class QuickSort {
	
	protected int comparison = 0;
	
	/**A constructor method to allow the driver to utilize this QuickSort class.
	 */
	public QuickSort(){	
	}
	
	
	/**If only an array is given as a parameter, then the quicksort method will be overrided to have the left index
	 * as the first element of the array, while the right index as the last element of the array.
	 */
	public void quickSort(int[] array) {
		quickSort(array, 0, array.length -1);
	}
	
	/** If the first element of the array is greater than the last element of the array, the partition method is called.
	 */
	public void quickSort(int[] array, int first, int last) {
		
		if (first < last) {
			int pivIndex = partition(array, first, last);
			
			quickSort(array, first, pivIndex - 1);
			
			quickSort(array, pivIndex + 1, last);
		}
		
	}
	
	/** The method contains three variables:  a pivot value, a left pointer, and a right pointer.  The program
	 * will find the location where the pivot value is less than, or equal to the value of the leftPointer as well 
	 * as finding where the pivot value is less than the rightPointer value.  When both values have been found, if
	 * the left pointer is less than the right pointer, then they will swap values.  The rightPointer value will be
	 * returned to determine which subarray needs to be quick-sorted.
	 */
	public int partition(int[] array, int first, int last) {
		
		int pivot = array[first];
		int leftPointer = first;
		int rightPointer = last;
		
		
		//While the leftPointer is less than the right pointer (or the pointers haven't crossed yet) . . . 
		while (leftPointer < rightPointer) {
			
			//Finds the point where either leftPointer is more than last (rightmost index) OR the pivot is less
			//than the element at array[leftPointer].  If not, the leftPointer index increases until either condition
			//is met.
			while ((leftPointer < last) && (pivot >= array[leftPointer])) {
				leftPointer++;
			}
			
			//Finds the point where the pivot is equal to or greater than the element at array[rightPointer].  
			//Otherwise, the rightPointer index increases until the condition is met.
			while (pivot <  array[rightPointer]) {
				rightPointer--;
			}
			
			//At this point, if the leftPointer is less than the rightPointer, the elements of array[leftPointer]
			//and array[rightPointer] will swap.
			if (leftPointer < rightPointer) {
				int temp = array[leftPointer];
				array[leftPointer] = array[rightPointer];
				array[rightPointer] = temp;
				comparison++;
			}
		}
			//After the large while loop, the elements at array[rightPointer] and array[first] will swap
			int temp = array[rightPointer];
			array[rightPointer] = array[first];
			array[first] = temp;
			comparison++;
			
			//The right index is returned to be used to determine the next partition.
			return rightPointer;
		
		
	}
	
	
	
	
}
