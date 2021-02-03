<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<html>
    <head>
        <%@include file="headPanel.jsp" %>
    </head>
    <body>
        <%@include file="navbarPanel.jsp" %>
		<%
			UserDAO udao = new UserDAO();
			ArrayList<UserBean> ubarr = new ArrayList<UserBean>();
			try
			{
				ubarr = udao.getUtenti();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		%>
        <div id="container">
            <h1 class="title"><i class="fas fa-users"></i> Lista clienti</h1>
            <hr>
            <form method="POST" action="nuovo-utente" name="ServletNew"><button type="submit" name="action" value="nuovoU"  class="btn btn-azure f-left"><i class="fas fa-plus"></i> Nuovo utente</button></form>
            
            <form method="POST" name="ServletClienti" action="cliente">
            <table style="margin-top:70px;">
                <tbody>
                    <tr>
                        <th class="col-1">Numero</th>
                        <th class="col-3">Nome</th>
                        <th class="col-3">Cognome</th>
                        <th class="col-3">Email</th>
                        <th class="col-2">Azioni</th>
                    </tr>
                    <%
                    	if(ubarr!=null)
                    	{
							int i = 0;
                    		while(i<ubarr.size())
                    		{
                    %>
                    <tr>
                        <td><%=i+1 %></td>
                        <td><%=ubarr.get(i).getNome() %></td>
                        <td><%=ubarr.get(i).getCognome() %></td>
                        <td><%=ubarr.get(i).getEmail() %></td>
                        <td>
                            <a href="#"><span class="action-btn label label-success"><button type="submit" name="submit" value="<%=ubarr.get(i).getEmail() %>" style="background-color:transparent;border-color:transparent;" class="fas fa-pen"></button></span></a>
                            <a href="#"><span class="action-btn label label-alert"><button type="submit" name="delete" onClick="alert('Utente eliminato')" value="<%=ubarr.get(i).getEmail() %>" style="background-color:transparent;border-color:transparent;" class="fas fa-trash-alt"></button></span></a>
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
       <%@include file="footerPanel.jsp" %>
    </body>
</html>