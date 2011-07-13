package cn.com.st.dss.flexigrid;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.com.st.dss.jdbc.DBConnection;

/**
 * 
 * @author sangsl@si-tech.com.cn
 * @since 2010-08-05
 */

public abstract class FlexiGridTemplete {

	public FlexiGridTemplete(HttpServletRequest request,
			HttpServletResponse response, String tableName,String[] cellName,String sortname) {

		try {
			processList(request, response, tableName,cellName,sortname);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public void processList(HttpServletRequest request,
			HttpServletResponse response, String tableName,String[] cellName,String sortname) throws IOException {

		response.setContentType("html/txt");
		response.setCharacterEncoding("utf-8");
		response.setHeader("Cache-Control", "no-cache, must-revalidate");
		response.setHeader("Pragma", "no-cache");

		FlexiGridDomain fg = new FlexiGridDomain();
		//fg.setPage(Integer.parseInt(request.getParameter("page")));
		fg.setPage(Integer.parseInt(new String(request.getParameter("page").getBytes("iso8859-1"), "UTF-8")));
		
		
		
		fg.setQtype(new String(request.getParameter("qtype").getBytes("iso8859-1"), "UTF-8"));
		fg.setQuery(new String(request.getParameter("query").getBytes("iso8859-1"), "UTF-8"));
		fg.setRp( Integer.parseInt(request.getParameter("rp")));
		fg.setSortname(new String(request.getParameter("sortname").getBytes("iso8859-1"), "UTF-8"));
		fg.setSortorder(new String(request.getParameter("sortorder").getBytes("iso8859-1"), "UTF-8"));
		fg.setQop(new String(request.getParameter("qop").getBytes("iso8859-1"), "UTF-8"));
		
		 
		String q = fg.getQuery();
		 
//		try {
// 
//			if(q.length()>0){
//				//q = UrlDecoderUtils.getDecoderValue(request,"query");
//					//q =new String(request.getParameter("query").getBytes("gbk"), "UTF-8");
//				//String sortname_v =new String(request.getParameter("sortname").getBytes("iso8859-1"), "UTF-8"); 
//				//System.out.println("json2:=" +sortname_v);
//				//fg.setQuery(q);
//				//fg.setQuery(sortname_v);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		DBConnection db = new DBConnection();
		try {
			db.getCurrentConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String json = getFlexiGridJson(db, fg, tableName,cellName,sortname);
 
		response.getWriter().write(json);
		response.getWriter().flush();
		response.getWriter().close();

	}

	/**
	 * @param db
	 * @param fg
	 * @param tableName
	 * @return
	 */
	public abstract String getFlexiGridJson(DBConnection db, FlexiGridDomain fg,
			String tableName,String[] cellName,String sortname) ;

	/**
	 * 
	 * @param list
	 * @param pageInfo
	 * @return
	 */
	public abstract String toJSON(List list, Map pageInfo,String[] cellName,String sortname);

}
