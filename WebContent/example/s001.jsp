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
		//��ȡ��ѯ�ֶ���Ϣ
		var user_id=$("#user_id").val();
		var sql = "select  CITY_DESC as ����, REGION_DESC as �ֹ�˾, SITE_DESC as Ӫҵ��, ATTR_NAME as �û�֤��, USER_ATTR_NAME  as �ͻ�����, USER_NUM  as �����û���  from papp.NMDSS_TB_RPT_D001";
		//ƴ��SQL���
		if(user_id.length>0){
			sql = sql +" where trim(REGION_DESC)='"+$.trim(user_id)+"'";
		}
		$("#ptable").html("").append(sql+"<div style='margin-left: 13px;'><table class='flexgrid_sang' id='flexi_query_show' style='display:none'></table></div>");
		var qString = "����@�ֹ�˾@Ӫҵ��@�û�֤��@�ͻ�����@�����û���";

		$("#flexi_query_show").flexigrid(
			{
				width : 650,
				height: 200,
				url : '<%=request.getContextPath()%>/queryController?action=list&qString='+qString+' ',
				dataType : 'json',
				colModel : [ {
					display : '����',
					name : '����',
					width : 50,
					sortable : true,
					align : 'center'
				}, {
					display : '�ֹ�˾',
					name : '�ֹ�˾',
					width : 100,
					sortable : true,
					align : 'center'
				} , {
					display : 'Ӫҵ��',
					name : 'Ӫҵ��',
					width : 180,
					sortable : true,
					align : 'center'
				}, {
					display : '�û�֤��',
					name : '�û�֤��',
					width : 140,
					sortable : true,
					align : 'center'
				}
		
				, {
					display : '�ͻ�����',
					name : '�ͻ�����',
					width : 160,
					sortable : true,
					align : 'center'
				}
				, {
					display : '�����û���',
					name : '�����û���',
					width : 50,
					sortable : true,
					align : 'center'
				}
				],
				searchitems : [
							{display: '�ֹ�˾', name : '�ֹ�˾', isdefault: true},
							{display: 'Ӫҵ��', name : 'Ӫҵ��'}
				],
				errormsg : '�����쳣',
				sortname : "����",
				querySql : "(" + sql + " )",
				sortorder : "desc",
				//qop: "LIKE",//�����Ĳ�����
				usepager : true,
				title : '��ѯ���',
				pagestat : '��ʾ��¼��{from}��{to}������ {total} ��',
				useRp : true,
				rp : 6,
				rpOptions : [ 10, 15, 20, 30, 40, 100 ], //��ѡ���趨��ÿҳ�����
				nomsg : 'û�з��������ļ�¼����'
			 
		});
	}
		
</script>

</head>
<body>   
�ֹ�˾<input type='text' id='user_id' value=''/><input type="button" value="��ѯ" id="quert_for_userId" onclick="quert_for_userId()">
<hr>
<div id="ptable" style="margin:10px">
</div>  
 
</body>
</html>