<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>示例001-最简单的例子</title>
<link href="<%=request.getContextPath()%>/flexigrid/css/flexigrid.css" type="text/css" rel="stylesheet" />
<script src="<%=request.getContextPath()%>/flexigrid/js/jquery.js" type="text/javascript"></script>
<script src="<%=request.getContextPath()%>/flexigrid/js/jquery.flexigrid.js" type="text/javascript" ></script>
<script type="text/javascript">
$(function(){
	$("#ptable").html("").append("<div style='margin-left: 13px;'><table class='flexgrid_sang' id='flexi_query_show' style='display:none'></table></div>");
	var sql = " select regn_id,regn_name,part_id,regn_level,regn_desc from nmdss_cde_regn ";
	var qString = "REGN_ID@REGN_NAME@PART_ID@REGN_LEVEL@REGN_DESC";
		
	$("#flexi_query_show")
	.flexigrid(
			{
				width : 950,
				height: 200,
				url : "<%=request.getContextPath()%>/queryController?action=list&qString="+encodeURIComponent(qString)+' ',
				dataType : 'json',
				colModel : [ {
					display : 'REGN_ID',
					name : 'REGN_ID',
					width : 50,
					sortable : true,
					align : 'center'
				}, {
					display : 'REGN_NAME',
					name : 'REGN_NAME',
					width : 140,
					sortable : true,
					align : 'center'
				} , {
					display : 'PART_ID',
					name : 'PART_ID',
					width : 100,
					sortable : true,
					align : 'center'
				}, {
					display : 'REGN_LEVEL',
					name : 'REGN_LEVEL',
					width : 100,
					sortable : true,
					align : 'center'
				},{
					display : 'REGN_DESC',
					name : 'REGN_DESC',
					width : 100,
					sortable : true,
					align : 'center'
				}
 				],
				searchitems : [
				   			{display: 'regn_id', name : 'regn_id', isdefault: true}
				],
				errormsg : '发生异常',
				sortname : "REGN_ID",
				querySql : "(" + sql + " )",
				sortorder : "desc",
				//qop: "LIKE",//搜索的操作符
				usepager : true,
				title : '查询结果',
				pagestat : '显示记录从{from}到{to}，总数 {total} 条',
				useRp : true,
				rp : 10,
				rpOptions : [ 10, 15, 20, 30, 40, 100 ] //可选择设定的每页结果数
		});
});
</script>
</head>
<body>
<div id="ptable" style="margin:10px"></div>
</body>
</html>
