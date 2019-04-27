<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="cinema.Movie" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="user" class="cinema.User"/> 
<jsp:useBean id="movie" class="cinema.Movie"/> 
<c:if test="${!empty sessionScope.user }">
<c:set target="${user }" property="user_name" value="${sessionScope.user.user_name }"/>
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
							<li><a href="register.jsp">Register</a></li>
							<li><a href="login.jsp">Login</a></li>
						</c:if>
						</ul>
					</div>
				<div class="clear"></div>
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
				<div class="header_bottom">
					<div class="header_bottom_left">				
						<div class="categories">
						   <ul>
						  	   <h3>Categories</h3>
							      <li><a href="#">All</a></li>
							      <li><a href="#">Hindi</a></li>
							      <li><a href="#">Telugu</a></li>
							      <li><a href="#">English</a></li>
							      <li><a href="#">Tamil</a></li>
							       <li><a href="#">Malayalam</a></li>
							       <li><a href="#">Kannada</a></li>
							       <li><a href="#">Bengali</a></li>
							       <li><a href="#">Assami</a></li>
							       <li><a href="#">Kids</a></li>
							       <li><a href="#">Animation</a></li>
							       <li><a href="#">Games</a></li>
						  	 </ul>
						</div>					
		  	         </div>
						    <div class="header_bottom_right">					 
						 	    <!------ Slider ------------>
								  <div class="slider">
							      	<div class="slider-wrapper theme-default">
							            <div id="slider" class="nivoSlider">
							                <img src="images/1.jpg" data-thumb="images/1.jpg" alt="" />
							                <img src="images/2.jpg" data-thumb="images/2.jpg" alt="" />
							                <img src="images/3.jpg" data-thumb="images/3.jpg" alt="" />
							                <img src="images/4.jpg" data-thumb="images/4.jpg" alt="" />
							                 <img src="images/5.jpg" data-thumb="images/5.jpg" alt="" />
							            </div>
							        </div>
						   		</div>
						<!------End Slider ------------>
			         </div>
			     <div class="clear"></div>
			</div>
   		</div>
   </div>
   <!------------End Header ------------>
  <div class="main">
  	<%
  		ArrayList<Movie> ar = movie.addMovie(); 
  	%>
  	<div class="wrap">
      <div class="content">
    	<div class="content_top">
    		<div class="heading">
    		<h3>New Movies</h3>
    		</div>
    	</div>
	      <div class="section group">
	      <%
	      	for(int i = 0; i<5; i++)
	      	{ 
	      		if(i<ar.size())
	      		{
	      %>
	      	<div class="grid_1_of_5 images_1_of_5">
					 <a href="preview.jsp?movie_id=<%=ar.get(i).getMovie_id()%>"><img src="<%=ar.get(i).getMovie_pic() %>" alt="" /></a>
					 <h2><a href="preview.jsp?movie_id=<%=ar.get(i).getMovie_id()%>"><%=ar.get(i).getMovie_name()%></a></h2>
					<div class="price-details">
				       <div class="price-number">
							<p><span class="rupees">tag:<%=ar.get(i).getMovie_tag()%></span></p>
					    </div>
					       		<div class="add-cart">								
									<h4><a href="preview.jsp?movie_id=<%=ar.get(i).getMovie_id()%>">Watch</a></h4>
							     </div>
							 <div class="clear"></div>
					</div>					 
				</div>
	      <%
	      		}
	      	}
	       %>	
			</div>
			
			
       </div>
       <br>
       <div class="content">
    	
	      <div class="section group">
	      <%
	      	for(int i = 0; i<5; i++)
	      	{ 
	      		if(i<ar.size())
	      		{
	      %>
	      	<div class="grid_1_of_5 images_1_of_5">
					 <a href="preview.jsp?movie_id=<%=ar.get(i).getMovie_id()%>"><img src="<%=ar.get(i).getMovie_pic() %>" alt="" /></a>
					 <h2><a href="preview.jsp?movie_id=<%=ar.get(i).getMovie_id()%>"><%=ar.get(i).getMovie_name()%></a></h2>
					<div class="price-details">
				       <div class="price-number">
							<p><span class="rupees">tag:<%=ar.get(i).getMovie_tag()%></span></p>
					    </div>
					       		<div class="add-cart">								
									<h4><a href="preview.jsp?movie_id=<%=ar.get(i).getMovie_id()%>">Watch</a></h4>
							     </div>
							 <div class="clear"></div>
					</div>					 
				</div>
	      <%
	      		}
	      	}
	       %>	
			</div>
			<br>
			
			
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


