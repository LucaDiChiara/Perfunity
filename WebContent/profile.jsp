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
			if(request.getAttribute("successOrder")==null) { }
			else {
		%>
					<script>alert("Ordine effettuato con successo! Controlla il tuo profilo per l'avanzamento dei tuoi prodotti!");</script>
		<%
			  	 }
    	%>
    	<%
			if(request.getAttribute("success")==null) { }
			else {
		%>
					<script>alert("Messaggio di richiesta inviato con successo!");</script>
		<%
			  	 }
    	%>
        <div id="bodycover" class="col-12"><h2>Il mio account</h2></div>
        <%
       		HttpSession ssn = request.getSession();
			UserBean ub = (UserBean) ssn.getAttribute("userbean");
			if(ub==null)
			{
				Cookie[] arr = request.getCookies();
				Cookie ck = null;
				for(Cookie c: arr)
				{
					if(c.getName().equals("userCookie")) ck = c;
				}
				String email = ck.getValue();
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
			String numTel = ub.getNumTel();
			String email = ub.getEmail();
			TicketDAO tdao = new TicketDAO();
        	OrdiniDAO odao = new OrdiniDAO();
        	ArrayList<OrdiniBean> obarr = new ArrayList<OrdiniBean>();
        	ArrayList<TicketBean> tbarr = new ArrayList<TicketBean>();
        	try
        	{
        		obarr = odao.getOrdini(numTel);
        		tbarr = tdao.getTickets(email);
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	
        %>

        <div id="container" class="page-profile">
        	<form method="POST" name="ServletProfile" action="profilo">
            	<div class="square-box profile-box col-4 bg-primary">
            	    <h3>Torna allo shop</h3>
            	    <button type="submit" name="button" value="home" class="small-btn bg-light">Continua</button>
            	</div>

            	<div class="square-box profile-box col-3 bg-light">
            	    <h3>Cambia email</h3>
            	    <button type="submit" name="button" value="changeEmail" class="small-btn">Cambia</button>
           	 	</div>

            	<div class="square-box profile-box col-3 bg-light">
                	<h3>Cambia password</h3>
                	<button type="submit" name="button" value="changePsw" class="small-btn">Cambia</button>
            	</div>

            	<div class="square-box profile-box col-2 bg-primary">
               		<h3>Finito?</h3>
                	<button type="submit" name="button" value="logout" class="small-btn bg-light">Esci</button>
            	</div>
			</form>
            <div class="col-12"> <!-- tabella tickets -->
                <h3 class="title-box">I miei ordini</h3>
				<form method="POST" name="ServletOrdini" action="ordini">
                <table class="col-12 table-tickets">
                    <tbody>
                        <tr>
                            <th class="col-1 ta-center">ID</th>
                            <th class="col-3 ta-center">Stato</th>
                            <th class="col-3 ta-center">Totale</th>
                            <th class="col-1 ta-center">Azioni</th>
                        </tr>
                        <% 
                         if(obarr != null)
    	  				 {
   		  					int i = 0;
   		  					while(i<obarr.size()){
   		  				%>
                        <tr>
                      
                            <td class="ta-center"><%=obarr.get(i).getOrdineID()%></td>
                            <%
                            	if(obarr.get(i).getStatus().equals("In consegna"))
                            	{
                            %>
                            	<td class="ta-center"><span class="label-warning">In transito</span></td>
                            <%
                            	}
                            	else
                            	{
                            %>
                            	<td class="ta-center"><span class="label-success">Consegnato</span></td>
                            <%
                            	}
                           	%>
                            <td class="ta-center">EUR <%=obarr.get(i).getTotale()%></td>
                            <td class="ta-center"><a href="#"><button type="submit" name="button" value="<%=obarr.get(i).getOrdineID()%>" style="background-color:transparent;border-color:transparent; width:100%; height:100%;">Vedi ordine</button></a></td>
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


            <div class="col-12"> <!-- tabella tickets -->
                <h3 class="title-box">Le mie richieste</h3>
				<form method="POST" name="ServletTicket" action="assistenza">
                <table class="col-12 table-tickets">
                    <tbody>
                        <tr>
                            <th class="col-1 ta-center">ID</th>
                            <th class="col-5 ta-left">Oggetto</th>
                            <th class="col-3 ta-center">Stato</th>
                            <th class="col-1 ta-center">Azioni</th>
                        </tr>
                         <% 
                         if(tbarr != null)
    	  				 {
   		  					int i = 0;
   		  					while(i<tbarr.size()){
   		  				 %>
                        <tr>
                            <td class="ta-center"><%=tbarr.get(i).getRichiestaID() %></td>
                            <td class="ta-left"><%=tbarr.get(i).getOggetto() %></td>
                            <%
                            	if(tbarr.get(i).getFlag().equals("false"))
                        		{
                            %>
                            <td class="ta-center"><span class="label-warning">In attesa</span></td>
                            <%
                        		}
                            	else
                            	{
                            %>
                            <td class="ta-center"><span class="label-success">Risolto</span></td>
                            <%
                            	}
                            %>
                            <td class="ta-center"><a href="#"><button type="submit" name="button" value="<%=tbarr.get(i).getRichiestaID()%>" style="background-color:transparent;border-color:transparent; width:100%; height:100%;">Vedi ticket</button></a></td>
                        </tr>
                         <%
  								i++;
  							}
  						}
		  				%> 
                    </tbody>
                </table>
                </form>
                <form method="POST" name="ServletProfile" action="profilo">
                	<button type="submit" name="button" value="assistenza" class="primary-btn f-right">Richiedi assistenza</button>
                </form> 
            </div>

        </div>
        <%
			if(request.getAttribute("successEmail")==null) { }
			else {
		%>
					<script>alert("Email modificata con successo!");</script>
		<%
			  	 }
    	%>
    	<%
			if(request.getAttribute("successPass")==null) { }
			else {
		%>
					<script>alert("Password modificata con successo!!");</script>
		<%
			  	 }
    	%>
       <%@include file="footer.jsp" %>
    </body>
</html>