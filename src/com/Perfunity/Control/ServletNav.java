package com.Perfunity.Control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Perfunity.Model.ProdottoBean;
import com.Perfunity.Model.ProdottoDAO;

/**
 * Servlet implementation class ServetNav
 */
@WebServlet(name="ServletNav", urlPatterns="/Categoria")
public class ServletNav extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNav() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		//HttpSession ssn = request.getSession();
		String button = request.getParameter("type");
		
		if(button.equals("home"))
		{
			response.sendRedirect("index.jsp");
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
			request.setAttribute("page", 1);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("shop.jsp");
			requestDispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        
		
	}
			
			

}
