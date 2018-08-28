//服务层
myApp.service('uploadService',function($http){
	//查询实体
	this.uploadFile=function(){
		var formdata=new FormData();
		formdata.append('file',file.files[0]);//file 文件上传框的name
		
		return $http({
			url:'/upload.do',		
			method:'post',
			data:formdata,
			//anjularjs对于post和get请求默认的Content-Type header 是application/json。
			//通过设置‘Content-Type’: undefined，这样浏览器会帮我们把Content-Type 设置为 multipart/form-data
			headers:{ 'Content-Type':undefined },
			//通过设置 transformRequest: angular.identity ，anjularjs transformRequest function 将序列化我们的formdata object.
			transformRequest: angular.identity			
		});
	}
});