package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class TestServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String result;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
	
		if(id.equals("red")) {
			if(pwd.equals("blue")) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				result = "정상적으로 로그인되었습니다.";
			}
		}else {
			result = "없는 아이디입니다.";
		}
		System.out.println(id);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(result);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String name = request.getParameter("name");
		System.out.println(name);
		String age = request.getParameter("age");
		System.out.println(age);
		String address = request.getParameter("address");
		System.out.println(address);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println("입력하신 이름 : " + name);	
		pw.println("입력하신 나이 : " + age);
		pw.println("입력하신 주소 : " + address);
		
	}
}
