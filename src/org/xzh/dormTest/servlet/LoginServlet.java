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
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		//打印测试
		System.out.println("name:"+name+"password"+password);
		
		UserService userService = new UserServiceImpl();
		//去查询用户输入的登录名和密码是否正确
		User user = userService.findByNamAndPass(name,password);
	}

}
