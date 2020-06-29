package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Board_Model {
	public int Total_Count(Connection conn)
	{
		String sql = "SELECT COUNT(*) FROM notice_board.board";
		
		int result = 0;
		
		try {
			PreparedStatement pstmt;
			pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				result = rs.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<Map<String,String>> Select(Connection conn,int start,int end)
	{
		ArrayList<Map<String,String>> result = new ArrayList<>();
		
		String sql = "select * from notice_board.board order by board_num desc limit ?, ?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Map<String,String> temp = new HashMap<String, String>();
				temp.put("board_name", rs.getString("board_name"));
				temp.put("user_id", rs.getString("user_id"));
				temp.put("board_writer", rs.getString("board_writer"));
				temp.put("create_data", rs.getString("create_data"));
				temp.put("board_pass", rs.getString("board_pass"));
				temp.put("board_num", rs.getString("board_num"));
				result.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public void Write(Connection conn,String title, String writer,String id, String content, String pwd)
	{
		content=content.replaceAll("\r\n","<br>");
		
		String sql = "INSERT INTO board(user_id,board_pass,board_name,board_data,board_writer) VALUES(?,?,?,?,?)";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, writer);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String Pass(Connection conn,String board_num)
	{
		String sql = "select board_pass from notice_board.board WHERE board_num=?";
		
		PreparedStatement pstmt;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(board_num));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				return rs.getString("board_pass");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Map<String,String> View(Connection conn,String board_num)
	{
		Map<String,String> temp = new HashMap<String, String>();
		
		String sql = "select * from notice_board.board WHERE board_num=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(board_num));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				temp.put("board_name", rs.getString("board_name"));
				temp.put("user_id", rs.getString("user_id"));
				temp.put("board_writer", rs.getString("board_writer"));
				temp.put("create_data", rs.getString("create_data"));
				temp.put("board_pass", rs.getString("board_pass"));
				temp.put("board_num", rs.getString("board_num"));
				temp.put("update_data", rs.getString("update_data"));
				temp.put("board_data", rs.getString("board_data"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return temp;
	}
	
	public void Delete(Connection conn,String board_num)
	{
		String sql = "DELETE FROM board WHERE board_num = ?";
		
		PreparedStatement pstmt = null;
		
		String temp;
		try {
			temp = "SET foreign_key_checks = 0";
			pstmt = conn.prepareStatement(temp);
			pstmt.execute();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1,Integer.parseInt(board_num));
			pstmt.executeUpdate();
			temp = "SET foreign_key_checks = 1";
			pstmt = conn.prepareStatement(temp);
			pstmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Map<String,String> Update(Connection conn,String board_num)
	{
		Map<String,String> temp = new HashMap<String, String>();
		
		String sql = "select * from notice_board.board WHERE board_num=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(board_num));
			ResultSet rs = pstmt.executeQuery();
			if(rs.next())
			{
				temp.put("board_name", rs.getString("board_name"));
				temp.put("user_id", rs.getString("user_id"));
				temp.put("board_writer", rs.getString("board_writer"));
				temp.put("create_data", rs.getString("create_data"));
				temp.put("board_pass", rs.getString("board_pass"));
				temp.put("board_num", rs.getString("board_num"));
				temp.put("update_data", rs.getString("update_data"));
				String data = rs.getString("board_data");
				data=data.replaceAll("<br>","\r\n");
				temp.put("board_data", data);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return temp;
	}
	
	public void Update(Connection conn,String title, String writer,String id, String content, String pwd,String board_num)
	{
		content=content.replaceAll("\r\n","<br>");
		
		String sql = "UPDATE board SET user_id=?,board_pass=?,board_name=?,board_data=?,board_writer=?,update_data=NOW() WHERE board_num=?";
		
		try {
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			pstmt.setString(3, title);
			pstmt.setString(4, content);
			pstmt.setString(5, writer);
			pstmt.setInt(6, Integer.parseInt(board_num));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
