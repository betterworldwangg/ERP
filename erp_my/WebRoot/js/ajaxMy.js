$(function(){
	
	//商品模块，增加修改页面Ajax响应
	$("#supplier").change(function(){
		var supplierUuid = $(this).val();
		$.post("goods_ajaxGetGdtBySuppId",{"supplierUuid":supplierUuid},function(data){
			$("#goodsType").empty();
			for(var i = 0; i<data.goodsTypeList.length;i++)
			{
				var goodType = data.goodsTypeList[i];
				
				$obj = $("<option value='"+goodType.uuid+"'>"+goodType.name+"</option>");
				
				$("#goodsType").append($obj);
			}
		});
	});
	
	//商品模块，增加修改页面Ajax响应
	$("#ordsupplier").change(function(){
		var supplierUuid = $(this).val();
		$.post("order_ajaxGetGoodTypeBySupp",{"supplierUuid":supplierUuid},function(data){
			$("#orderGoodType").empty();
			
			for(var i = 0; i<data.goodsTypeList.length;i++)
			{
				var goodType = data.goodsTypeList[i];
				
				$obj = $("<option value='"+goodType.uuid+"'>"+goodType.name+"</option>");
				
				$("#orderGoodType").append($obj);
			}
			$("#ordergoods").empty();
			for(var i = 0 ; i<data.goodsList.length;i++)
			{
				var goods = data.goodsList[i];
				$obj =$("<option value='"+goods.uuid+"'>"+goods.name+"</option>");
				
				$("#ordergoods").append($obj);
			}
			
			var price = data.goods.inPriceView;
			$("[name=prices]").val(price);
			$(".total").text(price+" 元");
		});
	});
	
	$("#orderGoodType").change(function(){
		var goodsTypeUuid = $(this).val();
		$.post("order_ajaxGetGoodsByType",{"goodsTypeUuid":goodsTypeUuid},function(data){
			$("#ordergoods").empty();
			for(var i = 0; i<data.goodsList.length;i++)
			{
				var goods = data.goodsList[i];
				
				$obj = $("<option value='"+goods.uuid+"'>"+goods.name+"</option>");
				
				$("#ordergoods").append($obj);
			}
		});
	});
	
	$("#ordergoods").change(function(){
		var goodsUuid = $(this).val();
		$.post("order_ajaxGetPrice",{"goodsUuid":goodsUuid},function(data){
			
			var price = data.inPriceView;
			
			$("[name=prices]").val(price);
			$(".total").text(price+" 元");
		});
	});
});
