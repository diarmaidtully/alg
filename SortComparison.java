//testing
// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author diarmaid tully
 * @version HT 2019
 */

class SortComparison {

	/**
	 * Sorts an array of doubles using InsertionSort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double[] insertionSort(double a[]) {
		
		double numberToSwap;
		for (int i = 0; i < a.length; i++) {
			for (int j = i; j > 0; j--) {
				if (a[j] < a[j - 1]) {
					numberToSwap = a[j];
					a[j] = a[j - 1];
					a[j - 1] = numberToSwap;
				}
			}
		}
		return a;
	}

	/**
	 * Sorts an array of doubles using Quick Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] quickSort(double a[]) {
		
		Sort(a, 0, a.length - 1);
		return a;

	}

	 static void Sort(double[] arr, int start, int finish) {
		if (start < finish) {
			int index = partition(arr, start, finish);
			Sort(arr, start, index - 1);
			Sort(arr, index + 1, finish);
		}
	}

	 static int partition(double[] arr, int start, int finish) {
		double PIVOT = arr[finish];
		int i = (start - 1);

		for (int j = start; j < finish; j++) {
			if (arr[j] <= PIVOT) {
				i++;

				double temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		double swapTemp = arr[i + 1];
		arr[i + 1] = arr[finish];
		arr[finish] = swapTemp;

		return i + 1;
	}// 

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus it
	 * can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */

	 
	   public static double[] mergeSortIterative(double[] a) {
	        int x = a.length;
	        double[] auxilery = new double[x];
	        for (int length = 1; length < x; length *= 2) {
	            for (int low = 0; low < x-length; low += length+length) {
	                int mid  = low+length-1;
	                int hi = Math.min(low+length+length-1, x-1);
	                merge(a, auxilery, low, mid, hi);
	            }
	        }
	        return a;
	    }
	   
	   private static void merge(double[] a, double[] auxilery, int low, int middle, int high) {

	        
	        for (int k = low; k <= high; k++) {
	        	auxilery[k] = a[k]; 
	        }

	        int i = low, j = middle+1;
	        for (int k = low; k <= high; k++) {
	            if      (i > middle)              a[k] = auxilery[j++];  
	            else if (j > high)               a[k] = auxilery[i++];
	            else if ((auxilery[j]<auxilery[i])) a[k] = auxilery[j++];
	            else                           a[k] = auxilery[i++];
	        }

	    }// end mergesortIterative

	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort. This
	 * method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a: An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */
	
	
	 static double[] mergeSortRecursive(double[] array) {
		
		if (array.length > 1) {
			int middle = array.length / 2;
			double[] leftSide = new double[middle];
			for (int i = 0; i < middle; i++) {
				leftSide[i] = array[i];
			}
			double[] rightSide = new double[array.length - middle];
			for (int i = middle; i < array.length; i++) {
				rightSide[i - middle] = array[i];
			}
			mergeSortRecursive(leftSide);
			mergeSortRecursive(rightSide);
			int i = 0;
			int j = 0;
			int k = 0;
			while (i < leftSide.length && j < rightSide.length) {
				if (leftSide[i] < rightSide[j]) {
					array[k] = leftSide[i];
					i++;
				} else {
					array[k] = rightSide[j];
					j++;
				}
				k++;
			}
			while (i < leftSide.length) {
				array[k] = leftSide[i];
				i++;
				k++;
			}
			while (j < rightSide.length) {
				array[k] = rightSide[j];
				j++;
				k++;
			}
		}
		return array;
	}// end mergesortRecursive

	/**
	 * Sorts an array of doubles using Selection Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a: An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {
		
		double temp;
		double small;
		for (int i = 0; i < a.length; i++) {
			for (int j = i + 1; j < a.length; j++) {
				small = a[i];
				if (a[j] < small) {
					temp = a[i];
					a[i] = a[j];
					a[j] = temp;
				}
			}
		}
		return a;
	}// end selectionsort

	public static void main(String[] args) {

		// todo: do experiments as per assignment instructions
		
	}

	public static String toString(double a[]) {
		String result = "";
		for (int i = 0; i < a.length; i++) {
			result += a[i] + ", ";
		}
		return result;

	}
}// end class
