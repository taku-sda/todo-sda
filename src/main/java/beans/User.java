package beans;

import java.io.Serializable;

/**
 * ユーザー情報を保持するクラス
 */
public class User implements Serializable{

	//ユーザーごとに固有のID*/
	private String userId;

	//*ユーザーのパスワード*/
	private String pass;

	public User() {}

	public User(String userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}

	//以下、getter及びsetter
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}


}
