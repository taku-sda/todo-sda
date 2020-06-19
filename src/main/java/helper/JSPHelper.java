package helper;

import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * JSPファイルで用いる共通の記述、処理をまとめたクラス
 *
 */
public class JSPHelper {

	public static  String getTop() {
		return "<a href=\"/\">トップページへ戻る</a>";
	}

	public static  String getHome() {
		return "<a href=\"/Home\">ホームへ戻る</a>";
	}

	public static String getLogout() {
		return "<a href=\"/Logout\">ログアウト</a>";
	}

	public static String getNow() {
		LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC+09:00"));
		StringBuilder sb = new StringBuilder();
		sb.append("今日は");
		sb.append(now.getYear()).append("年");
		sb.append(now.getMonthValue()).append("月");
		sb.append(now.getDayOfMonth()).append("日です");

		return sb.toString();
	}

}
