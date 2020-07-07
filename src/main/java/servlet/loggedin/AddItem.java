package servlet.loggedin;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Item;
import model.AddItemLogic;
import model.DateCheckLogic;

/**
 * Servlet implementation class AddItem
 *
 * ToDoの追加に関するサーブレットクラス
 */
@WebServlet("/LoggedIn/AddItem")
public class AddItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//現在の時刻をパラメータに設定して、ToDo追加画面にフォワード
		LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC+09:00"));
		request.setAttribute("now", now);
		request.getRequestDispatcher("/WEB-INF/jsp/addItem.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//入力された日付が存在するか確認を行う
		int year = Integer.parseInt(request.getParameter("year"));
		int month = Integer.parseInt(request.getParameter("month"));
		int day = Integer.parseInt(request.getParameter("day"));
		boolean isAvailable = DateCheckLogic.dateCheck(year, month, day);

		if (!isAvailable) {
			//存在しない場合は、パラメータにエラーメッセージを設定して、ToDo追加画面にフォワード
			request.setAttribute("errMsg", "入力された日付は存在しません。" + System.lineSeparator() + "日付を確認して再度お試しください。");
			request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
		}

		//ToDo追加の処理に必要な情報を設定
		HttpSession session = ((HttpServletRequest) request).getSession();
		String userId = (String) session.getAttribute("userId");
		String title = request.getParameter("title");
		int importance = Integer.parseInt(request.getParameter("importance"));
		String memo = request.getParameter("memo");
		int hour = Integer.parseInt(request.getParameter("hour"));
		int minute = Integer.parseInt(request.getParameter("minute"));
		LocalDateTime deadLine = LocalDateTime.of(year, month, day, hour, minute);

		//追加するToDoインスタンスを作成
		Item addItem = new Item(userId, title, memo, deadLine, importance);

		//ToDoの追加を行う
		boolean result = AddItemLogic.execute(addItem);

		if (result) {
			//ToDoの追加処理に成功した場合は、ホーム画面にリダイレクト
			response.sendRedirect("/LoggedIn/Home");
		} else {
			//失敗した場合は、パラメータにエラーメッセージを設定して、ToDo追加画面にフォワード
			request.setAttribute("errMsg", "ToDoの追加に失敗しました" + System.lineSeparator() + "時間をおいて再度お試しください。");
			request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
		}

	}

}
