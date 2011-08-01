<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
 
<script type="text/javascript">
$(function(){
	
	
	log("加载［执行targets］模块");
	showPage("p1");
	
	//alert('dd');  
    var TARGET_SHOW_NUM = 0;
    var target_data = null;
	
	$.getJSON('<%=request.getContextPath()%>/GetAntTargetListByFileName', {},function(data) {
		//alert(data[0]);
		target_data = data;
		  
		show_target_list(data,TARGET_SHOW_NUM);
		
	});
    
	function show_target_list(data,target_show_num){
		var finalStr = "";
		
		switch (target_show_num) {
		case 0:
			finalStr =  checkBox(data) ;
			break;
		case 1:
			finalStr = select(data);
			break;
		case 2:
			finalStr = radio(data);
			break;

		default:
			finalStr =  checkBox(data) ;
			break;
		}
		//alert(finalStr);
		$("#targetList > span").html(finalStr);
	}
	
	/*<input type="radio" name="aa" value="1">11
	*/
	function radio(data){
		
		var s = [];
		
		
		$.each(data, function(index, value) { 
			var content = value;
			
			s.push("<input  type='radio' name='targetNames' value='"+content+"'>"+content+"");
	
			  //alert(index + ': ' + value); 
		});
		
		
		var finalStr = s.join('');
		return finalStr;
	}
	
	function checkBox(data){
		var s = [];
		
		
		$.each(data, function(index, value) { 
			var content = value;
			
			s.push("<input  type='checkbox' name='targetNames' value='"+content+"'>"+content+"");
	
			  //alert(index + ': ' + value); 
		});
		
		
		var finalStr = s.join('');
		return finalStr;	
	}
	
	function select(data){
		var s = [];
		s.push('<select>', "name='targetNames' ");
		
		$.each(data, function(index, value) { 
			var content = value;
			s.push('<option value=');
			s.push(content);
			s.push('>');
			s.push(content);
			s.push('</option>');
	
			  //alert(index + ': ' + value); 
		});
		
		s.push('</select>');
		var finalStr = s.join('');
		return finalStr;
	}
	
	
	 $("#target_exec_btn").click(function (){
	  	
		$("#log").html("");
		$("#showMsgContainer_status").html("<center><font color=red>TARGET正在执行中,请等待...</font></center>");
		 var targetName = "";
		
		 $("input[name='targetNames']:checked").each(function(){
		     
		      targetName += "_"+$(this).attr("value");
		 
		 })
		 
		 var me = targetName.substring(1, targetName.length);
		// alert(me + " -- " +targetName);
		  
		target_exec_by_name(targetName);
		  
	});
	 
	 
	 
	 function target_exec_by_name(targetName){

		 $.getJSON('<%=request.getContextPath()%>/servlet/ant', {'method':'100002','target':targetName},function(data) {
				
				$("#log").load('<%=request.getContextPath()%>/TailLogServlet',{'logName' : "" + data.mylogname},
							function(data) {
								$("#showMsgContainer_status").html(
										"<b><center><font color=green>执行日志：</font></center></b>");
							 
				});

		  });
	 }

		$("input[rel='sant_exe_target']").click(function(){
			showPage("p2");
		});
		
		
		$(".targetIcon").click(function(){
			var a = $(this).attr("name");
			//alert(a );
			
			show_target_list(target_data,parseInt(a));
			
		});
		
		
		
	 
		
		$("#adsf").click(function(){
			//$("a[rel='sant_exe_target']").colorbox.close();
			showPage("p1");
		});
 
		$.a =function(a){
			alert(a);
		}
	 
});


</script>

<body onload="">
 <!-- default part -->
 <div class="santpage" id="p1">
	<p>
			<strong>执行Targets.</strong>
	</p>
  
	<hr>
	<input type="text" value="/ant">
	<br>
	<input type="text" value="build.xml">
	<br>
		<p>
		 
		<input type="button" value="确定"  rel="sant_exe_target" title="配置执行target信息－控制中心.">		
				
		</p>
		
</div> 
 <!-- end default -->
 
	
	
<!--  part 1-->
 <div class="santpage" id="p2">
		<div id='sant_exe_target' style='padding: 10px; background: #fff;'>
			<p>
				<strong>执行target.</strong>
			</p>
			<hr>
			<p>
			<div id="targetIcon"  style="float: right;border-color:#000; border-style:solid; border-width:1px;">
				<input type="button" class="targetIcon" id="targetIcon_checkbox" name="0" value="多选">
				<input type="button" class="targetIcon" id="targetIcon_radio"  name="1" value="列表">
				<input type="button" class="targetIcon"  id="targetIcon_select"  name="2" value="单选">
			</div>
			<div id="targetList">
				targetList:<br>
				<span></span>
			</div>
			<br /> 
			</p>

			<p>
				 <input type="button"  id="target_exec_btn"   value="执行">
			</p>
			<p>
				<strong>说明：</strong>支持单选多选 
			</p>
				<!-- log -->
				<div id='showMsgContainer' style="background-color: lightblue">
					<div id='showMsgContainer_status' style="background-color: lightblue">
						<center>
							<font color=red>日志状态，暂无执行信息</font>
						</center>
					</div>
					<div id='log' style="background-color: lightblue"></div>
				</div>
	
			<br>
				<input type="button" value="返回" id="adsf" style="float: right;">
	
		</div>
<!-- end p2 -->
	

 
</body>
</html>
