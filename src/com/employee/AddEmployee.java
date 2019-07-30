package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AddEmployee extends HttpServlet{

	final static long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
		String name = request.getParameter("name");
		String dep = request.getParameter("dep");
		String salary = request.getParameter("salary");
		String jdate = request.getParameter("jdate");
		String designation = request.getParameter("designation");
		int emp_code = 100;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoProject","root","@123abhi");
			PreparedStatement ps = con.prepareStatement("insert into employee(emp_name,emp_dept,emp_code,emp_sal,emp_desig,emp_hiredate) values(?,?,?,?,?,?)");
			
			ps.setString(1, name);
			ps.setString(2, dep);
			ps.setInt(3, emp_code);
			ps.setString(4, salary);
			ps.setString(5, designation);
			ps.setString(6, jdate);
			
			int j = ps.executeUpdate();
			if(j>0) {
				out.print("Registered successfully");
			}
			RequestDispatcher rs=request.getRequestDispatcher("viewEmployee");          
		    rs.forward(request, response);  
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
	}
	
}
