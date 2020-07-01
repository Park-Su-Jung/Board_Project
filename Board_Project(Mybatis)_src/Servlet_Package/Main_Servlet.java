package Servlet_Package;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Controller.*;

@WebServlet("*.do")
public class Main_Servlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	public void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		this.process(request, response);
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		this.process(request, response);
	}
	
	public void process(HttpServletRequest request,HttpServletResponse response)
	{
		String url = request.getRequestURI();
		
		if(url.contains("Main.do"))
		{
			new Main_Controller().Main(request, response);
		}
		if(url.contains("login.do"))
		{
			new Login_Controller().Login(request, response);
		}
		if(url.contains("logout.do"))
		{
			new Login_Controller().Logout(request, response);
		}
		if(url.contains("create_form.do"))
		{
			new Create_Controller().Create_Form(request, response);
		}
		if(url.contains("create.do"))
		{
			new Create_Controller().Create(request, response);
		}
		if(url.contains("update_pre.do"))
		{
			new Update_Controller().Update_Form(request, response);
		}
		if(url.contains("update_pre_check.do"))
		{
			new Update_Controller().Update_Pre_Check(request, response);
		}
		if(url.contains("update.do"))
		{
			new Update_Controller().Update(request, response);
		}
		if(url.contains("update_check.do"))
		{
			new Update_Controller().Update_Check(request, response);
		}
		if(url.contains("delete.do"))
		{
			new Delete_Controller().Delete_Form(request, response);
		}
		if(url.contains("delete_check.do"))
		{
			new Delete_Controller().Delete(request, response);
		}
		if(url.contains("board.do"))
		{
			new Board_Contoller().Board(request, response);
		}
		if(url.contains("write.do"))
		{
			new Board_Contoller().Write(request, response);
		}
		if(url.contains("write_ok.do"))
		{
			new Board_Contoller().Write_Ok(request, response);
		}
		if(url.contains("view_pass.do"))
		{
			new Board_Contoller().Pass(request, response);
		}
		if(url.contains("pass_check.do"))
		{
			new Board_Contoller().Pass_Ok(request, response);
		}
		if(url.contains("view.do"))
		{
			new Board_Contoller().View(request, response);
		}
		if(url.contains("board_d.do"))
		{
			new Board_Contoller().Delete(request, response);
		}
		if(url.contains("board_u.do"))
		{
			new Board_Contoller().Update(request, response);
		}
		if(url.contains("update_ok.do"))
		{
			new Board_Contoller().Update_Ok(request, response);
		}
	}
}
