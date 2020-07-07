package servlet.loggedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class HowToUse
 * 使い方画面に関するサーブレットクラス
 */
@WebServlet("/LoggedIn/HowToUse")
public class HowToUse extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			//使い方画面にフォワード
			request.getRequestDispatcher("/WEB-INF/jsp/howToUse.jsp").forward(request, response);
	}

}
