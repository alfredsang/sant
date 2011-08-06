<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ include file="include/header.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="author" content="alfred sang"/>
<meta name="description" content="mine"/>
		
<link rel="stylesheet" href="index.style.css" type="text/css" media="screen" /> 
	 
	
		<!-- initialize REDIPS.drag library -->
<script type="text/javascript">

	$(function(){
		
		$(".drag").click(function(){
			
			var url = $(this).attr("url");
			//alert("/sant/"+url);
			$("#inline_example1").load("/sant/"+url,{},function(data){
				//$(this).html(eval(data));
				//$.getScript("/sant/"+url);
			});
			 
			$(this).colorbox({
				height : "80%",
				width : "80%",
				inline : true,
				href : "#inline_example1"
			});
			
			//alert($(this).html());
		});
		
	
		$("#btn").click(function(){
			$("#testtr").hide();
		});
		
	});	

</script>

 
	</head>
	<body onload="REDIPS.drag.init('index_drag')">
			<h2>控制面板</h2>
		<hr>
		<br><br> 
			<div id="index_drag">
				<table>
					<colgroup>
						<col width="100"/>
						<col width="100"/>
						<col width="100"/>
						<col width="100"/>
						<col width="100"/>
						<col width="100"/> 
						<col width="100"/>
						<col width="100"/>
						<col width="100"/>
					</colgroup>
					<tbody>
						<tr>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							
						</tr>
						<tr>
							<td></td>
							<td><div ></div></td>
							<td><div class="drag drag_index" url="jsp/target_exec.jsp">执行target</div></td>
							<td></td>
							<td><div class="drag drag_index"  url="jsp/target_config.jsp">配置target</div></td>
							<td></td>
							<td></td>
							<td><div class="drag drag_index" m="testloaad" url="jsp/testloaad.jsp">testload</div></td>
							
							<td><div class="drag drag_index"  url="jsp/target_config1.jsp">配置target1</div></td>
						</tr>
						<tr id='testtr'>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
		 
		 
		
		
		
		
	<!-- This contains the hidden content for inline calls -->
	<div style='display:none '>
		<div id='inline_example1' style='padding: 10px; background: #fff;'>
			 
		</div>
	</div>
	
	<div id="index_log_container">
	    
		<div id='index_log_container_div' style='padding: 10px; background-color: lightblue;'>
		  当前状态<hr>
		</div>
	</div>
	
	
	</body>
</html>