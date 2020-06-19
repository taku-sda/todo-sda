package model;

/**
 * 日付情報のチェックを行うクラス
 */
public class DateCheckLogic {

	/**
	 * 日付が存在するか確認するメソッド
	 *
	 * @param year		年
	 * @param month		月
	 * @param day		日
	 * @return		存在するならtrue
	 */
	public static boolean dateCheck(int year, int month, int day) {

		//日が28以下の場合は全てOK
		if(day <= 28) {return true;}

		boolean isAvailable = false;
		switch(month) {

			case 2 :
				//2月のはうるう年かつ29日ならOK
				if(year % 4 == 0 && day == 29) {
					isAvailable = true;
				}
				break;
			case 4 :
			case 6 :
			case 9 :
			case 11 :
				//4,6,9,11月は30日までOK
				if(day <= 30) {
					isAvailable = true;
				}
				break;
			default:
				//その他の月は31日までOK
				isAvailable = true;
				break;
		}

		return isAvailable;
	}

}
