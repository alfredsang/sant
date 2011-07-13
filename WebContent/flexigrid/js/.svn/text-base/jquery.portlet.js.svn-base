/*************************************************
**  报表隐藏插件 jQuery portlet version 0.0.1
**  copyright Snug sang <shiren1118@gmail.com>, licensed GPL & MIT
**  http://
**************************************************/
;(function($){  	
    $.fn.portlet = function(options, callback) { 
	 
		return this.each(function(){

			if($(this).attr("clazz")=="portlet"){
				
				var portlet=$(this);
				portlet.addClass(portlet.attr("clazz"));
				
				portlet.children("div[clazz='topper']").each(function(){
					var topper=$(this);
					/*topper配置区*/
					topper.addClass("portlet-topper");
					
					/*topper内容配置区*/
					topper.children("span[clazz='title']").addClass("portlet-title");
					
					topper.children("div[clazz='icons']").each(function(){
						var icons=$(this);
						
						icons.addClass("portlet-icons");//
						
						icons.children("div[clazz='icon_close']").each(function(){
							$(this).addClass("portlet_icon_div portlet_icon_close");//
						});
						
						icons.children("div[clazz='icon_config']").each(function(){
							$(this).addClass("portlet_icon_div portlet_icon_config");//
						});
						
						icons.children("div[clazz='icon_option']").each(function(){
							$(this).addClass("portlet_icon_div portlet_icon_option");//
						});
						
						icons.children("div[clazz='icon_restore']").each(function(){
							$(this).addClass("portlet_icon_div portlet_icon_restore");//
						});
						
						icons.children("div[clazz='icon_min']").each(function(){
							$(this).addClass("portlet_icon_div portlet_icon_min");//
						});
						
					});	
				});		

				portlet.children("div[clazz='content']").each(function(){
					var content=$(this);
					content.addClass("portlet-content");//
				});
			
				
			}
			
		
			
		});
    };//end function



    $.portlet = {
        defaults : {
        },
        currentRow: undefined,
		colSpanCount:1
	 };

})(jQuery);  