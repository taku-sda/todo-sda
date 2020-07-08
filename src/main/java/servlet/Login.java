package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import dao.UsersDAO;
import model.FormCheckLogic;

/**
 * Servlet implementation class Login
 * ログインに関するサーブレットクラス
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ログイン画面にフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");

		//フォームに入力された内容ををセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("pass", pass);

		User loginUser = new User(userId, pass);

		try {
			//入力内容のチェック
			FormCheckLogic.check(loginUser);

			//ログイン可能かチェック
			UsersDAO.login(loginUser);

		} catch (Exception e) {
			e.printStackTrace();
			//パラメータにエラー内容を設定して、エラー画面にフォワード
			request.setAttribute("errMsg", e.getMessage());
			request.setAttribute("errType", "login");
			request.getRequestDispatcher("/WEB-INF/jsp/userError.jsp").forward(request, response);
		}

		//セッションスコープのパスワードを破棄してホーム画面へ
		session.removeAttribute("pass");
		response.sendRedirect("/LoggedIn/Home");

	}

}
