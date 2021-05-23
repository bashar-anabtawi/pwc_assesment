package com.pwc.assesment.controllers;

import java.io.IOException;

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
import com.pwc.assesment.model.entities.Complaint.Status;
import com.pwc.assesment.utils.StringUtils;

/**
 * Servlet implementation class EditComplaint
 */
@WebServlet(name = "editComplaint", urlPatterns = { "/editComplaint" })
public class EditComplaint extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditComplaint() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = (User) request.getSession().getAttribute("user");
		
		if(user == null){
			response.sendRedirect("/pwc_assesment");
			return;
		} else if (!user.isAdmin()) { 
			response.sendRedirect("/pwc_assesment/logout");
			return;
		}
		
		//////////////////////
		String complaintID = request.getParameter("id");
		
		if(StringUtils.isNullOrEmpty(complaintID)) {
			request.setAttribute("error", "Complaint ID can not be empty!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminDashboard.jsp");
			
			try {
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		
		//////////////////////
		ComplaintDataHandler handler = new ComplaintDataHandler();
		Complaint complaint = (Complaint) handler.load(Integer.parseInt(complaintID));
		
		if(complaint == null) {
			request.setAttribute("error", "Complaint can not be found!");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("adminDashboard.jsp");
			
			try {
				requestDispatcher.forward(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return;
		}
		///////////////////////////////
		
		request.setAttribute("complaint", complaint);
		
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
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		
		if(StringUtils.isNullOrEmpty(name)) {
			request.setAttribute("error", "Name is required and can not be empty!");
			doGet(request, response);
			return;
		}
		
		String department = request.getParameter("department");
		String description = request.getParameter("description");
		
		int statusId = Integer.parseInt(request.getParameter("status"));
		
		Dao.DAO.executeUpdate("update complaint set "
				+ "name = '"+name+"', "
				+ "department = '"+department+"', "
				+ "description = '"+description+"', "
				+ "status = '"+statusId+"' "
				+ "where id = " + id);
		
		response.sendRedirect("/pwc_assesment/adminDashboard");
	}

}
