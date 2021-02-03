<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
    	<%@include file="headPanel.jsp" %>
    	<%
			// Check user credentials
			Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
			if ((adminRoles == null) || (!adminRoles.booleanValue()))
			{	
    			response.sendRedirect("./login.jsp");
    			return;
			}
		%>
    </head>
    <body>
		<%@include file="navbarPanel.jsp" %>
		
		<%
			if(request.getAttribute("error")==null){}
			else
			{
				%>
				<script> alert('Errore. Sei stato rimandato alla dashboard!'); </script>
				<%
			}
		%>
		
        <div id="container">
            <h1 class="title"><i class="fas fa-tachometer-alt"></i> Dashboard</h1>
            <hr><br><br>
            <p class="welcome-text">Questa è la Dashboard di amministrazione di Perfunity.</p>
            <p class="welcome-text">Qui potrai controllare in tempo reale gli ordini ricevuti, gli utenti registrati e l'anagrafica di tutti i prodotti presenti nel catalogo del negozio.</p>
            <p class="welcome-text">Inoltre, potrai valutare e moderare i feedback lasciati sulle singole pagine prodotto, eliminando quelle che ritieni contengano un linguaggio non appropriato.</p>
            <br>
            <p class="welcome-text">Questo è quanto...<span class="title">In bocca al lupo! 😉</span></p>
       </div>

        <%@include file="footerPanel.jsp" %>
    </body>
</html>