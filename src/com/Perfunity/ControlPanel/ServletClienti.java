package com.Perfunity.ControlPanel;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Perfunity.Model.UserBean;
import com.Perfunity.Model.UserDAO;

/**
 * Servlet implementation class ServletClienti
 */
@WebServlet(name="ServletClienti", urlPatterns="/cliente")
public class ServletClienti extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletClienti() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
		if (adminRoles!=null && adminRoles == true)
		{
			UserBean ub = new UserBean();
			UserDAO udao = new UserDAO();
			String delete = request.getParameter("delete");
			if(delete!=null && !delete.isEmpty())
			{
				try
				{
					if(udao.deleteUser(delete))
					{
						RequestDispatcher requestDispatcher3 = request.getRequestDispatcher("panel/clients.jsp");
						requestDispatcher3.forward(request, response);
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				String email = request.getParameter("submit");

				try
				{
					ub = udao.getUtente(email);
					if(ub!=null)
					{
						request.setAttribute("ub", ub);
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/single_client.jsp");
						requestDispatcher.forward(request, response);
					}
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

}
