//服务层
myApp.service('goodsService',function($http){
	//查询实体
	this.find=function(id){
		return $http.get('/goods/find.do?id='+id);
	}
	//保存 
	this.save=function(entity){
		return  $http.post('/goods/save.do',entity );
	}
	
	//删除
	this.dele=function(ids){
		return $http.get('/goods/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,queryVo){
		return $http.post('/goods/search.do?currentPage='+page+"&size="+rows, queryVo);
	}    
});