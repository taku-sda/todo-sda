package model;

import beans.ID;
import beans.Item;
import dao.ItemsDAO;

/**
 * DetailItemのロジッククラス
 */
public class DetailItemLogic {

	/**
	 * ToDoの詳細を取得するメソッド
	 * @param detailId		詳細を取得するToDoのID
	 * @return		ToDoの詳細
	 */
	public static Item execute(ID detailId) {

		return ItemsDAO.searchItemByItemId(detailId);
	}

}
