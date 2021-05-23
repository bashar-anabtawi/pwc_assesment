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
import com.pwc.assesment.model.entities.Complaint;
import com.pwc.assesment.model.entities.User;

/**
 * Servlet implementation class UserProfile
 */
@WebServlet(name = "userProfile", urlPatterns = { "/userProfile" })
public class UserProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null) {
			// If no user in the session then redirect to home page.
			response.sendRedirect("/pwc_assesment");
			return;
		}
		
		// load user complaints:
		ComplaintDataHandler dataHandler = new ComplaintDataHandler();
		List<Complaint> allUserComplaints = dataHandler.getAllUserComplaints(user);
		request.setAttribute("allUserComplaints", allUserComplaints);
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("userProfile.jsp");
		
		try {
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	

}
