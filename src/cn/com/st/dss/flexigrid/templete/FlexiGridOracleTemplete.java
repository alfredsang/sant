package cn.com.st.dss.flexigrid.templete;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import cn.com.st.dss.flexigrid.FlexiGridDomain;
import cn.com.st.dss.flexigrid.FlexiGridTemplete;
import cn.com.st.dss.jdbc.DBConnection;
/**
 * Oracle分页实现，目前快速查询改完
 * 
 * @author sangsl@si-tech.com.cn
 * @since 2010-08-15
 *
 */
public abstract class FlexiGridOracleTemplete extends FlexiGridTemplete{

	Log logger = LogFactory.getLog(FlexiGridOracleTemplete.class);
	
	public FlexiGridOracleTemplete(HttpServletRequest request,
			HttpServletResponse response, String tableName,String[] cellName,String sortname) {
		super(request, response, tableName,cellName,sortname);
		logger.debug("执行FlexiGridOracleTemplete");
	}

	/**
	 * @param db
	 * @param fg
	 * @param tableName
	 * @return
	 */
	public String getFlexiGridJson(DBConnection db, FlexiGridDomain fg,
			String tableName,String[] cellName,String sortname) {

		int count = 0;
		String sql = "";
		List list = null;

		try {
			sql = "select count(*) from " + tableName;
			if (!fg.getQuery().equals("")) {
				sql += " where " + fg.getQtype() + " like '%" + fg.getQuery() + "%'";
			}

//			System.out.println("sql=" + sql);
			count = db.executeQuery(sql);
//			System.out.println("count=" + count);

			sql = "select  * from (select t.*,rownum rn from " + tableName +" t where rownum <="+ ((fg.getPage()) * 
							fg.getRp())+" ) where rn>"+(fg.getPage() - 1) * 
					fg.getRp()+"";

			
			// "select * from " + tableName;
			if (!fg.getQuery().equals("")) {
				sql += " and " + fg.getQtype() + " like '%" + fg.getQuery() + "%'";
			}
			sql += " order by " + fg.getSortname() + " " + fg.getSortorder();
			// sql += " limit "
			// + ((Integer.parseInt(pageIndex) - 1) * Integer
			// .parseInt(pageSize)) + "," + pageSize;
 			//System.out.println("sql=" + sql);
			list = db.executeQueryList(sql);
//			System.out.println("list.size=" + list.size());
			if (list == null) {
//				System.out.println("===== =====");
				return "=== =====";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		Map map = new HashMap();
		map.put("page", fg.getPage()+"");
		map.put("total", count + "");
		
	
		String json = toJSON(list, map,cellName,sortname);

		db.commitTransaction();
		db.closeCurrentConnection();
		
		
		return json;
	}
}
