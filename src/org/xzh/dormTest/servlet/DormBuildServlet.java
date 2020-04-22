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
		
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("action");
		System.out.println("action:"+action);
		
		DormBuildService dormBuildService = new DormBuildServiceImpl();
		
		if(action != null & action.equals("list")) {
			//通过request.getParameter("id")方式获取的值都是String类型
			String id = request.getParameter("id");
			//条件判断是同页面中的无参查询方法还是带参查询方法
			List<DormBuild>  builds = new ArrayList<DormBuild>();
			if(id == null || id.equals("")) {
				//点击左侧宿舍楼管理，查询所有宿舍楼信息，跳转到宿舍楼列表页
				//request方法请求链没有断开，可以在下一个jsp或servlet获取保存在request中的参数
				builds = dormBuildService.find();
			}else if(id != null && !id.equals("")) {
				//点击搜索按钮搜索宿舍楼，根据宿舍楼id查询某一个宿舍楼信息，只会返回一个
				//强制类型转换成int型
				DormBuild build = dormBuildService.findById(Integer.parseInt(id));
				builds.add(build);
			}

			System.out.println("builds:"+builds);
			request.setAttribute("builds", builds);
			request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildList.jsp");
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
			System.out.println("dormBuild:"+dormBuild);
			
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
				build.setDisabled(0);
				dormBuildService.save(build);
				//跳转到main.jsp
				request.setAttribute("mainRight", "/WEB-INF/jsp/dormBuildList.jsp");
				request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
				
			}
		}
		
	}

}
