<%@ page import="com.employee.Department"%> 
<%@ page import="com.employee.Designation"%> 
<%@ page import="com.employee.Employee"%> 
<%@ page import="java.util.ArrayList"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<style>
#outer{
padding-top:20px;
margin-top:50px;
margin-left:35%;
margin-right:35%;
height:230px;
width:30%;

}
form{
width:100%;

}
#inner{
float:left;
height:20%;
width:100%;
font-size:20px;
margin-top:10px;
}
input{
margin-top: 8px;
float:right;
height:22px;
width:80%;
border-radius:5px;
font-size:15px;
}
select{
margin-top:8px;
float:right;
height:25px;
width:81.5%;
}

input[type=submit]{
margin-left:20%;
margin-right:20%;
margin-top:20px;
height:30px;
width:60%;
font-size:15px;
}
#names{
width:30%;
float:left;
}
#inputt{
width:70%;
float:right;
}
</style>
<body>
<div id="outer">


<div id="names">
<div id="inner">Name: </div>       
<div id="inner">Salary:   </div>            
<div id="inner">Joining Date: </div>        
<div id="inner">Designation: </div>     
<div id="inner">Department: </div>         
</div>

<div id="inputt">
<% ArrayList<Designation> designationList =  (ArrayList<Designation>)request.getAttribute("data2"); %>
 <% ArrayList<Department> departmentList =  (ArrayList<Department>)request.getAttribute("data1"); %>
 <% ArrayList<Employee> employeeList = (ArrayList<Employee>)request.getAttribute("data3"); %>
<%try{ %>



	<%if(employeeList.get(0).getId().equals("0")) {%>
			   <form action="addEmployee" method="post">
			   <input type="text" name="name"/><br/>
			   <input type="text" name="salary"/><br/>
			   <input type="date" name="jdate"/><br/>
			   
			   <select name="designation">
			  		<% for(Designation d1: designationList) {%>   
							<option><%=d1.getDesig_name()%></option>
					<% } %>
			   </select><br/>
			   <select name="dep">
			   		<% for(Department d: departmentList) {%>
							<option><%=d.getDept_name()%></option>	
					
					<%} %>
					
			  	</select><br/>
			  	<input type="submit" name="Submit">
				<form/>
	<% }else{ %>
	<%for(Employee e : employeeList) { %> 
						<form method="post" action="updateEmployee">
									<input type="hidden" name="id" value=<%=e.getId()%> />
							 	   <input type="text" name="name" value="<%=e.getName() %>" /><br/>
							 	 
								   <input type="text" name="salary" value=<%=e.getSalary() %> /><br/>
								   <input type="date" name="jdate" value=<%=e.getHiredate() %> /> <br/>
								   
								   <select name="designation">
								  		<% for(Designation d1: designationList) {%> 
								    		<%if(d1.getDesig_name().equals(e.getDesignation())){ %>
												<option selected><%=d1.getDesig_name()%></option>
											<% }else{ %>
											<option><%=d1.getDesig_name()%></option>
										<% } %>
										<%} %>
								    </select><br/>
									<select name="dep">
								   		<% for(Department d: departmentList) {%>
								   			<%if(d.getDept_name().equals(e.getDepartment())){ %>
												 <option selected><%=d.getDept_name()%></option>	
											<% }else{ %>
												<option><%=d.getDept_name()%></option>	
										<%} %>
									<%} %>
								 </select><br/>
								 <input type="submit" name="Submit">
					    </form>
	<%} %>
<%} %>
<%}catch(Exception el){ %>
<% el.printStackTrace(); } %>

</div>


</div>
</body>
</html>