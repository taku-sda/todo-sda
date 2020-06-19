package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ID;
import model.CompleteItemLogic;

/**
 * Servlet implementation class CompleteItem
 *
 * ToDoの完了処理に関するサーブレットクラス
 */
public class CompleteItem extends HttpServlet {
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

			//完了させるToDoのIDを設定
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			ID completeId = new ID(itemId, userId);

			//ToDoを完了させる
			boolean result = CompleteItemLogic.execute(completeId);

			if (result) {
				//ToDoの削除処理に成功した場合はホーム画面にリダイレクト
				response.sendRedirect("/Home");
			} else {
				//失敗した場合は、パラメータにエラーメッセージを設定して、エラーページにフォワード
				request.setAttribute("errMsg", "ToDoの更新処理に失敗しました。");
				request.getRequestDispatcher("WEB-INF/jsp/itemError.jsp").forward(request, response);
			}
		}
	}

}
