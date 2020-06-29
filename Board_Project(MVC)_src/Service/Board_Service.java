package Service;

import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Board_Model;

public class Board_Service{
	public void List(HttpServletRequest request,HttpServletResponse response,int now_page)
	{
		Board_Model bm = new Board_Model();
		
		Connect_Service conn = new Connect_Service();
		
		int total = bm.Total_Count(conn.getConn());
		
		int total_page = (int)Math.ceil(total/5);
		
		int start_point = (int)((now_page-1)/5)*5+1;
		
		ArrayList<Map<String, String>> list = bm.Select(conn.getConn(), (now_page-1)*5, 5);
		
		request.setAttribute("list", list);
		request.setAttribute("start_point", start_point);
		request.setAttribute("total_page", total_page+1);
		
		conn.Lose_Connection();
	}
	
	public void Write(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession s = request.getSession();
		
		String id = String.valueOf(s.getAttribute("USER_ID"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");

		Connect_Service conn = new Connect_Service();
		
		new Board_Model().Write(conn.getConn(), title, writer, id, content, pwd);
		
		conn.Lose_Connection();
	}
	
	public boolean Pass_Pre(HttpServletRequest request,HttpServletResponse response)
	{
		String board_num = request.getParameter("board_num");
		
		Connect_Service conn = new Connect_Service();
		String bp = new Board_Model().Pass(conn.getConn(), board_num);
		conn.Lose_Connection();
		if(bp==null||bp.equals("")||bp.equals("null"))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public boolean Pass(HttpServletRequest request,HttpServletResponse response)
	{
		String board_num = request.getParameter("board_num");
		String pass = request.getParameter("user_pass");
		
		Connect_Service conn = new Connect_Service();
		String bp = new Board_Model().Pass(conn.getConn(), board_num);
		conn.Lose_Connection();
		if(bp==null|| bp.equals(pass))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void View(HttpServletRequest request,HttpServletResponse response)
	{
		String board_num = request.getParameter("board_num");
		
		Connect_Service conn = new Connect_Service();
		
		Map<String,String> list = new Board_Model().View(conn.getConn(), board_num);
		request.setAttribute("list", list);
		
		conn.Lose_Connection();
	}
	
	public void Delete(HttpServletRequest request,HttpServletResponse response)
	{
		String board_num = request.getParameter("board_num");
		
		Connect_Service conn = new Connect_Service();
		
		new Board_Model().Delete(conn.getConn(), board_num);
		
		conn.Lose_Connection();
	}
	
	public void Update(HttpServletRequest request,HttpServletResponse response)
	{
		String board_num = request.getParameter("board_num");
		
		Connect_Service conn = new Connect_Service();
		
		Map<String,String> list = new Board_Model().Update(conn.getConn(), board_num);
		request.setAttribute("list", list);
		
		conn.Lose_Connection();
	}
	
	public void Update_Ok(HttpServletRequest request,HttpServletResponse response)
	{	
		String board_num = request.getParameter("board_num");
		String id = request.getParameter("user_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");

		Connect_Service conn = new Connect_Service();
		
		new Board_Model().Update(conn.getConn(), title, writer, id, content, pwd,board_num);
		
		conn.Lose_Connection();
	}
}
