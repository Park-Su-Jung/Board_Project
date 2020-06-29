package Controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Service.Board_Service;

public class Board_Contoller {
	public void Board(HttpServletRequest request,HttpServletResponse response)
	{
		String temp = request.getParameter("now_page");
		int now_page;
		if(temp==null)
		{
			now_page=1;
		}
		else
		{
			now_page=Integer.parseInt(temp);
		}
		new Board_Service().List(request, response, now_page);
		try {
			RequestDispatcher r = request.getRequestDispatcher("/View/Board.jsp?now_page="+String.valueOf(now_page));
			r.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Write(HttpServletRequest request,HttpServletResponse response)
	{
		try {
			RequestDispatcher r = request.getRequestDispatcher("/View/Write.jsp");
			r.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Write_Ok(HttpServletRequest request,HttpServletResponse response)
	{
		new Board_Service().Write(request, response);
		try {
			response.sendRedirect("board.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Pass(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession s = request.getSession();
		String id = (String) s.getAttribute("USER_ID");
		String now_page = request.getParameter("now_page");
		String board_num = request.getParameter("board_num");
		if(id.equals("123")||new Board_Service().Pass_Pre(request, response))
		{
			try {
				response.sendRedirect("view.do?board_num="+board_num+"&now_page="+now_page);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return;
		}
		try {
			RequestDispatcher r = request.getRequestDispatcher("/View/Board_Pass.jsp");
			r.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Pass_Ok(HttpServletRequest request,HttpServletResponse response)
	{
		String now_page = request.getParameter("now_page");
		String board_num = request.getParameter("board_num");
		try {
			if(new Board_Service().Pass(request, response))
			{
				response.sendRedirect("view.do?board_num="+board_num+"&now_page="+now_page);
			}
			else
			{
				request.setCharacterEncoding("utf-8");
				response.setContentType("text/html; charset=utf-8");
				String temp = request.getContextPath()+"/board.do?now_page="+now_page;
				PrintWriter out = response.getWriter();
				out.println("비밀번호가 틀렸습니다.<br/>");
				out.println("<form action='"+temp+"' method='post'><input type='submit' value='목록'></form>");
				out.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void View(HttpServletRequest request,HttpServletResponse response)
	{
		new Board_Service().View(request, response);
		try {
			RequestDispatcher r = request.getRequestDispatcher("/View/Board_View.jsp");
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
		new Board_Service().Delete(request, response);
		try {
			response.sendRedirect(request.getContextPath()+"/board.do");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Update(HttpServletRequest request,HttpServletResponse response)
	{
		new Board_Service().Update(request, response);
		try {
			RequestDispatcher r = request.getRequestDispatcher("/View/Board_Update.jsp");
			r.forward(request, response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Update_Ok(HttpServletRequest request,HttpServletResponse response)
	{
		String now_page=request.getParameter("now_page");
		new Board_Service().Update_Ok(request, response);
		try {
			response.sendRedirect(request.getContextPath()+"/board.do?now_page="+now_page);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
