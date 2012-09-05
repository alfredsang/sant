package com.yoyo.ant.servlet;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.yoyo.ant.template.SetExecuteTarget;

/**
 * Servlet implementation class AntServlet
 */
@WebServlet(name = "AntServlet", urlPatterns = { "/servlet/ant" })
public class AntServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AntServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int methodName = Integer.parseInt(request.getParameter("method")
				.toString());

		switch (methodName) {
		case 100001:
			//getConfigFilePath(request, response);
			//break;
		case 100002:
			getAllTargets(request, response);
			break;

		default:
			break;
		}
	}

	public String getWebClassesPath(String projectName) {
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		int lastFlagNumber = path.indexOf(projectName);

		path = path.substring(0, lastFlagNumber) + projectName;
		//System.out.println(path);
		return path;

	}

	/**
	 * 100001
	 * 
	 * @param request
	 * @param response
	 */
	public void getConfigFilePath(HttpServletRequest request,
			HttpServletResponse response) {
		String dir = getWebClassesPath(request.getContextPath())
				+ "/ant/build.xml";
		// String dir = request.getContextPath()+"/ant/";
		System.out.println(dir);
		File f = new File(dir);

		System.out.println(f.isFile());

		Long a = f.getTotalSpace();
		System.out.println(a);

		// System.out.println(request.);
		Map m = new HashMap();
		m.put("dir", dir);

		JSONObject json = new JSONObject(m);

		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

 

	/**
	 * 100002
	 * 
	 * Test URL:
	 * 		http://127.0.0.1:8080/sant/servlet/ant?method=100002&target=test
	 * 
	 * @param request
	 * @param response
	 */
	public void getAllTargets(HttpServletRequest request,
			HttpServletResponse response) {
		
		///String mytarget = request.getParameter("target").length()>0?request.getParameter("target"):"test";
		//System.out.println(mytarget);
		
		String rootDir = getWebClassesPath(request.getContextPath());
		String dir = rootDir+ "/ant/build.xml";
//		System.out.println(dir);
		System.out.print("-----------------------------------current target is ");
		System.out.print(request.getParameter("target"));
		System.out.print("-----------------------------------");
		
		Map<String, String> targetParamMap = new HashMap<String, String>();
		Vector<String> targetNameVector = new Vector<String>();

		File f = new File(dir);
 
		String mylogname= "" ;
		
		String targ = request.getParameter("target").toString();
		
		if(targ.contains("_")){
			String aa[]=targ.split("_");

			for (int i = 0; i < aa.length; i++) {
				targetNameVector.add(aa[i]);
			}
			
		}else{
			targetNameVector.add(targ);
		}
		
		
		try {
			targetParamMap.put("name", "saa");

			
			mylogname = new SetExecuteTarget(rootDir).setTarget(f, targetParamMap, targetNameVector).execute(); 
			//System.out.println(mylogname+"--mylogname");
			//new SetExecuteTarget(rootDir).setTarget(f, targetParamMap, targetNameVector).execute();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Map m = new HashMap();
		m.put("mylogname","/ant/log"+ mylogname);

		
		
		
		//String logName = "http://127.0.0.1:8080/sant/ant/log/[B@40d1e07c.log";
		 
		
		JSONObject json = new JSONObject(m);

		try {
			response.getWriter().write(json.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static void main(String[] args) {

	}

}
