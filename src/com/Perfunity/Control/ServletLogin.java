package com.Perfunity.Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Perfunity.Model.*;

/**
 * Servlet implementation class ServletLogin
 */
@WebServlet(name="ServletLogin", urlPatterns="/login")
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletLogin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ssn = request.getSession();
		
		synchronized (ssn) //SINCRONIZZO LA SESSIONE
		{
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			String ric = request.getParameter("remember");
			String redirectedPage;
			
			try
			{
				if(email.contains("@root"))
				{
					if(checkLogin(email,password))
					{		
						ssn.setAttribute("adminRoles", new Boolean(true));
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/index-admin.jsp");
						requestDispatcher.forward(request, response);
					}
				}
				else
				{
					UserDAO ud = new UserDAO();
					UserBean us = ud.getLogin(email,password);
					//Optional<String> cookies = readCookie(request,"userCookie");
					Cookie[] arr = request.getCookies();
					Cookie ck = null;
					for(Cookie c: arr)
					{
						if(c.getName().equals("userCookie")) ck = c;
					}
					//String ck = cookies.get();
					
					if(us != null)
					{
						if(ck!=null)
						{
							ssn.setAttribute("userbean", us);
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
							requestDispatcher.forward(request, response);
						}
						else
						{
							if(ric!=null && !ric.isEmpty())
							{
								String value = us.getEmail();
								Cookie user = new Cookie("userCookie",value);
								user.setMaxAge(2628002); //Un mese in secondi
								response.addCookie(user);
								ssn.setAttribute("userbean", us);
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
								requestDispatcher.forward(request, response);	
							}
							else
							{
								ssn.setAttribute("userbean", us);
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
								requestDispatcher.forward(request, response);
							}
						}
					}
					else
					{
						ssn.setAttribute("errorLogin", true);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
						requestDispatcher.forward(request, response);
					}
				}
			}
			catch (Exception e) 
			{
				request.getSession().setAttribute("adminRoles", new Boolean(false));
				redirectedPage = "/login.jsp";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
			
			
		}
	}
	
	private boolean checkLogin(String username, String password) throws Exception {
		if ("admin@root".equals(username) && "admin".equals(password)) {
			return true;
		} else
			throw new Exception("Invalid login and password");
	}
	
	/*private Optional<String> readCookie(HttpServletRequest request, String key) {
	    return Arrays.stream(request.getCookies())
	      .filter(c -> key.equals(c.getName()))
	      .map(Cookie::getValue)
	      .findAny();
	}*/

}
