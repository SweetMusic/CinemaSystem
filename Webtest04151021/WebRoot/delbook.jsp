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
    
    <title>My JSP 'addBook.jsp' starting page</title>
    
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
    <c:choose>
    	<c:when test="${param.id == 1}">
    	<c:set target="${book }" property="id" value="1"/>
    	<c:set target="${book }" property="price" value="111.0"/>
    	<%
    		cart.delBook(book);
    	%>
    	<c:set var="cartbook" value="${cart }" scope="session"/>
    	<c:redirect url="Cart.jsp"/>
    	</c:when>
    	<c:when test="${param.id == 2}">
    	<c:set target="${book }" property="id" value="2"/>
    	<c:set target="${book }" property="price" value="222.0"/>
    	<%
    		cart.delBook(book);
    	%>
    	<c:set var="cartbook" value="${cart }" scope="session"/>
    	<c:redirect url="Cart.jsp"/>
    	</c:when>
    	<c:when test="${param.id == 3}">
    	<c:set target="${book }" property="id" value="3"/>
    	<c:set target="${book }" property="price" value="333.0"/>
    	<%
    		cart.delBook(book);
    	%>
    	<c:set var="cartbook" value="${cart }" scope="session"/>
    	<c:redirect url="Cart.jsp"/>
    	</c:when>
    	<c:otherwise>
    	false
    	</c:otherwise>
    </c:choose>
  </body>
</html>
