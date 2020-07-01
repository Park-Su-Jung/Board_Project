package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Update_Service;

public class Update_Controller {
	public void Update_Form(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			RequestDispatcher r = request.getRequestDispatcher("/View/Update_Pre.jsp");
			r.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Update_Pre_Check(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			if(new Update_Service().Check(request, response))
			{
				response.sendRedirect(request.getContextPath()+"/update.do");
			}
			else
			{
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("비밀번호가 틀렸습니다.<br/>");
				out.println("<form action='"+request.getContextPath()+"/board.do' method='post'><input type='submit' value='돌아가기'></form>");
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Update(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			RequestDispatcher r = request.getRequestDispatcher("/View/Update.jsp");
			r.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Update_Check(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			new Update_Service().Update(request, response);
			PrintWriter out = response.getWriter();
			out.println("비밀번호가 변경되었습니다.<br/>");
			out.println("<form action='"+request.getContextPath()+"/board.do' method='post'><input type='submit' value='돌아가기'></form>");
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
