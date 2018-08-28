//服务层
myApp.service('specificationService',function($http){
	//查询实体
	this.find=function(id){
		return $http.get('/specification/find.do?id='+id);
	}
	//保存 
	this.save=function(entity){
		return  $http.post('/specification/save.do',entity );
	}
	
	//删除
	this.dele=function(ids){
		return $http.get('/specification/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,queryVo){
		return $http.post('/specification/search.do?currentPage='+page+"&size="+rows, queryVo);
	}    
	
	//下拉列表
	this.selectOptionList=function(){
		return $http.get('/specification/selectOptionList.do');
	}
});