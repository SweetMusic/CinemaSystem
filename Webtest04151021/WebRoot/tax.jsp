<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>
<jsp:useBean id="doTax" class="dao.Money"/> 
<jsp:useBean id="tax" class="po.Tax"></jsp:useBean>
<jsp:setProperty property="*" name="tax"/> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Shop.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <form action="tax.jsp" method = "get">
  	<BR>收入：<INPUT type="text" name="tax" value=<jsp:getProperty name="tax" property="tax"/>>
  	<INPUT TYPE="submit" value="计算" name="submit">
  	
  </form>
   <br>
   税额为：<%out.print(doTax.tax(tax.getTax())); %>
  </body>
</html>
