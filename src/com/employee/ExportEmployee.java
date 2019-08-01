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

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportEmployee extends HttpServlet{

	static final long serialVerionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String exportValue = request.getParameter("exportValue");
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/DemoProject", "root", "@123abhi");
			PreparedStatement ps = con.prepareStatement("select * from employee");
			
			ResultSet rs = ps.executeQuery();
			
			if(exportValue.equals("1")) {
				
				try {
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
						row.createCell(1).setCellValue(rs.getString("emp_name"));	
						row.createCell(2).setCellValue(rs.getString("emp_dept"));
						row.createCell(3).setCellValue(rs.getString("emp_code"));
						row.createCell(4).setCellValue(rs.getString("emp_sal"));
						row.createCell(5).setCellValue(rs.getString("emp_desig"));
						row.createCell(6).setCellValue(rs.getString("emp_hiredate"));
						
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
					RequestDispatcher rd=request.getRequestDispatcher("viewEmployee");          
				    rd.forward(request, response); 
				}
			}
			else {
				
				Document document = new Document();
				try {
					PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("/Users/abdwivedi/Desktop/Employee/AddTableExample.pdf"));
					document.open();
					
					Paragraph paragraph = new Paragraph();
					
					PdfPTable table = new PdfPTable(7);
					table.setWidthPercentage(100);
					table.setSpacingBefore(10f);
					table.setSpacingAfter(10f);
					
					float[] columnWidth = {1f,4f, 3f, 1f, 2f, 3f, 2f};
					table.setWidths(columnWidth);
					
					PdfPCell cell1 = new PdfPCell(new Paragraph("Id"));
					cell1.setBorderColor(BaseColor.BLACK);
					cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
					PdfPCell cell2 = new PdfPCell(new Paragraph("Name"));
					cell2.setBorderColor(BaseColor.BLACK);
					cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell2.setVerticalAlignment(Element.ALIGN_MIDDLE);
					PdfPCell cell3 = new PdfPCell(new Paragraph("Department"));
					cell3.setBorderColor(BaseColor.BLACK);
					cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell3.setVerticalAlignment(Element.ALIGN_MIDDLE);
					PdfPCell cell4 = new PdfPCell(new Paragraph("Code"));
					cell4.setBorderColor(BaseColor.BLACK);
					cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell4.setVerticalAlignment(Element.ALIGN_MIDDLE);
					PdfPCell cell5 = new PdfPCell(new Paragraph("Salary"));
					cell5.setBorderColor(BaseColor.BLACK);
					cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell5.setVerticalAlignment(Element.ALIGN_MIDDLE);
					PdfPCell cell6 = new PdfPCell(new Paragraph("Designation"));
					cell6.setBorderColor(BaseColor.BLACK);
					cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell6.setVerticalAlignment(Element.ALIGN_MIDDLE);
					PdfPCell cell7 = new PdfPCell(new Paragraph("Hiredate"));
					cell7.setBorderColor(BaseColor.BLACK);
					cell7.setHorizontalAlignment(Element.ALIGN_CENTER);
			        cell7.setVerticalAlignment(Element.ALIGN_MIDDLE);
			        
			        table.addCell(cell1);
			        table.addCell(cell2);
			        table.addCell(cell3);
			        table.addCell(cell4);
			        table.addCell(cell5);
			        table.addCell(cell6);
			        table.addCell(cell7);
			        
			        PdfPCell table_cell;
					
			        while(rs.next()) {	
			        	table_cell = new PdfPCell(new Phrase(rs.getString("emp_id")));
			        	table.addCell(table_cell);
			        	table_cell = new PdfPCell(new Phrase(rs.getString("emp_name")));
			        	table.addCell(table_cell);
			        	table_cell = new PdfPCell(new Phrase(rs.getString("emp_dept")));
			        	table.addCell(table_cell);
			        	table_cell = new PdfPCell(new Phrase(rs.getString("emp_code")));
			        	table.addCell(table_cell);
			        	table_cell = new PdfPCell(new Phrase(rs.getString("emp_sal")));
			        	table.addCell(table_cell);
			        	table_cell = new PdfPCell(new Phrase(rs.getString("emp_desig")));
			        	table.addCell(table_cell);
			        	table_cell = new PdfPCell(new Phrase(rs.getString("emp_hiredate")));
			        	table.addCell(table_cell);
			        }
			        
			        document.add(table);
			        document.close();
			        writer.close();
					
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				finally {
					RequestDispatcher rd=request.getRequestDispatcher("viewEmployee");          
				    rd.forward(request, response); 
				}
			}
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
