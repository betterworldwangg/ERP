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
	
	//点击供应商select三级联动
	$("#ordsupplier").live("change",function(){
		var supplierUuid = $(this).val();
		$.post("order_ajaxGetGoodTypeBySupp",{"supplierUuid":supplierUuid},function(data){
			//将原有商品类别select置空
			$("#orderGoodType").empty();
			
			//遍历返回data数据中商品类别
			for(var i = 0; i<data.goodsTypeList.length;i++)
			{
				var goodType = data.goodsTypeList[i];
				
				$obj = $("<option value='"+goodType.uuid+"'>"+goodType.name+"</option>");
				//将遍历到商品类别填入select中
				$("#orderGoodType").append($obj);
			}
			//将商品select置空
			$("#ordergoods").empty();
			for(var i = 0 ; i<data.goodsList.length;i++)
			{
				var goods = data.goodsList[i];
				$obj =$("<option value='"+goods.uuid+"'>"+goods.name+"</option>");
				//将接收到的商品填入select中
				$("#ordergoods").append($obj);
			}
			//修改商品价格
			var price = data.goods.inPriceView;
			$("[name=prices]").val(price);
			$(".total").text(price+" 元");
		});
		sumtotal();
	});
	//改变商品类别二级联动
	$(".goodsType").live("change",function(){
		
		var $tr = $(this).parent().parent();
		var $goods = $tr.children("td:eq(1)").children("select");
		var $num = $tr.children("td:eq(2)").children("input");
		var $price = $tr.children("td:eq(3)").children("input");
		var $total =  $tr.children("td:eq(4)");
		
		
		var goodsTypeUuid = $(this).val();
		var goodsArr = $(".ordergoods");
		
		var used = "";
		
		for(var i = 0 ; i<goodsArr.length;i++)
		{
			used += goodsArr[i].value;
			used +=",";
		}
		
		$.post("order_ajaxGetGoodsByType",{"goodsTypeUuid":goodsTypeUuid,"used":used},function(data){
			//将商品select置空
			
			$goods.empty();
			
			for(var i = 0; i<data.goodsList.length;i++)
			{
				var goods = data.goodsList[i];
				
				$obj = $("<option value='"+goods.uuid+"'>"+goods.name+"</option>");
				//将接收到商品信息填入商品select中
				$goods.append($obj);
			}
			
			var price = data.goods.inPriceView;
			$num.val(1);
			$price.val(price);
			$total.text(price+" 元");
		});
		sumtotal();
	});
	//改变商品select产生对价格的联动
	$(".ordergoods").live("change",function(){
		
		var $tr = $(this).parent().parent();
		var $goods = $tr.children("td:eq(1)").children("select");
		var $num = $tr.children("td:eq(2)").children("input");
		var $price = $tr.children("td:eq(3)").children("input");
		var $total =  $tr.children("td:eq(4)");
		
		var goodsUuid = $(this).val();
		$.post("order_ajaxGetPrice",{"goodsUuid":goodsUuid},function(data){
			
			var price = data.inPriceView;
			
			$num.val(1);
			$price.val(price);
			$total.text(price+" 元");
		});
		sumtotal();
	});
	
	
	//动态增加采购订单中订单项
	
	$("#add").click(function(){
		
		//将所有商品列出  supplier
		//根据已有商品列出剩余商品supplier goods
		//如果该商品类型已没有商品，找下一个商品类型
		
		var $tr = $(this).parent().parent();
		var $goods = $tr.children("td:eq(1)").children("select");
		var $num = $tr.children("td:eq(2)").children("input");
		var $price = $tr.children("td:eq(3)").children("input");
		var $total =  $tr.children("td:eq(4)");
		
		
		var goodsArr = $(".ordergoods");
		var supplierUuid = $("#ordsupplier").val();
		
		var used = "";
	
		for(var i = 0 ; i<goodsArr.length;i++)
		{
			
			used += goodsArr[i].value;
			used +=",";
		}
		
		$.post("order_ajaxGetGoodTypeBySupp2",{"supplierUuid":supplierUuid,"used":used},function(data){
		
			
			var price = data.goods.inPriceView;
			
			$("#ordsupplier").attr("disabled",true);
			$(".goodsType").attr("disabled",true);
			$(".ordergoods").attr("disabled",true);
			
			//创建要添加的行标签
			$tr = $('<tr align="center" bgcolor="#FFFFFF"></tr>');
			//创建列标签
			$td1 = $('<td height="30"></td>');
			//创建商品类别select标签
			$select1 = $('<select id="orderGoodType" class="goodsType"></select>');
			//创建select中option标签
			for(var i = 0 ; i<data.goodsTypeList.length; i++)
			{
				var goodsType = data.goodsTypeList[i];
				$option = $('<option value="'+goodsType.uuid+'">'+goodsType.name+'</option>');
				$select1.append($option);
			}
			$td1.append($select1);
			$tr.append($td1);
			
			//创建商品select
			$td2 = $('<td height="30"></td>');
			$select2 = $('<select name="goodsUuids" class="ordergoods" id="ordergoods"></select>');
			for(var i = 0 ; i<data.goodsList.length; i++)
			{
				var goods = data.goodsList[i];
				$option = $('<option value="'+goods.uuid+'">'+goods.name+'</option>');
				$select2.append($option);
			}
			$td2.append($select2);
			$tr.append($td2);
			
			//采购数量
			$td3 = $('<td height="30"><input name="nums" class="num order_num" style="width:67px;border:1px solid black;text-align:right;padding:2px" type="text" value="1"/></td>');
			$tr.append($td3);
			
			//采购单价
			$td4 = $('<td height="30"><input name="prices" class="prices order_num" style="width:93px;border:1px solid black;text-align:right;padding:2px" type="text" value="'+price+'"/> 元</td>');
			$tr.append($td4);
			
			//合计
			$td5 = $('<td height="30" class="total" align="right">'+price+'</td>');
			$tr.append($td5);
			
			//删除
			$td6 = $('<td height="30"><a class="deleteBtn delete xiu" value="4"><img src="images/icon_04.gif" /> 删除</a></td>');
			$tr.append($td6);
			
			$("#finalTr").before($tr);
			
			if(data.goodsTypeList.length == 1 && data.goodsList.length ==1)
			{
				$("#add").css("display","none");
			}
			
			$num.val(1);
			$price.val(price);
			$total.html(price+" 元");
			
			sumtotal();
			
		});
		
	});
	
	//删除订单项
	
	$(".deleteBtn").live("click",function(){
		
		if($(".deleteBtn").length == 1)
		{
			alert("订单项至少有一项");
			return;
		}
		
		$tR = $(this).parent().parent();
		$("#add").css("display","inline");
		$tR.remove();
		sumtotal();
	});
	
	//修改数量
	
	$(".num").live("keyup",function(){
		$(this).val($(this).val().replace(/[^\d]/g,""));
		count($(this));
	});
	
	//修改价格
	
	$(".prices").live("keyup",function(){
		//先把非数字的都替换掉，除了数字和. 
		$(this).val($(this).val().replace(/[^\d.]/g,""));
        //必须保证第一个为数字而不是. 
        $(this).val($(this).val().replace(/^\./g,"0."));
        //保证只有出现一个.而没有多个. 
        $(this).val($(this).val().replace(/\.{2,}/g,"."));
        //保证.只出现一次，而不能出现两次以上
        $(this).val($(this).val().replace(".","$#$").replace(/\./g,"").replace("$#$",".")); 
        count($(this));
	});
	
	//计算total
	
	function count(obj)
	{
		var $tr = obj.parent().parent();
		var $total =  $tr.children("td:eq(4)");
		var num = $tr.children("td:eq(2)").children("input").val();
		var price = $tr.children("td:eq(3)").children("input").val();
		
		var total = num*price;
		$total.text(intToFloat(total)+" 元");
		
		
		sumtotal();
	}
	
	//计算所有商品价格总和
	
	function sumtotal()
	{
		 var numArr = $(".num");
		 var pricesArr = $(".prices");
		
		 var sum = 0;
		 for(var i = 0 ; i<numArr.length;i++)
		 {
			var num = numArr[i].value;
			var pri = pricesArr[i].value;
			var total = num*pri;
			sum += total;
		 }
		 $(".all").html(intToFloat(sum)+" 元");
	}
	
	//保存时，将所有disabled改为false,这样才可以将数据传入后台
	
	$("#submitOrder").click(function(){
		
		$("#ordsupplier").attr("disabled",false);
		$(".goodsType").attr("disabled",false);
		$(".ordergoods").attr("disabled",false);
	});
	
});
//保留两位小数
function intToFloat(val){
	return new Number(val).toFixed(2);
}





