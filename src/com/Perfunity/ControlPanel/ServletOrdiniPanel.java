package com.Perfunity.ControlPanel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Perfunity.Model.OrdiniBean;
import com.Perfunity.Model.OrdiniDAO;

/**
 * Servlet implementation class ServletOrdiniPanel
 */
@WebServlet(name="ServletOrdiniPanel", urlPatterns="/ordine")
public class ServletOrdiniPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOrdiniPanel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		
		HttpSession session = request.getSession();
		Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
		if (adminRoles!=null && adminRoles == true)
		{
			OrdiniBean ob = new OrdiniBean();
			OrdiniDAO odao = new OrdiniDAO();
			String delete = request.getParameter("orderDelete");
			if(delete!=null && !delete.isEmpty())
			{
				try
				{
					if(odao.deleteOrdine(delete))
					{
						RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("panel/orders.jsp");
						requestDispatcher1.forward(request, response);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				String order = request.getParameter("order");
				//String email = request.getParameter("email");
				try
				{
					ob = odao.getOrdine(order);
					//request.setAttribute("email",email);
					request.setAttribute("ob",ob);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/single_order.jsp");
					requestDispatcher.forward(request, response);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
		if (adminRoles!=null && adminRoles == true)
		{
			OrdiniDAO odao = new OrdiniDAO();
			String status = request.getParameter("status");
			String ordineID = request.getParameter("ordineID");
			try
			{
				if(odao.updateStatus(status,ordineID))
				{
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/orders.jsp");
					requestDispatcher.forward(request, response);
				}
			}
			catch(Exception e)
			{
				e.printStackTrace();
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
