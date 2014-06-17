package vn.game.speedorder;

import java.util.List;

public class ResultChecker {
	List<Integer> output;

	private boolean failed = false;

	public ResultChecker(List<Integer> input) {
		// selectionSort(input);
		quickSort(input, 0, input.size() - 1);
		output = input;
	}

	public ResultChecker(List<Integer> elements, List<Integer> play) {
		failed = false;
		quickSort(elements, 0, elements.size() - 1);
		output = elements;
		for (int i = 0; i < elements.size(); i++) {
			_Log.v(elements.get(i) + "", play.get(i) + "");
			if (elements.get(i) != play.get(i))
				failed = true;
		}
	}

	public boolean isFailed() {
		return failed;
	}
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

	private void swap(List<Integer> input, int i, int j) {
		int temp = input.get(i);
		input.set(i, input.get(j));
		input.set(j, temp);
	}

	public List<Integer> getResult() {
		return output;
	}
}
