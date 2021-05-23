package com.pwc.assesment.model.dataHandlers;

import java.util.ArrayList;
import java.util.List;

import com.pwc.assesment.model.dao.Dao;
import com.pwc.assesment.model.entities.Complaint;
import com.pwc.assesment.model.entities.Complaint.Status;
import com.pwc.assesment.model.entities.EntityModel;
import com.pwc.assesment.model.entities.User;

public class ComplaintDataHandler implements DataHandlerModel {

	public static final String TABLE = "complaint";
	
	
	public ComplaintDataHandler() {
		if(!Dao.DAO.isTableExists(TABLE)) {
			Dao.DAO.executeUpdate("CREATE TABLE "+TABLE+" ("
					+ "id INT PRIMARY KEY, "
					+ "name VARCHAR(100), "
					+ "department VARCHAR(100), "
					+ "description VARCHAR(1000), "
					+ "status INT, "
					+ "complainer INT FOREIGN KEY REFERENCES user(id) ON DELETE CASCADE ON UPDATE CASCADE);");
		}
	}
	
	
	@Override
	public void save(EntityModel entity) {
		if(!(entity instanceof Complaint)) {
			throw new RuntimeException("Trying to save invalid entity in ComplaintDataHandler!");
		}

		Complaint complaint = (Complaint) entity;
		
		if(complaint.getId() == -1) {
			int nextId = Dao.DAO.getNextPrimaryKeyId(TABLE);
			complaint.setId(nextId);
		}
		
		// Add this complaint to the complaint table:
		Dao.DAO.executeUpdate("insert into "+TABLE+" values("
			+ "" +complaint.getId()+", "
			+ "'"+complaint.getName()+"', "
			+ "'"+complaint.getDepartment()+"', "
			+ "'"+complaint.getDescription()+"', "
			+ complaint.getStatus().id + ", "
			+ complaint.getComplainer().getId() + ")");

	}

	@Override
	public EntityModel load(int id) {
		List<List<Object>> table = Dao.DAO.execute("select * from "+TABLE+" where id = " + id);
		
		if(table.isEmpty()) {
			return null;
		}
		
		List<Object> record = table.get(0);
		
		int complaintId = (Integer) record.get(0); // id
		String name = (String) record.get(1); // name
		String department = (String) record.get(2); // department
		String description = (String) record.get(3); // description
		Status status = Status.getStatusById((Integer) record.get(4)); // status
		Integer complainerId = (Integer) record.get(5); // complainer id
		
		User complainer = (User) (new UserDataHandler()).load(complainerId);
		
		
		Complaint complaint = new Complaint(complaintId, name, description, department, status, complainer);
		
		return complaint;
	}
	
	public List<Complaint> getAllUserComplaints(User loggedInUser) {
		List<Complaint> complaints = new ArrayList<Complaint>();
		
		List<List<Object>> table = Dao.DAO.selectWhere(ComplaintDataHandler.TABLE, "complainer", loggedInUser.getId());
		
		for(List<Object> record : table) {
			int complaintId = (Integer) record.get(0);
			
			ComplaintDataHandler handler = new ComplaintDataHandler();
			Complaint complaint = (Complaint) handler.load(complaintId);
			
			complaints.add(complaint);
		}
		
		return complaints;
	}

}
