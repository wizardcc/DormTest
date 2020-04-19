package org.xzh.dormTest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DormBuildServlet
 */
@WebServlet("/dormBuild.action")
public class DormBuildServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DormBuildServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("======dormBuild.action======");
		
		String action = request.getParameter("action");
		System.out.println("action:"+action);
		if(action != null & action.equals("list")) {
			//查询宿舍楼信息，跳转到宿舍楼列表页
			request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildList.jsp");
			//request方法请求链没有断开，可以在下一个jsp或servlet获取保存在request中的参数
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			
		}else if(action != null & action.equals("preAdd")) {
			//表示跳转到宿舍楼添加页面
			request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildAddOrUpdate.jsp");
			//request方法请求链没有断开，可以在下一个jsp或servlet获取保存在request中的参数
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			
		}
		
	}

}
