package model;

import beans.ID;
import dao.ItemsDAO;

/**
 * CompleteItemのロジッククラス
 */
public class CompleteItemLogic {

	/**
	 * ToDoを完了させるメソッド
	 *
	 * @param completeId		完了させるToDoのID
	 * @return		完了処理に成功したらtrue
	 */
	public static boolean execute(ID completeId) {

		return ItemsDAO.completeItem(completeId);
	}

}
