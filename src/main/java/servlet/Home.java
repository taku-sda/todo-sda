package servlet;

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
 *
 * ホーム画面に関するサーブレットクラス
 */
@WebServlet("/Home")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッションスコープからユーザーIDを取得
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			//ログインしていない場合はトップ画面にリダイレクト
			response.sendRedirect("/");
		} else {
			//ログインしている場合の処理

			//ToDoの一覧を取得
			List<Item> itemList = HomeLogic.exectute(userId);

			//ToDoの一覧を期限順にソート
			HomeLogic.sortItem(itemList);

			//ToDoの一覧を期限、状態で分類
			List<List<Item>> resultList = HomeLogic.classificationItem(itemList);
			List<Item> completedList = resultList.get(0);
			List<Item> expiredList = resultList.get(1);
			List<Item> todayList = resultList.get(2);
			List<Item> otherList = resultList.get(3);

			//各種ToDoの一覧をリクエストスコープに保存
			request.setAttribute("completedList", completedList);
			request.setAttribute("expiredList", expiredList);
			request.setAttribute("todayList", todayList);
			request.setAttribute("otherList", otherList);

			//ホーム画面にフォワード
			request.getRequestDispatcher("WEB-INF/jsp/home.jsp").forward(request, response);
		}
	}

}
