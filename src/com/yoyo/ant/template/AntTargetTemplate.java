package com.yoyo.ant.template;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.Vector;

import org.apache.catalina.tribes.util.UUIDGenerator;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.XmlLogger;
import org.apache.tools.ant.listener.AnsiColorLogger;

/**
 * Ant Target Template
 * 
 * @author sang.shilong@yoyosys.com
 */
public abstract class AntTargetTemplate {

	protected Project p = null;
	protected File buildFile = null;

	protected String baseDir = "";
	
	private static final String PROJECTNAME = "sant";
	// sant/
	protected String rootDir = "";

	// build.xml所在路径,默认为根路径
	protected String execDir = this.getClass().getResource("/").getPath()
			.toString();

	// build文件名，默认是build.xml
	protected String buildFileName = "build.xml";

	protected String uuid = "";
	
	/*about ant target setting*/
	protected Map<String, String> targetParamMap = new HashMap<String, String>();
	protected Vector<String> targetNameVector = new Vector<String>();

	
	/*about log setting*/
	protected int logLevel = Project.MSG_INFO;
	protected String logFileName = "";
	protected String logDir = "";
	protected boolean logAppend = false;

	public AntTargetTemplate() {
		super();
	}

	/**
	 * 不改baseDir
	 * 
	 * @param execDir
	 *            执行build.xml位置
	 * @param buildFileName
	 *            构建文件名称
	 */
	public AntTargetTemplate(String execDir, String buildFileName) {
		if (execDir.length() > 0) {
			this.execDir = execDir;
		}

		if (buildFileName.length() > 0) {
			this.buildFileName = buildFileName;
		}
		
		setRootDir();
	}

	private void setRootDir(){
		String path = getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		
		int lastFlagNumber = path.indexOf(this.PROJECTNAME);
		
		path = path.substring(0, lastFlagNumber) + this.PROJECTNAME;
		
		this.rootDir = path;
	}
	/**
	 * 强制设置baseDir
	 * 
	 * @param baseDir
	 *            强制设置baseDir
	 * @param execDir
	 *            执行build.xml位置
	 * @param buildFileName
	 *            构建文件名称
	 */
	public AntTargetTemplate(String baseDir, String execDir,
			String buildFileName) {
		if (baseDir.length() > 0) {
			this.baseDir = baseDir;
		}

		if (execDir.length() > 0) {
			this.execDir = execDir;
		}

		if (buildFileName.length() > 0) {
			this.buildFileName = buildFileName;
		}
		
		setRootDir();
	}

	/**
	 * 设置build.xml相对路径baseDir
	 */
	private void setMyBaseDir() {
		if (baseDir.length() > 0) {
			p.setBaseDir(new File(baseDir));
		}
	}

	/**
	 * 设置日志内容
	 * 
	 * @param p
	 * @param logDir
	 * @return
	 * @throws IOException
	 */
	private String log(Project p,String logDir) throws IOException {
		//XmlLogger consoleLogger = new XmlLogger();

		DefaultLogger consoleLogger = new DefaultLogger();
		
		File log = null;
		String mylogName = "";
		String mylogdir = "";

		if (logFileName.length() > 0) {
			mylogName = logFileName + ".log";
		} else {
			mylogName = uuid + ".log";
		}
		
		if (logDir.length() > 0) {
			this.logDir = logDir;
			
			//
			if(mylogdir.substring((mylogdir.length()-1),mylogdir.length()).equals("/")){
				mylogdir = this.logDir;
			}else{
				mylogdir = this.logDir+"/";
			}
		}else {
			//
			if(this.rootDir.substring((this.rootDir.length()-1),this.rootDir.length()).equals("/")){
				System.out.println("");
				mylogdir =  this.rootDir +"ant/log/";
			}else{
				mylogdir =  this.rootDir +"/ant/log/";
			}
		}

		System.out.println(this.rootDir);
		System.out.println("LOG:"+mylogdir + "" + mylogName);
		
		
	
		
	 	File dir = new File(mylogdir);
	 	dir.mkdirs();
		
		log = new File(mylogdir + "" + mylogName);
		if (!log.exists()) {
			log.createNewFile();
		}else{
			log.createNewFile();
		}

		consoleLogger.setMessageOutputLevel(this.logLevel);
		consoleLogger.setErrorPrintStream(new PrintStream(log));
		consoleLogger.setOutputPrintStream(new PrintStream(log));
		
		p.addBuildListener(consoleLogger);
		
		return  "/"+ mylogName;
	}

	/**
	 * 对外暴露接口 核心方法
	 * @return 
	 * 
	 * @throws IOException
	 */
	public String execute() throws IOException {

		System.out.println(this.rootDir);
		
		uuid = new UUIDGenerator().randomUUID(true).toString();

		p = new Project();
		// System.out.println(this.getExecDir() + this.getBuildFileName());
		// System.out.println(buildFileName.toString());
		buildFile = new File(this.getExecDir() + this.getBuildFileName());

		String logName = this.log(p,"");

		
		System.out.println(logName+"=======");
		
		
		try {
			p.fireBuildStarted();
			// 初始化该项目
			p.init();
			ProjectHelper helper = ProjectHelper.getProjectHelper();

			// System.out.println(buildFileName.toString());

			// 解析项目的构建文件
			helper.parse(p, buildFile);

			// 设置build.xml相对路径
			setMyBaseDir();

			// 设置执行的target
			targetConfig();

			p.fireBuildFinished(null);

		} catch (BuildException be) {
			p.fireBuildFinished(be);
		}
		System.out.println(logName+"=======");
		
		return logName;
	}

	/**
	 * 需要子类实现的方法 如果要设置变量，请在子类的构造方法中进行设置
	 */
	public abstract void targetConfig();

	/* setting or getting method */

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

	public String getBaseDir() {
		return baseDir;
	}

	public void setBaseDir(String baseDir) {
		this.baseDir = baseDir;
	}

	
	
	public String getRootDir() {
		return rootDir;
	}

	public void setRootDir(String rootDir) {
		this.rootDir = rootDir;
	}

	public static void main(String[] args) {
		GetMyTaskInfo t = new GetMyTaskInfo();
		String dir = t.getClass().getResource("/").getPath().toString();

		//System.out.println(dir);

		Map<String, String> targetParamMap = new HashMap<String, String>();
		Vector<String> targetNameVector = new Vector<String>();

		targetParamMap.put("name", "saa");
		targetNameVector.add("test");

		try {
			new SetExecuteTarget("/").setTarget(dir, "build.xml", targetParamMap,
					targetNameVector).execute();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
