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
    <FORM action="servlet/FormServlet" method=post name=form>
    <P>请输入下列信息：
       <BR>用户名：<INPUT type="text" name="name" value="张华">
       <BR>密码：<INPUT type="password" name="password" value="123456">
       <BR>性别：<INPUT type="radio" name="R" value="男" checked="default">男 
                     <INPUT type="radio" name="R" value="女">女 
       <BR>年龄:<INPUT type="text" name="age" value="19">
       <BR>系别：<select name="xi">
       			<option value="计算机">计算机</option>
       			<option value="中文">中文</option>
       			<option value="电子">电子</option>
       			<option value="艺术">艺术</option>
       			</select>
       <BR>兴趣： 
          <input type="checkbox" name="like" value="文学" >文学
          <input type="checkbox" name="like" value="体育" >体育
          <input type="checkbox" name="like" value="电脑" >电脑
       <BR>学历：<select name="study">
       			<option value="学士">学士</option>
       			<option value="无">无</option>
       			</select>
       <BR>邮件地址：<INPUT type="text" name="email" value="adada@111.com">
       <BR>简历：<textarea name="resume" rows="10" cols="30">张华是一名大学生，她喜欢文学</textarea>
       <BR><INPUT TYPE="submit" value="提交" name="submit">
       <INPUT TYPE="button" value="重置" name="button">
   </FORM> 
  </body>
</html>
