package DAO;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import Mybatis.Factory_Class;

public class Update_Model {
	
	public void Update_data(String id,String pass)
	{
		SqlSession session = Factory_Class.getFactory().openSession();
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("user_id", id);
		map.put("user_pass", pass);
		
		session.update("Mapper.Update_ID", map);
		session.commit();
	}
}
