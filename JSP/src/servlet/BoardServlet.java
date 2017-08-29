package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class BoardServlet extends HttpServlet {

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
		String command = request.getParameter("command");
		System.out.println(command);
		if(command.equals("write"))
		{
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			String writer = request.getParameter("writer");
			HashMap hm = new HashMap();
			hm.put("title", title);
			hm.put("content", content);
			hm.put("writer", writer);
			String result = "저장이 완료되었습니다.";
			doProcess(response, result);
		}
	}
	
	
	public void doProcess(HttpServletResponse response, String result) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(result);
	}
}
	
