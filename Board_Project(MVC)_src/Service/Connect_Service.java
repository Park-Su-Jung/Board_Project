package Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect_Service {
	private final String DB_URL = "jdbc:mysql://localhost:3306/notice_board?serverTimezone=UTC";;
    private final String USERNAME = "root";
    private final String PASSWORD = "1234";
    
    private static Connection conn = null;
	
	public Connect_Service() {
		this.Get_Connection();
	}
	
	public Connection getConn() {
		return conn;
	}
	
	private boolean Get_Connection()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
			System.out.println("Connect Succese");
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean Lose_Connection()
	{
        try 
        {
        	if(conn!=null)
            {
        		conn.close();
        		System.out.println("Disconnect Succese");
        		return true;
            }
		} 
        catch (SQLException e) 
        {
			e.printStackTrace();
		}
        return false;
	}
}
