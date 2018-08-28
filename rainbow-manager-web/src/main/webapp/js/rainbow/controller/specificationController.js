 //控制层 
myApp.controller('specificationController' ,function($scope, $controller, specificationService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
	//查询实体 
	$scope.find=function(id){				
		specificationService.find(id).success(
			function(response){
				$scope.specification= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		specificationService.save($scope.specification).success(
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
		specificationService.dele( $scope.selectIds ).success(
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
		specificationService.search(page, rows, $scope.queryVo).success(
			function(response){
				$scope.list=response.records;//显示当前页数据 	
				$scope.paginationConf.totalItems=response.total;//更新总记录数 
			}			
		);
	}
	
	//增加规格选项行
	$scope.addTableRow=function(){
		$scope.specification.options.push({});			
	}
	
	//删除规格选项行
	$scope.deleTableRow=function(index){
		$scope.specification.options.splice(index,1);
	}
    
});