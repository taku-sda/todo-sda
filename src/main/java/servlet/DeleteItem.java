package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteItemLogic;

/**
 * Servlet implementation class DeleteItem
 *
 * ToDoの個別削除に関するサーブレットクラス
 */
@WebServlet("/DeleteItem")
public class DeleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

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

			int itemId = Integer.parseInt(request.getParameter("itemId"));

			//ToDoの削除を行う
			boolean result = DeleteItemLogic.execute(itemId, userId);

			if (result) {
				//ToDoの削除処理に成功した場合はホーム画面にリダイレクト
				response.sendRedirect("/Home");
			} else {
				//失敗した場合はパラメータにエラーメッセージを設定して、エラーページにフォワード
				request.setAttribute("errMsg", "ToDoの削除処理に失敗しました。");
				request.getRequestDispatcher("WEB-INF/jsp/itemError.jsp").forward(request, response);
			}
		}
	}
}
