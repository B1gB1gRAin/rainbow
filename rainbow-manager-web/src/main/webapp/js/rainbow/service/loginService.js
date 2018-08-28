//服务层
myApp.service('loginService',function($http){
	//查询实体
	this.getCurrentUser=function(){
		return $http.get('/login/getCurrentUser.do');
	}
});