import java.util.List;

/**
 * This QuickSort class is primarily used to sort a given list of TweetRecord objects. 
 * The quicksort method will call the partition method if the first index 
 * is less than the last.  Otherwise, a recursive quicksort call will be made from the first 
 * element to the pivot index minus one, as well as another recursive quicksort call
 * from the pivot index plus one all the way until the last element of the list.  
 * If only an list is given, the left endpoint is the first element of the list, 
 * and the right endpoint is the last element of the list. 
 * 
 * @author Original Author:  Jason Delos Reyes
 * @author Derivative Authors:  Hansen Cheng, Micah Angeles, Jason Delos Reyes  
 */
public class QuickSort {
	
	protected int comparison = 0;
	private List<TweetRecord> records;
	
	/**
   * Constructs the object to contain the unsorted list of records.
   * 
   * @param records - unsorted list of TweetRecord objects.
   */
	public QuickSort(List<TweetRecord> records){
	  this.records = records;
	}
	
	
	/**If only an list is given as a parameter, then the quicksort method will 
	 * be overrided to have the left index as the first element of the list, 
	 * while the right index as the last element of the list.
	 */
	public void quickSort(List<TweetRecord> record) {
		quickSort(record, 0, record.size() - 1);
	}
	
	/** If the first element of the list is greater 
	 * than the last element of the list, the partition method is called.
	 */
	public void quickSort(List<TweetRecord> record, int first, int last) {
		
		if (first < last) {
			int pivIndex = partition(record, first, last);
			
			quickSort(record, first, pivIndex - 1);
			
			quickSort(record, pivIndex + 1, last);
		}
		
	}
	
	/** The method contains three variables:  a pivot value, a left pointer, and a 
	 * right pointer. The program will find the location where the pivot value is 
	 * less than, or equal to the value of the leftPointer as well as finding where 
	 * the pivot value is less than the rightPointer value.  When both values have 
	 * been found, if the left pointer is less than the right pointer, then they 
	 * will swap values.  The rightPointer value will be returned to determine which sublist 
	 * needs to be quick-sorted.
	 */
	public int partition(List<TweetRecord> record, int first, int last) {
		
		String pivot = record.get(first).getTweetDate();
		int leftPointer = first;
		int rightPointer = last;
		
		
		//While the leftPointer is less than the right pointer (or the pointers haven't crossed yet) . . . 
		while (leftPointer < rightPointer) {
			
			//Finds the point where either leftPointer is more than last (rightmost index) OR the pivot is less
			//than the element at array[leftPointer].  If not, the leftPointer index increases until either condition
			//is met.
			while ((leftPointer < last) && (pivot.compareTo(record.get(leftPointer).getTweetDate()) >= 
			       record.get(leftPointer).getTweetDate().compareTo(pivot))) {
				
			  leftPointer++;
			}
			
			//Finds the point where the pivot is equal to or greater than the element at array[rightPointer].  
			//Otherwise, the rightPointer index increases until the condition is met.
			while (pivot.compareTo(record.get(rightPointer).getTweetDate()) < 
			        record.get(rightPointer).getTweetDate().compareTo(pivot)) {
				
			  rightPointer--;
			}
			
			//At this point, if the leftPointer is less than the rightPointer, the elements of 
			//record.get(leftPointer)and record.get(rightPointer) will be swapped.
			if (leftPointer < rightPointer) {
				TweetRecord temp = record.get(leftPointer);
				record.set(leftPointer, record.get(rightPointer));
				record.set(rightPointer, temp);
				comparison++;
			}
		}
			//After the large while loop, the elements at 
		  //record.get(rightPointer) and record.get(first) will be swapped.
			TweetRecord temp = record.get(rightPointer);
			record.set(rightPointer, record.get(first));
			record.set(first, temp);
			comparison++;
			
			//The right index is returned to be used to determine the next partition.
			return rightPointer;
		
		
	}
	
	/**
	 * Retrieves the sorted list of TweetRecord objects.
	 * 
	 * @return records - list of TweetRecord objects.
	 */
	public List<TweetRecord> getRecord() {
	  return records;
	}
}
