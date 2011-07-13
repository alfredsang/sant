<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ant test</title>


	<link href="<%=request.getContextPath()%>/js/uiportlet/style/portlet.css" rel="stylesheet" type="text/css"  media="screen" />
	
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/jquery-1.6.2.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/js/uiportlet/jquery.dragsort-0.3.10.js"></script>
	<style type="text/css">
		
		ul { margin:0px; padding:0px; margin-left:20px; }
		.placeHolder div { background-color:white !important; border:dashed 1px red !important; }
				
		#list1, #list2, #list3 { width:300px; list-style-type:none; margin:0px; }
		#list1 dl, #list2 dl, #list3 dl {  }
		#list1 div, #list2 div, #list3 div {}
		#list3{ float:left; }
		#list1{ float:left; }
		#list2{ float:left; }
	</style>
</head>
<body>

<p>portlet examples</p>
 
	
	
	<ul id="list2">
	 
			list1222
	
		<dl>
			<div>
				<div class="portlet" id="test_portlet1">
				<!-- portlet����ͷ����Ϣ -->
					<div class="portlet-topper">
						<span class="portlet-title">
						额度--1
						</span> 
						<div class="portlet-icons">
							<div class="portlet_icon_div portlet_icon_close" id="test_close"  onclick="divshow('test_portlet1')"></div>
							<div class="portlet_icon_div portlet_icon_config"></div>
							<div class="portlet_icon_div portlet_icon_option"></div>
							
							<div class="portlet_icon_div portlet_icon_min" onclick="divshow('div1');"></div>
						</div>
						 
					</div>
				
					<!-- portlet�������� -->
					<div class="portlet-content" id="div1">
					
<img alt="aaa" src="<%=request.getContextPath()%>/img/test/20110418113652797.png" width="250px" height="100px;">

					</div>
				</div>
			<div>
		</dl>	
		</ul>
		<ul id="list1">	
		sdf
		<dl>
			<div class="portlet" id="test_portlet2" >
			<!-- portlet����ͷ����Ϣ -->
				<div class="portlet-topper">
					<span class="portlet-title">
						大师傅--2
					</span> 
					<div class="portlet-icons">
						<div class="portlet_icon_div portlet_icon_close" id="test_close"  onclick="divshow('test_portlet2')"></div>
						<div class="portlet_icon_div portlet_icon_config"></div>
						<div class="portlet_icon_div portlet_icon_option"></div>
						
						<div class="portlet_icon_div portlet_icon_min" onclick="divshow('div2');"></div>
					</div>			
				</div>
				<!-- portlet�������� -->
				<div class="portlet-content" id="div2">
				 
<img alt="aaa" src="<%=request.getContextPath()%>/img/test/20110418113652797.png" width="250px" height="100px;">
				</div>
			</div>
		</dl>
	
				
	</ul> 
 
 <ul id="list1">	
		sdf
		<dl>
			<div class="portlet" id="test_portlet2" >
			<!-- portlet����ͷ����Ϣ -->
				<div class="portlet-topper">
					<span class="portlet-title">
						大师傅--3
					</span> 
					<div class="portlet-icons">
						<div class="portlet_icon_div portlet_icon_close" id="test_close"  onclick="divshow('test_portlet2')"></div>
						<div class="portlet_icon_div portlet_icon_config"></div>
						<div class="portlet_icon_div portlet_icon_option"></div>
						
						<div class="portlet_icon_div portlet_icon_min" onclick="divshow('div2');"></div>
					</div>			
				</div>
				<!-- portlet�������� -->
				<div class="portlet-content" id="div2">
				 
<img alt="aaa" src="<%=request.getContextPath()%>/img/test/20110418113652797.png" width="250px" height="100px;">
				</div>
			</div>
		</dl>
	
				
	</ul> 
	
	
	<ul id="list1">	
		sdf
		<dl>
			<div class="portlet" id="test_portlet2" >
			<!-- portlet����ͷ����Ϣ -->
				<div class="portlet-topper">
					<span class="portlet-title">
						大师傅--4
					</span> 
					<div class="portlet-icons">
						<div class="portlet_icon_div portlet_icon_close" id="test_close"  onclick="divshow('test_portlet2')"></div>
						<div class="portlet_icon_div portlet_icon_config"></div>
						<div class="portlet_icon_div portlet_icon_option"></div>
						
						<div class="portlet_icon_div portlet_icon_min" onclick="divshow('div2');"></div>
					</div>			
				</div>
				<!-- portlet�������� -->
				<div class="portlet-content" id="div2">
				 
<img alt="aaa" src="<%=request.getContextPath()%>/img/test/20110418113652797.png" width="250px" height="100px;">
				</div>
			</div>
		</dl>
	
				
	</ul> 
	
	<ul id="list1">	
		sdf
		<dl>
			<div class="portlet" id="test_portlet2" >
			<!-- portlet����ͷ����Ϣ -->
				<div class="portlet-topper">
					<span class="portlet-title">
						大师傅--5
					</span> 
					<div class="portlet-icons">
						<div class="portlet_icon_div portlet_icon_close" id="test_close"  onclick="divshow('test_portlet2')"></div>
						<div class="portlet_icon_div portlet_icon_config"></div>
						<div class="portlet_icon_div portlet_icon_option"></div>
						
						<div class="portlet_icon_div portlet_icon_min" onclick="divshow('div2');"></div>
					</div>			
				</div>
				<!-- portlet�������� -->
				<div class="portlet-content" id="div2">
				 
<img alt="aaa" src="<%=request.getContextPath()%>/img/test/20110418113652797.png" width="250px" height="100px;">
				</div>
			</div>
		</dl>
	
				
	</ul> 
	<!-- save sort order here which can be retrieved on server on postback -->
	<input name="list1SortOrder" type="hidden" />
	
	<script type="text/javascript">
		$("ul").dragsort({ 
			dragSelector: ".portlet-content", 
			dragBetween: true, 
			dragEnd: saveOrder, 
			proxyTemplate: "<div style='background: lightblue;border: 1px solid red;height: 58px;width: 158px;'>看得着，摸不着<hr></div>",
			placeHolderTemplate: "<dl><div style='border: 1px solid red;background: lightgreen;width:100%;height:100%'></div></dl>" 
		});
		
		function saveOrder() {
			var serialStr = "";
			//.$("#list2 dl").each(function(i, elm) { serialStr += (i > 0 ? "|" : "") + $(elm).children().html(); });
			
			//$("input[name=list1SortOrder]").val(serialStr);
			//alert(serialStr);
		};
		
		
 
		function divshow(infodiv)
		{	
			//alert(infodiv);
			var a=document.getElementById(infodiv);
			a.style.display=(a.style.display=="none"?"block":"none");
			
		};
		
		
		function clone(sourceId,containerId){
		    $("#"+sourceId).clone().appendTo($("#"+containerId));
		}
		
		
		
		$(".example7").colorbox({width:"80%", height:"80%",
			onComplete:function(){ 
				alert('onComplete: colorbox has displayed the loaded content'); 
			},
			iframe:true});
	 
	</script>
	
	<div style="clear:both;" id="msg"></div>
	
</body>
</html>
