package org.xzh.dormTest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xzh.dormTest.bean.DormBuild;
import org.xzh.dormTest.service.DormBuildService;
import org.xzh.dormTest.service.DormBuildServiceImpl;

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
		
		DormBuildService dormBuildService = new DormBuildServiceImpl();
		
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
			
		}else if(action != null & action.equals("save")) {
			//保存数据
			String name = request.getParameter("name");
			String remark = request.getParameter("remark");
			System.out.println("name:"+name+"remark"+remark);
			
			//宿舍楼名字不能重复，从数据库查询，当前用户输入的宿舍楼名字是否已经存在
			DormBuild dormBuild = dormBuildService.findByName(name);
			
			if(dormBuild != null) {
				//表明用户输入的宿舍楼名已存在
				//表示跳转到宿舍楼添加页面
				request.setAttribute("error", "当前宿舍楼名已存在，请重新输入!");
				request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildAddOrUpdate.jsp");
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
			}else {
				//当前用户输入的宿舍楼名不存在，则保存用户输入的信息到数据库
				DormBuild build = new DormBuild();
				build.setName(name);
				build.setRemark(remark);
				dormBuildService.save(build);
			}
		}
		
	}

}
