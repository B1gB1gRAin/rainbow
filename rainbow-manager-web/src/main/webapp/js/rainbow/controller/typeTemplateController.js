 //控制层 
myApp.controller('typeTemplateController' ,function($scope, $controller, typeTemplateService, brandService, specificationService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
	//查询实体 
	$scope.find=function(id){				
		typeTemplateService.find(id).success(
			function(response){
				$scope.typeTemplate= response;
				//转换字符串为json对象（集合）
				$scope.typeTemplate.brandIds=  JSON.parse( $scope.typeTemplate.brandIds);
				$scope.typeTemplate.specIds= JSON.parse($scope.typeTemplate.specIds);
				$scope.typeTemplate.customAttributeItems = JSON.parse($scope.typeTemplate.customAttributeItems);
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		typeTemplateService.save($scope.typeTemplate).success(
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
		typeTemplateService.dele( $scope.selectIds ).success(
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
		typeTemplateService.search(page, rows, $scope.queryVo).success(
			function(response){
				$scope.list=response.records;//显示当前页数据 	
				$scope.paginationConf.totalItems=response.total;//更新总记录数 
			}			
		);
	}
	
	$scope.brandList={data:[]};//品牌列表
    
	//读取品牌列表
	$scope.findBrandList=function(){
		brandService.selectOptionList().success(
			function(response){
				$scope.brandList={data:response};
			}
		);		
	}
	
	$scope.specList={data:[]};//规格列表
	
	//读取规格列表
	$scope.findSpecList=function(){
		specificationService.selectOptionList().success(
				function(response){
					$scope.specList={data:response};
				}
		);		
	}
	
	//增加扩展属性行
	$scope.addTableRow=function(){
		$scope.typeTemplate.customAttributeItems.push({});
	}
	//删除扩展属性行
	$scope.deleTableRow=function(index){
		$scope.typeTemplate.customAttributeItems.splice( index,1);
	}
    
});