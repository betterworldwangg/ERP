$(function(){

	
	
	
	$(".oper").live("click",function(){
		
		
		
		
		$tR = $(this).parent().parent();
		
		var orderDuuid = $tR.attr("orderDuuid");
		
		$.post("orderDetail_ajaxFindByUuid",{"orderDuuid":orderDuuid},function(data){
			
			alert(nameArr);
			
			$tRNext = $('<tr class="in"></tr>');
			$tD1 = $('<td align="right">仓库：</td>');
			
			$tD2 = $('<td height="30"></td>');
			$select = $('<select style="width:200px"></select>');
			
			for(var i = 0 ; i<nameArr.length;i++)
			{
				$option = $('<option value="11">'+i+'号仓库</option>');
				$select.append($option);
			}
			$tD2.append($select);
			
			$tD3 = $('<td align="right">入库量：</td>');
			
			$tD4 = $('<td><input type="text" value="50" id="inNum"></td>');
			
			$tD5 = $('<td align="center"><a class="ajaxIn xiu" href="javascript:void(0)"><img src="images/icon_3.gif">确定</a></td>');
			
			$tRNext.append($tD1);
			$tRNext.append($tD2);
			$tRNext.append($tD3);
			$tRNext.append($tD4);
			$tRNext.append($tD5);
			
			$tR.after($tRNext);
		});
		
		
	});
});