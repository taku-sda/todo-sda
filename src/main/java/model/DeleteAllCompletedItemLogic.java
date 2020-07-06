package model;

import dao.ItemsDAO;

/**
 * DeleteAllCompletedItemのロジッククラス
 */
public class DeleteAllCompletedItemLogic {

	/**
	 * 全ての完了状態のToDoを削除するメソッド
	 *
	 * @return		削除に成功したらtrue
	 */
	public static boolean execute() {

		return ItemsDAO.deleteAllCompletedItem();
	}

}
