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
		
		if(new Login_Model().Id_Check(id, pass))
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void Update(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession s = request.getSession();
		String id = String.valueOf(s.getAttribute("USER_ID"));
		String pass = request.getParameter("user_pass");
		
		new Update_Model().Update_data(id, pass);
	}
}
