package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

/**
 * USERSテーブルに関する操作を行うDAOクラス
 */
public class UsersDAO extends DAOParent {

	/**
	 *	ユーザー情報を登録するメソッド
	 *
	 * @param registerUser		登録するユーザー情報
	 * @exception Exception 	登録処理が完了しない場合
	 * @exception SQLException データベース処理の例外
	 */
	public static void register(User registerUser) throws Exception, SQLException {

		try (Connection con = DAOParent.createConnection()) {

			String sql = "SELECT * FROM USERS WHERE USERID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, registerUser.getUserId());
			ResultSet rs = ps.executeQuery();

			//ユーザーIDの重複がない場合はユーザーの追加を行う
			if (!rs.next()) {
				sql = "INSERT INTO USERS (USERID, PASS) VALUES (?, ?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, registerUser.getUserId());
				ps.setString(2, registerUser.getPass());
				int result = ps.executeUpdate();

				if (result != 1) {
					throw new Exception("ユーザーの追加に失敗しました。");
				}
			} else {
				throw new Exception("そのユーザー名は既に使用されています。");
			}
		}
	}

	/**
	 * ユーザーがログイン可能か確認するメソッド
	 *
	 * @param loginUser			確認するユーザー情報
	 * @exception Exception 	ログイン不可の場合
	 * @exception SQLException データベース処理の例外
	 */
	public static void login(User loginUser) throws Exception, SQLException {

		try (Connection con = DAOParent.createConnection()) {

			String sql = "SELECT * FROM USERS WHERE USERID = ? AND PASS = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginUser.getUserId());
			ps.setString(2, loginUser.getPass());
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				throw new Exception("ログインできませんでした。入力内容をご確認ください。");
			}
		}
	}

}
