<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
<title>用户管理后台</title>
<META http-equiv=Pragma content=no-cache>
<META http-equiv=Cache-Control content=no-cache>
<META http-equiv=Expires content=-1000>
<LINK href="css/admin.css" type="text/css" rel="stylesheet">
</head>

<FRAMESET border=0 frameSpacing=0 rows="60, *" frameBorder=0 >
<FRAME name=header src="<%=request.getContextPath()%>/manager/header.jsp" frameBorder=0 noResize scrolling=no>
<FRAMESET cols="170, *">
<FRAME name=menu src="<%=request.getContextPath()%>/manager/menu.jsp" frameBorder=0 noResize>
<FRAME name=main src="<%=request.getContextPath()%>/manager/content.jsp" frameBorder=0 noResize scrolling=yes>
</FRAMESET>
</FRAMESET>
<noframes>
</noframes>
</html>
