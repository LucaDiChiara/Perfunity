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
 * Servlet implementation class ServletProdottiPanel
 */
@WebServlet(name="ServletProdottiPanel", urlPatterns="/products")
public class ServletProdottiPanel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProdottiPanel() {
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
			
			String modify = request.getParameter("modify");
			ProdottoDAO pdao = new ProdottoDAO();
			if(modify!=null && !modify.isEmpty())
			{
				try
				{
					ProdottoBean pb = pdao.getProdotto(modify);
					request.setAttribute("pb",pb);
					RequestDispatcher requestDispatcher3 = request.getRequestDispatcher("panel/single_product.jsp");
					requestDispatcher3.forward(request, response);
					
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
						if(pdao.deleteProdotto(delete))
						{
							RequestDispatcher requestDispatcher3 = request.getRequestDispatcher("panel/products.jsp");
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
					request.setAttribute("error", " ");
					RequestDispatcher requestDispatcher3 = request.getRequestDispatcher("panel/index-admin.jsp");
					requestDispatcher3.forward(request, response);
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
			String button = request.getParameter("type");

			if(button.equals("Tutti"))
			{
				ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
				try {
					ProdottoDAO pdao = new ProdottoDAO();
					pbarr = pdao.getProdotti();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("pbarr",pbarr);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/products.jsp");
				requestDispatcher.forward(request, response);
			}
			else
			{
				ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
				try {
					ProdottoDAO pdao = new ProdottoDAO();
					pbarr = pdao.getProdottiStandard(button);
				}
				catch(Exception e) {
					e.printStackTrace();
				}
				request.setAttribute("pbarr",pbarr);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/products.jsp");
				requestDispatcher.forward(request, response);
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
