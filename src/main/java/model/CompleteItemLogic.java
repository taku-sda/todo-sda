package model;

import dao.ItemsDAO;

/**
 * CompleteItemのロジッククラス
 */
public class CompleteItemLogic {

	/**
	 * itemIdのToDoがログインしているユーザーのものであれば、完了状態にするメソッド
	 *
	 * @param itemId		完了させるToDoのID
	 * @param userId		ログインしているユーザーのID
	 * @return		完了処理に成功したらtrue
	 */
	public static boolean execute(int itemId, String userId) {

		return ItemsDAO.completeItem(itemId, userId);
	}

}
