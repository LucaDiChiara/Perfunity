<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
       <%@include file="head.jsp" %>
    </head>

    <body>
        <%@ include file="navbar.jsp" %>
        <div id="bodycover" class="col-12"></div>
        <%
        	HttpSession ssn = request.getSession();
			UserBean ub = (UserBean) ssn.getAttribute("userbean");
			if(ub==null)
			{
				Cookie[] arr = request.getCookies();
				Cookie ck = null;
				String email = null;
				for(Cookie c: arr)
				{
					if(c.getName().equals("userCookie")) ck = c;
				}
				if(ck!=null)
				{
					email = ck.getValue();
				}
				try
				{
					UserDAO udao = new UserDAO();
					ub = udao.getUtente(email);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				
			}
        %>
        
        <%
		if(request.getAttribute("success")==null) { }
		else {
		%>
		<script>alert("Recensione effettuata con successo!");</script>
		<%
		  	 }
    	%>

        <div id="container">
        <%
        	ProdottoBean prodotto = (ProdottoBean) request.getAttribute("pb");
        	String id = prodotto.getProdottoID();
        	ArrayList<RecensioneBean> rbarr = new ArrayList<RecensioneBean>();
        	RecensioneDAO rdao = new RecensioneDAO();
        	try
        	{
       			rbarr = rdao.getRecensioni(prodotto.getProdottoID());
       		}
       		catch(Exception e)
       		{
      			e.printStackTrace();
       		}

        	if(prodotto!=null)
        	{        		
        %>
            <div class="prod-details col-12">
                <div class="col-5">
                    <div class="col-10 primary-img">
                        <img class="" src="assets/img/<%=prodotto.getLinkImmagine()%>" alt="NAME_PROD">
                    </div>
                </div>
                <div class="col-7">
                    <div class="info-prod">
                    	<form method="POST" name="ServletCarrello" action="carrello">
                        	<h4 class="cat-prod"><span><%=prodotto.getStandard()%></span></h4>
                        	<h1 class="title-prod"><%=prodotto.getNome()%></h1>
                        	<h3 class="description-prod"><%=prodotto.getDescrizione() %></h3>

                        	<h2 class="price-prod">EUR <%=prodotto.getPrezzo() %></h2>

	                        <input class="qty-spinner qty-prodpage" type="number" name="quantity" min="1" max="9" value="1">
	                        <input type="hidden" name="prodottoID" value="<%=prodotto.getProdottoID() %>">
    	                    <button type="submit" name="action" value="goToCart" class="primary-btn addtocart-btn">Aggiungi al carrello</button>
    	               </form>
                    </div>
                </div>
         <%
        	}
         %>
            </div>
            
            <div class="container-feedback col-12">
			<%
				if(rbarr!=null)
				{
			%>
                <h3 class="title-box">Tutte le recensioni clienti</h3> 
				<%
					
						int i = 0;
   		  				while(i<rbarr.size()){
				%>
                <div class="row-review col-12">
                    <h1><span class="author-review"><%=rbarr.get(i).getNome()%></span> ha valutato questo articolo <span class="star-review"><%=rbarr.get(i).getValutazione() %></span></h1>
                    <p class="desc-review"><%=rbarr.get(i).getTesto() %></p>
                </div>
                <hr>
                <%
                			i++;
   	  					}
   	  				}
                %>
                
                <%
                	if(ub!=null)
                	{
                %>
                
			 	<form method="POST" action="negozio" name="ServletProdotto">
                	<div class="write-review">
                	    <h3 class="title-box">Scrivi una recensione</h3> <span class="author-review">Valutazione</span>
                	    <select name="valutazione">
				  			<option value="1">1</option>
				  			<option value="2">2</option>
				  			<option value="3">3</option>
				  			<option value="4">4</option>
				  			<option value="5" selected>5</option>
				  		</select>
                	    <textarea id="txtarea-fbck" name="testo" placeholder="Fai sapere agli altri cosa pensi di questo prodotto."></textarea>
                	    <input type="hidden" name="nome" value="<%=ub.getNome() %>">
                	    <input type="hidden" name="prodottoID" value="<%=id%>">
                	    <button type="submit" name="action" value="recensione" class="primary-btn">Invia recensione</button>
                	</div>
               </form>
               <%
                	}
                	else
                	{
                %>
                <form method="POST" action="negozio" name="ServletProdotto">
                	<div class="write-review">
                		<h3 class="title-box">Scrivi una recensione</h3> <span class="author-review">Valutazione</span>
                		<select name="valutazione">
				  			<option value="1">1</option>
				  			<option value="2">2</option>
				  			<option value="3">3</option>
				  			<option value="4">4</option>
				  			<option value="5" selected>5</option>
				  		</select>
               		    <textarea id="txtarea-fbck" name="testo" placeholder="Per scrivere una recensione, devi essere loggato!" disabled></textarea>
               	    	<button type="submit" name="action" value="recensione" class="primary-btn">Invia recensione</button>
                	</div>
                </form>
                <% 
                	}
               %>
            </div>
        </div>
                 
        <%@include file="footer.jsp" %>
    </body>
</html>