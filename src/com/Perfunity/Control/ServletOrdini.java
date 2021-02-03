package com.Perfunity.Control;

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
import com.Perfunity.Model.UserBean;

/**
 * Servlet implementation class ServletOrdini
 */
@WebServlet(name="ServletOrdini", urlPatterns="/ordini")
public class ServletOrdini extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletOrdini() {
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
		String button = request.getParameter("button");
		OrdiniBean ob = new OrdiniBean();
		
		if(ub!=null)
		{
			//String ordineID = request.getParameter("ordineID");
			try 
			{
				OrdiniDAO odao = new OrdiniDAO();
				ob = odao.getOrdine(button);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			request.setAttribute("ob", ob);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("order.jsp");
			requestDispatcher.forward(request, response);
		}
		else
		{
			request.setAttribute("error", " ");
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
