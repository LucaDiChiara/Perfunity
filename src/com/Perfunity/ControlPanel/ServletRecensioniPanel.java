package com.Perfunity.ControlPanel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Perfunity.Model.RecensioneDAO;

/**
 * Servlet implementation class ServletRecensioniPanel
 */
@WebServlet(name="ServletRecensioniPanel", urlPatterns="/updated")
public class ServletRecensioniPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRecensioniPanel() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
		if (adminRoles == true)
		{
			String action = request.getParameter("action");
			String id = request.getParameter("id");
			RecensioneDAO rdao = new RecensioneDAO();
			switch(action)
			{
				case "update":
						if(rdao.updateRecensioniTrue(id))
						{
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/reviews.jsp");
							requestDispatcher.forward(request, response);
						}
				break;
				
				case "delete":
						if(rdao.deleteRecensioni(id))
						{
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/reviews.jsp");
							requestDispatcher.forward(request, response);
						}
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
