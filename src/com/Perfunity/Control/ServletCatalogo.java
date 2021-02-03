package com.Perfunity.Control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Perfunity.Model.ProdottoBean;

/**
 * Servlet implementation class ServletCatalogo
 */
@WebServlet("/ServletCatalogo")
public class ServletCatalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCatalogo() {
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
		
		int pagina = Integer.parseInt(request.getParameter("page"));		
		
		@SuppressWarnings("unchecked")
		ArrayList<ProdottoBean> pbarr = (ArrayList<ProdottoBean>) request.getSession().getAttribute("pbarr");
		
		int j=(pagina-1)*4;

		while((j<pagina*4)&&(j<pbarr.size())){
			response.getWriter().append
			(
						"<div class=\"product-card col-3\" id=\"pb\" style=\"display: inline-flex;\">" +
						" <form method='GET' name='ServletProdotto' action='negozio'>    " + 
						"    <div class='pc-img col-10'><a href='#'><button type='submit' name='prodotto' value='"+pbarr.get(j).getProdottoID()+"' style='background-color:transparent;border-color:transparent; width:100%; height:100%;'><img src='assets/img/"+pbarr.get(j).getLinkImmagine()+"' width='100%'/></button></a></div> " + 
						"    <h1 class='pc-title'><a href='#'><button type='submit' name='prodotto' value='"+pbarr.get(j).getProdottoID()+"' style='background-color:transparent;border-color:transparent; width:100%; height:100%;'>"+pbarr.get(j).getNome()+"</button></a></h1> " + 
						"    <h3 class='pc-price'>EUR "+pbarr.get(j).getPrezzo()+"</h3> " + 
						"    <p class='pc-desc col-10'>"+pbarr.get(j).getDescrizione()+"</p> " + 
						" </form>  " +
						"</div>"

			);
			j++;
		}
	}
}
