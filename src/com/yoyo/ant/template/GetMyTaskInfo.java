package com.yoyo.ant.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

public class GetMyTaskInfo {

	public List taskNamesList = null;
	public List taskPropertiesList = null;
	public List targetsNameByBuildList = null;
	private String taskName = "";

	public List getTaskPropertiesList() {
		return taskPropertiesList;
	}

	public void setTaskPropertiesList(List taskPropertiesList) {
		this.taskPropertiesList = taskPropertiesList;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public void setTaskNamesList(List taskNamesList) {
		this.taskNamesList = taskNamesList;
	}

	/**
	 * List All Task Name Info
	 * 
	 * @return taskNamesList
	 */
	public List getTaskNamesList() {
		taskNamesList = new ArrayList<String>();
		Project p = new Project();
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		p.addBuildListener(consoleLogger);

		try {
			p.fireBuildStarted();
			// ÂàùÂßãÂåñËØ•È°πÁõÆ
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			// Ëß£ÊûêÈ°πÁõÆÁöÑÊûÑÂª∫Êñá‰ª∂
			// helper.parse(p, buildFile);

			Iterator tasks = p.getTaskDefinitions().keySet().iterator();
			while (tasks.hasNext()) {
				String tName = (String) tasks.next();
				taskNamesList.add(tName);
			}
			p.fireBuildFinished(null);

		} catch (BuildException be) {
			p.fireBuildFinished(be);
		}

		return taskNamesList;
	}

	/**
	 * List All Task Property Info by mytaskName
	 * 
	 * @return taskPropertiesList
	 */
	public List getTaskInfoByName(String mytaskName) {
		taskPropertiesList = new ArrayList<String>();

		Project p = new Project();
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		p.addBuildListener(consoleLogger);

		try {
			p.fireBuildStarted();
			// ÂàùÂßãÂåñËØ•È°πÁõÆ
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			// Ëß£ÊûêÈ°πÁõÆÁöÑÊûÑÂª∫Êñá‰ª∂
			// helper.parse(p, buildFile);

			Iterator tasks = p.getTaskDefinitions().keySet().iterator();
			while (tasks.hasNext()) {
				String tName = (String) tasks.next();

				if (tName.equalsIgnoreCase(mytaskName)) {
					Class t = (Class) p.getTaskDefinitions().get(tName);

					Field[] f = t.getDeclaredFields();

					System.out.println(tName + "{" + t.getName() + "}"
							+ "  all declaredFields count is:" + f.length);
					System.out
							.println("-----------------------------List properties----------------------------------");
					for (int i = 0; i < f.length; i++) {

						String taskProperty = processWord(f[i].getName());
						if (taskProperty.length() > 0) {
							taskPropertiesList.add(taskProperty);
						}

					}
					System.out
							.println("-------------------------------end list--------------------------------");

				}

			}

			p.fireBuildFinished(null);

		} catch (BuildException be) {
			p.fireBuildFinished(be);
		}

		return taskPropertiesList;
	}

	/**
	 * List All Task Property Info by mytaskName
	 * 
	 * @return taskPropertiesList
	 */
	public List getTaskInfoMapByName(String mytaskName) {
		taskPropertiesList = new ArrayList<String>();

		Project p = new Project();
		DefaultLogger consoleLogger = new DefaultLogger();
		consoleLogger.setErrorPrintStream(System.err);
		consoleLogger.setOutputPrintStream(System.out);
		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
		p.addBuildListener(consoleLogger);

		try {
			p.fireBuildStarted();
			// ÂàùÂßãÂåñËØ•È°πÁõÆ
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();
			// Ëß£ÊûêÈ°πÁõÆÁöÑÊûÑÂª∫Êñá‰ª∂
			// helper.parse(p, buildFile);

			Iterator tasks = p.getTaskDefinitions().keySet().iterator();
			while (tasks.hasNext()) {
				String tName = (String) tasks.next();

				if (tName.equalsIgnoreCase(mytaskName)) {
					Class t = (Class) p.getTaskDefinitions().get(tName);

					Field[] f = t.getDeclaredFields();

					System.out.println(tName + "{" + t.getName() + "}"
							+ "  all declaredFields count is:" + f.length);
					System.out
							.println("-----------------------------List properties----------------------------------");
					for (int i = 0; i < f.length; i++) {

						System.out.println(f[i].getType());
						String taskProperty = processWord(f[i].getName());
						if (taskProperty.length() > 0) {
							taskPropertiesList.add(taskProperty);
						}

					}
					System.out
							.println("-------------------------------end list--------------------------------");

				}

			}

			p.fireBuildFinished(null);

		} catch (BuildException be) {
			p.fireBuildFinished(be);
		}

		return taskPropertiesList;
	}

	/**
	 * getTaskInfoByName() utils
	 * 
	 * @param oldWord
	 * @return newWord
	 */
	public String processWord(String oldWord) {
		String newWord = oldWord;

		// Â§ßÂÜôÂ≠óÊØçÊó†Êïà
		Character firstChar = oldWord.charAt(0);
		if ((int) firstChar <= 90) {
			// System.out.println("Upper case");
			newWord = "";
		} else {

			if (oldWord.length() >= 5) {
				String firstFiveChar = oldWord.substring(0, 5);
				if (firstFiveChar.equals("class")) {
					// System.out.println(firstFiveChar + "==");
					newWord = "";
				} else {
					// System.out.println(firstFiveChar + "!=");
					newWord = oldWord;
				}
			}
		}

		System.out.println(newWord);
		return newWord;
	}

	/**
	 * List All Task Property Info by mytaskName
	 * 
	 * @return taskPropertiesList
	 */
	public List getTargetsNameByBuild(String dir) {
		targetsNameByBuildList = new ArrayList<String>();

		Project p = new Project();
//		DefaultLogger consoleLogger = new DefaultLogger();
//		consoleLogger.setErrorPrintStream(System.err);
//		consoleLogger.setOutputPrintStream(System.out);
//		consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
//		p.addBuildListener(consoleLogger);

		try {
			p.fireBuildStarted();
			// ÂàùÂßãÂåñËØ•È°πÁõÆ
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();

			File buildFile = new File(dir);

		
			
			// Ëß£ÊûêÈ°πÁõÆÁöÑÊûÑÂª∫Êñá‰ª∂
			helper.parse(p, buildFile);
			// p.getTargets().entrySet();
			Iterator it = p.getTargets().entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();
				

				String pName = e.getKey().toString();
				String pValue = e.getValue().toString();

				if (pName.length() > 0) {
					
					System.out.println("Key = " + e.getKey() + ", Value = "
						+ e.getValue());
					targetsNameByBuildList.add(pName);
				}

				// p.setProperty(pName, pValue);

			}

			p.fireBuildFinished(null);

		} catch (BuildException be) {
			p.fireBuildFinished(be);
		}

		return targetsNameByBuildList;
	}

