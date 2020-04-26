package org.xzh.dormTest.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xzh.dormTest.bean.DormBuild;
import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.service.DormBuildService;
import org.xzh.dormTest.service.DormBuildServiceImpl;
import org.xzh.dormTest.service.UserService;
import org.xzh.dormTest.service.UserServiceImpl;

import com.sun.org.apache.bcel.internal.generic.NEWARRAY;

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
		//在Tomcat8.0中解决post请求乱码问题
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		
		UserService userService = new UserServiceImpl();
		
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
			String name = request.getParameter("name");
			String passWord = request.getParameter("passWord");
			String sex = request.getParameter("sex");
			String tel = request.getParameter("tel");
			//获取复选框中用户选中的宿舍楼
			String[] dormBuildIds = request.getParameterValues("dormBuildId");
			System.out.println("name:"+name+"  pass:"+passWord+"  sex:"+sex+"  tel:"+tel+"  dormBuildIds:"+Arrays.toString(dormBuildIds));
			
			User user = new User(name, passWord, sex, tel, null, 1);
			user.setDisabled(0);
			//当前登录的用户
			User user2 = (User) request.getSession().getAttribute("session_user");
			user.setCreateUserId(user2.getId());
			//当前的登录的用户的ID
			userService.saveManager(user,dormBuildIds);
		}
	}

}
