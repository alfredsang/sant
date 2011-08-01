<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>


<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.6.2.min.js"></script>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>示例中心</title>
<script type="text/javascript">
$.getJSON('<%=request.getContextPath()%>/GetAntTaskNames', {},function(data) {
	alert(data[0]);
}); 


</script>
</head>
<body> 


<div id="showContent" style="margin:10px">
	<ul>
	 
	<ol><a href='testmy97.html'>my97</a></ol>
<ol><a href='jsp/antTest.jsp'>ant</a></ol>
	</ul>
 
</div>  
 

</body>
</html>
   