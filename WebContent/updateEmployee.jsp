<%@ page import="com.employee.Department"%> 
<%@ page import="com.employee.Designation"%> 
<%@ page import="java.util.ArrayList"%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
#external{
padding-top:20px;
margin-top:50px;
margin-left:35%;
margin-right:35%;
height:230px;
width:30%;

}
#inner1{
float:left;
height:20%;
width:40%;
font-size:23px;
margin-top:22px;

}

#inner2{
float:right;

}
select{
margin-top:0px;
float:right;
height:25px;
width:215px;
}
input{

width:210px;
text-align:center;
height:23px;
border-radius:5px;
}
input[type=submit]{
height:25px;
font-size:13px;
margin-left:25%;
margin-right:25%;
width:50%;
margin-top:18px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="external">

<div id="inner1">
Name:  <br/>   
Salary:  <br/>
Joining Date:<br/>
Designation: <br/>
Department: 
</div>

<form action="updateEmployee" method="post">
<div id="inner2">
 <input type="hidden" name="id" value=<%=request.getParameter("userId")%> /><br/>
 <input type="text" name="name" value=<%=request.getParameter("name")%> /><br/>
 <input type="text" name="salary" value=<%=request.getParameter("salary")%> /><br/>
 <input type="date" name="jdate" value=<%=request.getParameter("hiredate")%> /><br/>
 <select name="desig"> 
 <% ArrayList<Designation> designationList =  (ArrayList<Designation>)request.getAttribute("data2"); %>
 <%for(Designation d1: designationList) {%>
 
 <option><%=d1.getDesig_name()%></option>
 <% }%>
 </select><br/>
 <% ArrayList<Department> departmentList =  (ArrayList<Department>)request.getAttribute("data1"); %>
 <select name="dept"> 
 
 <option selected><%=request.getParameter("department")%></option> 
 
 
 </select> <br/>
</div>
<input type="submit" name="Submit">
</form>

</div>

</body>
</html>