package DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import Mybatis.Factory_Class;


public class Board_Model {
	public int Total_Count()
	{
		SqlSession session = Factory_Class.getFactory().openSession();
		
		int result = 0;
		
		result = session.selectOne("Mapper.Count_Board");
		
		return result;
	}
	
	public List<Map<String,Object>> Select(int start,int end)
	{
		List<Map<String,Object>> result = null;
		
		Map<String,Integer> point = new HashMap<>();
		
		point.put("Start", start);
		point.put("Count", end);
		
		SqlSession session = Factory_Class.getFactory().openSession();
		
		result = session.selectList("Mapper.Choose_Board",point);
		
		return result;
	}
	
	public void Write(String title, String writer,String id, String content, String pwd)
	{
		content=content.replaceAll("\r\n","<br>");
		
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("user_id", id);
		map.put("board_pass", pwd);
		map.put("board_name", title);
		map.put("board_data", content);
		map.put("board_writer", writer);
		
		SqlSession session = Factory_Class.getFactory().openSession();
		
		session.insert("Mapper.Write_Board",map);
		
		session.commit();
	}
	
	public String Pass(String board_num)
	{
		SqlSession session = Factory_Class.getFactory().openSession();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("board_num", board_num);
		
		String result = session.selectOne("Mapper.Board_Pass",map);
		
		if(result==null || result.equals("") || result.equals("null"))
		{
			return null;
		}
		else
		{
			return result;
		}
	}
	
	public Map<String,Object> View(String board_num)
	{
		SqlSession session = Factory_Class.getFactory().openSession();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("board_num", board_num);
		
		Map<String,Object> result =session.selectOne("Mapper.Board", map);
		
		return result;
	}
	
	public void Delete(String board_num)
	{
		SqlSession session = Factory_Class.getFactory().openSession();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("board_num", board_num);
		
		session.delete("Delete_Board", map);
		session.commit();
	}
	
	public Map<String,Object> Update_View(String board_num)
	{
		SqlSession session = Factory_Class.getFactory().openSession();
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("board_num", board_num);
		
		Map<String,Object> result =session.selectOne("Mapper.Board", map);
		
		result.replace("board_data", String.valueOf(result.get("board_data")).replaceAll("<br>", "\r\n"));
		
		return result;
	}
	
	public void Update(String title, String writer,String id, String content, String pwd,String board_num)
	{
		content=content.replaceAll("\r\n","<br>");
		
		SqlSession session = Factory_Class.getFactory().openSession();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("user_id", id);
		map.put("board_pass", pwd);
		map.put("board_name", title);
		map.put("board_data", content);
		map.put("board_writer", writer);
		map.put("board_num", board_num);
		
		session.update("Mapper.Update_Board", map);
		session.commit();
	}
}
