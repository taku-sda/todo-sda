package model;

import dao.ItemDAO;

/**
 * DeleteALLExpiredItemのロジッククラス
 */
public class DeleteAllExpiredItemLogic {

	/**
	 * 全ての期限切れのToDoを削除するメソッド
	 *
	 * @return		削除に成功したらtrue
	 */
	public static boolean execute() {

		return ItemDAO.deleteAllExpiredItem();
	}

}
