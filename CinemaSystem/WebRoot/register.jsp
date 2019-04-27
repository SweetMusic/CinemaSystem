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
  	<strong>用户注册</strong>
    <FORM action="dao/Register" method=post name=form>
    <P>请输入下列信息：
       <BR>用户名：<INPUT type="text" name="user_name" value="lplp">
       <BR>密码：<INPUT type="password" name="user_password" value="123456">
       <BR>性别：<INPUT type="radio" name="user_sex" value="1" checked="default">男 
                     <INPUT type="radio" name="user_sex" value="2">女 
       <BR>年龄:<INPUT type="text" name="user_age" value="19">
       <br>生日：<Input type="text" name="user_birthday">
       <BR>身份证：<INPUT type="text" name="user_idcard" >
       <br>手机号码：<input type="text" name="user_tel">
       <BR><INPUT TYPE="submit" value="提交" name="submit">
       <INPUT TYPE="button" value="重置" name="button">
   </FORM> 
  </body>
</html>
