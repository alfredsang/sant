<%@ page language="java" contentType="text/html; charset=gbk"
    pageEncoding="gbk"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html> 
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>FlexiGrid</title>

<link href="<%=request.getContextPath()%>/flexigrid/css/flexigrid.css" type="text/css" rel="stylesheet" />
<link href="<%=request.getContextPath()%>/flexigrid/css/contextmenu.css" type="text/css" rel="stylesheet"  />
<script src="<%=request.getContextPath()%>/flexigrid/js/jquery-1.4.2.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/flexigrid/js/jquery.contextmenu.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/flexigrid/js/jquery.flexigrid.js" type="text/javascript" ></script>

<script type="text/javascript">

	$(function(){
		quert_for_userId();
	});

	function quert_for_userId(){
		//获取查询字段信息
		var user_id=$("#user_id").val();
		var sql = "select  CITY_DESC as 区域, REGION_DESC as 分公司, SITE_DESC as 营业厅, ATTR_NAME as 用户证号, USER_ATTR_NAME  as 客户名称, USER_NUM  as 休眠用户数  from papp.NMDSS_TB_RPT_D001";
		//拼成SQL语句
		if(user_id.length>0){
			sql = sql +" where trim(REGION_DESC)='"+$.trim(user_id)+"'";
		}
		$("#ptable").html("").append(sql+"<div style='margin-left: 13px;'><table class='flexgrid_sang' id='flexi_query_show' style='display:none'></table></div>");
		var qString = "区域@分公司@营业厅@用户证号@客户名称@休眠用户数";

		$("#flexi_query_show").flexigrid(
			{
				width : 650,
				height: 200,
				url : '<%=request.getContextPath()%>/queryController?action=list&qString='+qString+' ',
				dataType : 'json',
				colModel : [ {
					display : '区域',
					name : '区域',
					width : 50,
					sortable : true,
					align : 'center'
				}, {
					display : '分公司',
					name : '分公司',
					width : 100,
					sortable : true,
					align : 'center'
				} , {
					display : '营业厅',
					name : '营业厅',
					width : 180,
					sortable : true,
					align : 'center'
				}, {
					display : '用户证号',
					name : '用户证号',
					width : 140,
					sortable : true,
					align : 'center'
				}
		
				, {
					display : '客户名称',
					name : '客户名称',
					width : 160,
					sortable : true,
					align : 'center'
				}
				, {
					display : '休眠用户数',
					name : '休眠用户数',
					width : 50,
					sortable : true,
					align : 'center'
				}
				],
				searchitems : [
							{display: '分公司', name : '分公司', isdefault: true},
							{display: '营业厅', name : '营业厅'}
				],
				errormsg : '发生异常',
				sortname : "区域",
				querySql : "(" + sql + " )",
				sortorder : "desc",
				//qop: "LIKE",//搜索的操作符
				usepager : true,
				title : '查询结果',
				pagestat : '显示记录从{from}到{to}，总数 {total} 条',
				useRp : true,
				rp : 6,
				rpOptions : [ 10, 15, 20, 30, 40, 100 ], //可选择设定的每页结果数
				nomsg : '没有符合条件的记录存在'
			 
		});
	}
		
</script>

</head>
<body>   
分公司<input type='text' id='user_id' value=''/><input type="button" value="查询" id="quert_for_userId" onclick="quert_for_userId()">
<hr>
<div id="ptable" style="margin:10px">
</div>  
 
</body>
</html>