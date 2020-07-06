package model;

import beans.User;
import dao.UsersDAO;

/**
 * Loginサーブレットのロジッククラス
 */
public class LoginLogic {

	/**
	 * ユーザーがログイン可能か確認するメソッド
	 *
	 * @param loginUser		確認するユーザー情報
	 * @return		ログイン可能ならtrue
	 */
	public static boolean execute(User loginUser) {

		return UsersDAO.login(loginUser);
	}

}
