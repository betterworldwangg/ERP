<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/Calendar.js"></script>

<script type="text/javascript">

	$(function(){
	
		$("#query").click(function(){
			$("form:first").submit();
		});
	
		$(".info").live("click",function(){
		
			var jsonParam = {};
			
			jsonParam["bhq.type"] = $("[name='bhq.type']").val();
			jsonParam["bhq.startTime"] = $("[name='bhq.startTime']").val();
			jsonParam["bhq.endTime"] = $("[name='bhq.endTime']").val();
			jsonParam["bhq.supplierUuid"] = $("[name='bhq.supplierUuid']").val();
			jsonParam["bhq.goodsUuid"]= $(this).attr("goodsUuid");
			
			$(".ajaxMsg").remove();
			
			$nowTR = $(this).parent().parent();
			$trTitle = $('<tr align="center" style="background:url(images/table_bg.gif) repeat-x;" class="ajaxMsg"><td height="30">订单号</td><td>订单时间</td><td>数量</td><td>单价</td><td>合计</td></tr>');
			$nowTR.after($trTitle);
		
			$nowTR = $trTitle;
			
			$.post("bill_ajaxFindODmByGoods",jsonParam ,function(data){
				
				var sum = 0 ; 
				for(var i = 0 ; i<data.length;i++)
				{
					var orderDM = data[i];
					
					$tR1 = $('<tr align="center" class="ajaxMsg"></tr>');
					
					$tD1 = $('<td height="30">'+orderDM.orderM.orderNum+'</td>');
					$tD2 = $('<td>'+orderDM.orderM.createTimeView+'</td>');
					$tD3 = $('<td>'+orderDM.num+'</td>');
					$tD4 = $('<td align="right">'+orderDM.priceView+'&nbsp;元</td>');
					$tD5 = $('<td align="right">'+orderDM.totalPriceView+'&nbsp;元</td>');
					
					$tR1.append($tD1);
					$tR1.append($tD2);
					$tR1.append($tD3);
					$tR1.append($tD4);
					$tR1.append($tD5);
					
					sum += orderDM.num*orderDM.priceView;
					$nowTR.after($tR1);
					
					$nowTR = $tR1;
				}
				
				$endTR = $('<tr align="center" class="ajaxMsg"><td height="30" align="right" colspan="4">总计：</td><td align="right">'+intToFloat(sum)+'&nbsp;元</td></tr>');
				$nowTR.after($endTR);
				
			});
		});
		
		$(".ajaxMsg").live("click",function(){
			
			$(".ajaxMsg").remove();
		});
	});
	
//保留两位小数
function intToFloat(val){
	return new Number(val).toFixed(2);
}

</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">进货报表</span>
		</div>
	</div>
	<div class="content-text">
		<form action="bill_list" method="post">
			<div class="square-o-top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0"
					style="font-size:14px; font-weight:bold; font-family:"黑体";">
					<tr>
						<td width="70" height="30">报表类别:</td>
						<td width="140">
							<input type="radio" name="all" checked="checked">商品名称
						</td>
						<td width="70">订单类别:</td>
						<td width="190">
							<s:select name="bhq.type" list="@org.erp.invoice.order.entity.OrderModel@buyTypeMap" headerKey="-1" headerValue="----请-选-择----"/>
							
						</td>
						<td width="70">开始日期:</td>
						<td width="190">
							<input type="text" size="18" onfocus="c.showMoreDay=false;c.show(this);" />
							<s:hidden name="bhq.startTime"/>
						<td ><a id="query"> <img
								src="images/can_b_01.gif" border="0" /> </a></td>
					</tr>
					<tr>
						<td height="30">&nbsp;</td>
						<td>
							<input type="radio" name="all">销售人员
						</td>
						<td>厂商名称:</td>
						<td>
							<s:select name="bhq.supplierUuid" list="supplierList" listKey="uuid" listValue="name" headerKey="-1" headerValue="----请-选-择----"/>
							
						</td>
						<td>结束日期:</td>
						<td width="190">
							<input type="text" size="18" onfocus="c.showMoreDay=false;c.show(this);" />
							<s:hidden name="bhq.endTime"/>
						<td>
							<a href="bill_getExecelBill?bhq.type=${bhq.type }&bhq.supplierUuid=${bhq.supplierUuid}&bhq.startTime=${bhq.startTime}&bhq.endTime=${endTime}">
								<img src="images/can_b_03.gif" border="0" />
							</a>
					</tr>
				</table>
			</div>
			<!--"square-o-top"end-->
			<div class="square-order">
				<table width="70%" border="1" cellpadding="0" cellspacing="0" style="float:left;">
					<tr align="center"
						style="background:url(images/table_bg.gif) repeat-x;">
						<td colspan="2" width="49%" height="30">商品名称</td>
						<td colspan="2" width="28%">总数量</td>
						<td width="23%">详情</td>
					</tr>
						<s:iterator value="billList" var="obj">
							<tr align="center" bgcolor="#FFFFFF">
								<td colspan="2" width="30%" height="30">${obj[0].name }</td>
								<td colspan="2">${obj[1]}</td>
								<td>
									<a goodsUuid="${obj[0].uuid }" href="javascript:void(0)" class="xiu info" value="1">
										详情
									</a>
								</td>
							</tr>
						</s:iterator>
				</table>
				<div style="float:right;"> 
					<a href="bill_getPieDataImage?bhq.type=${bhq.type }&bhq.supplierUuid=${bhq.supplierUuid}&bhq.startTime=${bhq.startTime}&bhq.endTime=${endTime}">
						<img id="pei" src="bill_getPieDataImage?bhq.type=${bhq.type }&bhq.supplierUuid=${bhq.supplierUuid}&bhq.startTime=${bhq.startTime}&bhq.endTime=${endTime}" width="228px" height="180px">
					</a>
				</div>
			</div>
		</form>
	</div>
	<div class="content-bbg"></div>
</div>
