package org.xzh.dormTest.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.xzh.dormTest.bean.User;
import org.xzh.dormTest.service.UserService;
import org.xzh.dormTest.service.UserServiceImpl;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("=======登录=======");
		//根据输入框标签的name属性值去获取对应用户输入的值（登录名和密码）
		String stuCode = request.getParameter("stuCode");
		String password = request.getParameter("password");
		//打印测试
		System.out.println("stuCode:"+stuCode+"password"+password);
		
		UserService userService = new UserServiceImpl();
		//去查询用户输入的登录名和密码是否正确
		User user = userService.findByStuCodeAndPass(stuCode,password);
		System.out.println("user"+user);
		
		if(user == null) {
			//用户输入的学号或密码错误，跳转到登录页面，并给予提示信息
			request.setAttribute("error", "您输入的学号或密码错误！");
			//请求链未断开的跳转，可以在下一个servlet或jsp中，获取保存在request中的数据
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}else {
			//用户输入的学号和密码正确，登陆成功。跳转到主页面
			//保存在session中的数据，默认是30min内有效，即浏览器和服务器无交互。保存在session中的数据，在整个项目中都可以获取得到（无论请求链是否断开）
			request.getSession().setAttribute("session_user", user);
			System.out.println("======跳转到主页面======");
			request.getRequestDispatcher("main.jsp").forward(request, response);
		}
	}

}
