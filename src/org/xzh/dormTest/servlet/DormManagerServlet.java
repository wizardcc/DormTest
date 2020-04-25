package org.xzh.dormTest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DormManagerServlet
 */
@WebServlet("/dormManager.action")
public class DormManagerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DormManagerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("===========dormManager.action==========");
		String action = request.getParameter("action");
		
		if(action != null & action.equals("list")) {
			//宿舍管理员查询
			request.setAttribute("mainRight", "dormManagerList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action != null & action.equals("preAdd")) {
			//跳转到宿舍管理员添加页面
			request.setAttribute("mainRight", "dormManagerAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action != null & action.equals("save")) {
			System.out.println("======保存宿舍管理员=========");
		}
	}

}
