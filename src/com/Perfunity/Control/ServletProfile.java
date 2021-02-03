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

import com.Perfunity.Model.UserBean;

/**
 * Servlet implementation class ServletProfile
 */
@WebServlet(name="ServletProfile", urlPatterns="/profilo")
public class ServletProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ssn = request.getSession();
		UserBean ub = (UserBean) ssn.getAttribute("userbean");
    	Cookie[] arr = request.getCookies();
		Cookie ck = null;
		for(Cookie c: arr)
		{
			if(c.getName().equals("userCookie")) ck = c;
		}

		if(ub!=null || ck!=null)
		{
			String button = request.getParameter("button");
			switch(button)
			{
				case "profile":
						//Se non è loggato reindirizza al login chiedendo all'utente di loggarsi
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
						requestDispatcher.forward(request, response);
				break;
			
				case "home":
					response.sendRedirect("index.jsp");
					break;
			
				case "changeEmail":
					response.sendRedirect("change-email.jsp");
				break;
			
				case "changePsw":
					response.sendRedirect("change-password.jsp");
				break;
			
				case "logout":
				
					//Se l'utente non è loggato, reindirizza alla home con un alert, altrimenti fa logout
				
					request.getSession().invalidate();
					Cookie user = new Cookie("userCookie","");
					user.setMaxAge(0); //Un mese in secondi
					response.addCookie(user);
					RequestDispatcher requestDispatcher14 = request.getRequestDispatcher("index.jsp");
					requestDispatcher14.forward(request, response);

				break;
		    
				case "assistenza":
					response.sendRedirect("new-ticket.jsp");
				break;
			}
		}
		else
		{
			request.setAttribute("error", " ");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
		}
		
	}

}
