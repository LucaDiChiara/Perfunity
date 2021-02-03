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

import com.Perfunity.Model.ProdottoBean;
import com.Perfunity.Model.ProdottoDAO;
import com.Perfunity.Model.RecensioneDAO;
import com.Perfunity.Model.UserBean;
import com.Perfunity.Model.UserDAO;

/**
 * Servlet implementation class ServletProdotto
 */
@WebServlet(name="ServletProdotto", urlPatterns= {"/negozio"})
public class ServletProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletProdotto() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		//HttpSession ssn = request.getSession();
		String prodotto = request.getParameter("prodotto");
		ProdottoBean pb = new ProdottoBean();
		try 
		{
				ProdottoDAO pdao = new ProdottoDAO();
				pb = pdao.getProdotto(prodotto);
		}
		catch(Exception e) {
				e.printStackTrace();
		}
		request.setAttribute("pb", pb);
		RequestDispatcher requestDispatcher = request.getRequestDispatcher("product.jsp");
		requestDispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession ssn = request.getSession();
		UserBean ub = (UserBean) ssn.getAttribute("userbean");
		Cookie[] arr = request.getCookies();
		Cookie ck = null;
		for(Cookie c: arr)
		{
			if(c.getName().equals("userCookie")) ck = c;
		}
		String action = request.getParameter("action");
		switch(action)
		{
			case "recensione":
					if(ub!=null || ck!=null)
					{
						if(ub==null)
						{
							UserDAO udao = new UserDAO();
							ub = udao.getUtente(ck.getValue());
						}
						String valutazione_req = request.getParameter("valutazione");
						int valutazione = Integer.parseInt(valutazione_req);
						String testo = request.getParameter("testo");
						String prodottoID = request.getParameter("prodottoID");
						String nome = request.getParameter("nome");
					
						RecensioneDAO rdao = new RecensioneDAO();
						try
						{
							if(rdao.insertRecensione(prodottoID,testo,valutazione,ub.getEmail(),nome))
							{
								request.setAttribute("success", "true");
								request.setAttribute("prodotto", prodottoID);
								doGet(request,response);
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
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
						requestDispatcher.forward(request, response);
					}
			break;
		}
	}

}
