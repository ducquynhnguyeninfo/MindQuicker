package vn.game.speedorder.logic;

import java.util.List;

import vn.game.speedorder.utils._Log;

/**
 * Checking the sorting action of player whether correct or not to decide to
 * re-process another game's cycle.
 * 
 * @author DUC QUYNH
 * 
 */
public class ResultChecker {
	List<Integer> output;

	/**
	 * Result of the checking.
	 */
	private boolean failed = false;

	/**
	 * Constructor, we can choose the sorting way here.
	 * 
	 * @param input
	 *            The raw list of numbers unarranged yet.
	 */
	public ResultChecker(List<Integer> input) {
		// selectionSort(input);
		quickSort(input, 0, input.size() - 1);
		output = input;
	}

	/**
	 * We process the arranging and then compare this result to the list that
	 * player has arranged to pick the answer true or false.
	 * 
	 * @param rawElements
	 *            The raw list of numbers un-arranged yet.
	 * @param play
	 *            The list that is the result player has arranged.
	 */
	public ResultChecker(List<Integer> rawElements, List<Integer> play) {
		failed = false;
		// quickSort(rawElements, 0, rawElements.size() - 1);
		selectionSort(rawElements);
		output = rawElements;
		/*
		 * Checking each element of 2 lists at the same position whether matches
		 * or doesn't.
		 */
		for (int i = 0; i < rawElements.size(); i++) {
			_Log.v(rawElements.get(i) + "", play.get(i) + "");
			if (rawElements.get(i) != play.get(i)) // If there is any of
													// elements doesn't match at
													// the same position in 2
													// list
				failed = true;// we set true to failed to indicate that 2 lists
								// aren't
								// match each other.
		}
	}

	public boolean isFailed() {
		return failed;
	}

	/**
	 * Selection sort algorithm.
	 * 
	 * @param input
	 *            Input raw list.
	 */
	private void selectionSort(List<Integer> input) {
		for (int i = 0; i < input.size() - 1; i++) {
			int minAssuming = i;
			for (int j = i + 1; j < input.size(); j++) {
				if (input.get(i) > input.get(j)) {
					minAssuming = j;
					swap(input, i, j);
				}
			}
		}
	}

	/**
	 * Quick sort algorithm.
	 * 
	 * @param Input
	 *            raw list.
	 * @param l
	 *            left: The very start index of list.
	 * @param r
	 *            right: The very end index of list.
	 */
	void quickSort(List<Integer> input, int l, int r) {
		if (l >= r)
			return;
		int i = l;
		int j = r;
		int x = input.get((l + r) / 2);

		while (i <= j) {
			while (input.get(i) < x)
				i++;
			while (input.get(j) > x)
				j--;
			if (i <= j) {
				swap(input, i, j);
				i++;
				j--;
			}
		}
		quickSort(input, l, j);
		quickSort(input, i, r);
	}

	/**
	 * Exchanging position between 2 elements of a list.
	 * 
	 * @param input
	 *            The list.
	 * @param i
	 *            Element 1st's position.
	 * @param j
	 *            Element 2nd's position.
	 */
	private void swap(List<Integer> input, int i, int j) {
		int temp = input.get(i);
		input.set(i, input.get(j));
		input.set(j, temp);
	}

	/**
	 * 
	 * @return The list has arranged correctly.
	 */
	public List<Integer> getResult() {
		return output;
	}
}
