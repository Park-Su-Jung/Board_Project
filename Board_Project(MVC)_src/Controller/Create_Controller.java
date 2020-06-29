package Controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Create_Service;

public class Create_Controller {
	
	public void Create_Form(HttpServletRequest request,HttpServletResponse response)
	{
		RequestDispatcher r = request.getRequestDispatcher("/View/Create.jsp");
		try {
			r.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Create(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("utf-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		response.setContentType("text/html; charset=utf-8");
		try {
			if(new Create_Service().Create(request, response))
			{
				PrintWriter out = response.getWriter();
				out.println("회원가입이 되었습니다.<br/>");
				out.println("<form action='"+request.getContextPath()+"/Main.do' method='post'><input type='submit' value='메인회면'></form>");
				out.close();
			}
			else
			{
				PrintWriter out = response.getWriter();
				out.println("중복된 아이디를 입력했습니다.<br/>");
				out.println("<form action='"+request.getContextPath()+"/Main.do' method='post'><input type='submit' value='메인회면'></form>");
				out.close();
			}
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
