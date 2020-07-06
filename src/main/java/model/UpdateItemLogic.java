package model;

import beans.Item;
import dao.ItemsDAO;

/**
 * UpdateItemのロジッククラス
 */
public class UpdateItemLogic {

	/**
	 * 	ToDoの更新を行うメソッド
	 *
	 * @param updateItem		更新するToDo
	 * @return		更新されたらtrue
	 */
	public static boolean execute(Item updateItem) {

		return ItemsDAO.updateItem(updateItem);
	}

}
