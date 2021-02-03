<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
    	<%@include file="headPanel.jsp" %>
    </head>
    <body>
        <%@include file="navbarPanel.jsp" %>
        <%
        	TicketBean tb = (TicketBean) request.getAttribute("tb");
        	if(tb!=null)
        	{
        %>

        <div id="container">
            <h1 class="title"><i class="fas fa-star"></i> Rispondi al ticket #<%=tb.getRichiestaID() %></h1>
            <hr><br><br>
            <h1 class="sub-title">Messaggio inviato da "<%=tb.getEmailUtenti() %>":</h1>

            <div>
                <div style="display: flex; justify-content: flex-start;">
                    <div class="ticket-msg col-8 bg-primary"><%=tb.getTesto() %></div>

                </div>
            </div>
        <%
        	}
        %>
            <hr><br><br>
            <h1 class="sub-title">Rispondi:</h1><br>
            <form name="ServletTicketPanel" action="tickets" method="POST">
            	<input type="hidden" name="richiestaID" value="<%=tb.getRichiestaID() %>">
                <textarea name="testo_risposta" id="" class="col-12" rows="10" style="resize:none;"></textarea><br><br>
                <button type="submit" class="btn btn-primary col-12">Invia</button>
            </form>

        </div>
        <% 
        if(request.getAttribute("error")==null) { }
		else {
		%>
		<script>alert("Errore nell'invio del ticket.");</script>
		<%
		  	 }
    	%>
    </body>
</html>