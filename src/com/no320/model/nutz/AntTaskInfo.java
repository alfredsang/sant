package com.no320.model.nutz;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Name;
import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.json.Json;

import com.yoyo.ant.servlet.GetAntTargetListByFileName;
import com.yoyo.ant.servlet.GetAntTaskNames;

/*
 * hsqldb
 CREATE TABLE t_person (
 id  identity NOT NULL  PRIMARY KEY,
 name  VARCHAR(50) NOT NULL ,
 age INT,
 CONSTRAINT UNIQUE_NAME UNIQUE (name)
 );
 */

@Table("TB_SANT_TASK_INFO")
// 声明了Person对象的数据表
public class AntTaskInfo {
	@Column
	// 表示该对象属性可以映射到数据库里作为一个字段
	@Id
	// 表示该字段为一个自增长的Id
	private int id;

	@Column
	@Name
	// 表示该字段可以用来标识此对象，或者是字符型主键，或者是唯一性约束
	private String name;

	@Column
	private String desc;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public List<AntTaskInfo> getAntTaskByName(String name) {

		Ioc ioc = new NutIoc(new JsonLoader("ioc/dao.js"));
		DataSource dataSource = ioc.get(BasicDataSource.class, "dataSource");

		Dao dao = new NutDao(dataSource);

		List<AntTaskInfo> p = dao.query(AntTaskInfo.class,
				Cnd.where("name", "like", ""+name+"%").asc("name"), null);

		System.out.println(p.size());
		System.out.println(p.get(0).getName());

		return p;
	}
	
	
	public QueryResult getAntTaskListByName(int pageNumber, int pageSize,String name){
		Ioc ioc = new NutIoc(new JsonLoader("ioc/dao.js"));
		DataSource dataSource = ioc.get(BasicDataSource.class, "dataSource");
		Dao dao = new NutDao(dataSource);
		
		Pager pager = dao.createPager(pageNumber, pageSize);
		//List<AntTaskInfo> list = dao.query(AntTaskInfo.class, null, pager);

		List<AntTaskInfo> list = dao.query(AntTaskInfo.class,
				Cnd.where("name", "like", ""+name+"%").asc("name"), pager);
		
		pager.setRecordCount(dao.count(AntTaskInfo.class));
		
		return new QueryResult(list, pager);
	}

	public static void main(String[] args) {
		
		QueryResult qr = new AntTaskInfo().getAntTaskListByName(0, 2, "c");

		//System.out.println(qr.get);
		for (int i = 0; i < qr.getList().size(); i++) {
			AntTaskInfo ati = (AntTaskInfo)qr.getList().get(i);
			System.out.println(ati.getName());
		}
		
		System.out.println(Json.toJson(qr));
		
//		Ioc ioc = new NutIoc(new JsonLoader("ioc/dao.js"));
//		DataSource dataSource = ioc.get(BasicDataSource.class, "dataSource");
//
//		Dao dao = new NutDao(dataSource);
//		// Person p = new Person();
//		// p.setName("ABC1");
//		// p.setAge(20);
//		// dao.insert(p);
//
//		List<AntTaskInfo> p = dao.query(AntTaskInfo.class,
//				Cnd.where("name", "like", "AB%"), null);
//
//		System.out.println(p.size());
//		System.out.println(p.get(0).getName());

	}
}
