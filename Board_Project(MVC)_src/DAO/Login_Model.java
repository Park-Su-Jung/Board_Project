package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login_Model {
	public boolean Id_Check(Connection conn,String id,String pass)
	{
		String sql = "SELECT user_id,user_pass FROM user WHERE user_id=? AND user_pass=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = null;
			stmt.setString(1, id);
			stmt.setString(2, pass);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean Create_Check(Connection conn,String id)
	{
		String sql = "SELECT user_id,user_pass FROM user WHERE user_id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			ResultSet rs = null;
			stmt.setString(1, id);
			rs=stmt.executeQuery();
			if(rs.next())
			{
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void Create_Id(Connection conn,String id,String pass)
	{
		String sql = "INSERT INTO user(user_id,user_pass) VALUES(?,?)";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, id);
			stmt.setString(2, pass);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
