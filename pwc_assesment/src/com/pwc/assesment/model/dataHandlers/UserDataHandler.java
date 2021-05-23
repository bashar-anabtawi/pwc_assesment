package com.pwc.assesment.model.dataHandlers;

import java.util.List;

import com.pwc.assesment.model.dao.Dao;
import com.pwc.assesment.model.entities.EntityModel;
import com.pwc.assesment.model.entities.User;

public class UserDataHandler implements DataHandlerModel {
	
	private static final String TABLE = "user";
	
	
	public UserDataHandler() {
		if(!Dao.DAO.isTableExists(TABLE)) {
			Dao.DAO.executeUpdate("CREATE TABLE user (id INT PRIMARY KEY, username VARCHAR(100), password VARCHAR(100), isadmin VARCHAR(100));");
		}
	}

	@Override
	public void save(EntityModel entity) {
		if(!(entity instanceof User)) {
			throw new RuntimeException("Trying to save invalid entity in UserDataHandler!");
		}

		User user = (User) entity;
		
		if(user.getId() == -1) {
			int nextId = Dao.DAO.getNextPrimaryKeyId(TABLE);
			user.setId(nextId);
		}
		
		// Add this user to the USER table:
		Dao.DAO.executeUpdate("insert into user values("+user.getId()+", '"+user.getUserName()+"', '"+user.getPassword()+"', '"+user.isAdmin()+"')");
	}

	@Override
	public EntityModel load(int id) {
		List<List<Object>> table = Dao.DAO.execute("select * from user where id = " + id);
		
		if(table.isEmpty()) {
			return null;
		}
		
		List<Object> record = table.get(0);
		
		int userId = (Integer) record.get(0); // id
		String userName = (String) record.get(1); // username
		String pass = (String) record.get(2); // password
		boolean isAdmin = Boolean.valueOf((String) record.get(3)); // isadmin
		
		User user = new User(userId, userName, pass, isAdmin);
		
		return user;
	}
	
	public User loadByUsernameAndPassword(String username, String password) {
		List<List<Object>> table = Dao.DAO.execute("select * from "+TABLE+" where username = '" + username + "' and password='" + password + "'");
		
		if(table.isEmpty()) {
			return null;
		}
		
		List<Object> record = table.get(0);
		
		int userId = (Integer) record.get(0); // id
		String userName = (String) record.get(1); // username
		String pass = (String) record.get(2); // password
		boolean isAdmin = Boolean.valueOf((String) record.get(3)); // isadmin
		
		User user = new User(userId, userName, pass, isAdmin);
		
		return user;
	}
	
	

}
