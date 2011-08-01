package com.no320.flexigrid;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;

import com.no320.model.nutz.AntTaskInfo;

public class NutzFlexiGridUtils {

	
	//entity class name,if need use apache dynamic bean.
	public Class domainClassName = null;
 
	 
	Ioc ioc = new NutIoc(new JsonLoader("ioc/dao.js"));
	DataSource dataSource = ioc.get(BasicDataSource.class, "dataSource");
	//Dao dao = new NutDao(dataSource);
	
	
	//ioc注入NutzDao，含数据源
	public Dao dao = new NutDao(dataSource);
	//Pager object,use to constuctor
	public Pager pager = null;
	//where a=b order by a desc;
	public String queryConditon = "";
	
	
	
	/*
	 * constructor
	 * param queryConditon=where a=b order by a desc;
	 * 
	 */
	
	
	public NutzFlexiGridUtils( Class domainClassName,String queryConditon,int pageNumber,int pageSize) {
		pager = dao.createPager(pageNumber, pageSize);
		
		this.queryConditon = queryConditon;
		this.domainClassName = domainClassName;
	}
	
	
	
	
	
	public NutzFlexiGridUtils() {
		// TODO Auto-generated constructor stub
	}





	public Dao getDao() {
		return dao;
	}

	public void setDao(Dao dao) {
		this.dao = dao;
	}

	public Class getdomainClassName() {
		return domainClassName;
	}

	public void setdomainClassName(Class domainClassName) {
		this.domainClassName = domainClassName;
	}

	 

	public Pager getPager() {
		return pager;
	}

	public void setPager(Pager pager) {
		this.pager = pager;
	}
 

	/**
	 * 
	 * @param dao
	 * @param clz
	 * @param queryConditon
	 * @param pager
	 * @return QueryResult
	 */
	public  QueryResult getNutzFlexiGridQueryResultByName( String queryConditon) {

		/*
		 * queryConditon need order info
		 */
		List list = dao.query(this.domainClassName, Cnd.wrap(queryConditon), this.pager);

		this.pager.setRecordCount(dao.count(this.domainClassName));

		return new QueryResult(list, this.pager);
	}

 
	
	public void printResult() {
		QueryResult qr = getNutzFlexiGridQueryResultByName(this.queryConditon);
		for (int i = 0; i < qr.getList().size(); i++) {
			AntTaskInfo ati = (AntTaskInfo)qr.getList().get(i);
			System.out.println(ati.getName());
			
		}
	}


	/**
	 * 通过cell的描述信息，返回list，这个和普通的flexigrid的jdbc走法，基本类似。只是查询改成厦门心事
	 * @param domainClassName
	 * @param queryConditon
	 * @param pageNumber
	 * @param pageSize
	 * @return 
	 */
	public List getListBySimpleSql(String mysql){

		Sql sql = Sqls.create(mysql);
		//sql.params().set("name", "A%");
		sql.setCallback(new SqlCallback() {
			
			@Override
			public Object invoke(Connection arg0, ResultSet rs, Sql sql)
					throws SQLException {
				List alResult = new ArrayList();
				while (rs.next()){
					 
					ResultSetMetaData rsmd = rs.getMetaData();
					int count = rsmd.getColumnCount();
					String colum[] = new String[count];
					for (int i = 0; i < colum.length; i++)
						if (rsmd.getColumnName(i + 1) != null)
							colum[i] = rsmd.getColumnName(i + 1);
						else
							colum[i] = rsmd.getColumnLabel(i + 1);

					HashMap row = null;
					String fieldValue = null;
					for (; rs.next(); alResult.add(row)) {
						row = new HashMap();
						for (int i = 0; i < colum.length; i++) {
							int iType = rsmd.getColumnType(i + 1);
							if (iType == 2 || iType == 3) {
								if (rsmd.getScale(i + 1) == 0)
									fieldValue = String.valueOf(rs.getLong(i + 1));
								else
									fieldValue = rs.getString(i + 1);
							} else if (iType == 8)
								fieldValue = String.valueOf(rs.getDouble(i + 1));
							else if (iType == 6 || iType == 7)
								fieldValue = String.valueOf(rs.getFloat(i + 1));
							else
								fieldValue = rs.getString(i + 1);
							if (fieldValue == null)
								fieldValue = "";
							else
								fieldValue = fieldValue.trim();

							row.put(colum[i], fieldValue);// .toLowerCase()
						}
					}
				}
					
				return alResult;
			}
		}
		);	
		
		dao.execute(sql);
		return null;
	}
	
	public void aa() {
		
	}
	
	public static void main(String[] args) {
		Ioc ioc = new NutIoc(new JsonLoader("ioc/dao.js"));
		DataSource dataSource = ioc.get(BasicDataSource.class, "dataSource");
		Dao dao = new NutDao(dataSource);
		
		String name = "c";
		
		String queryConditon= "name like '" + name + "%' order by name desc";
		int pageNumber = 5;
		int pageSize = 2;
		
		
		
		NutzFlexiGridUtils nf = new NutzFlexiGridUtils();
		String sql = "SELECT * FROM TB_SANT_TASK_INFO  WHERE name like 'cc%' order by name desc";
		final String[] cellName = new String[]{"id","name","desc"};
		List l = nf.getListBySimpleSql(sql);
		
		System.out.println(l.size());
		
		for (int i = 0; i < l.size(); i++) {
			HashMap<String, String> a = (HashMap)l.get(i);
			System.out.println(a.get("name"));
		 
			
		}
		//nf.printResult();

	}
}
