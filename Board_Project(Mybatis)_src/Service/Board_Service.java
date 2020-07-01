package Service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Board_Model;

public class Board_Service{
	public void List(HttpServletRequest request,HttpServletResponse response,int now_page)
	{
		Board_Model bm = new Board_Model();
		
		int total = bm.Total_Count();
		
		int total_page = (int)Math.ceil(total/5);
		
		int start_point = (int)((now_page-1)/5)*5+1;
		
		List<Map<String, Object>> list = bm.Select((now_page-1)*5, 5);
		
		request.setAttribute("list", list);
		request.setAttribute("start_point", start_point);
		request.setAttribute("total_page", total_page+1);
	}
	
	public void Write(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession s = request.getSession();
		
		String id = String.valueOf(s.getAttribute("USER_ID"));
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		new Board_Model().Write(title, writer, id, content, pwd);
	}
	
	public boolean Pass_Pre(HttpServletRequest request,HttpServletResponse response)
	{
		String board_num = request.getParameter("board_num");
		
		String bp = new Board_Model().Pass(board_num);

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
		
		String bp = new Board_Model().Pass(board_num);

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
		
		Map<String,Object> list = new Board_Model().View(board_num);
		request.setAttribute("list", list);
	}
	
	public void Delete(HttpServletRequest request,HttpServletResponse response)
	{
		String board_num = request.getParameter("board_num");
		
		new Board_Model().Delete(board_num);
	}
	
	public void Update(HttpServletRequest request,HttpServletResponse response)
	{
		String board_num = request.getParameter("board_num");
		
		Map<String,Object> list = new Board_Model().Update_View(board_num);
		request.setAttribute("list", list);
	}
	
	public void Update_Ok(HttpServletRequest request,HttpServletResponse response)
	{	
		String board_num = request.getParameter("board_num");
		String id = request.getParameter("user_id");
		String title = request.getParameter("title");
		String writer = request.getParameter("writer");
		String content = request.getParameter("content");
		String pwd = request.getParameter("pwd");
		
		new Board_Model().Update(title, writer, id, content, pwd,board_num);
	}
}
