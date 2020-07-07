package model;

import dao.ItemsDAO;

/**
 * DeleteALLExpiredItemのロジッククラス
 */
public class DeleteAllExpiredItemLogic {

	/**
	 * ログインユーザーの全ての期限切れのToDoを削除するメソッド
	 *
	 * @param userId 	ログインユーザーのID
	 * @return			削除に成功したらtrue
	 */
	public static boolean execute(String userId) {

		return ItemsDAO.deleteAllExpiredItem(userId);
	}

}
