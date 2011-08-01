package com.no320.flexigrid.custom;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.st.dss.flexigrid.FlexiGridDomain;
import cn.com.st.dss.jdbc.DBConnection;

import com.no320.flexigrid.FlexiGridTemplate;


/**
 * Nutz flexigrid plugin
 * 
 * @author shiren1118@gmail.com
 * @since 2011-07-27
 */

public class MyNutzFlexiGridImpl extends FlexiGridTemplate {

	public MyNutzFlexiGridImpl(HttpServletRequest request,
			HttpServletResponse response, String tableName, String[] cellName,
			String sortname) {
		super(request, response, tableName, cellName, sortname);
	}

	@Override
	public String getFlexiGridJson(DBConnection db, FlexiGridDomain fg,
			String tableName, String[] cellName, String sortname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toJSON(List list, Map pageInfo, String[] cellName,
			String sortname) {
		// TODO Auto-generated method stub
		return null;
	}

}
