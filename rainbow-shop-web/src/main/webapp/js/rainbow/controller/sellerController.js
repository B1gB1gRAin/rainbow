 //控制层 
myApp.controller('sellerController' ,function($scope, $controller, sellerService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
	//查询实体 
	$scope.find=function(id){				
		sellerService.find(id).success(
			function(response){
				$scope.seller= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		sellerService.save($scope.seller).success(
			function(response){
				if(response.code == '200'){
					location.href='/shoplogin.html';
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		sellerService.dele( $scope.selectIds ).success(
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
		sellerService.search(page, rows, $scope.queryVo).success(
			function(response){
				$scope.list=response.records;//显示当前页数据 	
				$scope.paginationConf.totalItems=response.total;//更新总记录数 
			}			
		);
	}
    
});