package com.yoyo.ant;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.Target;

public class AntUtil {

	// build.xml所在路径,默认为根路径
	String execDir = this.getClass().getResource("/").getPath().toString();

	// build文件名，默认是build.xml
	String buildFileName = "build.xml";
	
	// Ant中要执行的target名称。
	String target = "";

	File buildFile = null;

	// 创建一个 ANT 项目
	Project	p = new Project();
	
	/**
	 * 构造方法
	 */
	public AntUtil() {
		init();
	}
	
	/**
	 * 构造方法
	 * @param buildFileName ant的配置文件名称
	 */
	public AntUtil(String buildFileName) {
		this.buildFileName = buildFileName;
		init();
	}
	
	/**
	 * 构造方法
	 * @param path
	 * @param buildFileName
	 */
	public AntUtil(String path,String buildFileName) {
		this.execDir = path;
		this.buildFileName = buildFileName;
		init();
	}
	
	
	public void  init() {
		// 创建一个默认的监听器 , 监听项目构建过程中的日志操作
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		p.addBuildListener(consoleLogger);
		//p.executeTarget(p.getDefaultTarget());

		buildFile = new File(this.execDir+this.buildFileName);
	}
	
	public void executeAntTarget(String targetName) {

		try {
			p.fireBuildStarted();
			// 初始化该项目
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			// 解析项目的构建文件
			helper.parse(p, buildFile);

		
			// p.setUserProperty("name", "sss");
			p.setNewProperty("name", "ssss");
			// p.setProperty("name", "world");

			// 执行项目的某一个目标
			p.executeTarget(targetName);

			p.fireBuildFinished(null);
		} catch (BuildException be) {
			p.fireBuildFinished(be);
		}
	}
	
	
	public void executeAntTargetWithParams(String targetName,Map<String, String> paramMap) {

		try {
			p.fireBuildStarted();
			// 初始化该项目
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			// 解析项目的构建文件
			helper.parse(p, buildFile);


			if(!paramMap.isEmpty()){ 
			    Iterator it = paramMap.entrySet().iterator();
			    while(it.hasNext()) {
			      Map.Entry e = (Map.Entry)it.next();
			      System.out.println("Key = " + e.getKey() +
			        ", Value = " + e.getValue());
			      
			      String pName =  e.getKey().toString();
			      String pValue =  e.getValue().toString();
			      
			      p.setProperty(pName, pValue);
			      
			    }
			}

		
			// p.setUserProperty("name", "sss");
			//p.setNewProperty("name", "ssss");
			// p.setProperty("name", "world");

			// 执行项目的某一个目标
			p.executeTarget(targetName);

			p.fireBuildFinished(null);
		} catch (BuildException be) {
			p.fireBuildFinished(be);
		}
	}

	/**
	 * 
	 *  AntUtils a = new AntUtils();
	 *	
	 *	Vector vector = new Vector<String>();
	 *	vector.add("test");
	 *	vector.add("test1");
	 *	
	 *	a.executeAntTargets(vector);
	 * 
	 * @param targets
	 */
	public void executeAntTargets(Vector<String> targetVector) {

		try {
			p.fireBuildStarted();
			// 初始化该项目
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			// 解析项目的构建文件
			helper.parse(p, buildFile);
		
			// p.setUserProperty("name", "sss");
			p.setNewProperty("name", "ssss");
			// p.setProperty("name", "world");

			// 执行项目的某一个目标
			//p.executeTarget(p.getDefaultTarget());
			p.executeTargets(targetVector);

			// p.executeTarget("test");

			p.fireBuildFinished(null);
		} catch (BuildException be) {
			p.fireBuildFinished(be);
		}
	}
	
	
	public static void main(String[] args) {

		AntUtil a = new AntUtil();
		
//		Vector vector = new Vector<String>();
//		vector.add("test");
//		vector.add("test1");
//		
//		a.executeAntTargets(vector);
		

		Map map =new HashMap<String,String>();
		map.put("name", "sang");
		a.executeAntTargetWithParams("test1",map);
	}
	
	
}
