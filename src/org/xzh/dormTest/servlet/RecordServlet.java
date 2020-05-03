package org.xzh.dormTest.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xzh.dormTest.bean.DormBuild;
import org.xzh.dormTest.bean.Record;
import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.service.DormBuildService;
import org.xzh.dormTest.service.DormBuildServiceImpl;
import org.xzh.dormTest.service.RecordService;
import org.xzh.dormTest.service.RecordServiceImpl;
import org.xzh.dormTest.service.UserService;
import org.xzh.dormTest.service.UserServiceImpl;
import org.xzh.dormTest.util.PageModel;

/**
 * Servlet implementation class RecordServlet
 */
@WebServlet("/record.action")
public class RecordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("========record.action========");
		
		String action = request.getParameter("action");
		System.out.println("action:"+action);
		
		//获取当前登录的用户
		User userCurr = (User) request.getSession().getAttribute("session_user");
		
		UserService userService = new UserServiceImpl();
		RecordService recordService = new RecordServiceImpl();
		DormBuildService buildService = new DormBuildServiceImpl();
		
		if(action != null & action.equals("list")) {
			//查询缺勤记录并展示
			Integer roleId = userCurr.getRoleId();
			List<DormBuild>  builds = new ArrayList<DormBuild>();
			if(roleId.equals(0)) {
				//如当前用户是超级管理员，他能将学生添加到所有的宿舍楼,查询所有宿舍楼
				builds = buildService.findAll();
			}else if(roleId.equals(1)) {
				//如当前用户 为宿舍管理员，他只能添加学生到其管理的宿舍楼
				builds =  buildService.findByUserId(userCurr.getId());
			}
			System.out.println("builds:"+builds);
			request.setAttribute("builds", builds);
			
			
			
			
			request.setAttribute("mainRight", "/WEB-INF/jsp/recordList.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}else if(action != null && action.equals("preAdd")) {
			//跳转到添加页面
			request.setAttribute("mainRight", "/WEB-INF/jsp/recordAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			
		}else if(action != null && action.equals("save")) {
			//保存
			String stuCode = request.getParameter("stuCode");
			String date = request.getParameter("date");
			String remark = request.getParameter("remark");
			System.out.println("remark:"+remark+"  date:"+date+"  stuCode:"+stuCode);
			
			//做保存前，判断用户是否有添加该学号学生缺勤记录的权限
			User user = userService.findStuCodeAndManager(stuCode,userCurr);
			System.out.println("查询添加缺勤权限user:"+user);
			
			if(user != null) {
				//说明当前登录的用户有添加该学号学生缺勤记录的权限
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Date date2 = null;
				try {
					date2 = dateFormat.parse(date);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Record record = new Record();
				record.setStudentId(user.getId());
				record.setDate(date2);
				record.setDisabled(0);
				record.setRemark(remark);
				//保存数据到数据库
				recordService.save(record);
				
				response.sendRedirect(getServletContext().getContextPath()+"/record.action?action=list");
			}else {
				//没有添加的权限,跳转到添加页面
				request.setAttribute("error", "您没有添加该学号学生缺勤记录的权限！");
				request.setAttribute("mainRight", "/WEB-INF/jsp/recordAddOrUpdate.jsp");
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			}
		}
	}

}
