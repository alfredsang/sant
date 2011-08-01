<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
<script type="text/javascript">
 

$(function(){
	//alert('dd');
    $("#sdfdsdfs").html("aaaaaaaaaaaaaaaa");
    
   
    	
    	$("#sdfdsdfs").html("ssssssssssss"+'<%=request.getContextPath()%>').click(function(){
			alert("sdfsdfdsf");
		});
        
	    $.getJSON('http://127.0.0.1:8080/sant/GetAntTargetListByFileName', {},function(data) {
			alert(data[0]);
			// target_data = data;
			  
			//show_target_list(data,TARGET_SHOW_NUM);
			
		})
    
 
	 
});
	
/* function l(){
	alert('dd');
    
	 
	} */
</script>

<body onload="l();">
	<h1 onclick="l();">测试load页面</h1>
 
 <div id="sdfdsdfs">
sfsdfsf

</div>
</body>
</html>
