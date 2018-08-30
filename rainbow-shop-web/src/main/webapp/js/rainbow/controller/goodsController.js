 //控制层 
myApp.controller('goodsController' ,function($scope, $controller, $location, goodsService, uploadService, itemCatService, typeTemplateService){	
	
	$controller('baseController',{$scope:$scope});//继承
	
	//查询实体 
	$scope.find=function(){	
		var id=$location.search()['id'];
		if(id==null){
			return ;
		}	
		goodsService.find(id).success(
			function(response){
				$scope.goods= response;	
				editor.html($scope.goods.goodsDesc.introduction );//商品介绍 
				//商品图片
				$scope.goods.goodsDesc.itemImages=JSON.parse($scope.goods.goodsDesc.itemImages);
				//扩展属性
				$scope.goods.goodsDesc.customAttributeItems=JSON.parse($scope.goods.goodsDesc.customAttributeItems);
				//规格选择
				$scope.goods.goodsDesc.specificationItems= JSON.parse($scope.goods.goodsDesc.specificationItems);
				//转换sku列表中的规格对象
				
				for(var i=0;i< $scope.goods.itemList.length;i++ ){
					$scope.goods.itemList[i].spec=  JSON.parse($scope.goods.itemList[i].spec);					
				}
			}
		);				
	}
	
	//保存 
	$scope.save=function(){		
		$scope.goods.goodsDesc.introduction=editor.html();
		goodsService.save($scope.goods).success(
			function(response){
				if(response.code == '200'){
					alert('保存成功');					
					//$scope.goods={};
					//editor.html("");//清空富文本编辑器
					location.href='/admin/goods.html';
				}else{
					alert(response.message);
				}
			}		
		);				
	}
	 
	//批量删除 
	$scope.dele=function(){			
		//获取选中的复选框			
		goodsService.dele( $scope.selectIds ).success(
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
		goodsService.search(page, rows, $scope.queryVo).success(
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
						$scope.image_entity.url= response.data;
					}else{
						alert(response.message);
					}
				}
		).error(function(){
			 alert("上传发生错误");
		});
	}
	
	$scope.goods={
					goodsDesc:{
						itemImages:[],
						specificationItems:[]
					}
				 };
	//将当前上传的图片实体存入图片列表
	$scope.add_image_entity=function(){
		$scope.goods.goodsDesc.itemImages.push($scope.image_entity);	
		//去除file上之前选中的文件
		$("#file").val("");
	}
	
	//移除图片
	$scope.remove_image_entity=function(index){
		$scope.goods.goodsDesc.itemImages.splice(index,1);
	}
	
	//查询一级商品分类列表
	$scope.selectItemCat1List=function(){
	
		itemCatService.findByParentId(0).success(
			function(response){
				$scope.itemCat1List=response;			
			}
		);
		
	}
	
	//$watch方法用于监控某个变量的值，当被监控的值发生变化，就自动执行相应的函数
	//查询二级商品分类列表
	$scope.$watch('goods.category1Id',function(newValue,oldValue){
		if(newValue!=undefined){
			itemCatService.findByParentId(newValue).success(
					function(response){
						$scope.itemCat2List=response;			
					}
			);
		}
		
	});
	
	//查询三级商品分类列表
	$scope.$watch('goods.category2Id',function(newValue,oldValue){
		if(newValue!=undefined){
			itemCatService.findByParentId(newValue).success(
					function(response){
						$scope.itemCat3List=response;			
					}
			);
		}
		
	});
	
	//读取模板ID
	$scope.$watch('goods.category3Id',function(newValue,oldValue){
		if(newValue!=undefined){
			itemCatService.find(newValue).success(
				function(response){
					$scope.goods.typeTemplateId=response.typeId;
				}
			);
		}
	});
	
	//读取模板ID后，读取品牌列表 扩展属性  规格列表
	$scope.$watch('goods.typeTemplateId',function(newValue,oldValue){
		if(newValue!=undefined){//防止第一次加载报错
			typeTemplateService.find(newValue).success(
					function(response){
						$scope.typeTemplate=response;// 模板对象 
										
						$scope.typeTemplate.brandIds= JSON.parse($scope.typeTemplate.brandIds);//品牌列表类型转换
						//扩展属性
						if( $location.search()['id']==null ){//如果是增加商品
							$scope.goods.goodsDesc.customAttributeItems= JSON.parse($scope.typeTemplate.customAttributeItems);
						}
					}
				);
				//读取规格
				typeTemplateService.findSpecList(newValue).success(
					function(response){
						$scope.specList=response;
					}
				);	
		}
		
	});
	
	$scope.updateSpecAttribute=function($event,name,value){
		
		var object= $scope.searchObjectByKey(
				$scope.goods.goodsDesc.specificationItems ,'attributeName', name);
		
		if(object!=null){	
			if( $event.target.checked ){
				object.attributeValue.push(value);		
			}else{//取消勾选
				object.attributeValue.splice( object.attributeValue.indexOf(value ) ,1);//移除选项
				//如果选项都取消了，将此条记录移除
				if(object.attributeValue.length==0){
					$scope.goods.goodsDesc.specificationItems.splice(
							$scope.goods.goodsDesc.specificationItems.indexOf(object),1);
				}
				
			}
		}else{	
			$scope.goods.goodsDesc.specificationItems.push({"attributeName":name,"attributeValue":[value]});
		}
		
	}
	
	//创建SKU列表
	$scope.createItemList=function(){
		
		$scope.goods.itemList=[{spec:{},price:0,num:99999,status:'0',isDefault:'0'} ];//列表初始化
		
		var items= $scope.goods.goodsDesc.specificationItems;
		
		for(var i=0;i<items.length;i++){
			$scope.goods.itemList= addColumn( $scope.goods.itemList, items[i].attributeName,items[i].attributeValue );			
		}	
		
	}
	
	addColumn=function(list,columnName,columnValues){
		
		var newList=[];		
		for(var i=0;i< list.length;i++){
			var oldRow=  list[i];			
			for(var j=0;j<columnValues.length;j++){
				var newRow=  JSON.parse( JSON.stringify(oldRow)  );//深克隆
				newRow.spec[columnName]=columnValues[j];
				newList.push(newRow);
			}			
		}		
		return newList;
	}
	
	
	//格式化审核状态
	$scope.status=['未审核','已审核','审核未通过','已关闭'];
	
	$scope.itemCatList=[];//商品分类列表
	//查询商品分类列表
	$scope.findItemCatList=function(){
		itemCatService.findAll().success(
			function(response){
				for(var i=0;i<response.length;i++){
					$scope.itemCatList[response[i].id]=response[i].name;
				}
			}
		);
		
	}
	
	//判断规格与规格选项是否应该被勾选
	$scope.checkAttributeValue=function(specName,optionName){
		var items= $scope.goods.goodsDesc.specificationItems;
		var object =$scope.searchObjectByKey( items,'attributeName', specName);
		
		if(object!=null){
			if(object.attributeValue.indexOf(optionName)>=0){//如果能够查询到规格选项
				return true;
			}else{
				return false;
			}			
		}else{
			return false;
		}		
	}
});