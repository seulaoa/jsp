package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import common.DBCon;
import service.UserService;
import service.UserServiceImpl;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private UserService us = new UserServiceImpl();
	private String result;

/*	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		if (id.equals("red")) {
			if (pwd.equals("blue")) {
				HttpSession session = request.getSession();
				session.setAttribute("id", id);
				result = "정상적으로 로그인되었습니다.";
			}
		} else {
			result = "없는 아이디입니다.";
		}
		System.out.println(id);
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(result);
	}
*/
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command = request.getParameter("command"); //command의 value값을 가져와
		System.out.println(command);
		
		
		if (command.equals("login")) {
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			Map<String, String> hm = us.getUserLogin(id, pwd);
			String result = "로그인에 싷패하셨습니다.";
			if(hm!=null) {
				HttpSession session = request.getSession();
				session.setAttribute("user", hm);
				
				result = "로그인에 성공했씁니다.";
				response.sendRedirect("/main.jsp");
			}
			doProcess(response, result);
			
			
		} else if (command.equals("signin")) {

			String str = request.getParameter("param");
			Gson g = new Gson();
			HashMap<String,String> hm = g.fromJson(str, HashMap.class);
			
			result = "회원가입에 실패하셨습다";
			
			int rCnt = us.insertUser(hm); //insertUser함수 호출
			if(rCnt == 1){
				result ="회원가입에 성공하셨습니다.";
				result += "다시로그인 ㄱ";

			}
			HashMap<String,String> rHm = new HashMap<String, String>();
			rHm.put("msg", result);
			rHm.put("url", "/login.jsp");
			
			result = g.toJson(rHm);
			doProcess(response, result);

		}else if(command.equals("logout")) {
			HttpSession session = request.getSession();
			session.invalidate(); //session이 가ㅣㅈ고있던 키값들을 날려버림
			response.sendRedirect("/login.jsp");
		}
		
		
		
		else if (command.equals("modify")) {
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			String name = request.getParameter("name");
			String[] hobbies = request.getParameterValues("hobby");
			String hobby = "";
			for (String h : hobbies) {
				hobby += h + ",";
			}
			hobby = hobby.substring(0, hobby.length()-1); //substring함수 사용, hobby의 맘지막 값을 빼겠다는 말 = ,를 빼겠다.
		
			Map<String,String> hm = new HashMap<String, String>();
			hm.put("id", id);
			hm.put("pwd", pwd);
			hm.put("name", name);
			hm.put("hobby", hobby);
			HttpSession session = request.getSession();
			Map<String, String> user = (Map) session.getAttribute("user");
			hm.put("user_no", user.get("user_no"));
			int rCnt = us.updateUser(hm);

			String result; 
			result = "회원정보 수정에 실패하셨습니다";
		
			
			if(rCnt == 1){
				session.invalidate();
				result = "<script>";
				result+="alert('회원 수정에 성공하셨습니다. 다시 로그인하시기 바랍니다.');";
				result+="location.href='/login.jsp'";
				result+="</script>";
			}
			doProcess(response, result);
	
		}		

		else if (command.equals("delete")) {
			String userNo = request.getParameter("userNo");
			Map<String,String> hm = new HashMap<String, String>();
			hm.put("user_no", userNo);
			int rCnt = us.deleteUser(hm);

			String result; 
			result = "회원탈퇴에 실패하셨습니다";
			
			if(rCnt == 1){
				result = "<script>";
				result+="alert('회원 탈퇴에 성공하셨습니다.');";
				result+="location.href='/login.jsp'";
				result+="</script>";
			}
			doProcess(response, result);
	
		}				
		else if(command.equals("list")) {

			Map<String, String> hm = new HashMap<String, String>();
			hm.put("name", request.getParameter("name"));
			List<Map<String, String>> userList = us.getUserList(hm);
			String result = "<table border='1'>";
			for(Map<String,String> m : userList) {
				result += "<tr>";
				result += "<td>" + m.get("user_no")+"</td>";
				result += "<td>" + m.get("id")+"</td>";
				result += "<td>" + m.get("name")+"</td>";
				result += "<td>" + m.get("hobby")+"</td>";
				result += "<td><input type = 'button' value='수정' data-num ='" + m.get("user_no") +"'></td>";
				result += "<td><input type = 'button' value='삭제' data-num ='" + m.get("user_no") +"'></td>";
				result += "</tr>";
				
			}
			result +="</table>";
			doProcess(response,result);
					
		}else if(command.equals("view"))
		{
			String userNo = request.getParameter("userNo");
			Map<String, String> hm = us.selectUser(userNo); //쿼리로 데이터 받아서 해쉬ㅁㅂ에 널기

			Gson g = new Gson();
			String result = g.toJson(hm);
			doProcess(response, result); //{key:value, key:value, ...} 이 형태로  result호 보내
		}
	}

	
	
	
	
	
	public void doProcess(HttpServletResponse response, String result) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter pw = response.getWriter();
		pw.println(result);
	}
}
