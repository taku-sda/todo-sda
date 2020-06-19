package servlet;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Item;
import model.DateCheckLogic;
import model.UpdateItemLogic;

/**
 * Servlet implementation class UpdateItem
 *
 * ToDoの更新に関するサーブレットクラス
 */
@WebServlet("/UpdateItem")
public class UpdateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//セッションスコープからユーザーIDを取得
		HttpSession session = request.getSession();
		String userId = (String) session.getAttribute("userId");

		if (userId == null) {
			//ログインしていない場合はトップ画面にリダイレクト
			response.sendRedirect("/");
		} else {
			//ログインしている場合の処理

			//入力された日付が存在するか確認を行う
			int year = Integer.parseInt(request.getParameter("year"));
			int month = Integer.parseInt(request.getParameter("month"));
			int day = Integer.parseInt(request.getParameter("day"));
			boolean isAvailable = DateCheckLogic.dateCheck(year, month, day);

			if (!isAvailable) {
				//存在しない場合は、パラメータにエラーメッセージを設定して、エラーページにフォワード
				request.setAttribute("errMsg", "入力された日付は存在しません。" + System.lineSeparator() + "日付を確認して再度お試しください。");
				request.getRequestDispatcher("WEB-INF/jsp/itemError.jsp").forward(request, response);
			}

			//ToDoの更新に必要な情報を設定
			int itemId = Integer.parseInt(request.getParameter("itemId"));
			String title = request.getParameter("title");
			String memo = request.getParameter("memo");
			boolean completed = Boolean.valueOf(request.getParameter("completed"));
			int importance = Integer.parseInt(request.getParameter("importance"));
			int hour = Integer.parseInt(request.getParameter("hour"));
			int minute = Integer.parseInt(request.getParameter("minute"));
			LocalDateTime deadLine = LocalDateTime.of(year, month, day, hour, minute);

			//更新するToDoインスタンスを作成
			Item updateItem = new Item(itemId, title, memo, deadLine, completed, importance);

			//ToDoの更新を行う
			boolean result = UpdateItemLogic.execute(updateItem);

			if (result) {
				//ToDoの更新処理に成功した場合はホーム画面にリダイレクト
				response.sendRedirect("/Home");
			} else {
				//失敗した場合は、パラメータにエラーメッセージを設定して、エラーページにフォワード
				request.setAttribute("errMsg", "ToDoの更新処理に失敗しました。" + System.lineSeparator() + "時間をおいて再度お試しください。");
				request.getRequestDispatcher("WEB-INF/jsp/itemError.jsp").forward(request, response);
			}
		}
	}

}
