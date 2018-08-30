//服务层
myApp.service('contentCategoryService',function($http){
	//查询所有
	this.findAll= function(){
		return $http.get("/contentCategory/getAll.do");
	}
	
	//查询实体
	this.find=function(id){
		return $http.get('/contentCategory/find.do?id='+id);
	}
	//保存 
	this.save=function(entity){
		return  $http.post('/contentCategory/save.do',entity );
	}
	
	//删除
	this.dele=function(ids){
		return $http.get('/contentCategory/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,queryVo){
		return $http.post('/contentCategory/search.do?currentPage='+page+"&size="+rows, queryVo);
	}    
});