package com.employee;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig
public class AddEmployee extends HttpServlet{

	final static long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
				
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
		String[] desig = request.getParameterValues("designation");
		String designation = "";
		for(int i=0;i<desig.length;i++) {
			designation = designation+desig[i];
			if(i<desig.length-1){
				designation = designation+",";
			}
		}
		int emp_code = 100;
		
		Part filePart = request.getPart("picture");
		String fileName =  getSubmittedFileName(filePart);
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoProject","root","@123abhi");
			PreparedStatement ps = con.prepareStatement("insert into employee(emp_name,emp_dept,emp_code,emp_sal,emp_desig,emp_hiredate,emp_pic) values(?,?,?,?,?,?,?)");
			
			ps.setString(1, name);
			ps.setString(2, dep);
			ps.setInt(3, emp_code);
			ps.setString(4, salary);
			ps.setObject(5, designation);
			ps.setString(6, jdate);
			ps.setString(7, fileName);
			
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
	
	private static String getSubmittedFileName(Part part) {
	    for (String cd : part.getHeader("content-disposition").split(";")) {
	        if (cd.trim().startsWith("filename")) {
	            String fileName = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
	            return fileName.substring(fileName.lastIndexOf('/') + 1).substring(fileName.lastIndexOf('\\') + 1); // MSIE fix.
	        }
	    }
	    return null;
	}
}
