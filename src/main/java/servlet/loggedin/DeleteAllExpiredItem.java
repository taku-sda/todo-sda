package servlet.loggedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DeleteAllExpiredItemLogic;

/**
 * Servlet implementation class DeleteAllExpiredItem
 *
 * 期限切れのToDoの一括削除に関するサーブレットクラス
 */
@WebServlet("/LoggedIn/DeleteAllExpiredItem")
public class DeleteAllExpiredItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			//全ての期限切れのToDoを削除
			boolean result = DeleteAllExpiredItemLogic.execute();

			if (result) {
				//ToDoの削除処理に成功した場合はホーム画面にリダイレクト
				response.sendRedirect("/LoggedIn/Home");
			} else {
				//失敗した場合は、パラメータにエラーメッセージを設定して、エラーページにフォワード
				request.setAttribute("errMsg", "ToDoの削除処理に失敗しました。" + System.lineSeparator() + "時間をおいて再度お試しください。");
				request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
			}
	}

}
