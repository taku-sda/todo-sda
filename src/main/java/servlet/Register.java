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
import model.RegisterLogic;

/**
 * Servlet implementation class Register
 *
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
		request.getRequestDispatcher("WEB-INF/jsp/register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//入力内容からUserインスタンスを生成
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		User registerUser = new User(userId, pass);

		//入力された情報をセッションスコープに保存
		HttpSession session = request.getSession();
		session.setAttribute("userId", userId);
		session.setAttribute("pass", pass);

		//入力情報のチェック
		String errMsg = FormCheckLogic.registerCheck(registerUser);

		//入力内容に誤りがあった場合の処理
		if (errMsg.length() != 0) {
			//パラメータにエラー内容を設定して、エラー画面にフォワード
			request.setAttribute("errMsg", errMsg);
			request.setAttribute("errType", "register");
			request.getRequestDispatcher("WEB-INF/jsp/userError.jsp").forward(request, response);
		}

		//データベース登録処理
		boolean result = RegisterLogic.execute(registerUser);

		if (result) {
			//登録が完了した場合、セッションスコープのパスワードを破棄し、登録完了画面の表示用にリクエストスコープに設定
			//登録完了画面にフォワード
			session.removeAttribute("pass");
			request.setAttribute("pass", pass);
			request.getRequestDispatcher("WEB-INF/jsp/registerOK.jsp").forward(request, response);

		} else {
			//パラメータにエラー内容を設定して、エラー画面にフォワード
			request.setAttribute("errMsg", "入力されたユーザーIDは既に使用されている可能性があります。");
			request.setAttribute("errType", "register");
			request.getRequestDispatcher("WEB-INF/jsp/userError.jsp").forward(request, response);
		}
	}
}
