package com.yoyo.ant.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.json.Json;

import com.no320.flexigrid.NutzFlexiGridUtils;
import com.no320.model.nutz.AntTaskInfo;
import com.yoyo.ant.template.GetMyTaskInfo;

/**
 * Servlet implementation class GetAntTaskNames
 */
@WebServlet("/GetAntTaskNames")
public class GetAntTaskNames extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAntTaskNames() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 实现分页，以json方式返回
	 * 
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String searchKey = request.getParameter("search");
//		int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
//		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
//		
//		String queryConditon= "name like '" + searchKey + "%' order by name desc";
//		
//		
//		NutzFlexiGridUtils nf = new NutzFlexiGridUtils(AntTaskInfo.class, queryConditon, 1, 20);
//		
//		String jsonStr = Json.toJson(nf.getNutzFlexiGridQueryResultByName(queryConditon));
		
		List l = new GetMyTaskInfo().getTaskNamesList();
		
		String jsonStr = Json.toJson(l);
		response.getWriter().write(jsonStr);
		response.getWriter().flush();
	 
	}

}
