package beans;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * ToDoの各種情報を保持するクラス
 */
public class Item extends ID implements Serializable{

	/**ToDoのタイトル*/
	private String title;

	/**ToDoについてのメモ*/
	private String memo;

	/**ToDoの期限*/
	private LocalDateTime deadLine;

	/**ToDoの完了状態*/
	private boolean completed;

	/**ToDoの重要度*/
	private int importance;

	public Item() {}

	/**
	 * ToDoの追加に必要な情報を設定するためのコンストラクタ
	 *
	 * @param userId	ToDoを作成したユーザーのID
	 * @param title		ToDoのタイトル
	 * @param memo		ToDoについてのメモ
	 * @param deadLine	ToDoの期限
	 * @param importance	ToDoの重要度
	 */
	public Item(String userId, String title, String memo, LocalDateTime deadLine, int importance) {
		this.userId = userId;
		this.title = title;
		this.memo = memo;
		this.deadLine = deadLine;
		this.importance = importance;
	}

	/**
	 * ToDoの更新に必要な情報を設定するためのコンストラクタ
	 *
	 * @param itemId	ToDoごとに固有のID
	 * @param title		ToDoのタイトル
	 * @param memo		ToDoについてのメモ
	 * @param deadLine	ToDoの期限
	 * @param completed		ToDoの完了状態
	 * @param importance	ToDoの重要度
	 */
	public Item(int itemId, String title, String memo, LocalDateTime deadLine, boolean completed, int importance) {
		this.itemId = itemId;
		this.title = title;
		this.memo = memo;
		this.deadLine = deadLine;
		this.completed = completed;
		this.importance = importance;
	}

	/**
	 * ToDoをホーム画面に表示するために必要な情報を設定するためのコンストラクタ
	 *
	 * @param itemId
	 * @param title
	 * @param deadLine
	 * @param completed
	 * @param importance
	 */
	public Item(int itemId, String title, LocalDateTime deadLine, boolean completed, int importance) {
		this.itemId = itemId;
		this.title = title;
		this.deadLine = deadLine;
		this.completed = completed;
		this.importance = importance;
	}


	//以下、getter及びsetter
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public LocalDateTime getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(LocalDateTime deadLine) {
		this.deadLine = deadLine;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

	public int getImportance() {
		return importance;
	}

	public void setImportance(int importance) {
		this.importance = importance;
	}

	public int getYear() {
		return deadLine.getYear();
	}

	public int getMonth() {
		return deadLine.getMonthValue();
	}

	public int getDay() {
		return deadLine.getDayOfMonth();
	}

	public int getHour() {
		return deadLine.getHour();
	}

	public int getMinute() {
		return deadLine.getMinute();
	}

}
