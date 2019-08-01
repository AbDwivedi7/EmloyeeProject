package com.employee;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportEmployee extends HttpServlet{

	final static long serialVerionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoProject", "root", "@123abhi");
			PreparedStatement ps = con.prepareStatement("select * from employee");
			
			ResultSet rs = ps.executeQuery();
			
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet sheet = workbook.createSheet("employee");
			HSSFRow rowhead = sheet.createRow((short) 0);
			rowhead.createCell(0).setCellValue("Id");
			rowhead.createCell(1).setCellValue("Name");
			rowhead.createCell(2).setCellValue("Department");
			rowhead.createCell(3).setCellValue("Code");
			rowhead.createCell(4).setCellValue("Salary");
			rowhead.createCell(5).setCellValue("Designation");
			rowhead.createCell(6).setCellValue("Joining Date");
			
			int i=1;
			while(rs.next()) {
				HSSFRow row = sheet.createRow(i);
				row.createCell(0).setCellValue(rs.getString("emp_id"));
				System.out.print(rs.getString("emp_id"));
				row.createCell(1).setCellValue(rs.getString("emp_name"));
				System.out.print(rs.getString("emp_name"));
				row.createCell(2).setCellValue(rs.getString("emp_dept"));
				System.out.print(rs.getString("emp_dept"));
				row.createCell(3).setCellValue(rs.getString("emp_code"));
				System.out.print(rs.getString("emp_code"));
				row.createCell(4).setCellValue(rs.getString("emp_sal"));
				System.out.print(rs.getString("emp_sal"));
				row.createCell(5).setCellValue(rs.getString("emp_desig"));
				System.out.print(rs.getString("emp_desig"));
				row.createCell(6).setCellValue(rs.getString("emp_hiredate"));
				System.out.print(rs.getString("emp_hiredate"));
				System.out.println();
				i++;
			}
			
			FileOutputStream file = new FileOutputStream(new File("/Users/abdwivedi/Desktop/Employee/employee.xlsx"));
			workbook.write(file);
			file.close();
			workbook.close();
			
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		finally {
			RequestDispatcher rs=request.getRequestDispatcher("viewEmployee");          
		    rs.forward(request, response); 
		}
	}
}
