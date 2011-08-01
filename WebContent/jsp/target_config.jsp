<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		
		
<script type="text/javascript">

//alert('dddd');
$(function(){
	//alert('dddd');

	log("加载［配置Targets］模块");
	
	
	 
	showPage("target_config_p2");
	
	

	
	
	$("a[rel='target_sant_manager']").click(function(){
		//alert("sss");
		showPage("target_config_p2");
	});
	
	
	$("#target_config_p2_back").click(function(){
		//$("a[rel='sant_exe_target']").colorbox.close();
		showPage("target_config_p1");
	});
	
	$("#target_config_select_target_category").html($("#target_config_p3").html());
	$("#target_config_select_target_category").change(function(){
		alert($(this).attr("value"));
	});	
	
	
	var mydata;
	$.getJSON('<%=request.getContextPath()%>/GetAntTaskNames', {},function(data) {
		//alert(data.length);
		var columnCount = 4;
		var pageSize = 40;
		
		var varCurrent = 0;
		
		mydata=data;
		
		var maxCnt = parseInt(data.length/pageSize)+1;
		
		$.each(data,function(n,val){
			//alert(n%pageSize);
			if(n%pageSize==0){
				varCurrent++;
				//alert(varCurrent);
				
				var start =(varCurrent-1)*pageSize;
				var end = (varCurrent)*pageSize-1;
				
				if(varCurrent<maxCnt){
					if(varCurrent==1){
	
						var sss = getPageContent(start,end,'block');
						//alert(sss); 
						$('#table1').append(sss);
					}else{
	
						var sss = getPageContent(start,end,'none');
						//alert(sss); 
						$('#table1').append(sss);
					}
					
					
				}else{
					//the last parapha
					
				}
			
			}
			
		});
		
		
	 
		 
		init1();
		
		var pager = createPager(varCurrent);
		//alert("sd");
		$('#table1').append(pager);
		//pageNumberClick(5);
		
		 
	}); 
	
	function createPager(pageSize){
		var sb = [];
		sb.push("<tr><td colspan='4'>");
		
		for(var i =1;i<=pageSize;i++){
			sb.push("<div  style='float:right;'><a name='page' href='#' onclick='pageNumberClick("+i+")'; >"+i+"</a>-</div>");	 
		}
		sb.push("</td></tr>");
		return sb.join('');
	}
	

	function getPageContent( begin,end,display){
		var columnCount =4;
		var sb = [];
		
		//alert(start+"--"+end);
		for(var i = begin;i<=end;i++){
			//alert(i);
			sb.push("");	
			
			 if(i%columnCount==0){
				 sb.push("<tr class='target_cfg_my_tr_"+parseInt(i/40)+"' style='display:"+display+";'>");
			 }
			 
			 if(mydata[i].length<=5){
				 sb.push("<td><div id='"+mydata[i]+"' class='drag'>"+mydata[i]+"</div></td>");	 
			 }else{
				 var content = mydata[i].substr(0,4);
				 sb.push("<td><div id='"+mydata[i]+"' class='drag'>"+content+"..</div></td>");
			 }
			 
			 //alert(mydata[i]);
			 
			 if(i%columnCount==3){
				 sb.push("</tr>");
			 }
			 
			 
		}
		return sb.join('');
	}
		
});

function pageNumberClick(pageNo){
	//alert($(".target_cfg_my_tr_"+(parseInt(pageNo)-1)).html());
	$(".^target_cfg_my_tr_").closest("tr").hide();
	$(".target_cfg_my_tr_"+(parseInt(pageNo)-1)).closest("tr").hide();
}

	
</script>

<style>

.current_target_config_desc {
	width: 100%;
	z-index: 20;
	border: 2px solid #cfcf3d; 
	font-family: arial;
	font-size: 11px;
	/* round corners */
	border-radius: 4px; /* Opera, Chrome */
	-moz-border-radius: 4px; /* FF */
}
</style>	
 
	
 <!-- default part -->
 <div class="santpage" id="target_config_p1">
		<p>
			<strong>执行Targets .</strong>
		</p>
		<hr>
		<p>
			<a 
				rel="target_sant_manager" title="My  Target管理－控制中心.">
				作业配置 </a>
		</p>
		
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<div class="current_target_config_desc">
			<div style="margin: 10px;">
				<strong>
					此模块使用说明
				</strong>
			 	<ul>
			 		<li>根据一个或多个target生成对应build.xml</li>
			 		<li>可拖拽排序</li>
			 		<li>可预览，测试与执行</li>
			 		<li>可合并多个build.xml</li>
			 	</ul>
			</div>
		
		</div>
