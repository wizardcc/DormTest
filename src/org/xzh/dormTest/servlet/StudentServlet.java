package org.xzh.dormTest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/student.action")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("=========student.action=============");
		
		String action = request.getParameter("action");
		
		if(action != null & action.equals("list")) {
			//查询学生在右侧展示
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/studentList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			
		}else if(action != null & action.equals("preAdd")) {
			
			//跳转到学生的添加页面
			request.setAttribute("mainRight", "/WEB-INF/jsp/studentAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}
		
		request.setAttribute("mainRight", "/WEB-INF/jsp/studentList.jsp");
		request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
	}

}
