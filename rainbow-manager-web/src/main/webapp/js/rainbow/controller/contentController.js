 //控制层 
myApp.controller('contentController' ,function($scope, $controller, contentService, contentCategoryService, uploadService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
	//查询实体 
	$scope.find=function(id){				
		contentService.find(id).success(
			function(response){
				$scope.content= response;					
			}
		);				
	}
	
	//保存 
	$scope.save=function(){				
		contentService.save($scope.content).success(
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
		contentService.dele( $scope.selectIds ).success(
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
		contentService.search(page, rows, $scope.queryVo).success(
			function(response){
				$scope.list=response.records;//显示当前页数据 	
				$scope.paginationConf.totalItems=response.total;//更新总记录数 
			}			
		);
	}
    
	//上传
	$scope.uploadFile=function(){
		uploadService.uploadFile().success(
				function(response){
					if(response.code="200"){
						alert(response.message);
						$scope.content.pic= response.data;
					}else{
						alert(response.message);
					}
				}
		).error(function(){
			 alert("上传发生错误");
		});
	}
	
	$scope.status=['无效','有效'];
	
	$scope.contentCategoryMap=[];
	//查询广告分类 列表
	$scope.findContentCategoryList=function(){
		contentCategoryService.findAll().success(
			function(response){
				$scope.contentCategoryList=response;
				for(var i=0;i<response.length;i++){
					$scope.contentCategoryMap[response[i].id]=response[i].name;
				}
			}
		);
	}
});