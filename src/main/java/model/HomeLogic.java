package model;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import beans.Item;
import comparator.DeadLineComparator;
import dao.ItemsDAO;

/**
 * Homeサーブレットのロジッククラス
 */
public class HomeLogic {

	/**
	 * ユーザーのToDo一覧を取得するメソッド
	 *
	 * @param userId	ユーザーのID
	 * @return		ToDo一覧のリスト
	 */
	public static List<Item> exectute(String userId) {

		return ItemsDAO.searchItemByUserId(userId);
	}


	/**
	 * ToDO一覧を期限順にソートするメソッド
	 */
	public static void sortItem(List<Item> itemList) {

		Collections.sort(itemList, new DeadLineComparator());
	}


	/**
	 * ToDoの一覧を完了、期限切れ、今日まで、その他のToDoに分類するメソッド
	 *
	 * @param itemList		分類するToDoのリスト
	 * @return		分類されたリスト
	 * 				要素番号の0:完了 1:期限切れ 2:今日まで 3:その他のToDo
	 */
	public static List<List<Item>> classificationItem(List<Item> itemList) {

		List<List<Item>> resultList = new ArrayList<>();
		List<Item> completedList = new ArrayList<>();
		List<Item> expiredList = new ArrayList<>();
		List<Item> todayList = new ArrayList<>();
		List<Item> otherList = new ArrayList<>();

		LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC+09:00"));

		for (Item item : itemList) {
			if (item.isCompleted() == true) {
				completedList.add(item);
			} else if (now.isAfter(item.getDeadLine())) {
				expiredList.add(item);
			} else if (item.getYear() == now.getYear() && item.getMonth() == now.getMonthValue()
					&& item.getDay() == now.getDayOfMonth()) {
				todayList.add(item);
				otherList.add(item);
			} else {
				otherList.add(item);
			}
		}
		resultList.add(completedList);
		resultList.add(expiredList);
		resultList.add(todayList);
		resultList.add(otherList);

		return resultList;
	}

}
