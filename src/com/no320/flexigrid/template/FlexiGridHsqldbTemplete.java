package com.no320.flexigrid.template;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.st.dss.flexigrid.FlexiGridDomain;
import cn.com.st.dss.flexigrid.FlexiGridTemplete;
import cn.com.st.dss.jdbc.DBConnection;

public abstract class FlexiGridHsqldbTemplete extends FlexiGridTemplete{

	public FlexiGridHsqldbTemplete(HttpServletRequest request,
			HttpServletResponse response, String tableName,String[] cellName,String sortname) {
		super(request, response, tableName,cellName,sortname);
		// TODO Auto-generated constructor stub
	}

	 
	public String getFlexiGridJson(DBConnection db, FlexiGridDomain fg,
			String tableName,String[] cellName,String sortname) {

		int count = 0;
		String sql = "";
		List list = null;

		try {
			sql = "select count(*) from " + tableName;
			if (!fg.getQuery().equals("")) {
				sql += " where " + fg.getQtype() + "='" + fg.getQuery() + "'";
			}
//			System.out.println("sql=" + sql);
			count = db.executeQuery(sql);
//			System.out.println("count=" + count);

			sql = "select limit  "
					+ (fg.getPage() - 1) * 
							fg.getRp()+ "  " + fg.getRp()
					+ " * from " + tableName;

			// "select * from " + tableName;
			if (!fg.getQuery().equals("")) {
				sql += " where " + fg.getQtype() + "='" + fg.getQuery() + "'";
			}
			sql += " order by " + fg.getSortname() + " " + fg.getSortorder();
			// sql += " limit "
			// + ((Integer.parseInt(pageIndex) - 1) * Integer
			// .parseInt(pageSize)) + "," + pageSize;
//			System.out.println("sql=" + sql);
			list = db.executeQueryList(sql);
//			System.out.println("list.size=" + list.size());
			if (list == null) {
//				System.out.println("===");
				return "";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		Map map = new HashMap();
		map.put("page", fg.getPage()+"");
		map.put("total", count + "");
		
		String json = toJSON(list, map,cellName,sortname);
//		System.out.println("FlexiGridHsqldbTemplete+json:="+json);

		db.commitTransaction();
		db.closeCurrentConnection();
		
		
		return json;
	}


}
