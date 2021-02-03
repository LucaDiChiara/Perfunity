package com.Perfunity.ControlPanel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Perfunity.Model.TicketBean;
import com.Perfunity.Model.TicketDAO;

/**
 * Servlet implementation class ServletTicketPanel
 */
@WebServlet(name="ServletTicketPanel", urlPatterns="/tickets")
public class ServletTicketPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTicketPanel() {
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
			String risp = request.getParameter("risp");
			TicketBean tb = new TicketBean();
			TicketDAO tdao = new TicketDAO();
			
			if(risp!=null && !risp.isEmpty())
			{
				try
				{
					tb = tdao.getTicket(risp);
					request.setAttribute("tb",tb);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/reply_ticket.jsp");
					requestDispatcher.forward(request, response);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				String delete = request.getParameter("delete");
				if(delete!=null && !delete.isEmpty())
				{
					try
					{
						if(tdao.deleteTicket(delete))
						{
							request.setAttribute("success","true");
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/tickets.jsp");
							requestDispatcher.forward(request, response);
						}
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
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
			TicketDAO tdao = new TicketDAO();
			String richiestaID = request.getParameter("richiestaID");
			String testo_risposta = request.getParameter("testo_risposta");
			try
			{
				if(tdao.rispTicket(richiestaID,testo_risposta))
				{
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/tickets.jsp");
					requestDispatcher.forward(request, response);
				}
				else
				{
					request.setAttribute("error"," ");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/reply_ticket.jsp");
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
