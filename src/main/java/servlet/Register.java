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
 * Servlet implementation class Register
 * ユーザー登録に関するサーブレットクラス
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//ユーザー登録画面にフォワード
		request.getRequestDispatcher("/WEB-INF/jsp/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");

		//フォームに入力された情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("pass", pass);

		User registerUser = new User(userId, pass);

		try {
			//入力情報のチェック
			FormCheckLogic.check(registerUser);

			//データベース登録処理
			UsersDAO.register(registerUser);

		} catch (Exception e) {
			e.printStackTrace();
			//パラメータにエラー内容を設定して、エラー画面にフォワード
			request.setAttribute("errMsg", e.getMessage());
			request.setAttribute("errType", "register");
			request.getRequestDispatcher("/WEB-INF/jsp/userError.jsp").forward(request, response);
		}

		//セッションスコープのパスワードを破棄
		session.removeAttribute("pass");

		//画面表示用にパラメータにパスワードを設定し、
		//登録完了画面にフォワード
		request.setAttribute("pass", pass);
		request.getRequestDispatcher("/WEB-INF/jsp/registerOK.jsp").forward(request, response);
	}
}
