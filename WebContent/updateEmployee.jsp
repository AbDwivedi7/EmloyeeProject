<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="updateEmployee" method="post">
 <input type="hidden" name="id" value=<%=request.getParameter("userId")%> /><br/>
Name:         <input type="text" name="name" value=<%=request.getParameter("name")%> /><br/>
Salary:       <input type="text" name="salary" value=<%=request.getParameter("salary")%> /><br/>
Joining Date: <input type="date" name="jdate" value=<%=request.getParameter("hiredate")%> /><br/>
Designation:  <input type="text" name="desig" value=<%=request.getParameter("desig")%> /><br/>
Department:   <input type="text" name="dept" value=<%=request.getParameter("department")%> /><br/>
<input type="submit" name="Submit">

</form>


</body>
</html>