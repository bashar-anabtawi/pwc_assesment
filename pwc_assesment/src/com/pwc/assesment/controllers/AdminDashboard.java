package com.pwc.assesment.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pwc.assesment.model.dao.Dao;
import com.pwc.assesment.model.dataHandlers.ComplaintDataHandler;
import com.pwc.assesment.model.dataHandlers.UserDataHandler;
import com.pwc.assesment.model.entities.Complaint;
import com.pwc.assesment.model.entities.User;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/adminDashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminDashboard() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Make sure the current user is admin, otherwise log him/her out!
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){
			response.sendRedirect("/pwc_assesment");
			return;
		} else if (!user.isAdmin()) { 
			response.sendRedirect("/pwc_assesment/logout");
			return;
		}
		
		
		List<User> allUsers = getAllUsers();
		request.setAttribute("allUsers", allUsers);
		
		List<Complaint> allComplaints = getAllComplaints();
		request.setAttribute("allComplaints", allComplaints);
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminDashboard.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		
		List<List<Object>> table = Dao.DAO.select("user");
		
		for(List<Object> record : table) {
			int userId = (Integer) record.get(0);
			
			UserDataHandler userDataHanler = new UserDataHandler();
			User user = (User) userDataHanler.load(userId);
			
			users.add(user);
		}
		
		return users;
	}
	
	private List<Complaint> getAllComplaints() {
		List<Complaint> complaints = new ArrayList<Complaint>();
		
		List<List<Object>> table = Dao.DAO.select(ComplaintDataHandler.TABLE);
		
		for(List<Object> record : table) {
			int complaintId = (Integer) record.get(0);
			
			ComplaintDataHandler handler = new ComplaintDataHandler();
			Complaint complaint = (Complaint) handler.load(complaintId);
			
			complaints.add(complaint);
		}
		
		return complaints;
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
