import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2019
 */

/*
 * 						| insert	|	quick	| merge recursive	|	merge iterative 	|	selection	|
 * ----------------------------------------------------------------------------------------------------------
 * 10 random			|366557		|2768		|6927				|19387					|8183			|
 * ----------------------------------------------------------------------------------------------------------
 * 100 random			|418512		|1421860	|122848			|393689					|1018463		|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 random			|4774570	|3083769	|202133			|393438					|8207494		|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 few unique		|478518		|369149		|214845			|164749					|272616			|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 nearly ordered	|485595		|296778		|189295			|288096					|244925			|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 reverse order	|365696		|314527		|184637				|293380					|327492			|
 * ----------------------------------------------------------------------------------------------------------
 * 1000 sorted			|415696		|344221		|219247			|114408					|296027			|
 * ----------------------------------------------------------------------------------------------------------
 * 
 * Questions:
 * a)Which of the sorting algorithms does the order of input have an impact on? Why? 
 * Insertion, selection and quick
 * the reverse order and nearly order have very different run times as there are more steps involved (iterations)
 * 		
 * 
 * b)Which algorithm has the biggest difference between the best and worst performance, based
	on the type of input, for the input of size 1000? Why?
	Selection sort as it has an O(n^2) in all cases
			
			
 * c)Which algorithm has the best/worst scalability, i.e., the difference in performance time
	based on the input size? Please consider only input files with random order for this answer. 
		Best = Quick Sort
 *      Worst = Selection Sort -> bc its O(n^2)
		
 * d)Did you observe any difference between iterative and recursive implementations of merge
	sort?
		 Ìterative takes a lot longer when the data is larger and random
		
   e)Which algorithm is the fastest for each of the 7 input files? 
    10 random - selection sort
 *          100 random - quick sort
 *          1000 random - quick sort
 *          1000 few unique - merge sort recursive
 *          1000 nearly ordered - insertion sort
 *          1000 reverse order - merge sort recursive
 *          1000 sorted - insertion sort
 */
@RunWith(JUnit4.class)
public class SortComparisonTest 
{
	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new SortComparison();
	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the methods work for empty arrays
	 */
	@Test
	public void testEmpty()
	{
		String expected="";

		double[] test0= {};
		double[] result=SortComparison.insertionSort(test0);
		assertEquals(expected,SortComparison.toString(result));

		double[] test1= {}; 	
		double[] result2=SortComparison.quickSort(test1);
		assertEquals(expected,SortComparison.toString(result2));

		double[] test2= {};   	
		double[] result3=SortComparison.mergeSortIterative(test2);
		assertEquals(expected,SortComparison.toString(result3));

		double[] test3= {};
		double[] result4=SortComparison.mergeSortRecursive(test3);
		assertEquals(expected,SortComparison.toString(result4));

		double[] test4= {};
		double[] result5=SortComparison.selectionSort(test4);
		assertEquals(expected,SortComparison.toString(result5));
	}


	// TODO: add more tests here. Each line of code and ech decision in Collinear.java should
	// be executed at least once from at least one test.

	// ----------------------------------------------------------

	@Test
	public void testInsertionSort()
	{
		double[] myTest= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.insertionSort(myTest);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(myTest));
	}

	@Test
	public void testSelertionSort()
	{
		double[] myTest= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.selectionSort(myTest);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(myTest));
	}

	@Test
	public void testQuickSort()
	{
		double[] myTest= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.quickSort(myTest);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(myTest));
	}

	@Test
	public void testMergeSortIterative()
	{
		double[] myTest= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.mergeSortIterative(myTest);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(myTest));
	}

	@Test
	public void testMergeSortRecursive()
	{
		double[] myTest= {2,9,1,7,};
		double []expected = {1,2,7,9};
		double result[]=SortComparison.mergeSortRecursive(myTest);
		assertEquals(SortComparison.toString(expected), SortComparison.toString(myTest));
	}
	/**
	 *  Main Method.
	 *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
	 * @throws IOException 
	 *
	 */

	public static void main(String[] args) throws IOException   
	{

		double[] random10=readFile(10,"numbers10.txt");
		double[] random100=readFile(100,"numbers100.txt");
		double[] random1000=readFile(1000,"numbers1000.txt");
		double[] fewUnique1000=readFile(1000,"numbers1000Duplicates.txt");
		double[] nearlyOrdered1000=readFile(1000,"numbersNearlyOrdered1000.txt");
		double[] reverse1000=readFile(1000,"numbersReverse1000.txt");
		double[] sorted1000=readFile(1000,"numbersSorted1000.txt");

		print("random 10: ",runTests(random10));
		print("random 100: ",runTests(random100));
		print("random 1000: ",runTests(random1000));
		print("few unique 1000: ",runTests(fewUnique1000));
		print("nearly Ordered 1000: ",runTests(nearlyOrdered1000));
		print("reverse 1000: ",runTests(reverse1000));
		print("sorted 1000: ",runTests(sorted1000));

	}
	public static long[] runTests(double[] arr) {
		long[] results=new long[5];
		results[0]=testInsertion(arr);
		results[1]=testQuick(arr);
		results[2]=testMergeRecursive(arr);
		results[3]=testMergeIterative(arr);
		results[4]=testSelection(arr);
		return results;
	}
	
	public static long testInsertion(double[] arr) {
		long result = 0;
		
		for(int a=3;a>0;a--) {
			long start=System.nanoTime();
			SortComparison.insertionSort(arr);
			long end=System.nanoTime();
			long x=end-start;
			result+=(x);
		}
		return (result/3);

	}
	public static long testSelection(double[] arr) {
		long result=0;
		for(int a=3;a>0;a--) {
			long begin=System.nanoTime();
			SortComparison.selectionSort(arr);
			long finish=System.nanoTime();
			result+=(finish-begin);
		}
		return (result/3);

	}
	public static long testQuick(double[] arr) {
		long answer=0;
		for(int a=3;a>0;a--) {
			long start=System.nanoTime();
			SortComparison.quickSort(arr);
			long end=System.nanoTime();
			answer+=(end-start);
		}
		return (answer/3);

	}
	public static long testMergeIterative(double[] arr) {
		long result=0;
		for(int a=3;a>0;a--) {
			long begin=System.nanoTime();
			SortComparison.mergeSortIterative(arr);
			long finish=System.nanoTime();
			result+=(finish-begin);
		}
		return (result/3);

	}
	public static long testMergeRecursive(double[] arr) {
		long result=0;
		for(int a=3;a>0;a--) {
			long begin=System.nanoTime();
			SortComparison.mergeSortRecursive(arr);
			long finish=System.nanoTime();
			result+=(finish-begin);
		}
		return (result/3);
	}



	public static double[] readFile(int length,  String string) throws IOException {
		File file=new File(string);
		Scanner keyboard =  new Scanner(file);
		double[] list = new double[length];
		int x = 0;
		while(keyboard.hasNextDouble()){
			list[x++] = keyboard.nextDouble();
		}
		keyboard.close();
		return list;
	}

	public static void print(String string,long[] arr) {
		System.out.print(string);
		for(int i=0;i<arr.length;i++) {
			System.out.print(arr[i]+" ");
		}
		System.out.print("\n");
	}


}
