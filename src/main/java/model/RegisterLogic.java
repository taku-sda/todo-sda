package model;

import beans.User;
import dao.UsersDAO;

/**
 * Registerサーブレットのロジッククラス
 */
public class RegisterLogic {

	/**
	 * ユーザー登録処理を行うメソッド
	 *
	 * @param registerUser		登録するユーザー情報
	 * @return		登録が完了した場合はtrue
	 */
	public static boolean execute(User registerUser) {

		 return UsersDAO.register(registerUser);

	}

}
