package com.Perfunity.ControlPanel;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Perfunity.Model.ProdottoBean;
import com.Perfunity.Model.ProdottoDAO;


/**
 * Servlet implementation class ServletDashboard
 */
@WebServlet(name="ServletDashboard", urlPatterns="/dashboard")
public class ServletDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletDashboard() {
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
		
		HttpSession session = request.getSession();
		Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
		if (adminRoles!=null && adminRoles == true)
		{
			String action = request.getParameter("action");
			switch(action)
			{
				case "index":
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/index-admin.jsp");
					requestDispatcher.forward(request, response);
				break;
			
				case "orders":
					RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("panel/orders.jsp");
					requestDispatcher1.forward(request, response);
				break;
			
				case "products":
					ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
		    		ProdottoDAO pdao = new ProdottoDAO();
		    		try
		    		{
		    			pbarr = pdao.getProdotti();
		    		}
		        	catch(Exception e)
		        	{
		        		e.printStackTrace();
		        	}
		    		request.setAttribute("pbarr",pbarr);
		        	RequestDispatcher requestDispatcher2 = request.getRequestDispatcher("panel/products.jsp");
					requestDispatcher2.forward(request, response);
				break;
			
				case "clients":
					RequestDispatcher requestDispatcher3 = request.getRequestDispatcher("panel/clients.jsp");
					requestDispatcher3.forward(request, response);
				break;
			
				case "reviews":
					RequestDispatcher requestDispatcher4 = request.getRequestDispatcher("panel/reviews.jsp");
					requestDispatcher4.forward(request, response);
				break;
				
				case "richieste":
					RequestDispatcher requestDispatcher5 = request.getRequestDispatcher("panel/tickets.jsp");
					requestDispatcher5.forward(request, response);
				break;
				
				case "logout":
					request.getSession().invalidate();
					//Hoemepage
					response.sendRedirect("./index.jsp");
				break;
			}
		}
		else
		{
			String redirectedPage;
			request.getSession().setAttribute("adminRoles", new Boolean(false));
			redirectedPage = "/login.jsp";
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}
		
	}

}
