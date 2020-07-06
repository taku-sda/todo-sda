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
public class ItemsDAO extends DAOParent{

	/**データベースから取得したDateTimeをLocalDateTimeに変換する際のフォーマット*/
	private static final DateTimeFormatter DTF = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


	/**
	 * ToDoを追加するメソッド
	 *
	 * @param addItem	追加するToDo情報(userId、タイトル、メモ、期限)
	 * @return		追加に成功した場合はtrue
	 */
	public static boolean addItem(Item addItem) {

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

			//追加に成功した場合はtrueを返す
			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

		//追加に失敗した場合はfalseを返す
		return false;
	}


	/**
	 * ToDOの内容を更新するメソッド
	 *
	 * @param updateItem	更新するToDo情報(itemId、タイトル、メモ、期限)
	 * @return		更新に成功した場合はtrue
	 */
	public static boolean updateItem(Item updateItem) {

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

			//更新に成功した場合はtrueを返す
			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//更新に失敗した場合はfalseを返す
		return false;
	}


	/**
	 * userIdに対応するToDoの一覧を検索し、取得するメソッド
	 *
	 * @param userId	検索するユーザーのuserId
	 * @return		検索結果のToDoのリスト
	 */
	public static List<Item> searchItemByUserId(String userId) {

		/**検索結果のToDoを格納するリスト*/
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

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (DateTimeParseException e) {
			e.printStackTrace();
		}

		return itemList;

	}


	/**
	 * ITEMIDとUSERIDに対応するToDoの詳細を取得するメソッド
	 *
	 * @param itemId	ITEMID
	 * @param userId	USERID
	 * @return			取得したToDo情報(存在しない場合はnull)
	 */
	public static Item searchItemByItemId(int itemId, String userId) {

		/**ToDo情報を格納するItemインスタンス*/
		Item detailItem = null;

		try (Connection con = DAOParent.createConnection()) {

			String sql = "SELECT TITLE, MEMO, DEADLINE , COMPLETED, IMPORTANCE FROM ITEMS WHERE ITEMID=? AND USERID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, itemId);
			ps.setString(2, userId);
			ResultSet rs = ps.executeQuery();

			//ToDoが存在する場合は詳細を格納
			if (rs.next()) {
				detailItem = new Item();
				detailItem.setItemId(itemId);
				detailItem.setTitle(rs.getString("TITLE"));
				detailItem.setMemo(rs.getString("MEMO"));
				//取得したDateTimeをLocalDateTimeに変換
				detailItem.setDeadLine(LocalDateTime.parse(rs.getString("DEADLINE"), DTF));
				detailItem.setCompleted(rs.getBoolean("COMPLETED"));
				detailItem.setImportance(rs.getInt("IMPORTANCE"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return detailItem;

	}


	/**
	 * ITEMIDとUSERIDに対応するToDoをデータベースから削除するメソッド
	 *
	 * @param itemId	ITEMID
	 * @param userId	USERID
	 * @return			削除に成功した場合はtrue
	 */
	public static boolean deleteItem(int itemId, String userId) {

		try (Connection con = DAOParent.createConnection()) {

			//SQLの実行
			String sql = "DELETE FROM ITEMS WHERE ITEMID = ? AND USERID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, itemId);
			ps.setString(2, userId);

			//実行結果を取得
			int result = ps.executeUpdate();

			//削除に成功した場合はtrueを返す
			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//削除に失敗した場合はfalseを返す
		return false;
	}


	/**
	 * ITEMIDとUSERIDに対応するToDoを完了状態にするメソッド
	 *
	 *
	 * @param itemId 	ITEMID
	 * @param userId   USERID
	 * @return			完了状態への変更が実行されたらtrue
	 */
	public static boolean completeItem(int itemId, String userId) {

		try (Connection con = DAOParent.createConnection()) {

			//SQLの実行
			String sql = "UPDATE ITEMS SET COMPLETED=? WHERE ITEMID=? AND USERID=?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setBoolean(1, true);
			ps.setInt(2, itemId);
			ps.setString(3, userId);

			//実行結果の取得
			int result = ps.executeUpdate();

			//変更が実行されていたらtrueを返す
			if (result == 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//変更が実行されなかったらfalseを返す
		return false;

	}


	/**
	 * 期限が現在よりも前のToDoをすべて削除するメソッド
	 *
	 * @return		削除が実行されたらtrue
	 */
	public static boolean deleteAllExpiredItem() {

		try (Connection con = DAOParent.createConnection()) {

			//SQLの実行
			String sql = "DELETE FROM ITEMS WHERE DEADLINE < TO_TIMESTAMP(?, ?) AND COMPLETED = FALSE";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, toTimestampStr(LocalDateTime.now()));
			ps.setString(2, "'YYYY/MM/DD HH24:MI:SS'");

			//実行結果の取得
			int result = ps.executeUpdate();

			//削除が実行されていたらtrueを返す
			if (result >= 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//削除が実行されなかったらfalseを返す
		return false;
	}


	/**
	 * 完了状態のToDoをすべて削除するメソッド
	 *
	 * @return		削除が実行されたらtrue
	 */
	public static boolean deleteAllCompletedItem() {

		try (Connection con = DAOParent.createConnection()) {

			//SQLの実行
			String sql = "DELETE FROM ITEMS WHERE COMPLETED = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setBoolean(1, true);

			//実行結果の取得
			int result = ps.executeUpdate();

			//削除が実行されていたらtrueを返す
			if (result >= 1) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		//削除が実行されなかったらfalseを返す
		return false;
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
		sb.append("0'");

		return sb.toString();
	}

}
