package model;

import beans.User;

/**
 * ユーザー情報のチェック処理を行うクラス
 */
public class FormCheckLogic {

	/**
	 * ユーザー情報が条件を満たしているかチェックするメソッド
	 * @param checkUser		チェックするユーザー情報
	 * @return		条件を満たしていない場合の警告文(満たしている場合は空の文字列)
	 *
	 */
	public static String registerCheck(User checkUser) {

		StringBuilder sb = new StringBuilder();

		//ユーザーIDのチェック
		if(! checkUser.getUserId().matches("[a-zA-Z0-9]{1,20}")) {
			sb.append("ユーザーIDは英数字20文字以内である必要があります。").append(System.lineSeparator());
		}

		//パスワードのチェック
		if(! checkUser.getPass().matches("[a-zA-Z0-9]{4,8}")) {
			sb.append("パスワードは英数字4～8文字である必要があります。");
		}

		return sb.toString();
	}
}
