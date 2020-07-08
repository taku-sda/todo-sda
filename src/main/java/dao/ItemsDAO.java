package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import beans.Item;

/**
 * ITEMテーブルに関する操作を行うDAOクラス
 */
public class ItemsDAO extends DAOParent {

	/**データベースから取得したDateTimeをLocalDateTimeに変換する際のフォーマット*/
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

	/**
	 * ToDoを追加するメソッド
	 *
	 * @param addItem			追加するToDo情報(ユーザーID、タイトル、メモ、期限)
	 * @exception Exception 	追加に失敗した場合
	 * @exception SQLException データベース処理の例外
	 */
	public static void addItem(Item addItem) throws Exception, SQLException {

		try (Connection con = DAOParent.createConnection()) {

			String sql = "INSERT INTO ITEMS (USERID, TITLE, MEMO, DEADLINE, COMPLETED, IMPORTANCE) VALUES(?, ?, ?, TO_TIMESTAMP(?, ?), ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, addItem.getUserId());
			ps.setString(2, addItem.getTitle());
			ps.setString(3, addItem.getMemo());
			ps.setString(4, toTimestampStr(addItem.getDeadLine()));
			ps.setString(5, "'YYYY/MM/DD HH24:MI:SS'");
			ps.setBoolean(6, false);
			ps.setInt(7, addItem.getImportance());

			int result = ps.executeUpdate();

			if (result < 1) {
				throw new Exception("ToDoの追加に失敗しました。");
			}
		}
	}

	/**
	 * ToDOの内容を更新するメソッド
	 *
	 * @param updateItem		更新するToDo情報(ToDoのID、タイトル、メモ、期限、完了状態、重要度)
	 * @exception Exception 	更新に失敗した場合
	 * @exception SQLException データベース処理の例外
	 */
	public static void updateItem(Item updateItem) throws Exception, SQLException {

		try (Connection con = DAOParent.createConnection()) {

			String sql = "UPDATE ITEMS SET TITLE = ?, MEMO = ?, DEADLINE = TO_TIMESTAMP(?, ?), COMPLETED = ?, IMPORTANCE = ? WHERE ITEMID = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, updateItem.getTitle());
			ps.setString(2, updateItem.getMemo());
			ps.setString(3, toTimestampStr(updateItem.getDeadLine()));
			ps.setString(4, "'YYYY/MM/DD HH24:MI:SS'");
			ps.setBoolean(5, updateItem.isCompleted());
			ps.setInt(6, updateItem.getImportance());
			ps.setInt(7, updateItem.getItemId());

			int result = ps.executeUpdate();

			if (result < 1) {
				throw new Exception("ToDoの更新に失敗しました。");
			}

		}
	}

	/**
	 * ログインユーザーのToDoの一覧を取得するメソッド
	 *
	 * @param userId						ログインユーザーのID
	 * @return								ToDoの一覧
	 * @exception SQLException 			データベース処理の例外
	 * @exception DateTimeParseException 	DateTimeの変換時のエラー
	 */
	public static List<Item> getListByUserId(String userId) throws SQLException, DateTimeParseException {

		List<Item> itemList = new ArrayList<>();

		try (Connection con = DAOParent.createConnection()) {

			String sql = "SELECT ITEMID, TITLE, DEADLINE, COMPLETED, IMPORTANCE FROM ITEMS WHERE USERID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ResultSet rs = ps.executeQuery();

			//検索結果が存在する場合はリストに追加する
			while (rs.next()) {
				int itemId = rs.getInt("ITEMID");
				String title = rs.getString("TITLE");
				//取得したDateTimeをLocalDateTimeに変換
				LocalDateTime deadLine = LocalDateTime.parse(rs.getString("DEADLINE"), DTF);
				boolean completed = rs.getBoolean("COMPLETED");
				int importance = rs.getInt("IMPORTANCE");
				Item item = new Item(itemId, title, deadLine, completed, importance);
				itemList.add(item);
			}

		}

		return itemList;
	}

