package com.pwc.assesment.model.entities;

public class Complaint implements EntityModel {
	
	private int id;
	private String name;
	private String description;
	private String department;
	private Status status;
	
	private User complainer;
	
	
	public Complaint(int id, String name, String description, String department, Status status, User complainer) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.department = department;
		this.status = status;
		this.complainer = complainer;
	}
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public User getComplainer() {
		return complainer;
	}
	public void setComplainer(User complainer) {
		this.complainer = complainer;
	}


	public enum Status {
		NEW(1, "New"), PENDING_RESOLUTION(2, "Pending Resolution"), RESOLVED(3, "Resolved"), DISMISSED(4, "Dismissed");
		
		public int id;
		public String name;
		
		Status(int id, String name) {
			this.id = id;
			this.name = name;
		}
		
		public int getId() {
			return id;
		}
		
		public String getName() {
			return name;
		}
		
		public static Status getStatusById(int id) {
			
			switch(id) {
				case 1:
					return NEW;
				case 2:
					return PENDING_RESOLUTION;
				case 3:
					return RESOLVED;
				case 4:
					return DISMISSED;
				default:
						return NEW;
			}
		}
	}
}
