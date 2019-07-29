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
<form action="addEmployee" method="post">

<div id="names">
<div id="inner">Name: </div>       
<div id="inner">Salary:   </div>            
<div id="inner">Joining Date: </div>        
<div id="inner">Designation: </div>     
<div id="inner">Department: </div>         
</div>

<div id="inputt">
			   <input type="text" name="name"/><br/>
			   <input type="text" name="salary"/><br/>
			   <input type="date" name="jdate"/><br/>
			    <select name="designation">
			     
				<option> Manager </option>
				<option> Engineer </option>
				<option> Executive </option>	
			  </select><br/>
			   <select name="dep">
				<option> Software </option>
				<option> Hardware </option>
				<option> Support </option>	
				</select><br/>
					
</div>
<input type="submit" name="Submit">
</form>

</div>
</body>
</html>