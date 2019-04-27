<%@ page language="java" import="java.util.*" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="cinema.Movie" %>
<%@ page import="cinema.Show" %>
<%@ page import="cinema.Seat" %>
<%@ page import="cinema.Comment" %>
<%@ page import="cinema.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<jsp:useBean id="user" class="cinema.User"/> 
<jsp:useBean id="movie" class="cinema.Movie"/> 
<jsp:useBean id="shows" class="cinema.Show"/> 
<jsp:useBean id="seat" class="cinema.Seat"/> 
<jsp:useBean id="comment" class="cinema.Comment"/> 
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
<jsp:include page="/servlet/UpdataShows">
		<jsp:param value="123" name="123"/>
		</jsp:include>   <!-- 测试 -->
<!DOCTYPE HTML>
<head>
<title>Preview</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<script type="text/javascript" src="js/jquery-1.9.0.min.js"></script> 
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
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
   		</div>
   </div>
   <%
   		movie = movie.findMovie(Integer.parseInt(request.getParameter("movie_id")));
   		ArrayList<Show> showsList = shows.getShows(Integer.parseInt(request.getParameter("movie_id")));
   		ArrayList<Comment> commentList = comment.getCommentList(Integer.parseInt(request.getParameter("movie_id")));
    %>
   <div class="main">
   	 <div class="wrap">
   	 	<div class="content_top">
    		<div class="back-links">
    		<p><a href="index.jsp">Home</a> &gt;&gt;&gt;&gt;<a href="#" class="active">movie</a></p>
    	    </div>
    		<div class="clear"></div>
    	</div>
   	 	<div class="section group">
				<div class="cont-desc span_1_of_2">
				  <div class="product-details">				
					<div class="grid images_3_of_2">
						<img src="<%=movie.getMovie_pic()%>" alt="" />
				  </div>
				<div class="desc span_3_of_2">
					<h2><%=movie.getMovie_name() %> </h2>
					<p>主演：<%=movie.getMovie_star() %></p>	
					<p>简介：<%=movie.getMovie_text() %></p>	
					<p>评分：<%=movie.getMovie_score() %></p>					
					<div class="price">
						<p>票价: <span>￥<%=movie.getMovie_price() %></span></p>
					</div>
					<div class="available">
						<%
							for(Show s : showsList)
							{
								if(s.getShow_status() != 0){
									ArrayList<Integer> ari = seat.seatSelectList(seat.getSeatList(s.getShow_id()));
						 %>
						<ul>
						  <li><span>日期:</span> &nbsp; <fmt:formatDate value="<%=s.getShow_date() %>" pattern="yyyy-MM-dd"/></li>
						  <li><span>时间:</span>&nbsp; <%=s.getShow_startTime() %> - <%=s.getShow_endTime() %></li>
						  <li><span>影厅:</span>&nbsp; <%=s.getShow_area() %></li>
						  <li><span>剩余位置:</span>&nbsp; <%=ari.size() %></li>
						  <form action="servlet/Movies" method=post name=form>
						  	<select name="user_seat">
						  	<%
						  		for(int i = 0; i < ari.size(); i++)
						  		{
						  	 %>
       							<option value="<%=ari.get(i)%>"><%=ari.get(i)%>号</option>
       						<%
       							}
       						 %>
       						</select>
       						<input type="hidden" value="<%=s.getShow_id() %>" name="show_id">
       						<input type="hidden" value="<%=user.getUser_id() %>" name="user_id">
       						<input type="hidden" value="<%=movie.getMovie_id() %>" name="movie_id">
       						<INPUT TYPE="submit" value="购买" name="submit">
						  </form>
						  <li><hr></li>
					    </ul>
						 <%		}
						 	}
						 %>
					</div>
				
			</div>
			<div class="clear"></div>
		  </div>
		<div class="product_desc">	
			 <h2>评论列表:</h2>
			 <hr>
			  <%
			  	for(Comment c : commentList)
			  	{
			  		User userb = user.findUser(c.getUser_id());
			  %>
			  用户名:<%=userb.getUser_name()%><br>评分:<%=c.getComment_score() %>&nbsp;&nbsp;<br>评论:<%=c.getComment_text() %><hr>
			  <%
			   	}
			  %>
		</div>
		<div><hr>
		<h2>发表评论:</h2>
			<form action="servlet/CommentDao" method="post">
				<input type="hidden" value="<%=user.getUser_id() %>" name="user_id">
				<input type="hidden" value="<%=user.getUser_name() %>" name="user_name">
				<input type="hidden" value="<%=request.getParameter("movie_id") %>" name="movie_id">
				打分：<select name="comment_score">
       					<option value="1">1</option>
       					<option value="2">2</option>
       					<option value="3">3</option>
       					<option value="4">4</option>
       					<option value="5">5</option>
       				  </select><br>
				评论：<br><textarea name="comment" rows="10" cols="30"></textarea><br>
				<input type="submit" value="提交 " name="submit">
			</form>
		</div>
   </div>
				
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

