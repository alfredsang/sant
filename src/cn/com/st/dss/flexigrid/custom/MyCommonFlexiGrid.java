package cn.com.st.dss.flexigrid.custom;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.st.dss.flexigrid.templete.FlexiGridHsqldbTemplete;
import cn.com.st.dss.flexigrid.templete.FlexiGridOracleTemplete;

import net.sf.json.JSONObject;

public class MyCommonFlexiGrid extends FlexiGridHsqldbTemplete  {

	Log logger = LogFactory.getLog(MyCommonFlexiGrid.class);

	private HttpServletRequest myRequest = null;

	public MyCommonFlexiGrid(HttpServletRequest request,
			HttpServletResponse response, String tableName, String[] cellName,
			String sortname) {
		super(request, response, tableName, cellName, sortname);
		this.myRequest = request;
	}

	public String toJSON(List list, Map pageInfo, String[] cellName,
			String sortname) {
		List mapList = new ArrayList();

		for (int i = 0; i < list.size(); i++) {
			Map cellMap = new HashMap();
			Map myRecordMap = (Map) list.get(i);

			Object[] me = new Object[cellName.length];
			for (int j = 0; j < cellName.length; j++) {

				String cn = "";
				try {
					cn = new String(cellName[j].getBytes("iso8859-1"), "gbk");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
				cn = cellName[j];
				me[j] = myRecordMap.get(cn.trim());
				// cellMap.put("cell",myRecordMap.get(cellName[j].toUpperCase()));
			}

			cellMap.put(sortname, myRecordMap.get(sortname).toString());

			cellMap.put("cell", me);
			mapList.add(cellMap);
		}
		
		pageInfo.put("rows", mapList);
		JSONObject object = new JSONObject(pageInfo);
		System.out.println(object.toString());
		return object.toString();
	}

}
