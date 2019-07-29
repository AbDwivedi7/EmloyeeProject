<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
div{
position:absolute;
height:50px;
margin-left:41.5%;
margin-left:41.5%;
margin-top:20px;
margin-bottom-50px;
font-size:40px;
width:100%;
}
form{
height:100px;
}

}
input[type=submit]{
width:50px;
height:100px;
font-size:80px;
}

</style>

</head>
<body >

<div>Employee Details</div>



<form action="viewEmployee" method="post">
<input type="submit" value="View Employee" style="height:100px; " />
</form>


<form action="employeeForm" method="post">
<input type="submit" value="Add Employee"/> 
</form>


</body>
</html>