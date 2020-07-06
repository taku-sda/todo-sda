package model;

import beans.ID;
import dao.ItemsDAO;

/**
 * DeleteItemのロジッククラス
 */
public class DeleteItemLogic {

	/**
	 * ToDoの削除を行うメソッド
	 *
	 * @param deleteId		削除するToDoのID
	 * @return		削除に成功したらtrue
	 */
	public static boolean execute(ID deleteId) {

		return ItemsDAO.deleteItem(deleteId);
	}

}
