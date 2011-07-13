<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ant test</title>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-1.6.2.min.js"></script>
<script type="text/javascript">
$(function(){
	//alert('dd');
<%-- 	
	$.get('<%=request.getContextPath() %>/servlet/ant', function(data) {
		alert(data);
		/* var obj = jQuery.parseJSON(data);
		//alert( obj );
		alert(eval("''"+data+"'")); */
		
		  //$('#targetList').html(data.sss);
		 // alert('Load was performed.');
	}); --%>
	
	
	$.getJSON('<%=request.getContextPath() %>/servlet/ant', {'method':'100001'},function(data) {
		
		//alert(data.dir);
		$('#targetList>span').html(data.dir);
	});
	
	
<%-- 	$.getJSON('<%=request.getContextPath() %>/servlet/ant', {'method':'100002'},function(data) {
		
		//alert(data.dir);
		$('#targetList>span').html(data.dir);
	});
	 --%>
	
	
	
	
});
</script>
</head>
<body>

ant test
<hr>
<input type="text" value="/ant"><br>
<input type="text" value="build.xml"><br>
<div id="targetList">【targetList】:<span></span></div>
 


<input type="button" value="execute"> 





</body>
</html>
