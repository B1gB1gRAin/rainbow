 //控制层 
myApp.controller('indexController' ,function($scope, $controller, loginService){	
	
	//获取当前登录用户信息
	$scope.getCurrentUser=function(id){				
		loginService.getCurrentUser().success(
			function(response){
				$scope.currentUser= response;					
			}
		);				
	}
    
});