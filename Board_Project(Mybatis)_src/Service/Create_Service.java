package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Login_Model;

public class Create_Service {
	public boolean Create(HttpServletRequest request,HttpServletResponse response)
	{
		String id = request.getParameter("user_id");
		String pass = request.getParameter("user_pass");
		
		Login_Model lm = new Login_Model();
		if(lm.Create_Check(id))
		{
			return false;
		}
		else
		{
			lm.Create_Id(id, pass);
			return true;
		}
	}
}
