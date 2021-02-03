<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="head.jsp" %>
    </head>

    <body>
		<%@ include file="navbar.jsp" %>
        <div id="bodycover" class="col-12"><h2>Il mio ticket</h2></div>
        <%
        	TicketBean ticket = (TicketBean) request.getAttribute("tb");
        	if(ticket!=null)
			{
        %>

        <div id="container">
        	<form method="POST" name="ServletProfile" action="profilo">
            <button type="submit" name="button" value="profile" class="full-btn"><i class="fas fa-long-arrow-alt-left"></i> Torna al profilo</button>
			</form>
            <div class="messages-ticket">
                    <h3 class="title-box">Dettagli ticket #<%=ticket.getRichiestaID() %> 
                    <%
                    	if(ticket.getFlag().equals("true"))
                    	{
                    %>
                    		<span class="label-success">Risolto</span></h3><br>
                    <%
                    	}
                    	else
                    	{
                    %>
                    		<span class="label-warning">In attesa</span></h3><br>
                    <%
                    	}
                    %>

                    <div style="display: flex; justify-content: flex-end;">
                        <div class="ticket-msg col-6 bg-primary"><%=ticket.getTesto() %></div>
                    </div>
					<%
						if(ticket.getTesto_risposta() != null && !ticket.getTesto_risposta().isEmpty())
						{
					%>
                    <div style="display: flex; justify-content:flex-start;">
                        <div class="ticket-msg col-6 bg-light"><%=ticket.getTesto_risposta()%></div>
                    </div>
                    <%
						}
			}
                    %>
            </div>
			<form method="POST" name="ServletProfile" action="profilo">
            	<button type="submit" name="button" value="assistenza" class="full-btn bg-primary"><i class="fas fa-plus"></i> Apri nuovo ticket</button>
            </form>
        </div>

        <%@include file="footer.jsp" %>
    </body>
</html>