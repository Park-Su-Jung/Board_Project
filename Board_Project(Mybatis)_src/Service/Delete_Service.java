package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.Delete_Model;

public class Delete_Service {
	public boolean Delete(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession s = request.getSession();
		String id = String.valueOf(s.getAttribute("USER_ID"));
		String pass = request.getParameter("user_pass");
		
		Delete_Model m = new Delete_Model();
		if(m.Id_Check(id, pass))
		{
			m.Delete(id, pass);
			return true;
		}
		else
		{
			return false;
		}
	}
}
