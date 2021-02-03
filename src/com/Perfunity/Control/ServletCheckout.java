package com.Perfunity.Control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Perfunity.Model.CarrelloBean;
import com.Perfunity.Model.IndirizzoDAO;
import com.Perfunity.Model.OrdiniDAO;
import com.Perfunity.Model.ProdottoBean;
import com.Perfunity.Model.UserBean;
import com.Perfunity.Model.UserDAO;

/**
 * Servlet implementation class ServletCheckout
 */
@WebServlet(name="ServletCheckout", urlPatterns="/profile")
public class ServletCheckout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCheckout() {
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
		HttpSession ssn = request.getSession();
		UserBean ub = (UserBean) ssn.getAttribute("userbean");
		Cookie[] arr = request.getCookies();
		Cookie ck = null;
		for(Cookie c: arr)
		{
			if(c.getName().equals("userCookie")) ck = c;
		}
		
		if(ub!=null || ck!=null)
		{
			try 
			{
				if(ub==null)
				{
					UserDAO udao = new UserDAO();
					ub = udao.getUtente(ck.getValue());
				}
				CarrelloBean cb = (CarrelloBean) ssn.getAttribute("Carrello");
				OrdiniDAO odao = new OrdiniDAO();
				IndirizzoDAO idao = new IndirizzoDAO();
				String via = request.getParameter("via");
				String citta = request.getParameter("citta");
				String CAP_req = request.getParameter("CAP");
				int CAP = Integer.parseInt(CAP_req);
				float totale = cb.getTotaleCart();
				Random rand = new Random();
				int id_int = rand.nextInt(1000)+1;
				String ordineID = String.valueOf(id_int);

				Date d = new Date();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				String data = sdf.format(d);

				if(odao.insertOrder(ordineID,data,ub.getNumTel(),totale))
				{
					ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
					int i = 0;
					pbarr = cb.getProdotti();
					while(i<pbarr.size())
					{
						String prodottoID = pbarr.get(i).getProdottoID();
						int qty = cb.getQty(pbarr.get(i).getProdottoID());

						odao.insertQtyItems(prodottoID,ordineID,qty);
						i++;
					}
					
					idao.insertIndirizzo(ordineID,via,citta,CAP);
					odao.effettuaOrdine(ub.getEmail(),ordineID);
					cb.clean();
					request.setAttribute("successOrder"," ");
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
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
