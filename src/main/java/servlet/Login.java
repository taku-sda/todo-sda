package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.User;
import model.FormCheckLogic;
import model.LoginLogic;

/**
 * Servlet implementation class Login
 *
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
		request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//入力内容からUserインスタンスを生成
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		User loginUser = new User(userId, pass);

		//入力内容をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("pass", pass);

		//入力内容のチェック
		String errMsg = FormCheckLogic.registerCheck(loginUser);

		//入力内容に誤りがあった場合の処理
		if (errMsg.length() != 0) {
			//パラメータにエラー内容を設定して、エラー画面にフォワード
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("errType", "login");
			request.getRequestDispatcher("WEB-INF/jsp/userError.jsp").forward(request, response);
		}

		//ログイン可能か、確認を行う
		boolean result = LoginLogic.execute(loginUser);

		if (result) {
			//ログイン可能な場合は、セッションスコープのパスワードを破棄してホーム画面へ
			session.removeAttribute("pass");
			response.sendRedirect("/Home");
		} else {
			//ログイン不可な場合は、パラメータにエラー内容を設定して、エラー画面にフォワード
			request.setAttribute("errMsg", "ユーザーIDまたはパスワードが正しくありません。" + System.lineSeparator() + "入力内容をご確認ください。");
			request.setAttribute("errType", "login");
			request.getRequestDispatcher("WEB-INF/jsp/userError.jsp").forward(request, response);
		}
	}

}
