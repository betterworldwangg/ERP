<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<link href="css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.8.3.js"></script>
<script type="text/javascript" src="js/Calendar.js"></script>
<script type="text/javascript">
	$(function(){
		$("#all").click(function(){
			$("[name=roleUuids]").attr("checked",$("#all").attr("checked")=="checked");
		});
		$("#reverse").click(function(){
			$("[name=roleUuids]").each(function(){
				$(this).attr("checked", !$(this).attr("checked"));
			});
			checkStatus();
		});
		
		$("[name=roleUuids]").click(function(){
			checkStatus();
		});
		function checkStatus(){
			//全选按钮的状态应该是所有组件的状态的综合结果，都是选中，全选是选中，否则全选是不选中
			var f = true;
			$("[name=roleUuids]").each(function(){
				var flag = $(this).attr("checked")=="checked";
				//&表示位运算，&&表示逻辑与运算
				//js是弱类型语言
				f = f && flag;
			});
			$("#all").attr("checked",f);
		}
	});
</script>
<div class="content-right">
	<div class="content-r-pic_w">
		<div style="margin:8px auto auto 12px;margin-top:6px">
			<span class="page_title">员工管理</span>
		</div>
	</div>
	<div class="content-text">
		<div class="square-order">
			<form action="employee_save" method="post"> 
			<s:hidden name="model.uuid"></s:hidden>
  			<div style="border:1px solid #cecece;">
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				  <tr bgcolor="#FFFFFF">
				    <td>&nbsp;</td>
				  </tr>
				</table>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">用&nbsp;户&nbsp;名</td>
				      <td width="32%">
				      	<s:textfield name="model.userName" size="25"/>
				      </td>
				      <td width="18%"align="center">真实姓名</td>
				      <td width="32%">
				      	<s:textfield name="model.name" size="25"/>
					  </td>
				    </tr>
				    <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td align="center">密&nbsp;&nbsp;&nbsp;&nbsp;码</td>
				      <td>
				      	<input name="model.userPass" type="text" size="25"/>
				      </td>
				      <td  align="center">确认密码</td>
				      <td >
				      	<input type="text" size="25"/>
				      </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">电子邮箱</td>
				      <td>
				      	<s:textfield name="model.email" size="25"/>
				      <td align="center">电话号码</td>
				      <td>
				      	<s:textfield name="model.phone" size="25"/>
					  </td>
				     </tr>
				      <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">性&nbsp;&nbsp;&nbsp;&nbsp;别</td>
				      <td>
				      <s:select name="model.sex" list="@org.erp.auth.employee.entity.EmployeeModel@sexMap"></s:select>
				      	<%-- <select style="width:190px">
								<option value="-1">----请-选-择----</option>
								<option value="1">男</option>
								<option value="0">女</option>
							</select> --%>
					  </td>
				      <td align="center">地&nbsp;&nbsp;&nbsp;&nbsp;址</td>
				      <td>
				      	<s:textfield name="model.addres" size="25"/>
					  </td>
				    </tr>
				     <tr bgcolor="#FFFFFF">
					  <td colspan="4">&nbsp;</td>
					</tr>
				    <tr  bgcolor="#FFFFFF">
				      <td height="30" align="center">出生日期</td>
				      <td>
				      	<input type="text" size="25" onfocus="c.showMoreDay=false;c.show(this);" readonly="true" value="${model.birthDayView }"/>
				      	<s:hidden name="model.birthday"/>
					  </td>
				      <td align="center">所属部门</td>
				      <td>
				      		<s:select list="depts" name="model.departM.uuid" listKey="uuid" listValue="name">
				      		</s:select>
				      	<%-- <select style="width:190px">
							<option value="-1">----请-选-择----</option>
							<option value="1">销售部</option>
							<option value="2">采购部</option>
						</select> --%>
					  </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">角色名称</td>
				      <td width="82%" colspan="3">
				      	<input type="checkbox" id="all">全选&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				      	<input type="checkbox" id="reverse">反选
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td width="18%" height="30" align="center">&nbsp;</td>
				      <td width="82%" colspan="3" id="rol">
				      	<s:checkboxlist name="roleUuids" list="roles" listKey="uuid" listValue="name"></s:checkboxlist>
				      </td>
				    </tr>
				    <tr  bgcolor="#FFFFFF">
				      <td colspan="4">&nbsp;</td>
				    </tr>
				</table>
			</div>
			<div class="order-botton">
				<div style="margin:1px auto auto 1px;">
					<table width="100%"  border="0" cellpadding="0" cellspacing="0">
					  <tr>
					    <td>
					    	<a href="javascript:document.forms[0].submit()"><img src="images/order_tuo.gif" border="0" /></a>
					    </td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					    <td>&nbsp;</td>
					    <td><a href="#"><img src="images/order_tuo.gif" border="0" /></a></td>
					  </tr>
					</table>
				</div>
			</div>
			</form>
		</div><!--"square-order"end-->
	</div><!--"content-text"end-->
	<div class="content-bbg"><img src="images/content_bbg.jpg" /></div>
</div>
