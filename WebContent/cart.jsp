<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file="head.jsp" %>
    </head>

    <body>
    	<%@ include file="navbar.jsp" %>
    	<%
    		HttpSession ssn = request.getSession();
			UserBean ub = (UserBean) ssn.getAttribute("userbean");
			CarrelloBean cb = (CarrelloBean) ssn.getAttribute("Carrello");
    		ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
    		ArrayList<Float> totali_parziali = new ArrayList<Float>();
			try {
					pbarr = cb.getProdotti();
					totali_parziali = cb.getTotaleParziale(pbarr, cb.getAllQty());
				}
				catch(Exception e) {
				e.printStackTrace();
				}
    	%>

        <div id="bodycover" class="col-12">
            <h2>Il tuo carrello</h2>
        </div>

        <div id="container">
            <div id="cart-section">
            	<form method="POST" name="ServletCarrello" action="checkout">
                 <table class="col-12 table-cart" id="tbl">
                    <tbody>
                        <tr>
                            <th class=""></th>
                            <th class="col-6 ta-left">Descrizione articolo</th>
                            <th class="col-2 ta-center">Prezzo unitario</th>
                            <th class="col-2 ta-center">Quantità </th>
                            <th class="con-2 ta-center">Prezzo totale</th>
                        </tr>
                     <%
                        if(pbarr!=null)
                        {
                        	int i = 0;
                        	
       		  				while(i<pbarr.size()){    
                     %>
                        <tr class="row-item">
                            <td class="col-1"><img class="img-thumb" src="assets/img/<%=pbarr.get(i).getLinkImmagine()%>" alt="NAME_PROD" width=100 height=100></td>
                            <td class="ta-left"><%=pbarr.get(i).getNome()%></td>
                            <td class="ta-price"><input type="hidden" id="price" name="prodottoID" value="<%=pbarr.get(i).getProdottoID() %>">EUR <%=pbarr.get(i).getPrezzo()%></td>
                            <td class="ta-center">
                                <input class="qty-spinner" id="qty" type="number" name="quantity" min="1" max="9" value="<%=cb.getQty(pbarr.get(i).getProdottoID())%>">
                            </td>
                            <td class="ta-center">EUR <%=totali_parziali.get(i) %></td>
                        </tr>
                    <% 
                     			i++;
       	  					}
       	  				}       
                    %>
                    </tbody>
                </table>
                <% if(pbarr.size()!=0)
                   {
                %>
                <div style="display: flex; justify-content: flex-end;">
                    <button class="button full-btn col-4" type="submit" name="action" value="svuota"  style="vertical-align:middle"><span><i class="fas fa-trash-alt"></i> Svuota carrello</span></button>
                    <button class="button full-btn col-4" type="submit" name="action" value="update" style="vertical-align:middle"><span><i class="fas fa-sync-alt"></i> Aggiorna carrello</span></button>
                    <button class="button full-btn bg-primary col-4" type="submit" name="action" value="checkout" style="vertical-align:middle"><span>Vai alla cassa <i class="fas fa-long-arrow-alt-right"></i></span></button>
                
                </div>
                <%
                   }
                %>
			</form>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-1.10.2.js"></script>
        <script type="text/javascript">
        	/*function tot()
        	{
        		var prezzo_req = document.getElementById("price").value;
        		var spinner_req = document.getElementById("qty").value;
        		var prezzo = parseFloat(prezzo_req);
        		var spinner = parseInt(spinner_req);
        		var totale = parseFloat(prezzo*spinner);
        		document.getElementById("totale").innerHTML = totale;
        		//$('#totale').text(totale); 		
        	}*/
        	
        	function tot(price){
        		var spinner = $(this);

        		var prezzo_req = spinner.closest('#price').value;
        		var spinner_req = spinner.closest('#qty').value;

        		var prezzo = parseFloat(prezzo_req);
        		var qty = parseInt(spinner_req);

        		var totale = parseFloat(prezzo*qty);
        		//spinner.closest("#totale").innerHTML = totale;
        		console.log(totale);
        		}
        </script>
       <%@ include file="footer.jsp" %>
    </body>
</html>