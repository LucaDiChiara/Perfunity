<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<html>
    <head>
       <%@include file="headPanel.jsp" %>
    </head>
    <body>
        <%@include file="navbarPanel.jsp" %>
        <%
        	UserBean ub = (UserBean) request.getAttribute("ub");
        	ArrayList<RecensioneBean> rbarr = new ArrayList<RecensioneBean>();
        	ArrayList<OrdiniBean> obarr = new ArrayList<OrdiniBean>(); 
        	RecensioneDAO rdao = new RecensioneDAO();
        	OrdiniDAO odao = new OrdiniDAO();
        	try
        	{
        		rbarr = rdao.getRecensioniUtente(ub.getEmail());
        		obarr = odao.getOrdini(ub.getNumTel());
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	
        	if(ub!=null)
        	{
        %>

        <div id="container" class="flex">
            <div class="sub-container col-8">
                <h1 class="title"><i class="fas fa-users"></i> Cliente</h1>
                <hr>
                <br><br>
                <%
                	if(ub.getEmail()==null)
                	{
                %>
                <form method="POST" name="ServletNew" action="nuovo-utente">
                    <label for="user-firstname">Nome: </label>
                    <input type="text" class="col-12" name="nome" placeholder="Nome" value="" /><br><br>
                    
                    <label for="user-lastname">Cognome: </label>
                    <input type="text" class="col-12" name="cognome" placeholder="Cognome" value="" /><br><br>
                    
                    <label for="user-password">Sesso: </label>
                    <select name="gender">
                    	<option>Uomo</option>
                    	<option>Donna</option>
                    	<option>Preferisco non specificarlo</option>
                	</select><br><br>
                    
                    <label for="user-password">Email: </label>
                    <input type="email" class="col-12" name="email" placeholder="Email" value="" /><br><br>
                    
                    <label for="user-telefono">Telefono: </label>                    
                    <input type="tel" class="col-12" name="cellulare" placeholder="Telefono" value="" /><br><br>
                    
                    <label for="user-password">Password: </label>                    
                    <input type="password" class="col-12" name="password" placeholder="Password" value="" /><br><br>

                    <button type="submit" name="action" value="insertU" onClick="if(validateRegistrazione()==false){return false;}" class="btn btn-primary col-12">Invia</button>
                </form>
                <%
                	}
                	else
                	{
                %>
                <form method="POST" name="ServletNew" action="nuovo-utente">
                    <label for="user-firstname">Nome: </label>
                    <input type="text" class="col-12" name="nome" placeholder="Nome" value="<%=ub.getNome() %>" /><br><br>
                    
                    <label for="user-lastname">Cognome: </label>
                    <input type="text" class="col-12" name="cognome" placeholder="Cognome" value="<%=ub.getCognome() %>" /><br><br>
                    
                    <label for="user-password">Sesso: </label>
                    <select name="gender">
                    	<option>Uomo</option>
                    	<option>Donna</option>
                    	<option>Preferisco non specificarlo</option>
                	</select> <br><br>
                    
                    <label for="user-password">Email: </label>
                    <input type="email" class="col-12" name="email" placeholder="Email" value="<%=ub.getEmail() %>" /><br><br>
                    
                    <label for="user-telefono">Telefono: </label>                    
                    <input type="tel" class="col-12" name="cellulare" placeholder="Telefono" value="<%=ub.getNumTel() %>" /><br><br>
                    
                    <label for="user-password">Password: </label>                    
                    <input type="password" class="col-12" name="password" placeholder="Password" value="<%=ub.getPassword() %>" /><br><br>

                    <button type="submit" name="action" value="modifyU" onClick="if(validateRegistrazione()==false){return false;}" class="btn btn-primary col-12">Invia</button>
                </form>
                <%
                	}
                %>
            </div>
            <%
        	}
            %>
            <div class="sub-container col-4 bg-light" style="box-shadow: 0 4px 8px 0 rgba(197, 197, 197, 0.3);">
                <h1 class="sub-title "><i class="fas fa-star"></i> Recensioni cliente</h1><br><br>
                <table>
                    <tbody>
                    <%
                	if(rbarr!=null)
            		{
            			int i=0;
            			while(i<rbarr.size())
            			{
               		 %>
                        <tr>
                            <td class="col-1">
                                <%=rbarr.get(i).getValutazione() %> <i class="fas fa-star yellow"></i>
                            </td>
                            <td class="col-10">
                                <%=rbarr.get(i).getTesto() %>
                            </td>
                        </tr>
                        <%
                        	i++;
        				}
        			}
                        %>
                    </tbody>
                </table>
                <br><br>
                <h1 class="sub-title "><i class="fas fa-shopping-cart"></i> Ordini cliente</h1><br>
                <form method="GET" action="ordine" name="ServletOrdiniPanel">
                <input type="hidden" name="email" value="<%=ub.getEmail() %>">
                <table>
                    <tbody>
                        <tr>
                            <th>ID</th>
                            <th>Data</th>
                            <th>Totale</th>
                            <th></th>
                        </tr>
                        <%
                        if(obarr!=null)
                		{
                			int i=0;
                			while(i<obarr.size())
                			{
                        %>
                        <tr>
                            <td><%=obarr.get(i).getOrdineID() %></td>
                            <td><%=obarr.get(i).getData() %></td>
                            <td>EUR <%=obarr.get(i).getTotale() %></td>
                            <td class="col-1"><button type="submit" name="order" value="<%=obarr.get(i).getOrdineID() %>" class="btn btn-light small-btn"><i class="fas fa-angle-double-right"></i></button></td>
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
        </div>
        <%@include file="footerPanel.jsp" %>
    </body>
</html>