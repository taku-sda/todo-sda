package servlet.loggedin;

import java.io.IOException;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Item;
import dao.ItemsDAO;
import model.DateCheckLogic;

/**
 * Servlet implementation class UpdateItem
 * ToDoの更新に関するサーブレットクラス
 */
@WebServlet("/LoggedIn/UpdateItem")
public class UpdateItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));

		//入力された日付が存在するかチェック
		boolean isAvailable = DateCheckLogic.existCheck(year, month, day);

		if (!isAvailable) {
			//存在しない場合は、パラメータにエラー内容を設定して、エラー画面にフォワード
			request.setAttribute("errMsg", "入力された日付は存在しません。日付を確認して再度お試しください。");
			request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
		}

		int itemId = Integer.parseInt(request.getParameter("itemId"));
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		boolean completed = Boolean.valueOf(request.getParameter("completed"));
		int importance = Integer.parseInt(request.getParameter("importance"));

		int hour = Integer.parseInt(request.getParameter("hour"));
		int minute = Integer.parseInt(request.getParameter("minute"));
		LocalDateTime deadLine = LocalDateTime.of(year, month, day, hour, minute);

		Item updateItem = new Item(itemId, title, memo, deadLine, completed, importance);

		try {
			//ToDoの追加を行う
			ItemsDAO.updateItem(updateItem);
		} catch (Exception e) {
			e.printStackTrace();
			//パラメータにエラー内容を設定して、エラー画面にフォワード
			request.setAttribute("errMsg", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
		}

		//ホーム画面にリダイレクト
		response.sendRedirect("/LoggedIn/Home");

	}

}
