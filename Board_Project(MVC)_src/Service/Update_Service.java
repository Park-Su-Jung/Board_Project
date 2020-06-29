package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Login_Model;
import DAO.Update_Model;

public class Update_Service{
	
	public boolean Check(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession s = request.getSession();
		String id = String.valueOf(s.getAttribute("USER_ID"));
		String pass = request.getParameter("user_pass");
		
		Connect_Service conn = new Connect_Service();
		if(new Login_Model().Id_Check(conn.getConn(), id, pass))
		{
			conn.Lose_Connection();
			return true;
		}
		else
		{
			conn.Lose_Connection();
			return false;
		}
	}
	
	public void Update(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession s = request.getSession();
		String id = String.valueOf(s.getAttribute("USER_ID"));
		String pass = request.getParameter("user_pass");
		
		Connect_Service conn = new Connect_Service();
		new Update_Model().Update_data(conn.getConn(), id, pass);
		conn.Lose_Connection();
	}
}
