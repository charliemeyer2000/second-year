package edu.virginia.cs.sde.efficiency;

import java.util.Scanner;

/**
 * Author: Paul "Will" McBurney
 * 
 * Example of sorting methodologies. This class will generate
 * a randomized array and then sort it:
 * 
 * Execution: java SortingExamples sortingMethod arraySize maxRandomNumber
 * 
 * Example: java SortingExamples insertion 6 10 print
 * 
 * The above example would perform an insertion sort on a random array of
 * size 6, which contains values 1 through 10. Each step of the sort will
 * be printed.
 * 
 * [print] is an optional 3rd argument. If you include print
 * as argument 3, the arrays will print for each step of the
 * sort. This is valuable for visualization, but do NOT print
 * for very large arrays, as printing is very slow.
 * 
 * Another Example: java SortingExamples bubble 10000 10000
 * 
 * This will perform a bubble sort on a random array of size 10000,
 * where each element is between 1 and 10000. This will NOT print the
 * array at each step.
 * 
 * sortingMethod: valid inputs are
 *        selection
 *        insertion
 *        bubble
 *        merge
 * 
 * 
 */


public class SortingExamples{
    private static int MAX_RANDOM_NUMBER;
    private static int ARRAY_SIZE;
    private static boolean PRINT_ARRAYS = false;
    
    public static void main(String args[]){
    	Scanner in = new Scanner(System.in);
    	
    	
        //Handle Command Line Arguments
    	System.out.print("Sorting method: 1) bubble 2) insertion 3) selection 4) merge : ");
    	int choice = in.nextInt();
    	String sortingMethod = "merge";
    	switch (choice) {
    	case 1:
    		sortingMethod = "bubble";
    		break;
    	case 2:
    		sortingMethod = "insertion";
    		break;
    	case 3:
    		sortingMethod = "selection";
    		break;
    	}
    	in.nextLine();
        System.out.print("How large an array would you like to sort? ");
        ARRAY_SIZE = in.nextInt();
        in.nextLine();
        System.out.print("What's the largest possible value in the array to have? ");
        MAX_RANDOM_NUMBER = in.nextInt();
        in.nextLine();
        System.out.print("Would you like to print the search? (Y/N) " );
        String response = in.nextLine();
        if (response.toUpperCase().startsWith("Y")) {
        	PRINT_ARRAYS = true;
        } else {
        	PRINT_ARRAYS = false;
        }
        
        //generate random array
        int[] array = randomIntArray(ARRAY_SIZE, MAX_RANDOM_NUMBER);
        
        //print whether or not the array is initially sorted
        System.out.println(isArraySorted(array));
        if (PRINT_ARRAYS) {
            printArray(array);
        }
        System.out.println("---Start Sorting----------");
        
        //select the correct sorting method and apply it
        if (sortingMethod.equalsIgnoreCase("selection")) {
            selectionSort(array);
        } else if (sortingMethod.equalsIgnoreCase("insertion")) {
            insertionSort(array);
        } else if (sortingMethod.equalsIgnoreCase("bubble")) {
            bubbleSort(array);
        } else if (sortingMethod.equalsIgnoreCase("merge")){
            mergeSort(array);
        } else {
            System.out.println("Illegal sorting method. Use selection, " +
                               "insertion, bubble, or merge");
        }
        
        System.out.println("---Sorting Over-----------");
        
        //ensure that the array is now sorted correctly.
        System.out.println(isArraySorted(array));
        if (PRINT_ARRAYS) {
            printArray(array);
        }
        in.close();
    }
    
    /**
     * Generate an array of "size" number of random numbers
     * The random numbers are between 1 and max
     * inclusive.
     */
    public static int[] randomIntArray(int size, int max){
        int[] output = new int[size];
        for(int i=0; i < output.length; i++){
            output[i] = (int)(Math.random()*max+ 1);
        }
        return output;
    }
    
    /**
     * Returns true if the array is sorted in ascending order
     */
    public static boolean isArraySorted(int[] array){
        for(int i=0; i<array.length-1; i++){
            if(array[i] > array[i+1]){
                return false;
            }
        }
        return true;
    }
    /**
     * Print the contents of the array
     */
    public static void printArray(int[] array){
        for(int i=0; i < array.length; i++){
            System.out.print(array[i]);
            if (i != array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
    
    /**
     * Swap two values in an array
     */
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[j];
        arr[j] = arr[i];
        arr[i] = temp;
    }
    
    /**
     * Bubble sort algorithm
     */
    public static void bubbleSort(int[] array){
        for(int i=0; i < array.length-1; i++){
            for(int j=0; j < array.length-1; j++){
                if(array[j] > array[j+1]){
                    swap(array, j, j+1);
                }
            }
            if (PRINT_ARRAYS) {
                printArray(array);
            }
        }
    }
    
    /**
     * Selection sort algorithm
     */
    public static void selectionSort(int[] array){
        for(int i=0; i<array.length-1; i++){
            int min = array[i];
            int minIndex = i;
            for(int j=i+1; j<array.length; j++){
                if(array[j]<min){
                    min = array[j];
                    minIndex = j;
                }
            }
            //here
            swap(array, i, minIndex);
            if (PRINT_ARRAYS) {
                printArray(array);
            }
        }
    }
    
    
    
    /**
     * Insertion sort algorithm
     */
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0; j--) {
                if (array[j-1] > (array[j])) {
                    swap(array, j - 1, j);
                }
                else break;
            }
            if (PRINT_ARRAYS) {
                printArray(array);
            }
        }
    }
    
    
    /**
     * Merge sort (covered on Thursday)
     */
    public static void mergeSort(int[] a)
    {
        int[] tmp = new int[a.length];
        mergeSort(a, tmp,  0,  a.length - 1);
    }
    
    
    private static void mergeSort(int[] a, int[] tmp, int left, int right)
    {
        if( left < right )//base case is left >= right
        {
            int center = (left + right) / 2;
            mergeSort(a, tmp, left, center);
            mergeSort(a, tmp, center + 1, right);
            merge(a, tmp, left, center + 1, right);
            if (PRINT_ARRAYS) {
                printArray(a);
            }
        }
    }
    
    /**
     * Merge helper function
     */
    private static void merge(int[] a, int[] tmp, int left, int right, int rightEnd )
    {
        int leftEnd = right - 1;
        int k = left;
        int num = rightEnd - left + 1;
        
        while(left <= leftEnd && right <= rightEnd){
            if (a[left]<=a[right]){
                tmp[k] = a[left];
                k++;
                left++;
            }
            else{
                tmp[k] = a[right];
                k++;
                right++;
            }
        }
        
        while(left <= leftEnd) {   // Copy rest of first half
            tmp[k] = a[left];
            k++;
            left++;
        }
        
        while(right <= rightEnd){  // Copy rest of right half
            tmp[k] = a[right];
            k++;
            right++;
        }
        
        // Copy tmp back
        for(int i = 0; i < num; i++){
            a[rightEnd] = tmp[rightEnd];
            rightEnd--;
        }
    }
}