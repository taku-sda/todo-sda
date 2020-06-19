package model;

import beans.ID;
import dao.ItemDAO;

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

		return ItemDAO.completeItem(completeId);
	}

}
