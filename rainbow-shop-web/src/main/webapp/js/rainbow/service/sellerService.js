//服务层
myApp.service('sellerService',function($http){
	//查询实体
	this.find=function(id){
		return $http.get('/seller/find.do?id='+id);
	}
	//保存 
	this.save=function(entity){
		return  $http.post('/seller/save.do',entity );
	}
	
	//删除
	this.dele=function(ids){
		return $http.get('/seller/delete.do?ids='+ids);
	}
	//搜索
	this.search=function(page,rows,queryVo){
		return $http.post('/seller/search.do?currentPage='+page+"&size="+rows, queryVo);
	}    
	
	//下拉列表数据
	this.selectOptionList=function(){
		return $http.get('/seller/selectOptionList.do');
	}
});