package model;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
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
	 * ホーム画面の表示用に分類されたToDo一覧を取得するメソッド
	 *
	 * @param userId	ユーザーのID
	 * @return			分類されたToDo一覧
	 * @exception SQLException 			データベース処理の例外
	 * @exception DateTimeParseException 	DateTimeの変換時のエラー
	 */
	public static List<List<Item>> getClassifiedLists(String userId) throws SQLException, DateTimeParseException {

		//ログインユーザーのToDoの一覧を取得
		List<Item> itemList = ItemsDAO.getListByUserId(userId);

		//期限で昇順ソート
		Collections.sort(itemList, new DeadLineComparator());

		//状態ごとに分類したToDo一覧を返す
		return classifyItem(itemList);
	}

	/**
	 * ToDoの一覧を完了、期限切れ、今日まで、その他のToDoに分類するメソッド
	 *
	 * @param itemList	分類するToDoのリスト
	 * @return			分類されたリスト
	 * 					要素番号の0:完了 1:期限切れ 2:今日まで 3:その他のToDo
	 */
	private static List<List<Item>> classifyItem(List<Item> itemList) {

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
