package model;

import dao.ItemsDAO;

/**
 * DeleteItemのロジッククラス
 */
public class DeleteItemLogic {

	/**
	 * itemIdのToDoがログインしているユーザーのものであれば、削除を行うメソッド
	 *
	 * @param itemId		削除するToDoのID
	 * @param userId		ログインしているユーザーのID
	 * @return				削除に成功したらtrue
	 */
	public static boolean execute(int itemId, String userId) {

		return ItemsDAO.deleteItem(itemId, userId);
	}

}
