package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * DAOクラスにおける変換処理を行うクラス
 */
public class DAOConverter {

	/**
	 * LocalDateTimeをPostgreSQL対応のtimestamp文字列に変換するメソッド
	 *
	 * @param ldt	変換するLocalDateTime
	 * @return		変換後のtimestamp文字列
	 */
	public static String toTimestampStr(LocalDateTime ldt) {

		StringBuilder timestampStr = new StringBuilder();
		timestampStr.append("'");
		timestampStr.append(ldt.getYear()).append("/");
		timestampStr.append(ldt.getMonthValue()).append("/");
		timestampStr.append(ldt.getDayOfMonth()).append(" ");
		timestampStr.append(ldt.getHour()).append(":");
		timestampStr.append(ldt.getMinute()).append(":");
		timestampStr.append("0'"); //秒は設定しない

		return timestampStr.toString();
	}

	/**
	 * PostgreSQL対応のtimestamp文字列をLocalDateTimeに変換するメソッド
	 * @param timestamp		変換するtimestamp文字列
	 * @return		変換後のLocalDateTime
	 */
	public static LocalDateTime toLocalDateTime(String timestampStr) {

		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		return LocalDateTime.parse(timestampStr, dtf);

	}
}
