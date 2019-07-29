package com.employee;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmployeeForm extends HttpServlet {

	final static long serialVersionUID = 1L;
	
	
	
	/*public void doGet(HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException{
		   
        response.setContentType("text/html"); 
		
		RequestDispatcher dis=request.getRequestDispatcher("/addEmployeeForm.jsp");          
	    dis.forward(request, response); 
	}*/
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		PrintWriter out  = response.getWriter();
		//out.print("hello");
		
		
		
		RequestDispatcher rs=request.getRequestDispatcher("addEmployeeForm.jsp");          
	    rs.forward(request, response);  
	}
	    
}
