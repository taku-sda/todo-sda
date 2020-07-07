package servlet.loggedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CompleteItemLogic;

/**
 * Servlet implementation class CompleteItem
 *
 * ToDoの完了処理に関するサーブレットクラス
 */
@WebServlet("/LoggedIn/CompleteItem")
public class CompleteItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			//ToDoを完了させる
			boolean result = CompleteItemLogic.execute(itemId, userId);

			if (result) {
				//ToDoの削除処理に成功した場合はホーム画面にリダイレクト
				response.sendRedirect("/LoggedIn/Home");
			} else {
				//失敗した場合は、パラメータにエラーメッセージを設定して、エラーページにフォワード
				request.setAttribute("errMsg", "ToDoの更新処理に失敗しました。");
				request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
			}
	}

}
