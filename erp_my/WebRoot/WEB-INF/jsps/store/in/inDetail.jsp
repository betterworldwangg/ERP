<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/ajaxInstore.js"></script>
<script type="text/javascript">
$(function(){

	var uuidArr = new Array();
	var nameArr = new Array();
	var i = 0;
	<s:iterator value="storeList">
		uuidArr[i] = ${uuid};
		nameArr[i] = '${name}';
		i++;
	</s:iterator>
	
	
	$(".oper").live("click",function(){
		
		$(".in").remove();
		
		$tRNext = $('<tr class="in"></tr>');
		
		$tR = $(this).parent().parent();
		
		var orderDuuid = $tR.attr("orderDuuid");
		
		$.post("orderDetail_ajaxFindByUuid",{"orderDuuid":orderDuuid},function(data){
			
		
			
			$tD1 = $('<td align="right">仓库：</td>');
			
			$tD2 = $('<td height="30"></td>');
			$select = $('<select style="width:200px" id="store"></select>');
			
			for(var i = 0 ; i<nameArr.length;i++)
			{
				$option = $('<option value="'+uuidArr[i]+'">'+nameArr[i]+'</option>');
				$select.append($option);
			}
			$tD2.append($select);
			
			$tD3 = $('<td align="right">入库量：</td>');
			
			$tD4 = $('<td><input type="text" value="'+data.surplus+'" id="inNum"></td>');
			
			$tD5 = $('<td align="center"><a class="ajaxIn xiu" href="javascript:void(0)"><img src="images/icon_3.gif">确定</a></td>');
			
			$tRNext.append($tD1);
			$tRNext.append($tD2);
			$tRNext.append($tD3);
			$tRNext.append($tD4);
			$tRNext.append($tD5);
			
			$tR.after($tRNext);
		});
		
	});
	
	//入库Ajax
	
	$(".ajaxIn").live("click",function(){
		
		$nowTR = $(this).parent().parent();
		$tR = $nowTR.prev();
		
		//ajax发送:订单明细uuid，入库数量，仓库
		var jsonParam = {};
		
		jsonParam['storeUuid'] = $("#store").val();
		
		jsonParam['inNum'] = $("#inNum").val();
		
		jsonParam['odMuuid'] = $tR.attr("orderDuuid");
		
		$.post("order_ajaxInstore",jsonParam,function(data){
		
			if(data.surplus == 0 && $(".inTR").length ==1)
			{
				$("#allInTitle").show();
				$("#return").show();
				
				$("#inOrderTitle").hide();
				$("#inOrder").hide();
				
				return;
			}	
		
			if(data.surplus == 0)
			{
				$nowTR.remove();
				$tR.remove();
				return;
			}
				
			$tR.children("td:eq(2)").html(data.num-data.surplus);
			$tR.children("td:eq(3)").html(data.surplus);
			$("#inNum").val(data.surplus);
			
		});
		
	});
});
	
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">入库</span>
		</div>
	</div>
	<div class="content-text">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td>订 单 号:</td>
						<td class="order_show_msg">${model.orderNum }</td>
						<td>商品总量:</td>
						<td class="order_show_msg">${model.totalNum }</td>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<center id="inOrderTitle" style="text-decoration:underline;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;单&nbsp;&nbsp;据&nbsp;&nbsp;明&nbsp;&nbsp;细&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<br/>
				<table id="inOrder" width="100%" border="1" cellpadding="0" cellspacing="0">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td width="20%" height="30">商品名称</td>
						<td width="30%">总数量</td>
						<td width="10%">已入库数量</td>
						<td width="30%">剩余数量</td>
						<td width="10%">入库</td>
					</tr>
						<s:iterator value="model.orderDetails">
							<tr class="inTR" orderDuuid = "${uuid }" aa="bb" align="center" bgcolor="#FFFFFF">
								<td height="30">${goodsM.name }</td>
								<td>${num }</td>
								<td>${num-surplus }</td>
								<td>${surplus }</td>
								<td><a href="javascript:void(0)" class="oper xiu"><img src="images/icon_3.gif" />入库</a></td>
							</tr>
						</s:iterator>
				</table>
				
				<center id="allInTitle" style="display:none;font-size:16px; font-weight:bold; font-family:"黑体";">&nbsp;&nbsp;&nbsp;&nbsp;全&nbsp;&nbsp;部&nbsp;&nbsp;入&nbsp;&nbsp;库&nbsp;&nbsp;&nbsp;&nbsp;</center>
				<table id="return" style="display:none" >
					<tr>
						<td>&nbsp;</td>
						<td width="100%" align="center">
							<a href="order_inStoreList" style="color:#f00;font-size:20px;padding-top:2px;font-weight:bold;text-decoration:none;width:82px;height:28px;display:block;background:url(images/btn_bg.jpg)">
								返回
							</a>
						</td>
						<td>&nbsp;</td>
					</tr>
				</table>
			</div>
	</div>
	<div class="content-bbg"></div>
</div>
