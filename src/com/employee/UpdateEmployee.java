package com.employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
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

@WebServlet("/update")
@MultipartConfig
public class UpdateEmployee extends HttpServlet{

	final static long serialVersionUID = 1L;
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter writer = response.getWriter();
		
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
		Part filePart = request.getPart("picture");
		//System.out.println(filePart);
		String fileName = getFileName(filePart);
		String path = "/Users/abdwivedi/Desktop/Employee";
		
		OutputStream out = null;
		InputStream fileContent = null;
		
		try {
			out = new FileOutputStream(new File(path+File.separator+fileName));
			fileContent = filePart.getInputStream();
			int read = 0;
			byte[] bytes = new byte[1024*1024];
			while ((read = fileContent.read(bytes)) != -1) {
	            out.write(bytes, 0, read);
	        }
			writer.println("New file " + fileName + " created at " + path);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			if(out != null) {
				out.close();
			}
			if(fileContent !=  null) {
				fileContent.close();
			}
		}
		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoProject","root","@123abhi");
			PreparedStatement ps = con.prepareStatement("update employee set emp_name=?, emp_dept=?, emp_sal=?, emp_desig=?, emp_hiredate=?, emp_pic=? where emp_id=?");
			
			ps.setString(1, name);
			ps.setString(2, dep);
			ps.setString(3, salary);
			ps.setString(4, designation);
			ps.setString(5, jdate);
			ps.setString(6, fileName);
			ps.setString(7, id);
			
			ps.executeUpdate();
			
			RequestDispatcher rs=request.getRequestDispatcher("viewEmployee");          
		    rs.forward(request, response);  	
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	private String getFileName(Part part) {
	    for (String content : part.getHeader("content-disposition").split(";")) {
	        if (content.trim().startsWith("filename")) {
	            return content.substring(
	                    content.indexOf('=') + 1).trim().replace("\"", "");
	        }
	    }
	    return null;
	}
	
}