	public static void main(String[] args) {
		GetMyTaskInfo t = new GetMyTaskInfo();

		String dir = t.getClass().getResource("/").getPath().toString()
		+ "build.xml";
		List l= t.getTargetsNameByBuild(dir);
		
		 for (int i = 0; i < l.size(); i++) {
			 System.out.println(l.get(i));
		 }
		
		
//		Character ss = 'Z';
//		// System.out.println((int) ss);
//		// t.execute();
//		// List l = t.getTaskInfoMapByName("echo");
//

//		 t.getTargetsNameByBuild(dir);
//		 List l =t.targetsNameByBuildList;
//		// t.processWord("Iclass");
//		// List l = t.getTaskNamesList();
//		// // System.out.println(l.size());
//		 for (int i = 0; i < l.size(); i++) {
//		 System.out.println(l.get(i));
//		 }
//		 String dir = t.getClass().getResource("/").getPath().toString();
//
//		 System.out.println(dir);
//		 
//
//		Map<String, String> targetParamMap = new HashMap<String, String>();
//		Vector<String> targetNameVector= new Vector<String>();
//		
//		targetParamMap.put("name", "saa");
//		targetNameVector.add("test");

//		try {
//			//new SetExecuteTarget(dir, "build.xml", targetParamMap, targetNameVector).execute();
//			
//			
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//		
		
		
	}

}
