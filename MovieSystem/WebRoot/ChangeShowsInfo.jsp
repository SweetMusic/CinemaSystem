<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="cinema.Movie" %>
<%@ page import="cinema.Show" %>
<%@ page import="cinema.Seat" %>
<%@ page import="cinema.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="user" class="cinema.User"/> 
<jsp:useBean id="admin" class="cinema.Admin"/> 
<jsp:useBean id="movie" class="cinema.Movie"/> 
<jsp:useBean id="shows" class="cinema.Show"/> 
<jsp:useBean id="seat" class="cinema.Seat"/> 
<c:if test="${empty sessionScope.admin }">
<c:redirect url="servlet/ReturnPageA"></c:redirect>
</c:if>
<c:if test="${!empty sessionScope.admin }">
<c:set target="${admin }" property="admin_id" value="${sessionScope.admin.admin_id }"/>
<c:set target="${admin }" property="admin_name" value="${sessionScope.admin.admin_name }"/>
<c:set target="${admin }" property="admin_level" value="${sessionScope.admin.admin_level }"/>
<c:set target="${admin }" property="admin_status" value="${sessionScope.admin.admin_status }"/>
	<%if(admin.getAdmin_status() == 0){ %>
		<c:redirect url="servlet/ReturnPageA"></c:redirect>
	<%} %>
</c:if>
<!DOCTYPE HTML>
<head>
<title>Home</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<link href="css/slider.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script> 
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
<script type="text/javascript" src="js/jquery.nivo.slider.js"></script>
<script type="text/javascript">
    $(window).load(function() {
        $('#slider').nivoSlider();
    });
    </script>
</head>
<body>
	<div class="header">
		 <div class="headertop_desc">
			<div class="wrap">
				<div class="nav_list">
					<ul>
						<li><a href="Admin.jsp">Home</a></li>
					</ul>
				</div>
					<div class="account_desc">
						<ul>
						<c:if test="${!empty sessionScope.admin }">
							<li><a href="userdata.jsp"><font color="#9C9C9C">管理员 <c:out value="${admin.admin_name }"/>,欢迎你</font></a></li>
						</c:if>
						<c:if test="${empty sessionScope.admin }">
							<li><a href="AdminRegister.jsp">Register</a></li>
							<li><a href="AdminLogin.jsp">Login</a></li>
						</c:if>
						</ul>
					</div>				<div class="clear"></div>
			</div>
	  	</div>
  	  		<div class="wrap">
				<div class="header_top">
					<div class="logo">
						<a href="index.jsp"><img src="images/logo.png" alt="" /></a>
					</div>
						
						  <script type="text/javascript">
								function DropDown(el) {
									this.dd = el;
									this.initEvents();
								}
								DropDown.prototype = {
									initEvents : function() {
										var obj = this;
					
										obj.dd.on('click', function(event){
											$(this).toggleClass('active');
											event.stopPropagation();
										});	
									}
								}
					
								$(function() {
					
									var dd = new DropDown( $('#dd') );
					
									$(document).click(function() {
										// all dropdowns
										$('.wrapper-dropdown-2').removeClass('active');
									});
					
								});
					    </script>
			 <div class="clear"></div>
  		</div>     
				
   		</div>
   </div>
   <!------------End Header ------------>
  <div class="main">
  	<div class="wrap">
      <div class="content">
      <%
      	Show s = new Show();
      	s = s.getShow(Integer.parseInt(request.getParameter("show_id")));
       %>
      	<font size="5">修改演出信息</font>
      	<br><br>  
      	当前信息: 
      	<br> 
      	<br>
    	<form method="post" action="servlet/ChangeShowsInfo">
    						<input type="hidden" name="show_id" value="${param.show_id }">
    						<div>
						    	<span><label>电影id</label></span>
						    	<span><input name="movie_id" type="text" class="textbox" value="<%=s.getMovie_id()%>"></span>
						    </div>
					    	<div>
						    <div>
						    	<span><label>日期</label></span>
						    	<span><input name="show_date" type="text" class="textbox" value="<fmt:formatDate value="<%=s.getShow_date()%>" pattern="yyyy-MM-dd"/>"></span>
						    </div>
					    	<div>
						    	<span><label>开始时间</label></span>
						    	<span><input name="show_startTime" type="text" class="textbox" value="<%=s.getShow_startTime()%>"></span>
						    </div>
						    <div>
						    	<span><label>结束时间</label></span>
						    	<span><input name="show_endTime" type="text" class="textbox" value="<%=s.getShow_endTime()%>"></span>
						    </div>
						    <div>
						     	<span><label>影厅</label></span>
						    	<span><input name="show_area" type="text" class="textbox" value="<%=s.getShow_area()%>"></span>
						    </div>
						   <div>
						   		<span><input type="submit" value="确定" ></span>
						  </div>
					    </form>
      </div>
  </div>
</div>
   <div class="footer">
   	  <div class="wrap">	
	     <div class="section group">
				<div class="col_1_of_4 span_1_of_4">
						<h4>Information</h4>
						<ul>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Customer Service</a></li>
						<li><a href="#">Advanced Search</a></li>
						<li><a href="#">Orders and Returns</a></li>
						<li><a href="contact.jsp">Contact Us</a></li>
						</ul>
					</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>Why buy from us</h4>
						<ul>
						<li><a href="#">About Us</a></li>
						<li><a href="#">Customer Service</a></li>
						<li><a href="#">Privacy Policy</a></li>
						<li><a href="contact.jsp">Site Map</a></li>
						<li><a href="#">Search Terms</a></li>
						</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>My account</h4>
						<ul>
							<li><a href="contact.jsp">Sign In</a></li>
							<li><a href="index.jsp">View Cart</a></li>
							<li><a href="#">My Wishlist</a></li>
							<li><a href="#">Track My Order</a></li>
							<li><a href="contact.jsp">Help</a></li>
						</ul>
				</div>
				<div class="col_1_of_4 span_1_of_4">
					<h4>Contact</h4>
						<ul>
							<li><span>+91-123-456789</span></li>
							<li><span>+00-123-000000</span></li>
						</ul>
						<div class="social-icons">
							<h4>Follow Us</h4>
					   		  <ul>
							      <li><a href="#" target="_blank"><img src="images/facebook.png" alt="" /></a></li>
							      <li><a href="#" target="_blank"><img src="images/twitter.png" alt="" /></a></li>
							      <li><a href="#" target="_blank"><img src="images/skype.png" alt="" /> </a></li>
							      <li><a href="#" target="_blank"> <img src="images/linkedin.png" alt="" /></a></li>
							      <div class="clear"></div>
						     </ul>
   	 					</div>
				</div>
			</div>
			 <div class="copy_right">
				<p>Copyright &copy; 2014.Company name All rights reserved.<a target="_blank" href="http://sc.chinaz.com/moban/">&#x7F51;&#x9875;&#x6A21;&#x677F;</a></p>
		   </div>			
        </div>
    </div>
    <script type="text/javascript">
		$(document).ready(function() {			
			$().UItoTop({ easingType: 'easeOutQuart' });
			
		});
	</script>
    <a href="#" id="toTop"><span id="toTopHover"> </span></a>
<div style="display:none"><script src='http://v7.cnzz.com/stat.php?id=155540&web_id=155540' language='JavaScript' charset='gb2312'></script></div>
</body>
</html>