	/**
	 * IDに対応するToDoの詳細を取得するメソッド。
	 * ログインユーザーのToDoである場合のみ取得する
	 *
	 * @param itemId			取得するToDoのID
	 * @param userId			ログインユーザーのID
	 * @return					取得したToDo情報
	 * @exception Exception 	取得できるToDoがない場合
	 * @exception SQLException データベース処理の例外
	 */
	public static Item getDetail(int itemId, String userId) throws Exception, SQLException {

		Item detailItem = null;

		try (Connection con = DAOParent.createConnection()) {

			String sql = "SELECT TITLE, MEMO, DEADLINE , COMPLETED, IMPORTANCE FROM ITEMS WHERE ITEMID=? AND USERID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, itemId);
			ps.setString(2, userId);
			ResultSet rs = ps.executeQuery();

			if (!rs.next()) {
				throw new Exception("該当するToDoが見つかりませんでした。");
			}

			detailItem = new Item();
			detailItem.setItemId(itemId);
			detailItem.setTitle(rs.getString("TITLE"));
			detailItem.setMemo(rs.getString("MEMO"));
			//取得したDateTimeをLocalDateTimeに変換
			detailItem.setDeadLine(LocalDateTime.parse(rs.getString("DEADLINE"), DTF));
			detailItem.setCompleted(rs.getBoolean("COMPLETED"));
			detailItem.setImportance(rs.getInt("IMPORTANCE"));
		}

		return detailItem;

	}

	/**
	 * IDに対応するToDoを削除するメソッド。
	 * ログインユーザーのToDoである場合のみ削除する
	 *
	 * @param itemId			削除するToDoのID
	 * @param userId			ログインユーザーのID
	 * @exception Exception 	削除に失敗した場合
	 * @exception SQLException データベース処理の例外
	 */
	public static void deleteItem(int itemId, String userId) throws Exception, SQLException {

		try (Connection con = DAOParent.createConnection()) {

			String sql = "DELETE FROM ITEMS WHERE ITEMID = ? AND USERID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, itemId);
			ps.setString(2, userId);

			int result = ps.executeUpdate();

			if (result < 1) {
				throw new Exception("ToDoの削除に失敗しました。");
			}
		}
	}

	/**
	 * IDに対応するToDoを完了状態にするメソッド。
	 * ログインユーザーのToDoである場合のみ変更する
	 *
	 * @param itemId			完了状態にするToDoのID
	 * @param userId			ログインユーザーのID
	 * @exception Exception 	変更に失敗した場合
	 * @exception SQLException データベース処理の例外
	 */
	public static void completeItem(int itemId, String userId) throws Exception, SQLException {

		try (Connection con = DAOParent.createConnection()) {

			String sql = "UPDATE ITEMS SET COMPLETED=? WHERE ITEMID=? AND USERID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setInt(2, itemId);
			ps.setString(3, userId);

			int result = ps.executeUpdate();

			if (result < 1) {
				throw new Exception("ToDoを完了状態にできませんでした。");
			}
		}
	}

	/**
	 * ログインユーザーの、期限が現在よりも前のToDoをすべて削除するメソッド。
	 * ただし、完了状態のToDoは削除しない
	 *
	 * @param userId	ログインユーザーのID
	 * @exception Exception 	削除に失敗した場合
	 * @exception SQLException データベース処理の例外
	 */
	public static void deleteAllExpiredItem(String userId) throws Exception, SQLException {

		try (Connection con = DAOParent.createConnection()) {

			String sql = "DELETE FROM ITEMS WHERE USERID = ? AND DEADLINE < TO_TIMESTAMP(?, ?) AND COMPLETED = FALSE";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setString(2, toTimestampStr(LocalDateTime.now()));
			ps.setString(3, "'YYYY/MM/DD HH24:MI:SS'");

			int result = ps.executeUpdate();

			if (result < 1) {
				throw new Exception("ToDoの削除に失敗しました。");
			}
		}
	}

	/**
	 * ログインユーザーの、完了状態のToDoをすべて削除にするメソッド。
	 *
	 * @param userId	ログインユーザーのID
	 * @exception Exception 	削除に失敗した場合
	 * @exception SQLException データベース処理の例外
	 */
	public static void deleteAllCompletedItem(String userId) throws Exception, SQLException {

		try (Connection con = DAOParent.createConnection()) {

			String sql = "DELETE FROM ITEMS WHERE USERID = ? AND COMPLETED = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, userId);
			ps.setBoolean(2, true);

			int result = ps.executeUpdate();

			if (result < 1) {
				throw new Exception("ToDoの削除に失敗しました。");
			}
		}
	}

	/**
	 * LocalDateTimeをPostgreSQL対応のtimestamp文字列に変換するメソッド
	 *
	 * @param dl	変換するLocalDateTime
	 * @return		変換後のtimestamp文字列
	 */
	private static String toTimestampStr(LocalDateTime dl) {

		StringBuilder sb = new StringBuilder();
		sb.append("'");
		sb.append(dl.getYear()).append("/");
		sb.append(dl.getMonthValue()).append("/");
		sb.append(dl.getDayOfMonth()).append(" ");
		sb.append(dl.getHour()).append(":");
		sb.append(dl.getMinute()).append(":");
		sb.append("0'"); //秒は設定しない

		return sb.toString();
	}

}
