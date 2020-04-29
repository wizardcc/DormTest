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
		//宿舍管理员ID
		String id = request.getParameter("id");
		
		UserService userService = new UserServiceImpl();
		DormBuildService buildService = new DormBuildServiceImpl();
		
		//查询所有的宿舍楼并保存，以便在添加和修改宿舍管理员时在前端展示
		List<DormBuild>  builds = buildService.find();
		request.setAttribute("builds", builds);
		
		if(action != null & action.equals("list")) {
			//宿舍管理员查询
			String searchType = request.getParameter("searchType");
			String keyword = request.getParameter("keyword");
			System.out.println("searchType:"+searchType+"  keyword:"+keyword);
			
			//查询宿舍管理员
			List<User>  users = userService.findManager(searchType,keyword);
			
			//将搜索的条件保存在request中
			request.setAttribute("searchType", searchType);
			request.setAttribute("keyword", keyword);
			request.setAttribute("users", users);
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
			
			//跳转到宿舍管理员列表页，查看所有的宿舍管理员
			//重定向，请求链断开，不能在下一个servlet或jsp中获取保存在request中的参数
			//动态获取项目名字
			response.sendRedirect(getServletContext().getContextPath()+"/dormManager.action?action=list");
		}else if(action != null & action.equals("preUpdate")) {
			//跳转到修改宿舍管理员的页面
			
			//根据宿舍管理员ID，获取宿舍管理员
			User user = userService.findById(Integer.parseInt(id));
			//根据宿舍管理员ID获取宿舍管理员管理的楼栋
			List<DormBuild> userBuilds = buildService.findByUserId(user.getId());
			user.setDormBuilds(userBuilds);
			System.out.println("user:"+user);
			
			List<Integer>  userBuildids = new ArrayList<>();
			//遍历当前宿舍管理员管理的所有宿舍，获取当时当前宿舍管理员管理的所有宿舍ID
			for (DormBuild userBuild : userBuilds) {
				userBuildids.add(userBuild.getId());
			}
			
			
			request.setAttribute("userBuildids", userBuildids);
			request.setAttribute("user", user);
			request.setAttribute("mainRight", "dormManagerAddOrUpdate.jsp");
			request.getRequestDispatcher("/WEB-INF/jsp/main.jsp").forward(request, response);
		}
	}

}
