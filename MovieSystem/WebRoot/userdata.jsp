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
<jsp:useBean id="movie" class="cinema.Movie"/> 
<jsp:useBean id="shows" class="cinema.Show"/> 
<jsp:useBean id="seat" class="cinema.Seat"/> 
<c:if test="${empty sessionScope.user }">
<c:redirect url="servlet/ReturnPage"></c:redirect>
</c:if>
<c:if test="${!empty sessionScope.user }">
<c:set target="${user }" property="user_name" value="${sessionScope.user.user_name }"/>
<c:set target="${user }" property="user_age" value="${sessionScope.user.user_age }"/>
<c:set target="${user }" property="user_birthday" value="${sessionScope.user.user_birthday }"/>
<c:set target="${user }" property="user_sex" value="${sessionScope.user.user_sex }"/>
<c:set target="${user }" property="user_tel" value="${sessionScope.user.user_tel }"/>
<c:set target="${user }" property="user_idcard" value="${sessionScope.user.user_idcard }"/>
<c:set target="${user }" property="user_id" value="${sessionScope.user.user_id }"/>
<c:set target="${user }" property="user_status" value="${sessionScope.user.user_status }"/>
	<%if(user.getUser_status() == 0){ %>
		<c:redirect url="servlet/ReturnPage"></c:redirect>
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
						<li><a href="index.jsp">Home</a></li>
					</ul>
				</div>
					<div class="account_desc">
						<ul>
						<c:if test="${!empty sessionScope.user }">
							<li><a href="userdata.jsp"><font color="#9C9C9C"><c:out value="${user.user_name }"/>,欢迎你</font></a></li>
						</c:if>
						<c:if test="${empty sessionScope.user }">
							<li><a href="contact.jsp">Register</a></li>
							<li><a href="contact.jsp">Login</a></li>
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
      	<font size="5">用户信息</font>&nbsp;&nbsp;&nbsp;&nbsp;<a href="ChangeUserData.jsp">修改</a>
      	<br><br>      	
    	<li>用户名：<c:out value="${user.user_name }"/></li>
    	<li>用户id：<c:out value="${user.user_id }"/></li>
    	<li>性别：<c:out value="${user.user_sex }"/></li>
    	<li>年龄：<c:out value="${user.user_age }"/></li>
    	<li>生日：<fmt:formatDate value="${user.user_birthday }" pattern="yyyy-MM-dd"/></li>
    	<li>身份证：<c:out value="${user.user_idcard }"/></li>
    	<li>手机号：<c:out value="${user.user_tel }"/></li>
    	<br>
    	<hr>
    	<br>
    	<font size="5">订单信息</font>
    	<br><br>
    	<%
    		ArrayList<Seat> ar = seat.getUserSeatList(user.getUser_id());
    		for(Seat s : ar)
    		{
    			Show show = shows.getShow(s.getShow_id());
    			Movie movies = movie.findMovie(show.getMovie_id());
    	 %>
    	<li>电影名称:<%=movies.getMovie_name() %>&nbsp;&nbsp;日期:<fmt:formatDate value="<%=show.getShow_date() %>" pattern="yyyy-MM-dd"/>&nbsp;&nbsp;开始时间:<%=show.getShow_startTime() %>&nbsp;&nbsp;结束时间:<%=show.getShow_endTime() %>&nbsp;&nbsp;影厅:<%=show.getShow_area() %>&nbsp;&nbsp;座位:<%=s.getShow_seat_pos() %>&nbsp;&nbsp;状态:<%if(show.getShow_status() == 0){out.print("已结束");}else{out.print("待观看");} %>&nbsp;&nbsp;</li>
    	
    	<%
    		}
    	 %>
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


