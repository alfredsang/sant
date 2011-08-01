package com.no320.mvc.nutz;

import org.nutz.mvc.annotation.*;

public class MainModule {


	@At("/hello")
	@Ok("jsp:jsp.index")
	public String doHello() {
		return "Hello Nutz";
	}
	
	
}
