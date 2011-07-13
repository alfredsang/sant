package com.yoyo.ant.template;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;


public class SetExecuteTarget extends AntTargetTemplate {

	public SetExecuteTarget() {
		// super(execDir, buildFileName);
		targetParamMap.put("name", "sang.shilong");
		targetNameVector.add("test");
		buildFileName = "build.xml";
	}

	public SetExecuteTarget(String rootDir) {
		super.rootDir = rootDir;
	}
	/**
	 * 构造函数
	 */
	public  SetExecuteTarget setTarget(String execDir, String buildFileName,Map targetParamMap,Vector targetNameVector ) {
		super.execDir = execDir;
		super.buildFileName = buildFileName;
		super.targetNameVector = targetNameVector;
		super.targetParamMap = targetParamMap;
		//targetNameVector.add("test1");
		return this;
	}
	
	/**
	 * 构造函数
	 */
	public SetExecuteTarget setTarget(File buildFile,Map targetParamMap,Vector targetNameVector ) {
		//super(execDir, buildFileName);
		
		System.out.println(super.rootDir);
		
		super.targetNameVector = targetNameVector;
		super.targetParamMap = targetParamMap;
		//targetNameVector.add("test1");
		return this;
	}

	@Override
	public void targetConfig() {
		// System.out.println("config method");

		if (!targetParamMap.isEmpty()) {
			Iterator it = targetParamMap.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry e = (Map.Entry) it.next();

//				System.out.println("Key = " + e.getKey() + ", Value = "
//						+ e.getValue());

				String pName = e.getKey().toString();
				String pValue = e.getValue().toString();

				p.setProperty(pName, pValue);

			}
		}

//		Iterator tasks = p.getCopyOfTaskDefinitions().keySet().iterator();
//		while (tasks.hasNext()) {
//			String tName = (String) tasks.next();
//		 
//			
//			if(tName.equalsIgnoreCase("echo")){
//				//System.out.println(tName);
//				
//				Class t = (Class) p.getTaskDefinitions().get(tName);
//				//System.out.println(t.getName()+"--classname");
//				
//				Field[] f = t.getDeclaredFields();
//				
//				System.out.println(f.length);
//				for (int i = 0; i < f.length; i++) {
//					//System.out.println(f[i].getName());
//				}
//				
//			}
//			
//		}

		// Vector<String> vector = new Vector<String>();
		// vector.add("test");
		// vector.add("test1");
		p.executeTargets(targetNameVector);
	}

	public static void main(String[] args) {
	 

	}
	
	

}
