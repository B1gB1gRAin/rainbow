//服务层
myApp.service('brandService',function($http){
	//查询实体
	this.find=function(id){
		return $http.get('/brand/find.do?id='+id);
	}
	//保存 
	this.save=function(entity){
		return  $http.post('/brand/save.do',entity );
	}
	
	//删除
	this.dele=function(ids){
		return $http.get('/brand/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,queryVo){
		return $http.post('/brand/search.do?currentPage='+page+"&size="+rows, queryVo);
	}    
	
	//下拉列表数据
	this.selectOptionList=function(){
		return $http.get('/brand/selectOptionList.do');
	}
});