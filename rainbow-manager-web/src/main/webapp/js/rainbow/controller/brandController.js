 //控制层 
myApp.controller('brandController' ,function($scope, $controller, brandService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
	//查询实体 
	$scope.find=function(id){				
		brandService.find(id).success(
			function(response){
				$scope.brand= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		brandService.save($scope.brand).success(
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
		brandService.dele( $scope.selectIds ).success(
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
		brandService.search(page, rows, $scope.queryVo).success(
			function(response){
				$scope.list=response.records;//显示当前页数据 	
				$scope.paginationConf.totalItems=response.total;//更新总记录数 
			}			
		);
	}
    
});