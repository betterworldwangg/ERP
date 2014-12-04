<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
<s:hidden name="currPage" id="pageNum"></s:hidden>

	<tr>
		<td width="51%">&nbsp;</td>
		<td width="13%">共${rows }条记录
		<td width="6%">
			<a id="fir" class="sye">首&nbsp;&nbsp;页</a>
		</td>
		<td width="6%">
			<a id="pre" class="sye">上一页</a>
		</td>
		<td width="6%">
			<a id="next" href="javascript:void(0)" class="sye">下一页</a>
		</td>
		<td width="6%">
			<a id="last" class="sye">末&nbsp;&nbsp;页</a>
		</td>
		<td width="12%">当前第<span style="color:red;">${currPage }</span>/${totalPage }页</td>
	</tr>
</table>
<script type="text/javascript">

$(function(){
		var currPage = ${currPage};
		var totalPage = ${totalPage};
		

		$("#next").click(function(){
			
			$("#pageNum").val($("#pageNum").val()*1+1);
			$("form:first").submit();
		});
		$("#pre").click(function(){
		
			$("#pageNum").val($("#pageNum").val()-1);
			$("form:first").submit();
		});
		$("#fir").click(function(){
		
			$("#pageNum").val(1);
			$("form:first").submit();
		});
		$("#last").click(function(){
		
			$("#pageNum").val(${totalPage});
			$("form:first").submit();
		});
		
		if(totalPage == 1)
		{
			$("#fir").css("display","none");
			$("#pre").css("display","none");
			$("#next").css("display","none");
			$("#last").css("display","none");
		}
		if(currPage == 1)
		{
			$("#fir").css("display","none");
			$("#pre").css("display","none");
			$("#next").css("display","inline");
			$("#last").css("display","inline");
		}
		if(currPage == totalPage)
		{
			$("#fir").css("display","inline");
			$("#pre").css("display","inline");
			$("#next").css("display","none");
			$("#last").css("display","none");
		}
		
		
	});
</script>