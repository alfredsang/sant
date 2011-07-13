package com.yoyo.ant.servlet;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Out;

/**
 * Servlet implementation class TailLogServlet
 */
@WebServlet("/TailLogServlet")
public class TailLogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public TailLogServlet() {
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
		String logName = request.getParameter("logName");
		
		System.out.println(logName+"--+");
		String aa= new URLDecoder().decode(logName,"UTF-8");
		System.out.println(aa+"--+1");
		
		String dir = getWebClassesPath(request.getContextPath()) + logName;
		// String dir = request.getContextPath()+"/ant/";
		System.out.println(dir);
		File f = new File(dir);

		BufferedReader reader = new BufferedReader(new FileReader(f));
		// PrintWriter bw = new PrintWriter(new BufferedWriter(new
		// FileWriter(f)));

		
		PrintWriter out = response.getWriter();
		
		String str = "";
		while ((str = reader.readLine()) != null) {

			str = str.replace("<", "&lt");
			str = str.replace(">", "&gt");
	
			out.println(str);
			out.println("<br>");
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

	public static void main(String[] args) {

		String logName = "http://127.0.0.1:8080/sant/ant/log/[B@40d1e07c.log";
		File f = new File(logName);

		System.out.println(f.exists());

	}
}
