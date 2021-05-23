package com.pwc.assesment.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pwc.assesment.model.dataHandlers.ComplaintDataHandler;
import com.pwc.assesment.model.entities.Complaint;
import com.pwc.assesment.model.entities.Complaint.Status;
import com.pwc.assesment.model.entities.User;
import com.pwc.assesment.utils.StringUtils;

/**
 * Servlet implementation class NewComplaint
 */
@WebServlet(name = "newComplaint", urlPatterns = { "/newComplaint" })
public class NewComplaint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewComplaint() {
        super();
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
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("complaintForm.jsp");
		
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
		
		String name = request.getParameter("name");
		
		if(StringUtils.isNullOrEmpty(name)) {
			request.setAttribute("error", "Name is required and can not be empty!");
			doGet(request, response);
			return;
		}
		
		String department = request.getParameter("department");
		String description = request.getParameter("description");
		
		User complainer = (User) request.getSession().getAttribute("user");
		
		Complaint complaint = new Complaint(-1, name, description, department, Status.NEW, complainer);
		
		ComplaintDataHandler dbHandler = new ComplaintDataHandler();
		dbHandler.save(complaint);
		
		
		response.sendRedirect("/pwc_assesment/userProfile");
	}

}
