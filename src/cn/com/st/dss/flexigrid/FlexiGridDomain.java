package cn.com.st.dss.flexigrid;

/**
 * 
 * @author sangsl@si-tech.com.cn
 * @since 2010-09-01
 * 
 *    詳見jquery.flexigrid.js第574行
 *     var param = [
					 { name: 'page', value: encodeURIComponent(p.newp) }
					, { name: 'rp', value: encodeURIComponent(p.rp) }
					, { name: 'sortname', value: encodeURIComponent(p.sortname) }
					, { name: 'sortorder', value: p.sortorder }
					, { name: 'query', value: encodeURIComponent(p.query) }
					, { name: 'qtype', value: encodeURIComponent(p.qtype )}
					, { name: 'qop', value: encodeURIComponent(p.qop) }
					, { name: 'querySql', value: encodeURIComponent(p.querySql) }
				];
 * 
 * 
 * 
 * 
 */
public class FlexiGridDomain {
	//current page,默认当前页
	int page;
	//每页默认的结果数
	int rp;
	//搜索查询的条件
	String query;
	//排序列名
	String sortname;
	// desc or asc    request.getParameter("sortorder")
	String sortorder;
	//搜索查询的类别
	String qtype;
	//搜索的操作符
	String qop;

	
	public String getQop() {
		return qop;
	}

	public void setQop(String qop) {
		this.qop = qop;
	}

	/**
	 * @return the query
	 */
	public String getQuery() {
		if (query == null) {
			return "";
		}
		return query;
	}

	/**
	 * @return the page
	 */
	public int getPage() {
		return page;
	}

	/**
	 * @param page the page to set
	 */
	public void setPage(int page) {
		this.page = page;
	}

	/**
	 * @return the rp
	 */
	public int getRp() {
		return rp;
	}

	/**
	 * @param rp the rp to set
	 */
	public void setRp(int rp) {
		this.rp = rp;
	}

	/**
	 * @param query
	 *            the query to set
	 */
	public void setQuery(String query) {
		this.query = query;
	}

	/**
	 * @return the qtype
	 */
	public String getQtype() {
		return qtype;
	}

	/**
	 * @param qtype
	 *            the qtype to set
	 */
	public void setQtype(String qtype) {
		this.qtype = qtype;
	}

	/**
	 * @return the sortname
	 */
	public String getSortname() {
		return sortname;
	}

	/**
	 * @param sortname
	 *            the sortname to set
	 */
	public void setSortname(String sortname) {
		this.sortname = sortname;
	}

	/**
	 * @return the sortorder
	 */
	public String getSortorder() {
		if (sortorder == null || sortorder.length() < 2) {
			return "desc";
		}
		return sortorder;
	}

	/**
	 * @param sortorder
	 *            the sortorder to set
	 */
	public void setSortorder(String sortorder) {
		this.sortorder = sortorder;
	}

}
