<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
       <%@ include file="head.jsp" %>
       <script src="assets/js/jqueryc.js"></script>
    </head>

    <body>
    	<%@ include file="navbar.jsp" %>
        <div id="bodycover" class="col-12">
            <h2>Negozio</h2>
        </div>

        <div id="container">
            <div id="catalog" class="col-12">
                <div class="col-12 nb-cat-filter">Ti ricordiamo che tutti i profumi sono di misura standard 100ml</div>
                <div id="cat-prods">
                <%
                	@SuppressWarnings("unchecked")
		  			ArrayList<ProdottoBean> pbarr =(ArrayList<ProdottoBean>) request.getAttribute("pbarr");
                	int pagina = (int) request.getAttribute("page");
                	request.getSession().setAttribute("pbarr",pbarr);
  					int i=(pagina-1)*4;
		  			if(pbarr != null)
		  			{
		  				while((i<pbarr.size()) && (i<pagina*4))
		  				{
  				%>
                	<div class="product-card col-3" id="pb" style="display: inline-flex;">
	                	<form method="GET" name="ServletProdotto" action="negozio">    
	                    	<div class="pc-img col-10"><a href="#"><button type="submit" name="prodotto" value="<%=pbarr.get(i).getProdottoID()%>" style="background-color:transparent;border-color:transparent; width:100%; height:100%;"><img src="assets/img/<%=pbarr.get(i).getLinkImmagine()%>" width="100%"/></button></a></div>
	                    	<h1 class="pc-title"><a href="#"><button type="submit" name="prodotto" value="<%=pbarr.get(i).getProdottoID()%>" style="background-color:transparent;border-color:transparent; width:100%; height:100%;"><%=pbarr.get(i).getNome()%></button></a></h1>
	                    	<h3 class="pc-price">EUR <%=pbarr.get(i).getPrezzo()%></h3>
	                    	<p class="pc-desc col-10"><%=pbarr.get(i).getDescrizione()%></p>
	                	</form> 
                	</div>
                <%
  							i++;
  						}
  				   }
		  		   else
		  		   {
		  		%>
                	<div align="center">
							<br>
								<p style="font-size: 21px; font-family:arial,sans-serif;">Nessun elemento presente.</p>
							<br>
					</div>
  				<%
  				   } 
  				%>
  			   </div>
            

		        <br><br>
		        <div align="center" class="moreProd">
		  				<%
		  				if(pbarr!=null)
		  				{
		  					for(int p=1; p<=(pbarr.size()/4)+1;p++)
		  					{
		  					%>
		  						<a href="#" class="moreProd1" onClick="changePage(<%=p%>)"><%= p %></a>
		  					<%
		  					}
		  				}
		  				%>
		  		</div><br>
	  		</div>
  		</div>
        <script>
		function changePage(pagina)
		{
			$(document).ready(function()	
    						  {
            					$.post("ServletCatalogo", {page: pagina}, function(data, status){
               						$("#cat-prods").html(data);
            						});
    						  });
		}
		</script>
		<%@include file="footer.jsp" %>
    </body>
</html>