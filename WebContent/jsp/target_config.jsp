<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
		
<style type="text/css"> 
　　 a{ text-decoration:none;color: red}  
　　 a:link { text-decoration: none;color: blue} 
　　 a:active { text-decoration:blink} 
　　 a:hover { text-decoration:underline;color: red}  
　　 a:visited { text-decoration: none;color: green} 
　　 
</style>		
<script type="text/javascript">
var mydata;

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
		
		
		$('#sant-t').poshytip();
		 
	}); 
	
	function createPager(pageSize){
		var sb = [];
		sb.push("<tr><td colspan='4'>");
		
		for(var i =pageSize;i>=1;i--){
			sb.push("<div  style='float:right;'><a id='target_config_a_href_"+i+"' name='page' href='#' onclick='pageNumberClick("+i+")'; >"+i+"</a>-</div>");	 
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
				 sb.push("<tr id='target_cfg_my_tr_"+parseInt(i)+"' style='display:"+display+";'>");
			 }
			 
			 if(mydata[i].length<=5){
				 sb.push("<td><div id='"+mydata[i]+"' class='drag clone'>"+mydata[i]+"</div></td>");	 
			 }else{
				 var content = mydata[i].substr(0,4);
				 sb.push("<td><div id='"+mydata[i]+"' class='drag clone'>"+content+"..</div></td>");
			 }
			 
			 //alert(mydata[i]);
			 
			 if(i%columnCount==3){
				 sb.push("</tr>");
			 }
			 
			 
		}
		return sb.join('');
	}
		
	//绑定事件
	$("#right  td").click(function(){
		
		var a = $(this).find("div eq 2");
		
		$(this).find("div").each(function(index){
			
			if(index==1){
				//alert(index + ': ' + $(this).text());
				var taskName = $(this).text();

				//根据返回信息，拼成表单
				$.getJSON('<%=request.getContextPath()%>/GetAntTaskNames', {},function(data) {
					//alert(data.length);
					
					$("#target_sant_ant_task_title").html(taskName+"-TASK SETTING FORM");
					showPage("target_config_p3");
				});
				
				
			}
			
		});
		//alert(a.html());
	});
	
	
	 $("#testtab4").tab({
			tabId:"#tabtag4",
			tabTag:"li",
			conId:"#tabcon4",
			conTag:"div",
			effact: "slow"
			})	; 
	 
	/*   $("#tab").tab({
	        //dft:0,  // 起始显示项,默认为第一项
	        //auto:true,  // 自动切换,默认为关闭
	        //act:"mouseover",  // 鼠标划过,默认为单击
	        //effact:" scrollx",  // 横向滚动效果,纵向滚动为 scrolly
	        effact:"slow",  // "slow" 效果
	        tabId: ".tags",  // 切换控制器
	        tabTag: "li",  // 切换控制器标签
	        conId: ".panes",  // 内容容器
	        conTag: ".pane"  // 内容容器标签
	    }) */
	    
	    $("#target_sant_ant_task_info_form_btn_return").click(function(){
	    	showPage("target_config_p2");
	    });
	
	
	    $("#target_sant_ant_task_info_form_btn_create").click(function(){
	    	showPage("target_config_p4");
	    });
	    
	   
	    
	    $("#target_config_p4_btn_return").click(function(){
	    	showPage("target_config_p3");
	    });
	    
	    $('#demo-manual-trigger').poshytip({
	    	content: 'Hey, there! This is a tooltip.',
	    	showOn: 'none',
	    	alignTo: 'target',
	    	alignX: 'inner-left',
	    	offsetX: 0,
	    	offsetY: 5
	    });
	    
	    
});

function pageNumberClick(pageNo){
	//alert($(".target_cfg_my_tr_"+(parseInt(pageNo)-1)).html());
	
	//$("#^target_cfg_my_tr_").closest("tr").hide();
	//$(".target_cfg_my_tr_"+(parseInt(pageNo)-1)).closest("tr").hide();
	
	var varCurrent = pageNo;
	var pageSize = 40;
	var start =(varCurrent-1)*pageSize;
	var end = (varCurrent)*pageSize-1;
	
	//alert(start,end);
	
	pageNumberClick_hide(start,end);
	
	
	$("#target_config_a_href_"+pageNo).css({'text-decoration': 'none','color': 'green'});
}

function pageNumberClick_hide( begin,end){
	var columnCount =4;
	
	
	//alert(start+"--"+end);
	for(var i = 0;i<=mydata.length;i++){
		//alert("#target_cfg_my_tr_"+parseInt(i/columnCount) );
		
		if(i%columnCount==0){
			//alert(i );
			$("#target_cfg_my_tr_"+parseInt(i)).hide();
			
		}
		
	}
	
	//alert(start+"--"+end);
	for(var i = begin;i<=end;i++){
		//alert("#target_cfg_my_tr_"+parseInt(i/columnCount) );
		
		if(i%columnCount==0){
			//alert(i );
			$("#target_cfg_my_tr_"+parseInt(i)).show();
			
		}
		
	}
}
	
