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

public class AntTargetConfig {

	// build.xml所在路径,默认为根路径
	private String execDir = this.getClass().getResource("/").getPath().toString();


	// build文件名，默认是build.xml
	private String buildFileName = "build.xml";
	
	// Ant中要执行的target名称。
	private String target = "";
	
	private Map<String, String> targetParamMap = new HashMap<String, String>();

	
	 
	



	/* setting or getting method */

	public AntTargetConfig() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Map<String, String> getTargetParamMap() {
		return targetParamMap;
	}

	public void setTargetParamMap(Map<String, String> targetParamMap) {
		this.targetParamMap = targetParamMap;
	}
	
	public String getExecDir() {
		return execDir;
	}

	public void setExecDir(String execDir) {
		this.execDir = execDir;
	}

	public String getBuildFileName() {
		return buildFileName;
	}

	public void setBuildFileName(String buildFileName) {
		this.buildFileName = buildFileName;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}
 
	
	
	
}
