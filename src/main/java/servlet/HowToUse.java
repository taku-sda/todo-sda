package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HowToUse
 * 使い方画面に関するサーブレットクラス
 */
@WebServlet("/HowToUse")
public class HowToUse extends HttpServlet {
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
			//ログインしている場合は使い方画面にフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/howToUse.jsp").forward(request, response);
		}
	}

}
