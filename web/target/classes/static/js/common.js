//设置AJAX的全局默认选项
$.ajaxSetup( {
/*    headers: { // 默认添加请求头
        "Author": "CodePlayer" ,
        "Powered-By": "CodePlayer"
    } ,*/
	complete:function(xhr,ts){
		
		if(xhr.status=="404"){
			window.location.href="/404";
		}else if(xhr.status=="400"){
			window.location.href="/400";
		}else if(xhr.status=="500"){
			window.location.href="/500";
		}
		
		if(xhr.getResponseHeader("permission")=="false"){
			window.location.href="/401";
		}else if(xhr.getResponseHeader("sessionStatus")=="false"){
			  window.parent.location.href="/admin";
		}
		showAuthorizedBtn();
	},
    error: function(jqXHR, textStatus, errorMsg){ // 出错时默认的处理函数
        // jqXHR 是经过jQuery封装的XMLHttpRequest对象
        // textStatus 可能为： null、"timeout"、"error"、"abort"或"parsererror"
        // errorMsg 可能为： "Not Found"、"Internal Server Error"等

        // 提示形如：发送AJAX请求到"/index.html"时出错[404]：Not Found
           
    }
} );
$(function(){
	showAuthorizedBtn();
});
function showAuthorizedBtn(){
	var buts = localStorage.getItem("authorizedButs");
	var btnJson =  $.parseJSON(buts);
	var authBtns = $("[authcode]");
	if(authBtns!=null){
		authBtns.hide();
	for(var i=0;i<authBtns.size();i++){
		for(var j=0;btnJson!=null&&j<btnJson.length;j++){
			if(btnJson[j]==authBtns.eq(i).attr("authcode")){
				authBtns.eq(i).show();
				break;
			}
		}
	  }
	}
}
/**
 * 表格权限控制
 * @param cols 表格列数组
 * @returns
 * 循环判断页面数组在后台返回有权限的表格列是否存在
 * 如果存在则显示不存在则不显示
 */
function showAuthCols(cols){
	if(fields==null){
		return cols;
	}
	for(var i=0;i<cols.length;i++){
		var c = cols[i];
		var index = $.inArray(c.field,fields);
		if(index==-1 && !c.toolbar){
			cols.splice(i--,1);
		}
		
	}
	return cols;
}
/**
 * 表格刷新
 * searchBtnId 查询按钮id
 */
$.extend({reloadData:function(searchBtnId){
		if($(".layui-laypage-btn")[0]){
			$(".layui-laypage-btn")[0].click();
		}else{
			$("#"+searchBtnId).click();
		}
	}
});
$.fn.serializeJson = function () {
          var serializeObj = {};
          $(this.serializeArray()).each(function () {
              serializeObj[this.name] = this.value;
          });
          return serializeObj;
      };

 $.extend({detailLoad:function (url,data,responseFun) {
			  $.ajax({
				type: "GET",
				url: url,
				data: data,
				dataType: "html",
				success: function(data){
					if(responseFun){
						responseFun(data);
					}
				},
				complete:function(){
					showDetailAuthCols();
				}
			});
	      }
      });

      function showDetailAuthCols(){
    		var $tr = $("thead tr");
    		var $th = $tr.find("th");
    		if(fields==null){
    			return;
    		}
    		for(var i=0;i<$th.length;i++){
    			var field = $th.eq(i).attr("data-field");
    			if(!field){
    				continue;
    			}
    			var index = $.inArray(field,fields);
    			if(index==-1){
    				$th.eq(i).hide();
    				var $tbTr = $("tbody tr");
    				for(var j=0;j<$tbTr.length;j++){
    					$tbTr.eq(j).find("td").eq(i).hide();
    				}
    			}
    		}
    	}
      
/**
 * 数值千分位
 * @param {} n
 * @return {}
 */
function convertQuartile(n){
　　var re=/\d{1,3}(?=(\d{3})+$)/g;
　　var n1=n.replace(/^(\d+)((\.\d+)?)$/,function(s,s1,s2){return s1.replace(re,"$&,")+s2;});
　　return n1;
}
