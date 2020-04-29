package org.xzh.dormTest.servlet;

import java.io.IOException;
import java.util.ArrayList;
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
		//获取当前登录的用户
		User user = (User) request.getSession().getAttribute("session_user");
		Integer roleId = user.getRoleId();
		
		DormBuildService buildService = new DormBuildServiceImpl();
		
		if(action != null & action.equals("list")) {
			//查询学生在右侧展示
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/studentList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			
		}else if(action != null & action.equals("preAdd")) {
			//根据用户角色查询宿舍楼进行添加学生，
			//如当前用户 为宿舍管理员，他只能添加学生到其管理的宿舍楼
			
			List<DormBuild>  builds = new ArrayList<DormBuild>();
			if(roleId.equals(0)) {
				//如当前用户是超级管理员，他能将学生添加到所有的宿舍楼,查询所有宿舍楼
				builds = buildService.findAll();
			}else if(roleId.equals(1)) {
				//如当前用户 为宿舍管理员，他只能添加学生到其管理的宿舍楼
				builds =  buildService.findByUserId(user.getId());
			}
			System.out.println("builds:"+builds);
			request.setAttribute("builds", builds);
			
			//跳转到学生的添加页面
			request.setAttribute("mainRight", "/WEB-INF/jsp/studentAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}
		
		request.setAttribute("mainRight", "/WEB-INF/jsp/studentList.jsp");
		request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
	}

}
