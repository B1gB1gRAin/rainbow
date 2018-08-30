 //控制层 
myApp.controller('contentCategoryController' ,function($scope, $controller, contentCategoryService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
	//查询实体 
	$scope.find=function(id){				
		contentCategoryService.find(id).success(
			function(response){
				$scope.contentCategory= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		contentCategoryService.save($scope.contentCategory).success(
			function(response){
				if(response.code == '200'){
					//重新查询 
		        	$scope.reloadList();//重新加载
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		contentCategoryService.dele( $scope.selectIds ).success(
			function(response){
				if(response.code == '200'){
					$scope.reloadList();//刷新列表
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.queryVo={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		contentCategoryService.search(page, rows, $scope.queryVo).success(
			function(response){
				$scope.list=response.records;//显示当前页数据 	
				$scope.paginationConf.totalItems=response.total;//更新总记录数 
			}			
		);
	}
    
});