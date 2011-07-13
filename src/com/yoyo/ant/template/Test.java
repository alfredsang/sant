package com.yoyo.ant.template;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.IntrospectionHelper;
import org.apache.tools.ant.Location;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
import org.apache.tools.ant.RuntimeConfigurable;
import org.apache.tools.ant.Target;
import org.apache.tools.ant.Task;
import org.apache.tools.ant.TypeAdapter;
import org.apache.tools.ant.TaskContainer;
import org.apache.tools.ant.UnknownElement;
import org.apache.tools.ant.util.FileUtils;
import org.apache.tools.ant.util.JAXPUtils;
import org.xml.sax.AttributeList;
import org.xml.sax.DocumentHandler;
import org.xml.sax.HandlerBase;
import org.xml.sax.InputSource;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.XMLReaderAdapter;

public class Test {

	public Test() {
		super();

		System.out.println("sss");
		// TODO Auto-generated constructor stub
	}

	private List targetsNameByBuildList = null;

	public List getTargetsNameByBuildList() {
		return targetsNameByBuildList;
	}

	public void setTargetsNameByBuildList(List targetsNameByBuildList) {
		this.targetsNameByBuildList = targetsNameByBuildList;
	}

	/**
	 * List All Task Property Info by mytaskName
	 * 
	 * @return taskPropertiesList
	 */
	public void getTargetsNameByBuild(String dir) {

		System.out.println("1=" + dir);

		targetsNameByBuildList = new ArrayList<String>();

		// ;

		// p.init();
		// System.out.println("2="+"init");
		ProjectHelper helper = ProjectHelper.getProjectHelper();
		//
		File buildFile = new File(dir);
		//
		helper.configureProject(new Project(), buildFile);
		// Iterator it = p.getTargets().entrySet().iterator();
		// while (it.hasNext()) {
		// Map.Entry e = (Map.Entry) it.next();
		//
		// String pName = e.getKey().toString();
		// String pValue = e.getValue().toString();
		//
		// if (pName.length() > 0) {
		//
		// System.out.println("Key = " + e.getKey() + ", Value = "
		// + e.getValue());
		// targetsNameByBuildList.add(pName);
		// }
		//
		// // p.setProperty(pName, pValue);
		//
		// }

		// return targetsNameByBuildList;
	}

	 /**
     * helper for path -> URI and URI -> path conversions.
     */
    private static final FileUtils FILE_UTILS = FileUtils.getFileUtils();

    /**
     * SAX 1 style parser used to parse the given file. This may
     * in fact be a SAX 2 XMLReader wrapped in an XMLReaderAdapter.
     */
    private org.xml.sax.Parser parser;

    /** The project to configure. */
    private Project project;

    /** The configuration file to parse. */
    private File buildFile;

    /**
     * Parent directory of the build file. Used for resolving entities
     * and setting the project's base directory.
     */
    private File buildFileParent;

    /**
     * Locator for the configuration file parser.
     * Used for giving locations of errors etc.
     */
    private Locator locator;
    
    
	/**
	 * Parses the project file, configuring the project as it goes.
	 * 
	 * @param project
	 *            project instance to be configured.
	 * @param source
	 *            the source from which the project is read.
	 * @exception BuildException
	 *                if the configuration is invalid or cannot be read.
	 */
	public void parse(Project project, Object source) throws BuildException {
		if (!(source instanceof File)) {
			throw new BuildException("Only File source supported by "
					+ "default plugin");
		}
		File bFile = (File) source;
		FileInputStream inputStream = null;
		InputSource inputSource = null;

		this.project = project;
		this.buildFile = new File(bFile.getAbsolutePath());
		buildFileParent = new File(this.buildFile.getParent());

		try {
			try {
				parser = JAXPUtils.getParser();
			} catch (BuildException e) {
				parser = new XMLReaderAdapter(JAXPUtils.getXMLReader());
			}
			String uri = FILE_UTILS.toURI(bFile.getAbsolutePath());
			inputStream = new FileInputStream(bFile);
			inputSource = new InputSource(inputStream);
			inputSource.setSystemId(uri);
			project.log("parsing buildfile " + bFile + " with URI = " + uri,
					Project.MSG_VERBOSE);
//			HandlerBase hb = new RootHandler(this);
//			parser.setDocumentHandler(hb);
//			parser.setEntityResolver(hb);
//			parser.setErrorHandler(hb);
//			parser.setDTDHandler(hb);
//			parser.parse(inputSource);
			
		} catch (IOException exc) {
			throw new BuildException("Error reading project file: "
					+ exc.getMessage(), exc);
		} finally {
			FileUtils.close(inputStream);
		}
	}

	
}
