package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import shopping.CartBook;

public class Shopping extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public Shopping() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		HttpSession hs = request.getSession();
		if(hs.getAttribute("item") != null)
		{
			//Map<String, Integer> map = (Map<String, Integer>)hs.getAttribute("item");
			CartBook cb = new CartBook();
			cb.setMap((Map<String, Integer>)hs.getAttribute("item"));
			Map<String, Integer> map = cb.getMap();
			if(cheak(request, map))
			{
				for(String key:map.keySet())
				{
					if(request.getParameter("id").toString().equals(key))
					{
						int i = map.get(key) + 1;
						map.put(key, i);
						hs.setAttribute("item", map);
					}
				}
			}
			else
			{
				map.put(request.getParameter("id").toString(), 1);
				//hs.setAttribute("item", map);
				cb.setMap(map);
				hs.setAttribute("item", cb);
			}
		}
		else
		{
			//Map<String, Integer> map = new HashMap<String, Integer>();
			CartBook cb = new CartBook();
			//map.put(request.getParameter("id").toString(), 1);
			cb.getMap().put(request.getParameter("id").toString(), 1);
			//hs.setAttribute("item", map);
			hs.setAttribute("item", cb);
		} 
		//Map<String, Integer> map = (Map<String, Integer>)hs.getAttribute("item");
		CartBook cb = new CartBook();
		cb.setMap((Map<String, Integer>)hs.getAttribute("item"));
		Map<String, Integer> map = cb.getMap();
		int i = map.get(request.getParameter("id"));
		for(String key:map.keySet())
		{
			if(request.getParameter("id").toString().equals(key))
			{
				out.print("购买成功，商品" + key + "有" + i + "个");
			}
		}
		
		
		out.print("<br><a href=\"/Webtest04151021/Shop.jsp\">back</a>");
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

	public boolean cheak(HttpServletRequest request, Map<String, Integer> map)
	{
		for(String key:map.keySet())
		{
			if(request.getParameter("id").toString().equals(key))
			{
				return true;
			}
		}
		return false;
	}
}
