package servlet.loggedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Item;
import model.DetailItemLogic;

/**
 * Servlet implementation class DetailItem
 *
 * ToDoの詳細画面に関するサーブレットクラス
 */
@WebServlet("/LoggedIn/DetailItem")
public class DetailItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			//ToDoの詳細を取得
			Item detailItem = DetailItemLogic.execute(itemId, userId);

			if (detailItem != null) {
				//取得できた場合は、詳細をパラメータに設定して、詳細画面にフォワード
				request.setAttribute("detailItem", detailItem);
				request.getRequestDispatcher("/WEB-INF/jsp/detailItem.jsp").forward(request, response);
			} else {
				//取得できなかった場合は、エラーメッセージをパラメータに設定して、エラー画面にフォワード
				request.setAttribute("errMsg", "ToDoの詳細の取得に失敗しました。");
				request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
			}
	}

}