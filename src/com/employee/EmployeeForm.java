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

public class EmployeeForm extends HttpServlet {

	final static long serialVersionUID = 1L;
	
	public void doPost( HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		//PrintWriter out  = response.getWriter();
		//out.print("hello");
		
		String id1 = request.getParameter("userId");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoProject","root","@123abhi");
			
				PreparedStatement ps1 = con.prepareStatement("select * from department");
				ResultSet rs1 = ps1.executeQuery();
				
				PreparedStatement ps2 = con.prepareStatement("select * from designation");
				ResultSet rs2 = ps2.executeQuery();
				
				ArrayList<Department> list1 = new ArrayList<Department>();
				while(rs1.next()) {
					Department departmentDAO = new Department();
					int id = rs1.getInt("dept_id");
					departmentDAO.setId(id);
					String name = rs1.getString("dept_name");
					departmentDAO.setDept_name(name);
					list1.add(departmentDAO);		
				}
				
			ArrayList<Designation> list2 = new ArrayList<Designation>();
			while(rs2.next()) {
				Designation designationDAO = new Designation();
				int id3 = rs2.getInt("desig_id");
				designationDAO.setId(id3);
				String name = rs2.getString("desig_name");
				designationDAO.setDesig_name(name);
				list2.add(designationDAO);
			}
			
			
			if(id1 == null) {
				request.setAttribute("data1", list1);
				request.setAttribute("data2", list2);
				ArrayList<Employee> employeeList = new ArrayList<Employee>();
				Employee employeeDao = new Employee();
				String id2 = "0";
				employeeDao.setId(id2);
				employeeList.add(employeeDao);
				
				request.setAttribute("data3", employeeList);
				RequestDispatcher rs=request.getRequestDispatcher("addEmployeeForm.jsp");          
			    rs.forward(request, response);  
			}
			else {
				ArrayList<Employee> employeeList = new ArrayList<Employee>();
				
				Employee employeeDao = new Employee();
				String id2 = request.getParameter("userId");
				employeeDao.setId(id2);
				String name = request.getParameter("name");
				employeeDao.setName(name);
				String dep = request.getParameter("dep");
				employeeDao.setDepartment(dep);
				String salary = request.getParameter("salary");
				employeeDao.setSalary(salary);
				String designation = request.getParameter("designation");
				employeeDao.setDesignation(designation);
				String jdate  = request.getParameter("hiredate");
				employeeDao.setHiredate(jdate);
				employeeList.add(employeeDao);
				
				request.setAttribute("data1", list1);
				request.setAttribute("data2", list2);
				request.setAttribute("data3", employeeList);
				
				RequestDispatcher rs=request.getRequestDispatcher("addEmployeeForm.jsp");          
			    rs.forward(request, response);  
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	    
}


