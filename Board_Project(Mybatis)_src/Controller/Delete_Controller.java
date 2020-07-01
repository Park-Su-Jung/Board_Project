package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Service.Delete_Service;

public class Delete_Controller {
	public void Delete_Form(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			RequestDispatcher r = request.getRequestDispatcher("/View/Delete.jsp");
			r.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Delete(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html; charset=utf-8");
			if(new Delete_Service().Delete(request, response))
			{
				PrintWriter out = response.getWriter();
				out.println("탈퇴되었습니다.<br/>");
				out.println("<form action='"+request.getContextPath()+"/Main.do' method='post'><input type='submit' value='돌아가기'></form>");
				out.close();
			}
			else
			{
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
}
