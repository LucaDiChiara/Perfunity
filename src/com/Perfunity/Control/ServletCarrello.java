package com.Perfunity.Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.Perfunity.Model.CarrelloBean;
import com.Perfunity.Model.ProdottoBean;
import com.Perfunity.Model.ProdottoDAO;
import com.Perfunity.Model.UserBean;

/**
 * Servlet implementation class ServletCarrello
 */
@WebServlet(name="ServletCarrello", urlPatterns= {"/checkout", "/carrello"})
public class ServletCarrello extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCarrello() {
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
		
		//HttpSession ssn = request.getSession();
		String action = request.getParameter("action");
		ProdottoBean pb = new ProdottoBean();
		ProdottoDAO pdao = new ProdottoDAO();
		CarrelloBean carrello = (CarrelloBean) request.getSession().getAttribute("Carrello");
		
		if(carrello == null || carrello.getProdotti().size() == 0) {
			carrello = new CarrelloBean();
			request.getSession().setAttribute("Carrello", carrello);
		}
		
		switch(action)
		{
			case "goToCart":
				String qty_req = request.getParameter("quantity");
				int qty = Integer.parseInt(qty_req); //Quantità del prodotto
				String prodottoID = request.getParameter("prodottoID");
				pb = pdao.getProdotto(prodottoID);
				if(carrello.getProdottoB(prodottoID)) //Controlla se il prodotto è già inserito nel carrello. Aggiorna la quantità if true
				{
					carrello.setQty(qty,prodottoID);
					request.getSession().setAttribute("Carrello", carrello);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
					requestDispatcher.forward(request, response);
				}
				else
				{
					carrello.addP(pb);
					carrello.setQty(qty,prodottoID);
					request.getSession().setAttribute("Carrello", carrello);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
					requestDispatcher.forward(request, response);
				}
			break;
			
			case "svuota":
				carrello.clean();
				request.getSession().setAttribute("Carrello", carrello);
				RequestDispatcher requestDispatcher = request.getRequestDispatcher("cart.jsp");
				requestDispatcher.forward(request, response);
			break;
			
			case "checkout":
				UserBean ub = (UserBean) request.getSession().getAttribute("userbean");
				if(ub!=null)
				{
					if(carrello.getTotaleCart()!=0)
					{
						float tot = carrello.getTotaleCart();
						request.setAttribute("tot",tot);
						RequestDispatcher requestDispatcherU = request.getRequestDispatcher("checkout.jsp");
						requestDispatcherU.forward(request, response);
					}
					else
					{
						float tot = carrello.getTotaleCart();
						request.setAttribute("tot",tot);
						RequestDispatcher requestDispatcherU = request.getRequestDispatcher("checkout.jsp");
						requestDispatcherU.forward(request, response);
					}
					
				}
				else
				{
					request.setAttribute("error", " ");
					RequestDispatcher requestDispatcherK = request.getRequestDispatcher("login.jsp");
					requestDispatcherK.forward(request, response);
				}
			break;
			
			case "cart":
				RequestDispatcher requestDispatcherC = request.getRequestDispatcher("cart.jsp");
				requestDispatcherC.forward(request, response);
			break;
			
			case "update":
				String[] qty_req1 = request.getParameterValues("quantity");
				String[] prodottoID1 = request.getParameterValues("prodottoID");
				int i = 0;
				while(i<qty_req1.length)
				{
					String qty_req11 = qty_req1[i];
					String prodottoID11 = prodottoID1[i];
					int qty1 = Integer.parseInt(qty_req11); //Quantità del prodotto
					carrello.setQty(qty1,prodottoID11);
					i++;
				}
				request.getSession().setAttribute("Carrello", carrello);
				RequestDispatcher requestDispatcher111 = request.getRequestDispatcher("cart.jsp");
				requestDispatcher111.forward(request, response);
			break;
			
		}
		
	}

}
