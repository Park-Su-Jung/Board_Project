package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Delete_Model {
	
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
	
	public void Delete(Connection conn,String id,String pass)
	{
		String sql = "DELETE FROM user WHERE user_id = ?";
		
		PreparedStatement pstmt = null;
		
		String temp;
		try {
			temp = "SET foreign_key_checks = 0";
			pstmt = conn.prepareStatement(temp);
			pstmt.execute();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
			temp = "SET foreign_key_checks = 1";
			pstmt = conn.prepareStatement(temp);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
