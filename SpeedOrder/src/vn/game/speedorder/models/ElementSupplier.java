package vn.game.speedorder.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * To supply a list of random value elements.
 * 
 * @author DUC QUYNH
 * 
 */
public class ElementSupplier {
	private static Random random = new Random();

	/**
	 * Supplies a new random list with distinct elements.
	 * 
	 * @param numberOfElement
	 * @param limit
	 * @return New random list with distinct elements.
	 */
	public static List<Integer> supply(int numberOfElement, int limit) {
		List<Integer> elements = new ArrayList<Integer>();
		/*
		 * At first, we make a random Integer as the first element of our list.
		 */
		elements.add(greateRandomElement(limit));
		/*
		 * Then, looping a while loop until the list's size equals number of
		 * elements we desired.
		 */
		while (elements.size() < numberOfElement) {
			/*
			 * Only add the random value that has inspected the distinction from
			 * other elements in the list.
			 */
			elements.add(inspectingDistinction(limit, elements,
					greateRandomElement(limit)));
		}
		return elements;
	}

	/**
	 * Using recursion algorithm, we made this to get other elements left in the
	 * list.
	 * 
	 * @param limit
	 *            The limit of nextInt().
	 * @param elements
	 *            The list under construction.
	 * @param newElement
	 *            New element we has made.
	 * @return New element we has made.
	 */
	private static Integer inspectingDistinction(int limit,
			List<Integer> elements, Integer newElement) {
		/*
		 * Examine all the elements in the list.
		 */
		for (int i = 0; i < elements.size(); i++) {
			/*
			 * If there is any list's element equals the new value, we make a
			 * new one.
			 */
			while (elements.get(i) == newElement) {
				newElement = inspectingDistinction(limit, elements,
						greateRandomElement(limit));
			}
		}
		return newElement;
	}

	/**
	 * Spawns a integer with random value by a given limit.
	 * 
	 * @param limit
	 *            Limit of nextInt().
	 * @return A random integer.
	 */
	private static Integer greateRandomElement(int limit) {
		return random.nextInt(limit);
	}
}
