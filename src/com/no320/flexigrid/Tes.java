package com.no320.flexigrid;

import org.nutz.json.Json;

import com.no320.model.nutz.AntTaskInfo;

public class Tes {

	public static void main(String[] args) {
		
		

		String name = "";
		
		String queryConditon= "name like '" + name + "%' order by name desc";
		
		
		NutzFlexiGridUtils nf = new NutzFlexiGridUtils(AntTaskInfo.class, queryConditon, 1, 200);

		String jsonStr = Json.toJson(nf.getNutzFlexiGridQueryResultByName(queryConditon));
		
		System.out.println(jsonStr);
	}
}
