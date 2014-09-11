<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<fmt:setBundle basename="app" var="myKey" scope="session"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<LINK href="<%=request.getContextPath()%>css/admin.css" type="text/css" rel="stylesheet">
<script type="text/javascript">
   function changeLanguage(language){
   				
                window.location.href="<%=request.getContextPath()%>/languageGet.jsp?language="+language;
                window.location.reload(true);
                window.parent.menu.location.reload();
                window.parent.main.location.reload();
 //               response.sendRedirect("<%=request.getContextPath()%>/");
                 
                
                
                }         
</script>
</head>

<body>
<TABLE cellSpacing="0" cellPadding="0" width="100%" background="<%=request.getContextPath()%>/image/header_bg.jpg" border="0">
  <TR height="56">
    <TD width="234"><IMG height="56" src="<%=request.getContextPath()%>/image/header_left.jpg" width="183"></TD>
    <TD width="1067" align="left" style="FONT-WEIGHT: bold; COLOR: #fff;">
<%
    String username="";
    String userrole="";
    if(request.getSession().getAttribute("name")!=null)
	  username=(String)request.getSession().getAttribute("name");
	   if(request.getSession().getAttribute("role")!=null)
	  userrole=(String)request.getSession().getAttribute("role");
    if(!"".equals(username))
    {
    %>
    	<%=userrole %>：<%=username %> &nbsp;&nbsp;  
    	
    	<A style="COLOR: #fff"  href="<%=request.getContextPath() %>/manager/passChange.jsp" target="main"><fmt:message  bundle="${myKey}" key="changepass"/></A>
    	<A style="COLOR: #fff" onclick="if (confirm('<fmt:message  bundle="${myKey}" key="msg_exit"/>？')) return true; else return false;" href="<%=request.getContextPath() %>/index.jsp" target="_top"><fmt:message  bundle="${myKey}" key="exit"/></A>

    	<%} %>  
    	   <select name="language" onchange="changeLanguage(this.value)">
   				<option>选择语言</option>
                <option value="zh_CN">简体中文</option>
                <option value="en_US">English</option>
   </select>
    	     
   	</TD>
    <TD align="right" width="268"><IMG height="56" src="<%=request.getContextPath()%>/image/header_right.jpg" width="109"></TD>
  </TR>
</TABLE>
<TABLE cellSpacing="0" cellPadding="0" width="100%" border="0">
  <TR bgColor="#1c5db6" height="4">
    <TD></TD>
  </TR>
</TABLE>
</body>
</html>