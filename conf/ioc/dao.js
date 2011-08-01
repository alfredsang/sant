var ioc = {
		dataSource : {
                type : "org.apache.commons.dbcp.BasicDataSource",
                events : {
                        depose : 'close'
                },
                fields : {
                        driverClassName : 'org.hsqldb.jdbcDriver',
                        url : 'jdbc:hsqldb:hsql://localhost:9001/testdb',
                        username : 'sa',
                        password : ''
                }
        },
        /*定义NutDao*/
        dao : {
        		type : "org.nutz.dao.impl.NutDao",
        		fields : {
        				dataSource : {refer : 'dataSource'}
        		}
        },
        /*定义NutzFlexiGridUtilsDao*/
        dao : {
        		type : "org.nutz.dao.impl.NutDao",
        		fields : {
        				dataSource : {refer : 'dataSource'}
        		}
        }
};