package comparator;

import java.time.LocalDateTime;
import java.util.Comparator;

import beans.Item;

/**
 * ToDoを期限でソートするためのComparator
 */
public class DeadLineComparator implements Comparator<Item> {

	public int compare(Item item1, Item item2) {

		LocalDateTime dl1 = item1.getDeadLine();
		LocalDateTime dl2 = item2.getDeadLine();

		return dl1.compareTo(dl2);
	}

}
