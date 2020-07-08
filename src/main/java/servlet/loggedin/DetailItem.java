package servlet.loggedin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Item;
import dao.ItemsDAO;

/**
 * Servlet implementation class DetailItem
 * ToDoの詳細画面に関するサーブレットクラス
 */
@WebServlet("/LoggedIn/DetailItem")
public class DetailItem extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

			HttpSession session = request.getSession();
			String userId = (String) session.getAttribute("userId");
			int itemId = Integer.parseInt(request.getParameter("itemId"));

			Item detailItem = null;

			try {
				//ToDoの詳細を取得
				detailItem = ItemsDAO.getDetail(itemId, userId);
			}catch(Exception e) {
				e.printStackTrace();
				//パラメータにエラー内容を設定して、エラー画面にフォワード
				request.setAttribute("errMsg", e.getMessage());
				request.getRequestDispatcher("/WEB-INF/jsp/itemError.jsp").forward(request, response);
			}

			//パラメータにToDo情報を格納して、ToDo詳細画面にフォワード
			request.setAttribute("detailItem", detailItem);
			request.getRequestDispatcher("/WEB-INF/jsp/detailItem.jsp").forward(request, response);
	}

}
