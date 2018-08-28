//服务层
myApp.service('typeTemplateService',function($http){
	//查询实体
	this.find=function(id){
		return $http.get('/typeTemplate/find.do?id='+id);
	}
	//保存 
	this.save=function(entity){
		return  $http.post('/typeTemplate/save.do',entity );
	}
	
	//删除
	this.dele=function(ids){
		return $http.get('/typeTemplate/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,queryVo){
		return $http.post('/typeTemplate/search.do?currentPage='+page+"&size="+rows, queryVo);
	}    
	
});