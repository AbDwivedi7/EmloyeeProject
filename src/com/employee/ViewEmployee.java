package com.employee;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewEmployee extends HttpServlet{

	final static long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		response.setContentType("text/html");
		//PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoProject","root","@123abhi");
			PreparedStatement ps = con.prepareStatement("select * from employee");
		
			ResultSet rs = ps.executeQuery();
			request.setAttribute("resultset",rs);
			
			ArrayList<Employee> list = new ArrayList<Employee>();
			
			while(rs.next()) {
				Employee employeeDAO = new Employee();
				 String id = rs.getString("emp_id");
				 employeeDAO.setId(id);
				 String name = rs.getString("emp_name");
				 employeeDAO.setName(name);
				 String dept = rs.getString("emp_dept");
				 employeeDAO.setDepartment(dept);
				 String code = rs.getString("emp_code");
				 employeeDAO.setCode(code);
				 String sal = rs.getString("emp_sal");
				 employeeDAO.setSalary(sal);
				 String desig = rs.getString("emp_desig");
				 employeeDAO.setDesignation(desig);
				 String date = rs.getString("emp_hiredate");
				 employeeDAO.setHiredate(date);;
				 list.add(employeeDAO);	
			}
			
			request.setAttribute("data", list);
			
			RequestDispatcher rs1=request.getRequestDispatcher("view.jsp");          
			rs1.forward(request, response);  
			//response.sendRedirect("/view.jsp");
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
