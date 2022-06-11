<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!-- CSS only -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-0evHe/X+R7YkIZDRvuzKMRqM+OrBnVFBL6DOitfPri4tjfHxaWutUpFmBp4vmVor" crossorigin="anonymous">
</head>
<body>
<div class ="container">
<form method=post action="${pageContext.request.contextPath}/AppExport/test" modelAttribute="NiveauModel">
  
    <div class="col-md-6">
    
   </div>
   
  <div >
  <label for="basic-url" class="form-label">Niveau</label>
  
  <select class="form-select" aria-label="Default select example"  name="num">

  <option value="2">GI1</option>
  <option value="1">GI2</option>
  <option value="3">GI3</option>
</select>
</div>
<br>
  <div  class="col-md-6" >
  <button type="submit" class="btn btn-warning">Exporter</button>
  </div>
   </form>
  
</div>
</body>
</html>