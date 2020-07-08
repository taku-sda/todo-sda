package servlet.loggedin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Item;
import model.HomeLogic;

/**
 * Servlet implementation class Home
 * ホーム画面に関するサーブレットクラス
 */
@WebServlet("/LoggedIn/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");

			List<List<Item>>classifiedList = null;

			try {
				//ホーム画面表示用に分類されたToDo一覧を取得
				classifiedList = HomeLogic.getClassifiedLists(userId);
			}catch(Exception e) {
				e.printStackTrace();
				//パラメータにエラー内容を設定して、エラー画面にフォワード
				request.setAttribute("errMsg", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
			}

			List<Item> completedList = classifiedList.get(0);		//完了
			List<Item> expiredList = classifiedList.get(1);		//期限切れ
			List<Item> todayList = classifiedList.get(2);			//今日まで
			List<Item> otherList = classifiedList.get(3);			//その他

			request.setAttribute("completedList", completedList);
			request.setAttribute("expiredList", expiredList);
			request.setAttribute("todayList", todayList);
			request.setAttribute("otherList", otherList);

			//ホーム画面にフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/home.jsp").forward(request, response);
	}

}
