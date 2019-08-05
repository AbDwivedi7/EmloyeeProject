<%@ page import="com.employee.Employee"%> 
<%@ page import="java.util.ArrayList"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<style>
img{
height:30px;
width:30px;
}
#scroll{
margin-top:30px;
width:100%;
max-height:600px;
position:absolute;
overflow-y:scroll;
}
table, td, th{
  border: 1px solid black;
}
table{
border-collapse: collapse;

width: 70%;
margin-left:15%;
margin-right:15%;

}
th {
  text-align: left;
}
input{

width:70px;
text-align:center;
border-radius:5px;
}
input[type=submit]{
height:25px;
font-size:13px;
}
form{

height:40px;
}
#add{
max-height:100px;
width:20%;
margin-top:20px;
margin-right:40%;
margin-left: 40%;
}
#form-button{
display:flex;
flex-direction:row;
width:100%;
height:50px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="form-buttom">
<form action="employeeForm" method="post" >
 
 <input id="add" type="submit" value="Add Employee">
 </form >
 
 <form action="exportEmployee" method="post" >
 <input type="hidden" name="exportValue"value="1">
 <input id="add" type="submit" value="Export in Excel">
 </form >
 
 <form action="exportEmployee" method="post" >
 <input type="hidden" name="exportValue" value="2">
 <input id="add" type="submit" value="Export in PDF">
 </form >
</div>
<div id="scroll">
<table >
       
   	
<tr>
   		<th>Id</th>
   		<th>Name</th>
   		<th>Department</th>
   		<th>Code</th>
   		<th>Salary</th>
   		<th>Designation</th>
   		<th>Joining Date</th>
   		<th>Image</th>
   		<th>Edit</th>
</tr>


 <% ArrayList<Employee> employeeList =  (ArrayList<Employee>)request.getAttribute("data"); %>

    <% for(Employee s: employeeList) {
         
    %>
   
   	<tr>
   		<td ><%=s.getId()%></td>
   		<td><%=s.getName()%></td>
   		<td><%=s.getDepartment()%></td>
   		<td><%=s.getCode() %></td>
   		<td><%=s.getSalary() %></td>
   		<td><%=s.getDesignation() %></td>
   		<td><%=s.getHiredate() %></td>
   		<td><img alt="image" src="/EmployeeProjectImg/abhishek.jpeg" ></td>
	   	<td>	<form action="employeeForm" method="post">
	   
	   			<input type="hidden" name="userId" value=<%=s.getId() %> /> 
	   			<input type="hidden" name="name" value="<%=s.getName() %>" /> 
	   			
	   			<input type="hidden" name="dep" value="<%=s.getDepartment() %>" />  
	   			<input type="hidden" name="salary" value=<%=s.getSalary() %> /> 
	   			<input type="hidden" name="designation" value="<%=s.getDesignation() %>" /> 
	   			<input type="hidden" name="hiredate" value=<%=s.getHiredate()%> /> 
	 			<input type="submit" value="Edit" />
	 		</form>
	 	</td>
   	</tr>
     
    
    <% } %>  
     
   </table>
 </div>
 
 
</body>
</html>