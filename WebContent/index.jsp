<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*" %>
<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="head.jsp" %>
   </head>

    <body>
    	<%@ include file="navbar.jsp" %>

    <div id="cover-home" class="col-12"></div>
    <%	
    if(request.getAttribute("errorLogout")==null) { }
		else {
	%>
		<script>alert("Logout non necessario. Verrai reindirizzato comunque alla home.");</script>
	<%
			 }
    %>

	<div id="container">
		<div class="sub-container col-12">
			<h1 class="title-box">
				🥇 I più venduti
			</h1>
			<div class="horiz-carousel-prod">
			<%
                    ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
                    try {
                   			ProdottoDAO pdao = new ProdottoDAO();
                   			pbarr = pdao.getProdottiVenduti();
                   	} catch (Exception e) {
                   		e.printStackTrace();
                   	}
                       
                    if(pbarr != null)
    	  			{
   		  				int i = 0;
   		  				while(i<pbarr.size()){
             %>
				<div class="product-card col-3">
					<form method="GET" name="ServletProdotto" action="negozio">
						<div class="pc-img col-10">
							<a href="#"><button type="submit" name="prodotto"
									value="<%=pbarr.get(i).getProdottoID()%>"
									style="background-color: transparent; border-color: transparent; width: 100%; height: 100%;">
									<img src="assets/img/<%=pbarr.get(i).getLinkImmagine()%>"
										width="100%" />
								</button></a>
						</div>
						<h1 class="pc-title">
							<a href="#"><button type="submit" name="prodotto"
									value="<%=pbarr.get(i).getProdottoID()%>"
									style="background-color: transparent; border-color: transparent; width: 100%; height: 100%;"><%=pbarr.get(i).getNome()%></button></a>
						</h1>
						<h3 class="pc-price">
							EUR <%=pbarr.get(i).getPrezzo()%></h3>
						<p class="pc-desc col-10"><%=pbarr.get(i).getDescrizione()%></p>
					</form>
				</div>
			<%
  					i++;
  					}
  				}
		  		%>
		  	</div>
		</div>
		<hr>
  <!--  <div class="sub-container col-12">
			<div class="box-link-cat" style="background-color: #00BCD4;"></div>
			<div class="box-link-cat" style="background-color: #E91E63;">2</div>
			<div class="box-link-cat" style="background-color: #4CAF50;">3</div>
			<div class="box-link-cat" style="background-color: #F44336;">4</div>
		</div>-->
		<hr>
		<div class="sub-container col-12">
			<h1 class="title-box" style="text-align: right;">I nuovi arrivati 😍</h1>
			<div class="horiz-carousel-prod">
			<%
                    	ArrayList<ProdottoBean> pbarr1 = new ArrayList<ProdottoBean>();
                        try {
                    			ProdottoDAO pdao = new ProdottoDAO();
                    			pbarr1 = pdao.getProdotti();
                    	} catch (Exception e) {
                    		e.printStackTrace();
                    	}
                        
                        if(pbarr1 != null)
    		  			{
    		  				int i = 0;
    		  				while(i<pbarr1.size()){
             %>
			
				<div class="product-card col-3">
					<form method="GET" name="ServletProdotto" action="negozio">
						<div class="pc-img col-10"><a href="#"><button type="submit" name="prodotto" value="<%=pbarr1.get(i).getProdottoID()%>" style="background-color: transparent; border-color: transparent; width: 100%; height: 100%;"><img src="assets/img/<%=pbarr1.get(i).getLinkImmagine()%>" width="100%" /></button></a></div>
						<h1 class="pc-title"><button type="submit" name="prodotto" value="<%=pbarr1.get(i).getProdottoID()%>" style="background-color: transparent; border-color: transparent; width: 100%; height: 100%;"><%=pbarr1.get(i).getNome()%></button></h1>
						<h3 class="pc-price">EUR <%=pbarr1.get(i).getPrezzo()%></h3>
						<p class="pc-desc col-10"><%=pbarr1.get(i).getDescrizione()%></p>
					</form>
				</div>

			<%
  					i++;
  					}
  				}
		  	%>
		  	</div>
		</div>
	</div>
	<%@include file="footer.jsp" %>
    </body>
</html>