package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.User;

/**
 * USERテーブルに関する操作を行うDAOクラス
 */
public class UsersDAO extends DAOParent {

	/**
	 *	ユーザー情報を登録するメソッド
	 *
	 * @param registerUser	登録するユーザー情報
	 * @return		登録に成功した場合はtrue
	 */
	public static boolean register(User registerUser) {

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

				//追加に成功した場合はtrueを返す
				if (result == 1) {
					return true;
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//登録に失敗した場合はfalsseを返す
		return false;
	}


	/**
	 * ログインユーザーが存在するか確認するメソッド
	 *
	 * @param loginUser		確認するユーザー情報
	 * @return		存在する場合はtrue
	 */
	public static boolean login(User loginUser) {

		try (Connection con = DAOParent.createConnection()) {

			String sql = "SELECT * FROM USERS WHERE USERID = ? AND PASS = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, loginUser.getUserId());
			ps.setString(2, loginUser.getPass());
			ResultSet rs = ps.executeQuery();

			//ログインユーザーが存在する場合はtrueを返す
			if (rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//ログインユーザーが存在しない場合はfalseを返す
		return false;
	}

}
