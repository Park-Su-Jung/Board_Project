package Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout_Service {
	public void Logout(HttpServletRequest request,HttpServletResponse response)
	{
		HttpSession session = request.getSession();
		session.removeAttribute("USER_ID");
	}
}
