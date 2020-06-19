package beans;

import java.io.Serializable;

/**
 * ToDoのID情報を保持するクラス
 */
public class ID implements Serializable {

	/**ToDoごとに固有のID*/
	protected int itemId;

	/**ToDoを作成したユーザーのID*/
	protected String userId;

	public ID() {}

	public ID(int itemId, String userId) {
		this.itemId = itemId;
		this.userId = userId;
	}

	//以下、getter及びsetter
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
