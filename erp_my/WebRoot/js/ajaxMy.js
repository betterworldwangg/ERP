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
});