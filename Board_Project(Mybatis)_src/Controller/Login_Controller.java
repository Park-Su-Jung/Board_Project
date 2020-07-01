package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Login_Service;
import Service.Logout_Service;

public class Login_Controller {
	
	public void Login(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			if(new Login_Service().Login(request, response))
			{
				response.sendRedirect(request.getContextPath()+"/board.do");
			}
			else
			{
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("로그인에 실패하였습니다.<br/>");
				out.println("<form action='"+request.getContextPath()+"/Main.do' method='post'><input type='submit' value='메인회면'></form>");
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Logout(HttpServletRequest request,HttpServletResponse response)
	{
		new Logout_Service().Logout(request, response);
		try {
			response.sendRedirect(request.getContextPath()+"/Main.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