</div>		
		
 <!--end default part -->		
		
		
		
		
		
		

 <!-- default part -->
 <div class="santpage" id="target_config_p2">
		<div id='target_sant_manager' style='padding: 10px; background: #fff;'>
			<p>
				<strong>My Target Build文件管理.</strong>
				
			</p>
			<hr>
		 
		 
				
		    <!-- hidden div element needed for hover -->
	    	<div id="hover_div" style="visibility:hidden; position:absolute; top:0; left:0;"></div>
	    	
	     
			
			<!-- tables inside this DIV could have draggable content -->
			<div id="target_config_p2_drag">
				<!-- left container -->
				<div id="left_container" >
				
					  
					<!-- this block will become sticky (with a little JavaScript help)-->
					<div id="left" class="page" style="position: relative;overflow: auto;">
					
						<table id="table1">
							<colgroup>
								<col width="40"/>
								<col width="40"/>
								<col width="40"/>
								<col width="40"/>
 							</colgroup>
							
							<tr>
								<td colspan="4">
									<div id="ns0.9" >
										<div>
											target category
											<select id="target_config_select_target_category">
												<option value="default">default</option>
												<option value="customize">customize</option>
											</select>
										</div>
									</div>
								</td> 
								
							</tr> 
							
						 
						 
								<!-- %
 
 int columnCount = 4;
 List l = (ArrayList)request.getSession().getAttribute("antTastNames");
 
 StringBuffer sb = new StringBuffer();
 for (int i = 0; i < l.size(); i++) {
	 if(i%columnCount==0){
		 sb.append("<tr>");
	 }
	 
	 if(l.get(i).toString().trim().length()<=5){
		 sb.append("<td><div id='"+l.get(i)+"' class='drag'>"+l.get(i)+"</div></td>");	 
	 }else{
		 String content = l.get(i).toString().substring(0,4);
		 sb.append("<td><div id='"+l.get(i)+"' class='drag'>"+content+"..</div></td>");
	 }
	 
	 
	 if(i%columnCount==3){
		 sb.append("</tr>");
	 }
 }

 


 System.out.println(sb.toString());
 out.print(sb.toString());
 
  %
  -->
  
			
							 
							
							<tr>
								<td colspan="4">
									<div id="ns0.9" >
										<div>
											build文件操作
										</div>
									</div>
								</td> 
								
							</tr>
							<tr>
								<td colspan="4">
									<div id="ns0.9" >
										<div>
											<input type="button" value="save">
											<input type="button" value="delete">
											<input type="button" value="concel">
										</div>
									</div>
								</td> 
								
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
					
	 
					
			
				</div>
			</div>
	
		</div>
		<div style="margin-left: 33%">  
		    <div><input type="radio" name="drop_option" class="checkbox" onclick="set_drop_option(this)" value="multiple" title="Enabled dropping to already taken table cells"/><span class="message_line">Enable dropping to already taken table cells</span></div>
			<div><input type="radio" name="drop_option" class="checkbox" onclick="set_drop_option(this)" value="single" title="Disabled dropping to already taken table cells"/><span class="message_line">Disable dropping to already taken table cells</span></div>
			<div><input type="radio" name="drop_option" class="checkbox" onclick="set_drop_option(this)" value="switch"  checked="true" title="Switch content"/><span class="message_line">Switch content</span></div>
			<div><input type="radio" name="drop_option" class="checkbox" onclick="set_drop_option(this)" value="switching" title="Switching content continuously"/><span class="message_line">Switching content continuously</span></div>
			<div><input type="radio" name="drop_option" class="checkbox" onclick="set_drop_option(this)" value="overwrite" title="Overwrite content"/><span class="message_line">Overwrite content</span></div>
			<div><input type="checkbox" class="checkbox" onclick="toggle_delete_cloned(this)" title="Remove cloned object if dragged outside of any table" checked="true"/><span class="message_line">Remove cloned object if dragged outside of any table</span></div>
			<div><input type="checkbox" class="checkbox" onclick="toggle_confirm(this)" title="Confirm before delete object" checked="true"/><span class="message_line">Confirm before delete object</span></div>
			<div><input type="checkbox" class="checkbox" onclick="toggle_dragging(this)" title="Enable dragging" checked="true"/><span class="message_line">Enable dragging</span></div>	
			<div><input type="button" value="Save" class="button" onclick="save()" title="Send content to the server"/><span class="message_line">Save content of the first table (it will only show accepted parameters)</span></div>
		</div>			
				 
		<br>
		<input type="button" value="返回" id="target_config_p2_back" style="float: right;">

				 
</div>
 <!--end  part 2--> 
 
 
 <!-- default part -->
 <div class="santpage" id="target_config_p3">
    
 	

 						
								
  
  
 </div>
 <!--end  part get_target_category-->  
 
 
		
		
 