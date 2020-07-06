package model;

import beans.Item;
import dao.ItemsDAO;

/**
 * DetailItemのロジッククラス
 */
public class DetailItemLogic {

	/**
	 * itemIdのToDoがログインしているユーザーのものであれば、詳細を取得するメソッド
	 *
	 * @param itemId		詳細を取得するToDoのID
	 * @param userId		ログインしているユーザーのID
	 * @return				ToDoの詳細
	 */
	public static Item execute(int itemId, String userId) {

		return ItemsDAO.searchItemByItemId(itemId, userId);
	}

}
