package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Login_Model;

public class Login_Service{
	
	public boolean Login(HttpServletRequest request, HttpServletResponse response)
	{
		String id = request.getParameter("user_id");
		String pass = request.getParameter("user_pass");
		
		Connect_Service conn = new Connect_Service();
		if(new Login_Model().Id_Check(conn.getConn(), id, pass))
		{
			conn.Lose_Connection();
			HttpSession s = request.getSession();
			s.setAttribute("USER_ID", id);
			return true;
		}
		else
		{
			conn.Lose_Connection();
			return false;
		}
	}
}
