<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ant test</title>
 
 
		<meta name="author" content="Darko Bunic"/>
		<meta name="description" content="Drag and drop table content with JavaScript"/>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/js/redips/style-screen.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/js/redips/style-print.css" type="text/css" media="print" />
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
</style>
<link media="screen" rel="stylesheet"
	href="<%=request.getContextPath()%>/js/colorbox/colorbox.css" />


<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-1.6.2.min.js"></script>
	
 
<script
	src="<%=request.getContextPath()%>/js/colorbox/colorbox/jquery.colorbox.js"></script>
	
<script type="text/javascript">
	

$(function(){
	$("a[rel='example1']").colorbox({
		height : "80%",
		width : "80%",
		inline : true,
		href : "#inline_example1"
	});
		
		
});
	
	
</script>
	
	</head>
	<body>
	
		<p>
			<a
				href="<%=request.getContextPath()%>/js/colorbox/content/ohoopee1.jpg"
				rel="example1" title="My  Target管理－控制中心.">
				作业配置 </a>
		</p>
		
		
		
		
		
		
		
		
		
	<!-- This contains the hidden content for inline calls -->
	<div style='display: none'>
		<div id='inline_example1' style='padding: 10px; background: #fff;'>
			<p>
				<strong>My Target管理.</strong>
			</p>
			<hr>
		 
				
		    <!-- hidden div element needed for hover -->
	    	<div id="hover_div" style="visibility:hidden; position:absolute; top:0; left:0;"></div>
	    	
	     
			
			<!-- tables inside this DIV could have draggable content -->
			<div id="drag">
				<!-- left container -->
				<div id="left_container">
					<!-- this block will become sticky (with a little JavaScript help)-->
					<div id="left">
						<table id="table1">
							<colgroup>
								<col width="60"/>
								<col width="60"/>
								<col width="60"/>
								<col width="60"/>
							</colgroup>
							
							<tr>
								<td><div id="ns0.9" class="drag">ns0.9</div></td>
								<td><div id="ns1.0a" class="drag">ns1.0a</div></td>
								<td><div id="ns1.1b" class="drag">ns1.1b</div></td>
								<td><div id="ns1.2" class="drag">ns1.2</div></td>
								
							</tr>
							<tr>
								<td><div id="ns1.2a" class="drag">ns1.2a</div></td>
								<td><div id="ns1.3" class="drag">ns1.3</div></td>
								<td><div id="ns1.3" class="drag">ns1.3a</div></td>
								<td><div id="ns1.5" class="drag">ns1.5</div></td>
								
							</tr>
							<tr>
								<td><div id="ns1.6" class="drag">ns1.6</div></td>
								<td><div id="ns1.8" class="drag">ns1.8</div></td>
								<td><div id="ns1.8a" class="drag">ns1.8a</div></td>
								<td><div id="ns1.8b" class="drag">ns1.8b</div></td>
								
							</tr>
							<tr>
								<td><div id="ns1.9" class="drag">ns1.9</div></td>
								<td><div id="ns1.9a" class="drag">ns1.9a</div></td>
								<td><div id="ns1.9b" class="drag">ns1.9b</div></td>
								<td><div id="ns3.1" class="drag">ns3.1</div></td>
								
							</tr>
							<tr>
								<td><div id="ns3.1a" class="drag">ns3.1a</div></td>
								<td><div id="ns3.1b" class="drag">ns3.1b</div></td>
								<td><div id="ns3.2" class="drag">ns3.2</div></td>
								<td><div id="ns3.2a" class="drag">ns3.2a</div></td>
								
							</tr>
							<tr>
								<td><div id="ns3.2b" class="drag">ns3.2b</div></td>
								<td><div id="ns3.3" class="drag">ns3.3</div></td>
								<td><div id="ns3.3a" class="drag">ns3.3a</div></td>
								<td><div id="ns3.3b" class="drag">ns3.3b</div></td>
								
							</tr>
							<tr>
								<td><div id="ns4.7" class="drag">ns4.7</div></td>
								<td><div id="ns4.7a" class="drag">ns4.7a</div></td>
								<td><div id="ns4.8b" class="drag">ns4.8b</div></td>
								<td><div id="ns4.9" class="drag">ns4.9</div></td>
								
							</tr>
							<tr>
								<td><div id="ns5.4a" class="drag">ns5.4a</div></td>
								<td><div id="af1.1" class="drag">af1.1</div></td>
								<td><div id="af1.2" class="drag">af1.2</div></td>
								<td><div id="af1.2a1" class="drag">af1.2a1</div></td>
							</tr>
							<tr>
								<td><div id="af1.2b" class="drag">af1.2b</div></td>
								<td><div id="af1.3" class="drag">af1.3</div></td>
								<td><div id="af1.3a" class="drag">af1.3a</div></td>
								<td><div id="af1.3b" class="drag">af1.3b</div></td>
								
							</tr>
							<tr>
								<td><div id="af1.5" class="drag">af1.5</div></td>
								<td><div id="af1.5a" class="drag">af1.5a</div></td>
								<td><div id="af2.1" class="drag">af2.1</div></td>
								<td><div id="af2.1a" class="drag">af2.1a</div></td>
								
							</tr>
							<tr>
								<td><div id="af2.1b" class="drag">af2.1b</div></td>
								<td><div id="af2.2" class="drag">af2.2</div></td>
								<td><div id="af2.2a" class="drag">af2.2a</div></td>
								<td><div id="af2.2b" class="drag">af2.2b</div></td>
								
							</tr>
							<tr>
								<td><div id="mg2.1" class="drag">mg2.1</div></td>
								<td><div id="mg2.2a" class="drag">mg2.2a</div></td>
								<td><div id="mg2.3a" class="drag">mg2.3a</div></td>
								<td></td>
							</tr>
							
						</table>
					</div>
				</div><!-- left container -->
				
				<!-- right container -->
				<div id="right">
					<div class="page">
						<table cellspacing="0" cellpadding="0">
							<colgroup><col width="100"/></colgroup>
							<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
						</table>
						<table cellspacing="0" cellpadding="0">
							<colgroup><col width="100"/></colgroup>
							<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
						</table>
						<table cellspacing="0" cellpadding="0">
							<colgroup><col width="100"/></colgroup>
							<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
						</table>
						<table cellspacing="0" cellpadding="0">
							<colgroup><col width="100"/></colgroup>
							<tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr><tr><td></td></tr>
						</table>
					</div>
	
					<br/>
				 
				</div>
			</div>
	
		</div>
	</div>
		
		
	</body>
</html>