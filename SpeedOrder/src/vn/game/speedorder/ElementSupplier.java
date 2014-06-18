package vn.game.speedorder;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import vn.game.speedorder.utils._Log;



public class ElementSupplier {
	private static Random random = new Random();

	public static List<Integer> elementSupplier(int numberOfElement, int limit) {
		List<Integer> elements = new ArrayList<Integer>();
		_Log.v("element.size() before add any element : ", elements.size() + "");
		elements.add(greateRandomElement(limit));
		_Log.v("element đầu tiên = ", elements.get(0) + "");
		while (elements.size() < numberOfElement) {
			_Log.v("element.size() sau khi add item thứ : " + elements.size(),
					elements.size() + "");
			Integer newElement = greateRandomElement(limit);
			_Log.v("new newElement greated: ", newElement + "");
			newElement = inspectingDistinction(limit, elements, newElement);
			_Log.v(" tAỌ newElement hoàn tất ", newElement + "");
			elements.add(newElement);
		}
		_Log.v("element.size() : ", elements.size() + "");
		return elements;
	}

	private static Integer inspectingDistinction(int limit,
			List<Integer> elements, Integer newElement) {
		for (int i = 0; i < elements.size(); i++) {
			_Log.v(" start the for loop round " + i + " ", i + "");
			_Log.v(" Đang so sanh elements.get(" + i + ") va " + newElement,
					elements.get(i) + " VS " + newElement);
			while (elements.get(i) == newElement) {
				_Log.v(" Trùng với elements.get(" + i + ") ", elements.get(i)
						+ " == " + newElement);
				newElement = inspectingDistinction(limit, elements,
						greateRandomElement(limit));
				_Log.v(" tạo newElement mới = ", newElement + "");
			}
			_Log.v(" kết thúc loop thứ : " + i + " vòng for", i + "");
		}
		return newElement;
	}

	private static Integer greateRandomElement(int limit) {
		return random.nextInt(limit);
	}
}
