//服务层
myApp.service('contentService',function($http){
	//查询实体
	this.find=function(id){
		return $http.get('/content/find.do?id='+id);
	}
	//保存 
	this.save=function(entity){
		return  $http.post('/content/save.do',entity );
	}
	
	//删除
	this.dele=function(ids){
		return $http.get('/content/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,queryVo){
		return $http.post('/content/search.do?currentPage='+page+"&size="+rows, queryVo);
	}    
});