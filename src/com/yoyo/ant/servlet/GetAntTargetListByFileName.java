package com.yoyo.ant.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import com.yoyo.ant.template.GetMyTaskInfo;

/**
 * Servlet implementation class GetAntTargetListByFileName
 */
@WebServlet("/GetAntTargetListByFileName")
public class GetAntTargetListByFileName extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAntTargetListByFileName() {
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
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		

		String rootDir = getWebClassesPath(request.getContextPath());
		String dir = rootDir+ "/ant/build.xml";
		
		GetMyTaskInfo t = new GetMyTaskInfo();
 
		List l= t.getTargetsNameByBuild(dir);
		
		 for (int i = 0; i < l.size(); i++) {
			 //System.out.println(l.get(i));
		 }
		
		JSONArray ja = new JSONArray(l) ;
		 
		try {
			response.getWriter().write(ja.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public String getWebClassesPath(String projectName) {
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		int lastFlagNumber = path.indexOf(projectName);

		path = path.substring(0, lastFlagNumber) + projectName;
		// System.out.println(path);
		return path;

	}


}
