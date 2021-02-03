package com.Perfunity.Control;

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
import com.Perfunity.Model.UserBean;

/**
 * Servlet implementation class ServletTicket
 */
@WebServlet(name="ServletTicket", urlPatterns="/assistenza")
public class ServletTicket extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletTicket() {
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
		//String richiestaID = request.getParameter("ticketID");
		if(ub!=null)
		{
			String button = request.getParameter("button");
			TicketBean tb = new TicketBean();
			TicketDAO tdao = new TicketDAO();
			if(button!=null && !button.isEmpty())
			{
				try 
				{
					tb = tdao.getTicket(button);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("tb", tb);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("ticket.jsp");
				requestDispatcher.forward(request, response);
			}
		
			String action = request.getParameter("action");
			if(action!=null && !action.isEmpty())
			{
				switch(action)
				{
					case "newTicket":
							String objTicket = request.getParameter("objTicket");
							String testoTicket = request.getParameter("testoTicket");
							String emailUser = request.getParameter("emailUser");
							try
							{
								if(tdao.insertTicket(emailUser,objTicket,testoTicket))
								{
									request.setAttribute("success", "true");
									RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
									requestDispatcher.forward(request, response);
								}
							}catch(Exception e) {
								e.printStackTrace();
							}
					break;
				}
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
