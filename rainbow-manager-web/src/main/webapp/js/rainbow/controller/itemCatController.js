 //控制层 
myApp.controller('itemCatController' ,function($scope, $controller, itemCatService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
	//查询实体 
	$scope.find=function(id){				
		itemCatService.find(id).success(
			function(response){
				$scope.itemCat= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){	
		$scope.itemCat.parentId=$scope.parentId;//赋予上级ID
		itemCatService.save($scope.itemCat).success(
			function(response){
				if(response.code == '200'){
					//重新查询 
		        	//$scope.reloadList();//重新加载
					$scope.findByParentId($scope.parentId);
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		itemCatService.dele( $scope.selectIds ).success(
			function(response){
				if(response.code == '200'){
					//$scope.reloadList();//刷新列表
					$scope.findByParentId($scope.parentId);
					$scope.selectIds=[];
				}						
			}		
		);				
	}
	
	$scope.queryVo={};//定义搜索对象 
	
	//搜索
	$scope.search=function(page,rows){			
		itemCatService.search(page, rows, $scope.queryVo).success(
			function(response){
				$scope.list=response.records;//显示当前页数据 	
				$scope.paginationConf.totalItems=response.total;//更新总记录数 
			}			
		);
	}
	$scope.parentId=0;//上级ID
	//根据parentID查询分类信息
	$scope.findByParentId=function(parentId){
		$scope.parentId=parentId;//记住上级ID
		itemCatService.findByParentId(parentId).success(
				function(response){
					$scope.list=response;
				}
		);
	}
	
	$scope.grade=1;//默认为1级	
	//设置级别
	$scope.setGrade=function(value){
		$scope.grade=value;
	}	
	
	//读取列表
	$scope.selectList=function(p_itemCat){			
		if($scope.grade==1){//如果为1级
			$scope.itemCat_1=null;	
			$scope.itemCat_2=null;
		}		
		if($scope.grade==2){//如果为2级
			$scope.itemCat_1=p_itemCat;	
			$scope.itemCat_2=null;
		}		
		if($scope.grade==3){//如果为3级
			$scope.itemCat_2=p_itemCat;		
		}		
		$scope.findByParentId(p_itemCat.id);	//查询此级下级列表
	}
    
});