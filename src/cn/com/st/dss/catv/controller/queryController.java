package cn.com.st.dss.catv.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import cn.com.st.dss.flexigrid.custom.MyCommonFlexiGrid;
import cn.com.st.dss.jdbc.DBConnection; 


/**
 * Servlet implementation class queryController
 * 
 * @author sangsl@si-tech.com.cn
 * @since 2010-08-05
 * 
 */
public class queryController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	Log logger = LogFactory.getLog(queryController.class);
	
	private DBConnection db = null;
	
	
 
	public void init(ServletConfig config) throws ServletException {
		
		super.init(config);
		
		String sValue = config.getInitParameter("s");
		System.out.println(sValue);
		
	}


	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public queryController() {
		db = new DBConnection();//
		try {
			db.getCurrentConnection();
		} catch (SQLException e1) {
		}
	}

	 
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 *      
	 *     通过url传过来的参数， new String(request.getParameter("qString").getBytes("iso8859-1"), "utf-8")
	 *     jquery异步ajax提交的 UrlDecoderUtils.getDecoderValue(request,"querySql")
	 *     
	 *     var param = [
						 { name: 'page', value: p.newp }
						, { name: 'rp', value: p.rp }
						, { name: 'sortname', value: p.sortname }
						, { name: 'sortorder', value: p.sortorder }
						, { name: 'query', value: p.query }
						, { name: 'qtype', value: p.qtype }
						, { name: 'qop', value: p.qop }
						, { name: 'querySql', value: p.querySql }
					];
	 *     
	 *     
	 *      
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String action = "";
		if (request.getParameter("action") != null) {
			action = request.getParameter("action");
		}

		if (action.equalsIgnoreCase("list")) {

			logger.info("flexigrid的queryController开始接收请求");
			
			/*获取参数*/
			//
			String querySql = new String(request.getParameter("querySql").getBytes("iso8859-1"),"utf-8");
			//String querySql =request.getParameter(
					//"querySql");
			// 如果前台有@,在此处将还原为'
			querySql = querySql.replaceAll("@", "'");
			String qString = new String(request.getParameter("qString")
					.getBytes("iso8859-1"), "utf-8");
			
			
			
			String cellName[] = qString.trim().split("@");
//			String sortname = request.getParameter(
//					"sortname");
			
			String sortname = new String(request.getParameter("sortname").getBytes("iso8859-1"),"utf-8");

			logger.info("查询语句: " + querySql); 
			logger.info("查询表头qString: " + qString); 
			logger.info("查询排序字段sortname: " + sortname);
			/*end获取参数*/
	 
		
			
			/*核心部分*/
			try {
				logger.info("进入flexigrid处理的模板模式" ); 
				new MyCommonFlexiGrid(request, response, querySql, cellName,
						sortname);
			} catch (Exception e) {
				logger.info("在querController的102行new WorkspaceList()的时候出现异常"); 
				request.setAttribute("exception_msg", "processList error~~~");
			}
			/*end核心部分*/

		} else {
			logger.info("url中没有匹配的action，请仔细核对"); 
		}

	}

 
}
