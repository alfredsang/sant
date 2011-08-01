<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<link href="<%=request.getContextPath()%>/flexigrid/css/flexigrid.css" type="text/css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/flexigrid/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/flexigrid/js/jquery.flexigrid.js" type="text/javascript" ></script>

 
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/redips/style-screen.css" type="text/css" media="screen" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/js/redips/style-print.css" type="text/css" media="print" />



<link media="screen" rel="stylesheet"
	href="<%=request.getContextPath()%>/js/colorbox/colorbox.css" />


<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.6.2.min.js"></script>
	
 
<script
	src="<%=request.getContextPath()%>/js/colorbox/colorbox/jquery.colorbox.js"></script>
	
<script type="text/javascript" src="<%=request.getContextPath()%>/js/redips/redips-drag-min.js"></script>

<script type="text/javascript" src="<%=request.getContextPath()%>/js/redips/script.js"></script>
 
<style type="text/css">
body {
	font: 12px/1.2 Verdana, sans-serif;
	padding: 0 10px;
}

a:link,a:visited {
	text-decoration: none;
	color: #416CE5;
	border-bottom: 1px solid #416CE5;
}

h2 {
	font-size: 13px;
	margin: 15px 0 0 0;
}
.santpage {
	display: none;
}
</style>

<script type="text/javascript">
/*
 * 
 */
function showPage(pid){
	 
	$(".santpage").each(function(){
		var id=$(this).attr("id");
		//alert(id +"--"+pid);
		
		if(id != pid){
			$(this).hide();
		}else{
			$(this).show();
		}
		
	});
	 
} 
</script>
<script type="text/javascript">
function log1(str){
	$("#index_log_container_div").html(""+(new Date())+" : "+str+"<br>");
}
	
function log(str){
	$("#index_log_container_div").append(""+(new Date())+" : "+str+"<br>");
}
</script>
		
			