<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="shopping.Book" %>
<%@page import="shopping.Cart" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="book" class="shopping.Book"/> 
<jsp:useBean id="cart" class="shopping.Cart"/>
<c:if test="${!empty sessionScope.cartbook }">
<%
	Cart c = (Cart)session.getAttribute("cartbook");
	cart.setCartList(c.getCartList()); 
%>
</c:if>
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
  <!-- 
    <%
    	int totalCount = 0;
    	if(session.getAttribute("item") != null)
    	{
    	
	    	Map<String, Integer> map = (Map<String, Integer>)session.getAttribute("item");
	    	for(String key:map.keySet())
					{
						totalCount += map.get(key);
	%>
	商品<%=key %>有<%=map.get(key) %>个<br>
	<%
					//out.print("商品" + key + "有" + map.get(key) + "个<br>");
					}
		}
     %>
     总共有：<%=totalCount %>个商品。<br>
   <a href="/Webtest04151021/Shop.jsp">继续购买</a>
 -->
 <c:set var="total" value="0" />
 <c:forEach var="b" items="${cart.cartList }">
 	商品：${b.id }有 ${b.num } 个，价格为${b.num * b.price }。   <a href="servlet/DeleteBook?id=${b.id }">删除一个</a><br>
 <c:set var="total" value="${total+b.num * b.price }"/>
 </c:forEach>
 	总价为：<c:out value="${total }"/>
  </body>
</html>
