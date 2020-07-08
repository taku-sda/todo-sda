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
import dao.ItemsDAO;
import model.DateCheckLogic;

/**
 * Servlet implementation class AddItem
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

		//フォーム表示用に現在の日時をパラメータに設定して、
		//ToDo追加画面にフォワード
		LocalDateTime now = LocalDateTime.now(ZoneId.of("UTC+09:00"));
		request.setAttribute("now", now);
		request.getRequestDispatcher("/WEB-INF/jsp/addItem.jsp").forward(request, response);

	}

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

		HttpSession session = ((HttpServletRequest) request).getSession();
		String userId = (String) session.getAttribute("userId");
		String title = request.getParameter("title");
		String memo = request.getParameter("memo");
		int importance = Integer.parseInt(request.getParameter("importance"));

		int hour = Integer.parseInt(request.getParameter("hour"));
		int minute = Integer.parseInt(request.getParameter("minute"));
		LocalDateTime deadLine = LocalDateTime.of(year, month, day, hour, minute);

		Item addItem = new Item(userId, title, memo, deadLine, importance);

		try {
			//ToDoの追加を行う
			ItemsDAO.addItem(addItem);
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