</script>


<style type="text/css">
body,ul,ol,li,p,h1,h2,h3,h4,h5,h6,table,td,th,form,fieldset,img,dl,dt,dd{margin:0px;padding:0px; font-family:Arial, Helvetica, sans-serif;}
 
img{ border:none;}
li{ list-style:none;}
 
.cont{ background:#080808; padding:8px; width:840px;  margin:0 auto;}
.main{  background:#eee; padding:6px;}
h2{ font-size:16px; font-family:"黑体"; color:#35679a;; padding:4px 10px; margin:10px 0 16px; font-weight:100; border-bottom:2px solid #ccc;}
h3{ padding-left:50px; font-size:16px; color:#555;}
.testtab{ border:4px solid  #ccc; margin:1px 1px; }
.tabtag{ line-height:24px; height:24px; border-bottom:2px solid #ccc;}
.tabtag li{ float:left; width:24%; text-align:center; background:#eee;}
.tabtag li.cur{ color:#900; background:#fff;}
.tabcon{ height:80%; overflow:auto;}
.tabcon div{ padding:10px; color:#900; font-size:14px;}
.tabcon li{ line-height:22px;}
pre{ color:#444;}
pre strong{ font-weight:900;}
</style>


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
										<div style="float: left">
										 category
											<input  type="radio">default
												
												<input  type="radio">customize 
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
    
 	<div id='target_sant_ant_task_info_form' style='padding: 10px; background: #fff;'>
			<p>
				<strong><span  id='target_sant_ant_task_title'></span>.</strong>
			</p>
			<hr>
		  <p>
		   <div id="targetIcon"  style="float: right;border-color:#000; border-style:solid; border-width:1px;">
				<input type="button" class="targetIcon" id="target_sant_ant_task_info_form_btn_create" name="0" value="创建模板">
				<input type="button" class="targetIcon"  id="target_sant_ant_task_info_form_btn_return"  name="2" value="返回">
			</div>
		   </p>
		 
		 <br>
				
		 
	    	<div id="current_task_info" style="width: 100%;">
		    	显示当前task配置信息
		   
		   
		  
		   
		    <div class="testtab" id="testtab4">
		    	<div id="tabtag4" class="tabtag" style="position:relative;">
		        	<ul>
		            	<li class="cur">已有模板</li>
		            	<li>Ant task 信息</li>
		                <li>生成xml源码</li> 
		                
		            </ul>
		        </div>
		        <div id="tabcon4" class="tabcon">
		        	<div>
		            	哈哈<br>已有模板如下<br>
		            	
		            	
		            </div>
		        	<div>
		            	哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>哈哈<br>
		            </div>
		            <div>
		            	嘎嘎 
		            </div>
		         
		        </div>
		    </div>
    
    
		    </div>
	    
	    
	    	
	 </div>
			
	<div style="clear: both;top: 82%;position: fixed;float: left">
	    		<hr> 
	    		<p>usage:点击task模范直接输入参数，如果没有模板或合适模板，可以自行创建 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> 
	    		 
	</div>
 						
								
  
  
 </div>
 <!--end  part get_target_category-->  
 
 
  <!-- create ant task template part -->
 <div class="santpage" id="target_config_p4">
    
 	<div id='target_sant_ant_task_info_form' style='padding: 10px; background: #fff;'>
			<p>
				<strong><span>create template for this task</span>.</strong>
			</p>
			<hr>
		  <p>
		   <div id="targetIcon"  style="float: right;border-color:#000; border-style:solid; border-width:1px;"> 
				<input type="button" class="targetIcon"  id="target_config_p4_btn_return"  name="2" value="返回">
			</div>
		   </p>
		 
		 <br>
				
		 
	    	<div id="current_task_info" style="width: 100%;">
		    	显示当前task配置信息
		  
		  
			  <p>
				   first field <input title="Hey, there! This is a tooltip."  type="text" id='sant_t'> 
			 </p>
		   
		  
		    poshytip
    
    
		    </div>
	    
	    
	    	
	 </div>
			
	<div style="clear: both;top: 82%;position: fixed;float: left">
	    		<hr> 
	    		<p>usage:点击task模范直接输入参数，如果没有模板或合适模板，可以自行创建 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p> 
	    		 
	</div>
 						
								
  
  
 </div>
 <!--end  part get_target_category-->  
 
 
		
		
 