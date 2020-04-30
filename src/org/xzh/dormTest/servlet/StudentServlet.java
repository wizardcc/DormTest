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
		
		//解决传递过来的中文乱码问题
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		//获取当前登录的用户
		User user = (User) request.getSession().getAttribute("session_user");
		Integer roleId = user.getRoleId();
		
		DormBuildService buildService = new DormBuildServiceImpl();
		UserService userService = new UserServiceImpl();
		
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
		
		if(action != null & action.equals("list")) {
			//查询学生在右侧展示
			String dormBuildId = request.getParameter("dormBuildId");
			String searchType = request.getParameter("searchType");
			String keyword = request.getParameter("keyword");
			System.out.println("dormBuildId:"+dormBuildId+"  searchType:"+searchType+"  keyword:"+keyword);
			List<User> students = userService.findStudent(dormBuildId,searchType,keyword,user);
			
			request.setAttribute("dormBuildId", dormBuildId);
			request.setAttribute("searchType", searchType);
			request.setAttribute("keyword", keyword);
			request.setAttribute("students", students);
			request.setAttribute("mainRight", "/WEB-INF/jsp/studentList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			
		}else if(action != null & action.equals("preAdd")) {
			//根据用户角色查询宿舍楼进行添加学生，
			//如当前用户 为宿舍管理员，他只能添加学生到其管理的宿舍楼
			
			
			//跳转到学生的添加页面
			request.setAttribute("mainRight", "/WEB-INF/jsp/studentAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		
		}else if(action != null & action.equals("save")) {
			//保存学生
			String stuCode = request.getParameter("stuCode");
			String name = request.getParameter("name");
			String sex = request.getParameter("sex");
			String tel = request.getParameter("tel");
			String passWord = request.getParameter("passWord");
			String  dormBuildId= request.getParameter("dormBuildId");
			String dormCode = request.getParameter("dormCode");
			System.out.println("stuCode:"+stuCode+"  name:"+name+"   sex:"+sex+
					"  tel:"+tel+" passWord:"+passWord+" dormBuildId:"+dormBuildId+
					"  dormCode:"+dormCode);
			
			User user2 = new User();
			user2.setStuCode(stuCode);
			user2.setSex(sex);
			user2.setTel(tel);
			user2.setName(name);
			user2.setPassWord(passWord);
			user2.setDormBuildId(Integer.parseInt(dormBuildId));
			user2.setDormCode(dormCode);
			user2.setRoleId(2);
			user2.setCreateUserId(user.getId());
			
			//在保存之前，查询数据库是否已经存在当前学号的学生，如已存在，则跳转到添加页面
			//未存在，则保存
			User student = userService.findByStuCode(stuCode);
			if(student != null) {
				//在保存之前，查询数据库是否已经存在当前学号的学生，如已存在，则跳转到添加页面
				//重定向方式不会自动拼接项目名，需要自己写一下
				response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=preAdd");
				
			}else {
				userService.saveStudent(user2);
				response.sendRedirect(getServletContext().getContextPath()+"/student.action?action=list");
			}
			
		}
	}

}
