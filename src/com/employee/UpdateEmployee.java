package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateEmployee extends HttpServlet{

	final static long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		String id = request.getParameter("id");
		//int id1 = Integer.parseInt(id);
		String name = request.getParameter("name");
		String[] department = request.getParameterValues("dep");
		String dep = "";
		for(int i=0;i<department.length;i++) {
			dep = dep+department[i];
			if(i<department.length-1) {
				dep = dep+",";
			}
		}
		String salary = request.getParameter("salary");
		String jdate = request.getParameter("jdate");
		String[] desig= request.getParameterValues("designation");
		String designation = "";
		for(int i=0;i<desig.length;i++) {
			designation = designation+desig[i];
			if(i<desig.length-1){
				designation = designation+",";
			}
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoProject","root","@123abhi");
			PreparedStatement ps = con.prepareStatement("update employee set emp_name=?, emp_dept=?, emp_sal=?, emp_desig=?, emp_hiredate=? where emp_id=?");
			
			ps.setString(1, name);
			ps.setString(2, dep);
			ps.setString(3, salary);
			ps.setString(4, designation);
			ps.setString(5, jdate);
			ps.setString(6, id);
			
			ps.executeUpdate();
			
			RequestDispatcher rs=request.getRequestDispatcher("viewEmployee");          
		    rs.forward(request, response);  	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
