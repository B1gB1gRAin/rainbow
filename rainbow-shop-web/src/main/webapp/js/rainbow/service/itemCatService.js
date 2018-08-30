//服务层
myApp.service('itemCatService',function($http){
	
	this.findAll = function(){
		return $http.get("/itemCat/findAll.do");
	}
	
	//根据parentId查询分类
	this.findByParentId = function(parentId){
		return $http.get('/itemCat/findByParentId.do?parentId='+parentId);
	}
	
	//查询实体
	this.find=function(id){
		return $http.get('/itemCat/find.do?id='+id);
	}
	//保存 
	this.save=function(entity){
		return  $http.post('/itemCat/save.do',entity );
	}
	
	//删除
	this.dele=function(ids){
		return $http.get('/itemCat/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,queryVo){
		return $http.post('/itemCat/search.do?currentPage='+page+"&size="+rows, queryVo);
	}  
});