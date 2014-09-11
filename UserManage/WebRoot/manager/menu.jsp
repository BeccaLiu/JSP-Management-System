<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="app" var="myKey" scope="session"/>
<html>
	<head>
		<title>Insert title here</title>
		<LINK href="<%=request.getContextPath()%>/css/admin.css" type="text/css" rel="stylesheet">
	</head>
	<body>

		<TABLE height="100%" cellSpacing=0 cellPadding=0 width=170
			background="<%=request.getContextPath()%>/image/menu_bg.jpg" border=0>
			<TR>
				<TD vAlign=top align=middle>
					<TABLE cellSpacing=0 cellPadding=0 width=150 border=0>

						<TR height=22>
							<TD style="PADDING-LEFT: 30px" background="<%=request.getContextPath()%>/image/menu_bt.jpg">
								<A class=menuParent href="javascript:void(0);"><fmt:message  bundle="${myKey}" key="manage"/></A>
							</TD>
						</TR>
					</TABLE>
					<TABLE id=child6 cellSpacing=0 cellPadding=0 width=150 border=0>
						<TR height=20>
							<TD align=middle width=30>
								<IMG height=9 src="<%=request.getContextPath()%>/image/menu_icon.gif" width=9>
							</TD>
							<TD>
								<A class=menuChild href="<%=request.getContextPath()%>/manager/schUserList.jsp" target=main><fmt:message  bundle="${myKey}" key="manage"/></A>
							</TD>
						</TR>
						
						<TR height=4>
							<TD colSpan=2></TD>
						</TR>
					</TABLE>
				</TD>
				<TD width=1 bgColor=#d1e6f7></TD>
			</TR>
		</TABLE>
	</body>
</html>