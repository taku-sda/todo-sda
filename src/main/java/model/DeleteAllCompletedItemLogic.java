package model;

import dao.ItemsDAO;

/**
 * DeleteAllCompletedItemのロジッククラス
 */
public class DeleteAllCompletedItemLogic {

	/**
	 * ログインユーザーの全ての完了状態のToDoを削除するメソッド
	 *
	 *@param userId		ログインユーザーのID
	 * @return		削除に成功したらtrue
	 */
	public static boolean execute(String userId) {

		return ItemsDAO.deleteAllCompletedItem(userId);
	}

}
