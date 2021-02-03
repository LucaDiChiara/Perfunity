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
        	TicketDAO tdao = new TicketDAO();
        	ArrayList<TicketBean> tbarrTrue = new ArrayList<TicketBean>();
        	ArrayList<TicketBean> tbarrFalse = new ArrayList<TicketBean>();
        	try
        	{
        		tbarrTrue = tdao.getTicketsTrue();
        		tbarrFalse = tdao.getTicketsFalse();
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	
        %>

        <div id="container">
            <h1 class="title"><i class="fas fa-comments"></i> Lista ticket assistenza</h1>
            <hr><br><br>
            <h2 class="sub-title yellow">In sospeso</h2>
            <div >
            <form method="GET" name="ServletTicketPanel" action="tickets">
            <table style="margin-top: 20px;">
                <tbody>
                    <tr>
                        <th class="col-1">ID</th>
                        <th class="col-2">Utente</th>
                        <th class="col-7 ta-left">Messaggio</th>
                        <th class="col-2">Azioni</th>
                    </tr>
                    <%
                    	if(tbarrFalse!=null)
                    	{
                    		int i = 0;
                    		while(i<tbarrFalse.size())
                    		{
                    %>
                    	
                    <tr>
                        <td><%=tbarrFalse.get(i).getRichiestaID() %></td>
                        <td><%=tbarrFalse.get(i).getEmailUtenti() %></td>
                        <td class="ta-left"><%=tbarrFalse.get(i).getTesto() %></td>
                        <td>
                            <a href="#"><span class="action-btn label label-success"><button type="submit" name="risp" value="<%=tbarrFalse.get(i).getRichiestaID() %>" style="background-color:transparent;border-color:transparent;" class="fas fa-reply"></button></span></a>
                            <a href="#"><span class="action-btn label label-alert"><button type="submit" name="delete" value="<%=tbarrFalse.get(i).getRichiestaID() %>" style="background-color:transparent;border-color:transparent;" class="fas fa-trash-alt"></button></span></a>
                        </td>
                    </tr>
                    <%
                    			i++;
                    		}
                    	}
                    %>
                </tbody>
            </table>
            </form>
            </div>
            <br><br>
            <h2 class="sub-title green">Risolti</h2>

            <table style="margin-top: 20px;">
                <tbody>
                    <tr>
                        <th class="col-1">ID</th>
                        <th class="col-3">Utente</th>
                        <th class="col-4">Messaggio</th>
                        <th class="col-4">Risposta</th>
                    </tr>
                    <%
                    	if(tbarrTrue!=null)
                    	{
                    		int i = 0;
                    		while(i<tbarrTrue.size())
                    		{
                    %>
                    <tr>
                        <td><%=tbarrTrue.get(i).getRichiestaID() %></td>
                        <td><%=tbarrTrue.get(i).getEmailUtenti() %></td>
                        <td><%=tbarrTrue.get(i).getTesto() %></td>
                        <td><%=tbarrTrue.get(i).getTesto_risposta() %></td>
                    </tr>
                    <%
                    			i++;
                    		}
                    	}
                    %>
                </tbody>
            </table>
        </div>
        <% 
        if(request.getAttribute("error")==null) { }
		else {
		%>
		<script>alert("Errore nell'eliminazione del ticket.");</script>
		<%
		  	 }
    	%>
    	<% 
        if(request.getAttribute("success")==null) { }
		else {
		%>
		<script>alert("Ticket eliminato con successo!");</script>
		<%
		  	 }
    	%>
        <%@include file="footerPanel.jsp" %>
    </body>
</html>