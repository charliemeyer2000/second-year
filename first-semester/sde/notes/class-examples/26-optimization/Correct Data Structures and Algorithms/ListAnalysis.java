package edu.virginia.cs.sde.efficiency;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ListAnalysis {
	private final static int MAXIMUM_RANDOM_INT = 1000000;

	private static Random random;

	public static void main(String[] args) {
		random = new Random();
		List<Integer> intAL = new ArrayList<Integer>();
		List<Integer> intLL = new LinkedList<Integer>();
		
		//time insert at End
		timeInsertEnd(intAL, 100000);
		timeInsertEnd(intLL, 100000);
		
		//clear lists
		intAL.clear();
		intLL.clear();
		
		System.out.println(); //line break
		
		//time insert at End
		timeInsertFront(intAL, 100000);
		timeInsertFront(intLL, 100000);
		
		//clear lists
		intAL.clear();
		intLL.clear();
		
		System.out.println(); //line break
		
		//insert into random position
		timeInsertRandom(intAL, 100000);
		timeInsertRandom(intLL, 100000);

		System.out.println(); //line break
		timeToAccessRandomElements(intAL, 1000);
		timeToAccessRandomElements(intLL, 1000);
		
		//clear lists
		intAL.clear();
		intLL.clear();
		
		System.out.println(); //line break
	}
	
	

	/**
	 * Insert "n" random integers into the list "list" at the end
	 * of the list
	 * @param list
	 * @param n
	 */
	public static void insertEnd(List<Integer> list, int n) {
		for (int i = 0; i < n; i++) {
			list.add(random.nextInt(MAXIMUM_RANDOM_INT));
		}
	}
	
	/**
	 * Time how long it takes to execute insertEnd and print result
	 * @param list 
	 * @param n
	 */
	public static void timeInsertEnd(List<Integer> list, int n) {
		long start = System.currentTimeMillis();
		insertEnd(list, n);
		long end = System.currentTimeMillis();
		double duration = (end - start) / 1000.0;
		System.out.println("It took " + duration + " seconds to insert " + n + 
				" elements to end of the " + list.getClass().getName());
	}
	
	/**
	 * Insert "n" random elements into "list" at the FRONT, rather than
	 * the back
	 * @param list
	 * @param n
	 */
	public static void insertFront(List<Integer> list, int n) {
		for (int i = 0; i < n; i++) {
			list.add(0,random.nextInt(MAXIMUM_RANDOM_INT));
		}
	}

	/**
	 * Time how long it takes to do insertFront
	 * @param list
	 * @param n
	 */
	public static void timeInsertFront(List<Integer> list, int n) {
		long start = System.currentTimeMillis();
		insertFront(list, n);
		long end = System.currentTimeMillis();
		double duration = (end - start) / 1000.0;
		System.out.println("It took " + duration + " seconds to insert " + n + 
				" elements to front of the " + list.getClass().getName());
	}
	
	/**
	 * Insert n elements into a random position in the list
	 * @param list
	 * @param n
	 */
	public static void insertRandom(List<Integer> list, int n) {
		for (int i = 0; i < n; i++) {
			list.add(random.nextInt(list.size()+1),random.nextInt(MAXIMUM_RANDOM_INT));
		}
	}
	
	/**
	 * Time how long it takes to do insertFront
	 * @param list
	 * @param n
	 */
	public static void timeInsertRandom(List<Integer> list, int n) {
		long start = System.currentTimeMillis();
		insertRandom(list, n);
		long end = System.currentTimeMillis();
		double duration = (end - start) / 1000.0;
		System.out.println("It took " + duration + " seconds to insert " + n + 
				" elements randomly into " + list.getClass().getName());
	}
	
	public static void getRandomElements(List<Integer> list, int n) {
		for (int i = 0; i < n; i++) {
			list.get(random.nextInt(list.size()));
		}
	}
	
	public static void timeToAccessRandomElements(List<Integer> list, int n) {
		long start = System.currentTimeMillis();
		getRandomElements(list, n);
		long end = System.currentTimeMillis();
		double duration = (end - start) / 1000.0;
		System.out.println("It took " + duration + " seconds to access " + n + 
				" elements randomly in " + list.getClass().getName());
	}
}
