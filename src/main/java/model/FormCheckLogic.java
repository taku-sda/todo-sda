package model;

import beans.User;

/**
 * ユーザー情報のチェック処理を行うクラス
 */
public class FormCheckLogic {

	/**
	 * ユーザー情報が条件を満たしているかチェックするメソッド
	 *
	 * @param checkUser						チェックするユーザー情報
	 * @throws IllegalArgumentException		条件を満たしていない場合
	 */
	public static void check(User checkUser) throws IllegalArgumentException{

		//ユーザーIDのチェック
		if(! checkUser.getUserId().matches("[a-zA-Z0-9]{1,20}")) {
			throw new IllegalArgumentException("ユーザーIDは英数字20文字以内である必要があります。");
		}

		//パスワードのチェック
		if(! checkUser.getPass().matches("[a-zA-Z0-9]{4,8}")) {
			throw new IllegalArgumentException("パスワードは英数字4～8文字である必要があります。");
		}
	}
}
