package DAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import Mybatis.Factory_Class;

public class Login_Model {
	public boolean Id_Check(String id,String pass)
	{
		SqlSession session = Factory_Class.getFactory().openSession();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("user_id", id);
		map.put("user_pass", pass);
		
		Map<String,String> result = session.selectOne("Mapper.Check_ID",map);
		
		if(result != null)
		{
			return true;
		}
		return false;
	}
	
	public boolean Create_Check(String id)
	{
		SqlSession session = Factory_Class.getFactory().openSession();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("user_id", id);
		
		Map<String,String> result = session.selectOne("Mapper.Check_Create",map);
		
		if(result != null)
		{
			return true;
		}
		return false;
	}
	
	public void Create_Id(String id,String pass)
	{
		SqlSession session = Factory_Class.getFactory().openSession();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("user_id", id);
		map.put("user_pass", pass);
		
		session.insert("Mapper.Create_ID", map);
		session.commit();
	}
}
