<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<HTML>
<HEAD>
<meta http-equiv="Content-Language" content="zh-cn">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${pageContext.request.contextPath}/css/Style1.css"
	rel="stylesheet" type="text/css" />
<script language="javascript"
	src="${pageContext.request.contextPath}/js/public.js"></script>
<style rel="stylesheet">
.fahuo {
	border: 1px solid black;
	padding: 0 5px;
	margin: 2px;
	font-size: 14px;
	border-radius: 2px;
	background: white;
}

a.fahuo:hover {
	color: red;
	border: 1px solid red;
}
</style>
<script>
	function check() {
		var value = confirm("是否确认发货");
		return value;
	}
</script>
</HEAD>
<body>
	<br>
	<form id="Form1" name="Form1"
		action="${pageContext.request.contextPath}/user/list.jsp"
		method="post">
		<table cellSpacing="1" cellPadding="0" width="100%" align="center"
			bgColor="#f5fafe" border="0">
			<TBODY>
				<tr>
					<td class="ta_01" align="center" bgColor="#afd1f3"><strong>
							<s:if test="statusNum == null">
							所有订单列表
							</s:if> <s:if test="statusNum == 1">
							未付款订单
							</s:if> <s:if test="statusNum == 2">
							已付款订单
							</s:if> <s:if test="statusNum == 3">
							已发货订单
							</s:if><s:if test="statusNum == 4">
							已完成订单
							</s:if>
					</strong></TD>
				</tr>
				<tr>
					<td class="ta_01" align="center" bgColor="#f5fafe">
						<table cellspacing="0" cellpadding="1" rules="all"
							bordercolor="gray" border="1" id="DataGrid1"
							style="BORDER-RIGHT: gray 1px solid; BORDER-TOP: gray 1px solid; BORDER-LEFT: gray 1px solid; WIDTH: 100%; WORD-BREAK: break-all; BORDER-BOTTOM: gray 1px solid; BORDER-COLLAPSE: collapse; BACKGROUND-COLOR: #f5fafe; WORD-WRAP: break-word">
							<tr
								style="FONT-WEIGHT: bold; FONT-SIZE: 12pt; HEIGHT: 25px; BACKGROUND-COLOR: #afd1f3">

								<td align="center" width="10%">订单编码</td>
								<td align="center" width="17%">收件人姓名</td>
								<td width="7%" align="center">编辑</td>
								<td width="7%" align="center">状态</td>
							</tr>
							<s:iterator var="order" value="pageBean.list" status="status">
								<tr onmouseover="this.style.backgroundColor = 'white'"
									onmouseout="this.style.backgroundColor = '#F5FAFE';">
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="18%"><s:property value="#order.oid" /></td>
									<td style="CURSOR: hand; HEIGHT: 22px" align="center"
										width="17%"><s:property value="#order.user.name" /></td>
									<td align="center" style="HEIGHT: 22px"><s:if
											test="#order.state == 2">
											<a class="fahuo" onclick="return check()"
												href="${pageContext.request.contextPath}/adminOrder_deliveryOrder.action?page=<s:property value="pageBean.page"/><s:if test="status != null"><s:property value="status"/></s:if>&orderId=<s:property value="#order.oid"/>">
												<%-- <img src="${pageContext.request.contextPath}/images/i_edit.gif" border="0" style="CURSOR: hand"> --%>
												发货
											</a>
										</s:if></td>
									<td align="center" style="HEIGHT: 22px"><s:if
											test="#order.state == 1">未付款</s:if> <s:if
											test="#order.state == 2">已付款</s:if> <s:if
											test="#order.state == 3">已发货</s:if></td>
								</tr>
							</s:iterator>
						</table>
					</td>
				</tr>
				<tr align="center">
					<td class="ta_01" align="center" bgColor="#afd1f3">第<s:property
							value="pageBean.page" />/<s:property value="pageBean.totalPage" />页&nbsp;&nbsp;&nbsp;&nbsp;
						<s:if test="pageBean.page!=1">
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAllByState.action?page=1<s:property value="status"/>">首页</a>|
								<a
								href="${pageContext.request.contextPath}/adminOrder_findAllByState.action?page=<s:property value="pageBean.page-1"/><s:property value="status"/>">上一页</a>|
							</s:if> <s:if test="pageBean.page!=pageBean.totalPage">
							<a
								href="${pageContext.request.contextPath}/adminOrder_findAllByState.action?page=<s:property value="pageBean.page+1"/><s:property value="status"/>">下一页</a>|
								<a
								href="${pageContext.request.contextPath}/adminOrder_findAllByState.action?page=<s:property value="pageBean.totalPage"/><s:property value="status"/>">尾页</a>|
							</s:if>
					</TD>
				</tr>
			</TBODY>
		</table>
	</form>
</body>
</HTML>

