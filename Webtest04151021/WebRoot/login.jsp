<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="register">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<strong>用户登录</strong>
    <FORM action="servlet/Login" method=post name=form>
    <P>请输入下列信息：
       <BR>用户名：<INPUT type="text" name="name">
       <BR>密码：<INPUT type="password" name="password">
       <BR>系别：<select name="xi">
       			<option value="计算机">计算机</option>
       			<option value="中文">中文</option>
       			<option value="电子">电子</option>
       			<option value="艺术">艺术</option>
       			</select>
       <BR><INPUT TYPE="submit" value="提交" name="submit">
       <INPUT TYPE="button" value="重置" name="button">
   </FORM> 
  </body>
</html>
