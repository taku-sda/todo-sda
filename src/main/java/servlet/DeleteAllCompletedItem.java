package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DeleteAllCompletedItemLogic;

/**
 * Servlet implementation class DeleteAllCompletedItem
 *
 * 完了状態のToDoの一括削除に関するサーブレットクラス
 */
public class DeleteAllCompletedItem extends HttpServlet {
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

			//全ての完了状態のToDoを削除
			boolean result = DeleteAllCompletedItemLogic.execute();

			if (result) {
				//ToDoの削除処理に成功した場合はホーム画面にリダイレクト
				response.sendRedirect("/Home");
			} else {
				//失敗した場合は、パラメータにエラーメッセージを設定して、エラーページにフォワード
				request.setAttribute("errMsg", "ToDoの削除処理に失敗しました。" + System.lineSeparator() + "時間をおいて再度お試しください。");
				request.getRequestDispatcher("WEB-INF/jsp/itemError.jsp").forward(request, response);
			}
		}
	}
}
