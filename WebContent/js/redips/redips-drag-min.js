/*
Copyright (c) 2008-2011, www.redips.net All rights reserved.
Code licensed under the BSD License: http://www.redips.net/license/
http://www.redips.net/javascript/drag-and-drop-table-content/
Version 4.3.3
Jul 11, 2011.
*/
"use strict";var REDIPS=REDIPS||{};REDIPS.drag=(function(){var init,init_tables,enable_drag,img_onmousemove,handler_onmousedown,handler_ondblclick,table_top,handler_onmouseup,handler_onmousemove,add_events,cell_changed,handler_onresize,set_trc,set_position,set_bgcolor,get_bgcolor,box_offset,calculate_cells,getScrollPosition,autoscrollX,autoscrollY,clone_object,clone_limit,elementControl,get_style,save_content,relocate,move_object,animation,get_table_index,get_position,row_opacity,row_clone,row_drop,form_elements,obj_margin=null,window_width=0,window_height=0,scroll_width=null,scroll_height=null,edge={page:{x:0,y:0},div:{x:0,y:0},flag:{x:0,y:0}},scroll_object,bgcolor_old,scrollable_container=[],tables=[],sort_idx,moved_flag=0,cloned_flag=0,cloned_id=[],currentCell=[],div_drag=null,div_box=null,pointer={x:0,y:0},table=null,table_old=null,table_source=null,row=null,row_old=null,row_source=null,cell=null,cell_old=null,cell_source=null,obj=false,obj_old=false,mode='cell',hover_color='#E7AB83',bound=25,speed=20,only={div:[],cname:'only',other:'deny'},mark={action:'deny',cname:'mark',exception:[]},border='solid',border_disabled='dotted',opacity_disabled,trash_cname='trash',trash_ask=true,drop_option='multiple',delete_cloned=true,source_cell=null,current_cell=null,previous_cell=null,target_cell=null,animation_pause=40,animation_step=2,clone_shiftKey=false;init=function(dc){var self=this,i,imgs,redips_clone;if(dc===undefined){dc='drag';}
div_drag=document.getElementById(dc);if(!document.getElementById('redips_clone')){redips_clone=document.createElement('div');redips_clone.id='redips_clone';redips_clone.style.width=redips_clone.style.height='1px';div_drag.appendChild(redips_clone);}
enable_drag('init');init_tables();handler_onresize();REDIPS.event.add(window,'resize',handler_onresize);imgs=div_drag.getElementsByTagName('img');for(i=0;i<imgs.length;i++){REDIPS.event.add(imgs[i],'mousemove',img_onmousemove);}
REDIPS.event.add(window,'scroll',calculate_cells);};img_onmousemove=function(){return false;};init_tables=function(){var i,j,k,element,level,group_idx,tables_nodeList,nested_tables,td,rowspan;tables_nodeList=div_drag.getElementsByTagName('table');for(i=0,j=0;i<tables_nodeList.length;i++){if(tables_nodeList[i].parentNode.id==='redips_clone'){continue;}
element=tables_nodeList[i].parentNode;level=0;do{if(element.nodeName==='TD'){level++;element.table=true;}
element=element.parentNode;}while(element&&element!==div_drag);tables[j]=tables_nodeList[i];tables[j].redips_container=div_drag;tables[j].redips_nestedLevel=level;tables[j].redips_idx=j;td=tables[j].getElementsByTagName('td');for(k=0,rowspan=false;k<td.length;k++){if(td[k].rowSpan>1){rowspan=true;break;}}
tables[j].redips_rowspan=rowspan;j++;}
for(i=0,group_idx=sort_idx=1;i<tables.length;i++){if(tables[i].redips_nestedLevel===0){tables[i].redips_nestedGroup=group_idx;tables[i].redips_sort=sort_idx*100;nested_tables=tables[i].getElementsByTagName('table');for(j=0;j<nested_tables.length;j++){nested_tables[j].redips_nestedGroup=group_idx;nested_tables[j].redips_sort=sort_idx*100+nested_tables[j].redips_nestedLevel;}
group_idx++;sort_idx++;}}};handler_onmousedown=function(e){var evt=e||window.event,offset,mouseButton,position,X,Y;X=pointer.x=evt.clientX;Y=pointer.y=evt.clientY;if(elementControl(evt)){return true;}
REDIPS.drag.obj_old=obj_old=obj||this;REDIPS.drag.obj=obj=this;table_top(obj);if(div_drag!==obj.redips_container){div_drag=obj.redips_container;init_tables();}
if(obj.className.indexOf('row')===-1){REDIPS.drag.mode=mode='cell';}
else{REDIPS.drag.mode=mode='row';REDIPS.drag.obj=obj=row_clone(obj);}
calculate_cells();if(obj.className.indexOf('clone')===-1&&mode==='cell'){obj.style.zIndex=999;}
set_trc();table_source=table_old=table;row_source=row_old=row;cell_source=cell_old=cell;REDIPS.drag.source_cell=source_cell=tables[table_source].rows[row_source].cells[cell_source];REDIPS.drag.current_cell=current_cell=source_cell;REDIPS.drag.previous_cell=previous_cell=source_cell;if(evt.which){mouseButton=evt.which;}
else{mouseButton=evt.button;}
if(mouseButton===1){moved_flag=0;cloned_flag=0;REDIPS.event.add(document,'mousemove',handler_onmousemove);REDIPS.event.add(document,'mouseup',handler_onmouseup);if(mode==='cell'){REDIPS.drag.myhandler_clicked();}
else{REDIPS.drag.myhandler_row_clicked();}
if(obj.setCapture){obj.setCapture();}}
if(table!==null||row!==null||cell!==null){bgcolor_old=get_bgcolor(table,row,cell);}
position=get_style(tables[table_source],'position');if(position!=='fixed'){position=get_style(tables[table_source].parentNode,'position');}
offset=box_offset(obj,position);obj_margin=[Y-offset[0],offset[1]-X,offset[2]-Y,X-offset[3]];div_drag.onselectstart=function(e){evt=e||window.event;if(!elementControl(evt)){if(evt.shiftKey){document.selection.clear();}
return false;}};return false;};handler_ondblclick=function(e){REDIPS.drag.myhandler_dblclicked();};table_top=function(obj){var e,i,tmp,group;e=obj.parentNode;while(e&&e.nodeName!=='TABLE'){e=e.parentNode;}
group=e.redips_nestedGroup;for(i=0;i<tables.length;i++){if(tables[i].redips_nestedGroup===group){tables[i].redips_sort=sort_idx*100+tables[i].redips_nestedLevel;}}
tables.sort(function(a,b){return b.redips_sort-a.redips_sort;});sort_idx++;};row_clone=function(el){var table_mini,offset,row_obj,row_index,div1,div2,row_last,id,i;if(el.nodeName==='TR'){row_obj=el;while(el&&el.nodeName!=='TABLE'){el=el.parentNode;}
row_index=row_obj.rowIndex;table_mini=el.cloneNode(true);table_mini.redips_source_row=row_obj;row_last=table_mini.rows.length-1;for(i=row_last;i>=0;i--){if(i!==row_index){table_mini.deleteRow(i);}}
form_elements(row_obj,table_mini.rows[0]);div1=row_obj.getElementsByTagName('div');div2=table_mini.getElementsByTagName('div');for(i=0;i<div2.length;i++){div2[i].redips_enabled=div1[i].redips_enabled;div2[i].redips_container=div1[i].redips_container;if(div1[i].redips_enabled){div2[i].onmousedown=handler_onmousedown;div2[i].ondblclick=handler_ondblclick;}}
table_mini.redips_container=el.redips_container;table_mini.redips_dragrow_id=row_obj.redips_dragrow_id;document.getElementById('redips_clone').appendChild(table_mini);offset=box_offset(row_obj,'fixed');table_mini.style.position='fixed';table_mini.style.top=offset[0]+"px";table_mini.style.left=offset[3]+"px";table_mini.style.width=(offset[1]-offset[3])+"px";return table_mini;}
else{id=el.id;while(el&&el.nodeName!=='TR'){el=el.parentNode;}
el.redips_dragrow_id=id;return el;}};row_drop=function(r_table,r_row,table_mini){var ts=tables[r_table].rows[0].parentNode,tr,src,rowIndex;if(table_mini===undefined){table_mini=obj;}
src=table_mini.redips_source_row;rowIndex=src.rowIndex;while(src&&src.nodeName!=='TABLE'){src=src.parentNode;}
src.deleteRow(rowIndex);tr=table_mini.getElementsByTagName('tr')[0];if(r_row<tables[r_table].rows.length){ts.insertBefore(tr,tables[r_table].rows[r_row]);}
else{ts.appendChild(tr);}
table_mini.parentNode.removeChild(table_mini);};form_elements=function(source,cloned){var i,j,k,type,src=[],cld=[];src[0]=source.getElementsByTagName('input');src[1]=source.getElementsByTagName('textarea');src[2]=source.getElementsByTagName('select');cld[0]=cloned.getElementsByTagName('input');cld[1]=cloned.getElementsByTagName('textarea');cld[2]=cloned.getElementsByTagName('select');for(i=0;i<src.length;i++){for(j=0;j<src[i].length;j++){type=src[i][j].type;switch(type){case'text':case'textarea':case'password':cld[i][j].value=src[i][j].value;break;case'radio':case'checkbox':cld[i][j].checked=src[i][j].checked;break;case'select-one':cld[i][j].selectedIndex=src[i][j].selectedIndex;break;case'select-multiple':for(k=0;k<src[i][j].options.length;k++){cld[i][j].options[k].selected=src[i][j].options[k].selected;}
break;}}}};handler_onmouseup=function(e){var evt=e||window.event,target_table,r_table,r_row,mt_tr,X,Y,i,target_elements,target_elements_length;X=evt.clientX;Y=evt.clientY;edge.flag.x=edge.flag.y=0;if(obj.releaseCapture){obj.releaseCapture();}
REDIPS.event.remove(document,'mousemove',handler_onmousemove);REDIPS.event.remove(document,'mouseup',handler_onmouseup);div_drag.onselectstart=null;obj.style.left=0;obj.style.top=0;obj.style.zIndex=-1;obj.style.position='static';scroll_width=document.documentElement.scrollWidth;scroll_height=document.documentElement.scrollHeight;edge.flag.x=edge.flag.y=0;if(cloned_flag===1&&(table===null||row===null||cell===null)){obj.parentNode.removeChild(obj);cloned_id[obj_old.id]-=1;REDIPS.drag.myhandler_notcloned();}
else if(table===null||row===null||cell===null){REDIPS.drag.myhandler_notmoved();}
else{if(table<tables.length){target_table=tables[table];REDIPS.drag.target_cell=target_cell=target_table.rows[row].cells[cell];set_bgcolor(table,row,cell,bgcolor_old);r_table=table;r_row=row;}
else if(table_old===null||row_old===null||cell_old===null){target_table=tables[table_source];REDIPS.drag.target_cell=target_cell=target_table.rows[row_source].cells[cell_source];set_bgcolor(table_source,row_source,cell_source,bgcolor_old);r_table=table_source;r_row=row_source;}
else{target_table=tables[table_old];REDIPS.drag.target_cell=target_cell=target_table.rows[row_old].cells[cell_old];set_bgcolor(table_old,row_old,cell_old,bgcolor_old);r_table=table_old;r_row=row_old;}
if(mode==='row'){if(moved_flag===0){REDIPS.drag.myhandler_row_notmoved();}
else{if(table_source===r_table&&row_source===r_row){mt_tr=obj.getElementsByTagName('tr')[0];obj_old.style.backgroundColor=mt_tr.style.backgroundColor;for(i=0;i<mt_tr.cells.length;i++){obj_old.cells[i].style.backgroundColor=mt_tr.cells[i].style.backgroundColor;}
obj.parentNode.removeChild(obj);REDIPS.drag.myhandler_row_dropped_source(target_cell);}
else{row_drop(r_table,r_row);REDIPS.drag.myhandler_row_dropped(target_cell);}}}
else if(moved_flag===0){REDIPS.drag.myhandler_notmoved();}
else if(cloned_flag===1&&table_source===table&&row_source===row&&cell_source===cell){obj.parentNode.removeChild(obj);cloned_id[obj_old.id]-=1;REDIPS.drag.myhandler_notcloned();}
else if(cloned_flag===1&&REDIPS.drag.delete_cloned===true&&(X<target_table.offset[3]||X>target_table.offset[1]||Y<target_table.offset[0]||Y>target_table.offset[2])){obj.parentNode.removeChild(obj);cloned_id[obj_old.id]-=1;REDIPS.drag.myhandler_notcloned();}
else if(target_cell.className.indexOf(REDIPS.drag.trash_cname)>-1){obj.parentNode.removeChild(obj);if(REDIPS.drag.trash_ask){setTimeout(function(){if(confirm('Are you sure you want to delete?')){REDIPS.drag.myhandler_deleted();if(cloned_flag===1){clone_limit();}}
else{if(cloned_flag!==1){tables[table_source].rows[row_source].cells[cell_source].appendChild(obj);calculate_cells();}
REDIPS.drag.myhandler_undeleted();}},20);}
else{REDIPS.drag.myhandler_deleted();if(cloned_flag===1){clone_limit();}}}
else if(REDIPS.drag.drop_option==='switch'){obj.parentNode.removeChild(obj);target_elements=target_cell.getElementsByTagName('DIV');target_elements_length=target_elements.length;for(i=0;i<target_elements_length;i++){if(target_elements[0]!==undefined){source_cell.appendChild(target_elements[0]);}}
REDIPS.drag.myhandler_dropped_before(target_cell);target_cell.appendChild(obj);if(target_elements_length){REDIPS.drag.myhandler_switched();REDIPS.drag.myhandler_dropped(target_cell);if(cloned_flag===1){clone_limit();}}
else{REDIPS.drag.myhandler_dropped(target_cell);if(cloned_flag===1){clone_limit();}}}
else if(REDIPS.drag.drop_option==='overwrite'){target_elements=target_cell.getElementsByTagName('DIV');target_elements_length=target_elements.length;for(i=0;i<target_elements_length;i++){target_cell.removeChild(target_elements[0]);}
REDIPS.drag.myhandler_dropped_before(target_cell);target_cell.appendChild(obj);REDIPS.drag.myhandler_dropped(target_cell);if(cloned_flag===1){clone_limit();}}
else{REDIPS.drag.myhandler_dropped_before(target_cell);target_cell.appendChild(obj);REDIPS.drag.myhandler_dropped(target_cell);if(cloned_flag===1){clone_limit();}}
if(table_source!==null&&row_source!==null){tables[table_source].rows[row_source].className=tables[table_source].rows[row_source].className;}
target_cell.parentNode.className=target_cell.parentNode.className;calculate_cells();}
table_old=row_old=cell_old=null;};handler_onmousemove=function(e){var evt=e||window.event,bound=REDIPS.drag.bound,sca,X,Y,i,scrollPosition;X=pointer.x=evt.clientX;Y=pointer.y=evt.clientY;if(moved_flag===0){if(obj.className.indexOf('clone')>-1||(REDIPS.drag.clone_shiftKey===true&&evt.shiftKey)){clone_object();cloned_flag=1;REDIPS.drag.myhandler_cloned();set_position();}
else{if(mode==='row'){REDIPS.drag.obj_old=obj_old=obj;REDIPS.drag.obj=obj=row_clone(obj);obj.style.zIndex=999;}
if(obj.setCapture){obj.setCapture();}
obj.style.position='fixed';calculate_cells();set_trc();if(mode==='cell'){REDIPS.drag.myhandler_moved();}
else{REDIPS.drag.myhandler_row_moved();}
set_position();}
if(X>window_width-obj_margin[1]){obj.style.left=(window_width-(obj_margin[1]+obj_margin[3]))+'px';}
if(Y>window_height-obj_margin[2]){obj.style.top=(window_height-(obj_margin[0]+obj_margin[2]))+'px';}}
moved_flag=1;if(X>obj_margin[3]&&X<window_width-obj_margin[1]){obj.style.left=(X-obj_margin[3])+'px';}
if(Y>obj_margin[0]&&Y<window_height-obj_margin[2]){obj.style.top=(Y-obj_margin[0])+'px';}
if(X<div_box[1]&&X>div_box[3]&&Y<div_box[2]&&Y>div_box[0]&&edge.flag.x===0&&edge.flag.y===0&&((currentCell.containTable===1)||(X<currentCell[3]||X>currentCell[1]||Y<currentCell[0]||Y>currentCell[2]))){set_trc();cell_changed();}
edge.page.x=bound-(window_width/2>X?X-obj_margin[3]:window_width-X-obj_margin[1]);if(edge.page.x>0){if(edge.page.x>bound){edge.page.x=bound;}
scrollPosition=getScrollPosition()[0];edge.page.x*=X<window_width/2?-1:1;if(!((edge.page.x<0&&scrollPosition<=0)||(edge.page.x>0&&scrollPosition>=(scroll_width-window_width)))){if(edge.flag.x++===0){REDIPS.event.remove(window,'scroll',calculate_cells);autoscrollX(window);}}}
else{edge.page.x=0;}
edge.page.y=bound-(window_height/2>Y?Y-obj_margin[0]:window_height-Y-obj_margin[2]);if(edge.page.y>0){if(edge.page.y>bound){edge.page.y=bound;}
scrollPosition=getScrollPosition()[1];edge.page.y*=Y<window_height/2?-1:1;if(!((edge.page.y<0&&scrollPosition<=0)||(edge.page.y>0&&scrollPosition>=(scroll_height-window_height)))){if(edge.flag.y++===0){REDIPS.event.remove(window,'scroll',calculate_cells);autoscrollY(window);}}}
else{edge.page.y=0;}
for(i=0;i<scrollable_container.length;i++){sca=scrollable_container[i];if(sca.autoscroll&&X<sca.offset[1]&&X>sca.offset[3]&&Y<sca.offset[2]&&Y>sca.offset[0]){edge.div.x=bound-(sca.midstX>X?X-obj_margin[3]-sca.offset[3]:sca.offset[1]-X-obj_margin[1]);if(edge.div.x>0){if(edge.div.x>bound){edge.div.x=bound;}
edge.div.x*=X<sca.midstX?-1:1;if(edge.flag.x++===0){REDIPS.event.remove(sca.div,'scroll',calculate_cells);autoscrollX(sca.div);}}
else{edge.div.x=0;}
edge.div.y=bound-(sca.midstY>Y?Y-obj_margin[0]-sca.offset[0]:sca.offset[2]-Y-obj_margin[2]);if(edge.div.y>0){if(edge.div.y>bound){edge.div.y=bound;}
edge.div.y*=Y<sca.midstY?-1:1;if(edge.flag.y++===0){REDIPS.event.remove(sca.div,'scroll',calculate_cells);autoscrollY(sca.div);}}
else{edge.div.y=0;}
break;}
else{edge.div.x=edge.div.y=0;}}
evt.cancelBubble=true;if(evt.stopPropagation){evt.stopPropagation();}};add_events=function(div){if(typeof(div)==='string'){div=document.getElementById(div);}
div.onmousedown=handler_onmousedown;div.ondblclick=handler_ondblclick;};cell_changed=function(){if(table<tables.length&&(table!==table_old||row!==row_old||cell!==cell_old)){if(table_old!==null&&row_old!==null&&cell_old!==null){set_bgcolor(table_old,row_old,cell_old,bgcolor_old);REDIPS.drag.previous_cell=previous_cell=tables[table_old].rows[row_old].cells[cell_old];REDIPS.drag.current_cell=current_cell=tables[table].rows[row].cells[cell];if(REDIPS.drag.drop_option==='switching'){relocate(current_cell,previous_cell);calculate_cells();set_trc();}
if(mode==='cell'){REDIPS.drag.myhandler_changed();}
else if(mode==='row'&&(table!==table_old||row!==row_old)){REDIPS.drag.myhandler_row_changed();}}
set_position();}};handler_onresize=function(){if(typeof(window.innerWidth)==='number'){window_width=window.innerWidth;window_height=window.innerHeight;}
else if(document.documentElement&&(document.documentElement.clientWidth||document.documentElement.clientHeight)){window_width=document.documentElement.clientWidth;window_height=document.documentElement.clientHeight;}
else if(document.body&&(document.body.clientWidth||document.body.clientHeight)){window_width=document.body.clientWidth;window_height=document.body.clientHeight;}
scroll_width=document.documentElement.scrollWidth;scroll_height=document.documentElement.scrollHeight;calculate_cells();};set_trc=function(){var cell_current,row_offset,row_found,cells,has_content,mark_found,only_found,single_cell,row_handler,tos=[],X,Y,i;X=pointer.x;Y=pointer.y;for(table=0;table<tables.length;table++){tos[0]=tables[table].offset[0];tos[1]=tables[table].offset[1];tos[2]=tables[table].offset[2];tos[3]=tables[table].offset[3];if(tables[table].sca!==undefined){tos[0]=tos[0]>tables[table].sca.offset[0]?tos[0]:tables[table].sca.offset[0];tos[1]=tos[1]<tables[table].sca.offset[1]?tos[1]:tables[table].sca.offset[1];tos[2]=tos[2]<tables[table].sca.offset[2]?tos[2]:tables[table].sca.offset[2];tos[3]=tos[3]>tables[table].sca.offset[3]?tos[3]:tables[table].sca.offset[3];}
if(tos[3]<X&&X<tos[1]&&tos[0]<Y&&Y<tos[2]){row_offset=tables[table].row_offset;for(row=0;row<row_offset.length-1&&row_offset[row][0]<Y;row++){currentCell[0]=row_offset[row][0];currentCell[2]=row_offset[row+1][0];if(Y<=currentCell[2]){break;}}
row_found=row;if(row===row_offset.length-1){currentCell[0]=row_offset[row][0];currentCell[2]=tables[table].offset[2];}
do{cells=tables[table].rows[row].cells.length-1;for(cell=cells;cell>=0;cell--){currentCell[3]=row_offset[row][3]+tables[table].rows[row].cells[cell].offsetLeft;currentCell[1]=currentCell[3]+tables[table].rows[row].cells[cell].offsetWidth;if(currentCell[3]<=X&&X<=currentCell[1]){break;}}}
while(tables[table].redips_rowspan&&cell===-1&&row-->0);if(row<0||cell<0){table=table_old;row=row_old;cell=cell_old;}
else if(row!==row_found){currentCell[0]=row_offset[row][0];currentCell[2]=currentCell[0]+tables[table].rows[row].cells[cell].offsetHeight;if(Y<currentCell[0]||Y>currentCell[2]){table=table_old;row=row_old;cell=cell_old;}}
cell_current=tables[table].rows[row].cells[cell];if('table'in cell_current){currentCell.containTable=1;}
else{currentCell.containTable=0;}
if(cell_current.className.indexOf(REDIPS.drag.trash_cname)===-1){only_found=cell_current.className.indexOf(REDIPS.drag.only.cname)>-1?true:false;if(only_found===true){if(cell_current.className.indexOf(only.div[obj.id])===-1){if((table_old!==null&&row_old!==null&&cell_old!==null)){table=table_old;row=row_old;cell=cell_old;}
break;}}
else if(only.div[obj.id]!==undefined&&only.other==='deny'){if((table_old!==null&&row_old!==null&&cell_old!==null)){table=table_old;row=row_old;cell=cell_old;}
break;}
else{mark_found=cell_current.className.indexOf(REDIPS.drag.mark.cname)>-1?true:false;if((mark_found===true&&REDIPS.drag.mark.action==='deny')||(mark_found===false&&REDIPS.drag.mark.action==='allow')){if(cell_current.className.indexOf(mark.exception[obj.id])===-1){if((table_old!==null&&row_old!==null&&cell_old!==null)){table=table_old;row=row_old;cell=cell_old;}
break;}}}}
single_cell=cell_current.className.indexOf('single')>-1?true:false;if((REDIPS.drag.drop_option==='single'||single_cell)&&cell_current.childNodes.length>0){if(cell_current.childNodes.length===1&&cell_current.firstChild.nodeType===3){break;}
has_content=false;for(i=cell_current.childNodes.length-1;i>=0;i--){if(cell_current.childNodes[i].className&&cell_current.childNodes[i].className.indexOf('drag')>-1){has_content=true;break;}}
if(has_content&&table_old!==null&&row_old!==null&&cell_old!==null){if(table_source!==table||row_source!==row||cell_source!==cell){table=table_old;row=row_old;cell=cell_old;break;}}}
row_handler=cell_current.className.indexOf('rowhandler')>-1?true:false;if(row_handler&&mode==='cell'){if((table_old!==null&&row_old!==null&&cell_old!==null)){table=table_old;row=row_old;cell=cell_old;}
break;}
break;}}};set_position=function(){if(table<tables.length&&table!==null&&row!==null&&cell!==null){bgcolor_old=get_bgcolor(table,row,cell);set_bgcolor(table,row,cell,[REDIPS.drag.hover_color]);table_old=table;row_old=row;cell_old=cell;}};set_bgcolor=function(t,r,c,color){var tr,i;if(mode==='cell'){tables[t].rows[r].cells[c].style.backgroundColor=color[0];}
else{tr=tables[t].rows[r];for(i=0;i<tr.cells.length;i++){tr.cells[i].style.backgroundColor=color[i]?color[i]:color[0];}}};get_bgcolor=function(t,r,c){var color=[],tr,i;if(mode==='cell'){color[0]=tables[t].rows[r].cells[c].style.backgroundColor;}
else{tr=tables[t].rows[r];for(i=0;i<tr.cells.length;i++){color[i]=tr.cells[i].style.backgroundColor;}}
return color;};box_offset=function(box,position,box_scroll){var scrollPosition,oLeft=0,oTop=0,box_old=box;if(position!=='fixed'){scrollPosition=getScrollPosition();oLeft=0-scrollPosition[0];oTop=0-scrollPosition[1];}
if(box_scroll===undefined||box_scroll===true){do{oLeft+=box.offsetLeft-box.scrollLeft;oTop+=box.offsetTop-box.scrollTop;box=box.offsetParent;}
while(box&&box.nodeName!=='BODY');}
else{do{oLeft+=box.offsetLeft;oTop+=box.offsetTop;box=box.offsetParent;}
while(box&&box.nodeName!=='BODY');}
return[oTop,oLeft+box_old.offsetWidth,oTop+box_old.offsetHeight,oLeft];};calculate_cells=function(){var i,j,row_offset,position,cb;for(i=0;i<tables.length;i++){row_offset=[];position=get_style(tables[i],'position');if(position!=='fixed'){position=get_style(tables[i].parentNode,'position');}
for(j=tables[i].rows.length-1;j>=0;j--){row_offset[j]=box_offset(tables[i].rows[j],position);}
tables[i].offset=box_offset(tables[i],position);tables[i].row_offset=row_offset;}
div_box=box_offset(div_drag);for(i=0;i<scrollable_container.length;i++){position=get_style(scrollable_container[i].div,'position');cb=box_offset(scrollable_container[i].div,position,false);scrollable_container[i].offset=cb;scrollable_container[i].midstX=(cb[1]+cb[3])/2;scrollable_container[i].midstY=(cb[0]+cb[2])/2;}};getScrollPosition=function(){var scrollX,scrollY;if(typeof(window.pageYOffset)==='number'){scrollX=window.pageXOffset;scrollY=window.pageYOffset;}
else if(document.body&&(document.body.scrollLeft||document.body.scrollTop)){scrollX=document.body.scrollLeft;scrollY=document.body.scrollTop;}
else if(document.documentElement&&(document.documentElement.scrollLeft||document.documentElement.scrollTop)){scrollX=document.documentElement.scrollLeft;scrollY=document.documentElement.scrollTop;}
else{scrollX=scrollY=0;}
return[scrollX,scrollY];};autoscrollX=function(so){var pos,old,scrollPosition,maxsp,edgeCrossed,X=pointer.x,Y=pointer.y;if(edge.flag.x>0){calculate_cells();set_trc();if(X<div_box[1]&&X>div_box[3]&&Y<div_box[2]&&Y>div_box[0]){cell_changed();}}
if(typeof(so)==='object'){scroll_object=so;}
if(scroll_object===window){scrollPosition=old=getScrollPosition()[0];maxsp=scroll_width-window_width;edgeCrossed=edge.page.x;}
else{scrollPosition=scroll_object.scrollLeft;maxsp=scroll_object.scrollWidth-scroll_object.clientWidth;edgeCrossed=edge.div.x;}
if(edge.flag.x>0&&((edgeCrossed<0&&scrollPosition>0)||(edgeCrossed>0&&scrollPosition<maxsp))){if(scroll_object===window){window.scrollBy(edgeCrossed,0);scrollPosition=getScrollPosition()[0];pos=parseInt(obj.style.left,10);if(isNaN(pos)){pos=0;}}
else{scroll_object.scrollLeft+=edgeCrossed;}
setTimeout(autoscrollX,REDIPS.drag.speed);}
else{REDIPS.event.add(scroll_object,'scroll',calculate_cells);edge.flag.x=0;currentCell=[0,0,0,0];}};autoscrollY=function(so){var pos,old,scrollPosition,maxsp,edgeCrossed,X=pointer.x,Y=pointer.y;if(edge.flag.y>0){calculate_cells();set_trc();if(X<div_box[1]&&X>div_box[3]&&Y<div_box[2]&&Y>div_box[0]){cell_changed();}}
if(typeof(so)==='object'){scroll_object=so;}
if(scroll_object===window){scrollPosition=old=getScrollPosition()[1];maxsp=scroll_height-window_height;edgeCrossed=edge.page.y;}
else{scrollPosition=scroll_object.scrollTop;maxsp=scroll_object.scrollHeight-scroll_object.clientHeight;edgeCrossed=edge.div.y;}
if(edge.flag.y>0&&((edgeCrossed<0&&scrollPosition>0)||(edgeCrossed>0&&scrollPosition<maxsp))){if(scroll_object===window){window.scrollBy(0,edgeCrossed);scrollPosition=getScrollPosition()[1];pos=parseInt(obj.style.top,10);if(isNaN(pos)){pos=0;}}
else{scroll_object.scrollTop+=edgeCrossed;}
setTimeout(autoscrollY,REDIPS.drag.speed);}
else{REDIPS.event.add(scroll_object,'scroll',calculate_cells);edge.flag.y=0;currentCell=[0,0,0,0];}};clone_object=function(){var obj_new=obj.cloneNode(true),offset,offset_dragged;document.getElementById('redips_clone').appendChild(obj_new);if(obj_new.setCapture){obj_new.setCapture();}
obj_new.style.zIndex=999;obj_new.style.position='fixed';offset=box_offset(obj);offset_dragged=box_offset(obj_new);obj_new.style.top=(offset[0]-offset_dragged[0])+'px';obj_new.style.left=(offset[3]-offset_dragged[3])+'px';obj_new.onmousedown=handler_onmousedown;obj_new.ondblclick=handler_ondblclick;obj_new.className=obj_new.className.replace('clone','');if(cloned_id[obj.id]===undefined){cloned_id[obj.id]=0;}
obj_new.id=obj.id+'c'+cloned_id[obj.id];cloned_id[obj.id]+=1;obj_new.redips_container=obj.redips_container;obj_new.redips_enabled=obj.redips_enabled;REDIPS.drag.obj_old=obj_old=obj;REDIPS.drag.obj=obj=obj_new;};clone_limit=function(){var match_arr,limit_type,limit,classes;classes=obj_old.className;match_arr=classes.match(/climit(\d)_(\d+)/);if(match_arr!==null){limit_type=parseInt(match_arr[1],10);limit=parseInt(match_arr[2],10);limit-=1;classes=classes.replace(/climit\d_\d+/g,'');if(limit<=0){classes=classes.replace('clone','');if(limit_type===2){classes=classes.replace('drag','');obj_old.onmousedown=null;obj_old.style.cursor='auto';REDIPS.drag.myhandler_clonedend2();}
else{REDIPS.drag.myhandler_clonedend1();}}
else{classes=classes+' climit'+limit_type+'_'+limit;}
classes=classes.replace(/^\s+|\s+$/g,'').replace(/\s{2,}/g,' ');obj_old.className=classes;}};elementControl=function(evt){var flag=false,srcName,classes,regex_nodrag=/\bnodrag\b/i;if(evt.srcElement){srcName=evt.srcElement.nodeName;classes=evt.srcElement.className;}
else{srcName=evt.target.nodeName;classes=evt.target.className;}
switch(srcName){case'A':case'INPUT':case'SELECT':case'OPTION':case'TEXTAREA':flag=true;break;default:if(regex_nodrag.test(classes)){flag=true;}
else{flag=false;}}
return flag;};enable_drag=function(enable_flag,div_id,type){var i,j,k,divs=[],tbls=[],borderStyle,opacity,cursor,overflow,autoscroll,enabled,cb,handler1,handler2,position,regex_drag=/\bdrag\b/i,regex_noautoscroll=/\bnoautoscroll\b/i;opacity=REDIPS.drag.opacity_disabled;if(enable_flag===true||enable_flag==='init'){handler1=handler_onmousedown;handler2=handler_ondblclick;borderStyle=REDIPS.drag.border;cursor='move';enabled=true;}
else{handler1=handler2=null;borderStyle=REDIPS.drag.border_disabled;cursor='auto';enabled=false;}
if(div_id===undefined){divs=div_drag.getElementsByTagName('div');}
else if(typeof(div_id)==='string'&&type==='container'){divs=document.getElementById(div_id).getElementsByTagName('div');}
else if(typeof(div_id)==='string'){divs[0]=document.getElementById(div_id);}
else{divs[0]=div_id;}
for(i=0,j=0;i<divs.length;i++){if(regex_drag.test(divs[i].className)){divs[i].onmousedown=handler1;divs[i].ondblclick=handler2;divs[i].style.borderStyle=borderStyle;divs[i].style.cursor=cursor;divs[i].redips_enabled=enabled;if(enable_flag==='init'){divs[i].redips_container=div_drag;}
else if(enable_flag===true&&typeof(opacity)==='number'){divs[i].style.opacity='';divs[i].style.filter='';}
else if(enable_flag===false&&typeof(opacity)==='number'){divs[i].style.opacity=opacity/100;divs[i].style.filter='alpha(opacity='+opacity+')';}}
else if(enable_flag==='init'){overflow=get_style(divs[i],'overflow');if(overflow!=='visible'){REDIPS.event.add(divs[i],'scroll',calculate_cells);position=get_style(divs[i],'position');cb=box_offset(divs[i],position,false);if(regex_noautoscroll.test(divs[i].className)){autoscroll=false;}
else{autoscroll=true;}
scrollable_container[j]={div:divs[i],offset:cb,midstX:(cb[1]+cb[3])/2,midstY:(cb[0]+cb[2])/2,autoscroll:autoscroll};tbls=divs[i].getElementsByTagName('table');for(k=0;k<tbls.length;k++){tbls[k].sca=scrollable_container[j];}
j++;}}}};get_style=function(el,style_name){var val;if(el&&el.currentStyle){val=el.currentStyle[style_name];}
else if(el&&window.getComputedStyle){val=document.defaultView.getComputedStyle(el,null).getPropertyValue(style_name);}
return val;};save_content=function(tbl){var query='',tbl_start,tbl_end,tbl_rows,cells,tbl_cell,t,r,c,d;tables.sort(function(a,b){return a.redips_idx-b.redips_idx;});if(tbl===undefined){tbl_start=0;tbl_end=tables.length-1;}
else if(tbl<0||tbl>tables.length-1){tbl_start=tbl_end=0;}
else{tbl_start=tbl_end=tbl;}
for(t=tbl_start;t<=tbl_end;t++){tbl_rows=tables[t].rows.length;for(r=0;r<tbl_rows;r++){cells=tables[t].rows[r].cells.length;for(c=0;c<cells;c++){tbl_cell=tables[t].rows[r].cells[c];if(tbl_cell.childNodes.length>0){for(d=0;d<tbl_cell.childNodes.length;d++){if(tbl_cell.childNodes[d].nodeName==='DIV'){query+='p[]='+tbl_cell.childNodes[d].id+'_'+t+'_'+r+'_'+c+'&';}}}}}}
query=query.substring(0,query.length-1);tables.sort(function(a,b){return b.redips_sort-a.redips_sort;});return query;};relocate=function(from,to){var i,childnodes_length;if(from===to){return;}
childnodes_length=from.childNodes.length;for(i=0;i<childnodes_length;i++){to.appendChild(from.childNodes[0]);}};move_object=function(ip){var p={'direction':1},x1,y1,w1,h1,x2,y2,w2,h2,row,col,dx,dy,pos,i,target;p.callback=ip.callback;if(typeof(ip.id)==='string'){p.obj=p.obj_old=document.getElementById(ip.id);}
if(ip.mode==='row'){p.mode='row';i=get_table_index(ip.source[0]);row=ip.source[1];p.obj_old=tables[i].rows[row];p.obj=row_clone(p.obj_old);}
else if(p.obj.className.indexOf('row')>-1){p.mode='row';while(p.obj&&p.obj.nodeName!=='TR'){p.obj=p.obj.parentNode;}
p.obj_old=p.obj;p.obj=row_clone(p.obj_old);}
else{p.mode='cell';}
p.obj.style.zIndex=999;if(div_drag!==p.obj.redips_container){div_drag=p.obj.redips_container;init_tables();}
pos=box_offset(p.obj);w1=pos[1]-pos[3];h1=pos[2]-pos[0];x1=pos[3];y1=pos[0];if(ip.target===undefined){ip.target=get_position();}
p.target=ip.target;i=get_table_index(ip.target[0]);row=ip.target[1];col=ip.target[2];p.target_cell=tables[i].rows[row].cells[col];if(p.mode==='cell'){pos=box_offset(p.target_cell);w2=pos[1]-pos[3];h2=pos[2]-pos[0];x2=pos[3]+(w2-w1)/2;y2=pos[0]+(h2-h1)/2;}
else{pos=box_offset(tables[i].rows[row]);w2=pos[1]-pos[3];h2=pos[2]-pos[0];x2=pos[3];y2=pos[0];}
dx=x2-x1;dy=y2-y1;p.obj.style.position='fixed';if(Math.abs(dx)>Math.abs(dy)){p.type='horizontal';p.m=dy/dx;p.b=y1-p.m*x1;p.k1=(x1+x2)/(x1-x2);p.k2=2/(x1-x2);if(x1>x2){p.direction=-1;}
i=x1;p.last=x2;}
else{p.type='vertical';p.m=dx/dy;p.b=x1-p.m*y1;p.k1=(y1+y2)/(y1-y2);p.k2=2/(y1-y2);if(y1>y2){p.direction=-1;}
i=y1;p.last=y2;}
animation(i,p);return[p.obj,p.obj_old];};animation=function(i,p){var k=(p.k1-p.k2*i)*(p.k1-p.k2*i),f;i=i+REDIPS.drag.animation_step*(4-k*3)*p.direction;f=p.m*i+p.b;if(p.type==='horizontal'){p.obj.style.left=i+'px';p.obj.style.top=f+'px';}
else{p.obj.style.left=f+'px';p.obj.style.top=i+'px';}
if((i<p.last&&p.direction>0)||((i>p.last)&&p.direction<0)){setTimeout(function(){animation(i,p);},REDIPS.drag.animation_pause*k);}
else{p.obj.style.zIndex=-1;p.obj.style.position='static';if(p.mode==='cell'){p.target_cell.appendChild(p.obj);}
else{row_drop(p.target[0],p.target[1],p.obj);}
if(typeof(p.callback)==='function'){p.callback();}}};get_position=function(ip){var toi,toi_source,ci,ri,ti,el,tbl,arr=[];if(ip===undefined){if(table<tables.length){toi=tables[table].redips_idx;}
else if(table_old===null||row_old===null||cell_old===null){toi=tables[table_source].redips_idx;}
else{toi=tables[table_old].redips_idx;}
toi_source=tables[table_source].redips_idx;arr=[toi,row,cell,toi_source,row_source,cell_source];}
else{if(typeof(ip)==='string'){el=document.getElementById(ip);}
else{el=ip;}
while(el&&el.nodeName!=='TD'){el=el.parentNode;}
if(el&&el.nodeName==='TD'){ci=el.cellIndex;ri=el.parentNode.rowIndex;tbl=el.parentNode;while(tbl&&tbl.nodeName!=='TABLE'){tbl=tbl.parentNode;}
ti=tbl.redips_idx;arr=[ti,ri,ci];}}
return arr;};get_table_index=function(idx){var i;for(i=0;i<tables.length;i++){if(tables[i].redips_idx===idx){break;}}
return i;};row_opacity=function(el,opacity,color){var td,i,j;if(typeof(el)==='string'){el=document.getElementById(el);while(el&&el.nodeName!=='TABLE'){el=el.parentNode;}}
if(el.nodeName==='TR'){td=el.getElementsByTagName('td');for(i=0;i<td.length;i++){td[i].style.backgroundColor=color?color:'';for(j=0;j<td[i].childNodes.length;j++){if(td[i].childNodes[j].nodeType===1){td[i].childNodes[j].style.opacity=opacity/100;td[i].childNodes[j].style.filter='alpha(opacity='+opacity+')';}}}}
else{el.style.opacity=opacity/100;el.style.filter='alpha(opacity='+opacity+')';}};return{obj:obj,obj_old:obj_old,mode:mode,source_cell:source_cell,previous_cell:previous_cell,current_cell:current_cell,target_cell:target_cell,hover_color:hover_color,bound:bound,speed:speed,only:only,mark:mark,border:border,border_disabled:border_disabled,opacity_disabled:opacity_disabled,trash_cname:trash_cname,trash_ask:trash_ask,drop_option:drop_option,delete_cloned:delete_cloned,cloned_id:cloned_id,clone_shiftKey:clone_shiftKey,animation_pause:animation_pause,animation_step:animation_step,init:init,enable_drag:enable_drag,add_events:add_events,save_content:save_content,relocate:relocate,move_object:move_object,get_position:get_position,row_opacity:row_opacity,getScrollPosition:getScrollPosition,get_style:get_style,myhandler_clicked:function(){},myhandler_dblclicked:function(){},myhandler_moved:function(){},myhandler_notmoved:function(){},myhandler_dropped:function(){},myhandler_dropped_before:function(){},myhandler_switched:function(){},myhandler_changed:function(){},myhandler_cloned:function(){},myhandler_clonedend1:function(){},myhandler_clonedend2:function(){},myhandler_notcloned:function(){},myhandler_deleted:function(){},myhandler_undeleted:function(){},myhandler_row_clicked:function(){},myhandler_row_moved:function(){},myhandler_row_notmoved:function(){},myhandler_row_dropped:function(){},myhandler_row_dropped_source:function(){},myhandler_row_changed:function(){}};}());if(!REDIPS.event){REDIPS.event=(function(){var add,remove;add=function(obj,eventName,handler){if(obj.addEventListener){obj.addEventListener(eventName,handler,false);}
else if(obj.attachEvent){obj.attachEvent('on'+eventName,handler);}
else{obj['on'+eventName]=handler;}};remove=function(obj,eventName,handler){if(obj.removeEventListener){obj.removeEventListener(eventName,handler,false);}
else if(obj.detachEvent){obj.detachEvent('on'+eventName,handler);}
else{obj['on'+eventName]=null;}};return{add:add,remove:remove};}());}