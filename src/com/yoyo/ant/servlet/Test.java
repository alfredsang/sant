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

import org.apache.tools.ant.Project;

import com.yoyo.ant.template.SetExecuteTarget;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Test() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getWebClassesPath(String projectName) {
		String path = getClass().getProtectionDomain().getCodeSource()
				.getLocation().getPath();
		int a = path.indexOf(projectName);

		path = path.substring(0, a) + projectName;
		System.out.println(path);
		return path;

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
		System.out.println("sss");

		String dir = getWebClassesPath(request.getContextPath())
				+ "/ant/build.xml";
		// String dir = request.getContextPath()+"/ant/";
		System.out.println(dir);
		File f = new File(dir);

		System.out.println(f.isFile());

		Long a = f.getTotalSpace();
		System.out.println(a);
		
		
		
		Map<String, String> targetParamMap = new HashMap<String, String>();
		Vector<String> targetNameVector= new Vector<String>();
		
		targetParamMap.put("name", "saa");
		targetNameVector.add("test");

	//	new SetExecuteTarget(f, targetParamMap, targetNameVector).execute(); 
		
		

	}

}
