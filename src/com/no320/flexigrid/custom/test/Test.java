package com.no320.flexigrid.custom.test;

import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.nutz.dao.Cnd;
import org.nutz.dao.Dao;
import org.nutz.dao.QueryResult;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import org.nutz.ioc.Ioc;
import org.nutz.ioc.impl.NutIoc;
import org.nutz.ioc.loader.json.JsonLoader;
import org.nutz.json.Json;

import com.no320.model.nutz.AntTaskInfo;

public class Test {

	public QueryResult getAntTaskListByName(Dao dao,int pageNumber, int pageSize,
			String name) {
		 
		Pager pager = dao.createPager(pageNumber, pageSize);
		// List<AntTaskInfo> list = dao.query(AntTaskInfo.class, null, pager);

		List<AntTaskInfo> list = dao.query(AntTaskInfo.class,
				Cnd.where("name", "like", "" + name + "%").asc("name"), pager);

		pager.setRecordCount(dao.count(AntTaskInfo.class));

		return new QueryResult(list, pager);
	}
	
	public QueryResult getAntTaskListByName(int pageNumber, int pageSize,
			String name) {
		Ioc ioc = new NutIoc(new JsonLoader("ioc/dao.js"));
		DataSource dataSource = ioc.get(BasicDataSource.class, "dataSource");
		Dao dao = new NutDao(dataSource);

		Pager pager = dao.createPager(pageNumber, pageSize);
		// List<AntTaskInfo> list = dao.query(AntTaskInfo.class, null, pager);

		List<AntTaskInfo> list = dao.query(AntTaskInfo.class,
				Cnd.where("name", "like", "" + name + "%").asc("name"), pager);

		pager.setRecordCount(dao.count(AntTaskInfo.class));

		return new QueryResult(list, pager);
	}

	public static void main(String[] args) {

		QueryResult qr = new AntTaskInfo().getAntTaskListByName(0, 2, "c");

		// System.out.println(qr.get);
		for (int i = 0; i < qr.getList().size(); i++) {
			AntTaskInfo ati = (AntTaskInfo) qr.getList().get(i);
			System.out.println(ati.getName());
		}

		System.out.println(Json.toJson(qr));

		// Ioc ioc = new NutIoc(new JsonLoader("ioc/dao.js"));
		// DataSource dataSource = ioc.get(BasicDataSource.class, "dataSource");
		//
		// Dao dao = new NutDao(dataSource);
		// // Person p = new Person();
		// // p.setName("ABC1");
		// // p.setAge(20);
		// // dao.insert(p);
		//
		// List<AntTaskInfo> p = dao.query(AntTaskInfo.class,
		// Cnd.where("name", "like", "AB%"), null);
		//
		// System.out.println(p.size());
		// System.out.println(p.get(0).getName());

	}

}
