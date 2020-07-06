package model;

import beans.Item;
import dao.ItemsDAO;

/**
 * AddItemのロジッククラス
 */
public class AddItemLogic {

	/**
	 * ToDoの追加を行うメソッド
	 *
	 * @param addItem	追加するToDo
	 * @return		追加されたらtrue
	 */
	public static boolean execute(Item addItem) {

		return ItemsDAO.addItem(addItem);

	}

}
