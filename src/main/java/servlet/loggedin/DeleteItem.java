package servlet.loggedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemsDAO;

/**
 * Servlet implementation class DeleteItem
 * ToDoの個別削除に関するサーブレットクラス
 */
@WebServlet("/LoggedIn/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");
		int itemId = Integer.parseInt(request.getParameter("itemId"));

		try {
			//ToDoを削除する
			ItemsDAO.deleteItem(itemId, userId);
		} catch (Exception e) {
			e.printStackTrace();
			//パラメータにエラー内容を設定して、エラー画面にフォワード
			request.setAttribute("errMsg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
		}

		//ホーム画面にリダイレクト
		response.sendRedirect("/LoggedIn/Home");
	}
}
