package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * DAOクラスの親クラス
 */
public class DAOParent {

	/**データベースドライバ名*/
	private static final String DRIVER_NAME = "org.postgresql.Driver";
	/**データベースURL*/
	private static final String DB_URL = System.getenv("TODO_APP_URL");
	/**データベースログインユーザー*/
	private static final String DB_USER = System.getenv("TODO_APP_USER");
	/**データベースログインパスワード*/
	private static final String DB_PASS = System.getenv("TODO_APP_PASS");

	//データベースのドライバクラスのロード
	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * データベースとのConnectionを得るメソッド
	 * @return					データベースとのConnection
	 * @throws SQLException		データベース処理の例外
	 */
	static Connection createConnection() throws SQLException {

		return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
	}

}
