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
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User alreadyLoggedInUser = (User) request.getSession().getAttribute("user");
			
		if(alreadyLoggedInUser != null) {
			response.sendRedirect("/pwc_assesment/userProfile");
		}
		
		
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			
		try {
			requestDispatcher.forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User loggedInUser = (User) request.getSession().getAttribute("user");
		
		if(loggedInUser == null) {
			
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			// TODO: do validations on passed params.
			
			UserDataHandler handler = new UserDataHandler();
			loggedInUser = handler.loadByUsernameAndPassword(username, password);
			
			if(loggedInUser == null) {
				request.setAttribute("error", "Invalid username or password");
				doGet(request, response);
				return;
			}
			
			request.getSession().setAttribute("user", loggedInUser);
		}
		
		if(loggedInUser.isAdmin()) {
			response.sendRedirect("/pwc_assesment/adminDashboard");
		} else {
			response.sendRedirect("/pwc_assesment/userProfile");
		}
	}

	

}
