package com.pwc.assesment.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pwc.assesment.model.dao.Dao;
import com.pwc.assesment.model.dataHandlers.UserDataHandler;
import com.pwc.assesment.model.entities.User;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("register.jsp");
		try {
			requestDispatcher.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean isAdmin = Boolean.valueOf(request.getParameter("isAdmin"));
		
		// Do validations on the passed params. & make sure no more than one user with the same credentials.
		UserDataHandler dataHandler = new UserDataHandler();
		User existingUser = dataHandler.loadByUsernameAndPassword(username, password);
		
		if(existingUser != null) {
			request.setAttribute("error", "User already exists, please try another combination of username and password.");
			
			doGet(request, response);
			return;
		}
		
		
		User user = new User(-1, username, password, isAdmin);

		UserDataHandler handler = new UserDataHandler();
		handler.save(user);
		
		request.getSession().setAttribute("user", user);

		if(user.isAdmin()) {
			response.sendRedirect("/pwc_assesment/adminDashboard");
		} else {
			response.sendRedirect("/pwc_assesment/userProfile");
		}
		
	}

}
