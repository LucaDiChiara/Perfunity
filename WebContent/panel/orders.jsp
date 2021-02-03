<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<html>
    <head>
        <%@include file="headPanel.jsp" %>
    </head>
    <body>

		<%@include file="navbarPanel.jsp" %>
		<%
			OrdiniDAO odao = new OrdiniDAO();
			ArrayList<OrdiniBean> obarr = new ArrayList<OrdiniBean>();
			UserBean ub = new UserBean();
			UserDAO udao = new UserDAO();
			try
			{
				obarr = odao.getOrdiniTotali();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		%>

        <div id="container">
            <h1 class="title"><i class="fas fa-shopping-cart"></i> Lista ordini</h1>
            <hr><br><br>
            <form method="GET" action="ordine" name="ServletOrdiniPanel">
            <table style="margin-top: 20px;">
                <tbody>
                    <tr>
                        <th class="col-1">ID</th>
                        <th class="col-4">Cliente</th>
                        <th class="col-2">Data</th>
                        <th class="col-2">Stato</th>
                        <th class="col-1Completato">Totale</th>
                        <th class="col-2">Azioni</th>
                    </tr>
                    <%
                    if(obarr!=null)
                    {
                    	int i = 0;
                    	while(i<obarr.size())
                    	{
                    		try
                    		{
                    			ub = udao.getUtenteTel(obarr.get(i).getNumTel());
                    		}
                    		catch(Exception e)
                    		{
                    			e.printStackTrace();
                    		}
                    		
                    		if(ub!=null)
                    		{
                    %>
                    <tr>
                        <td><%=obarr.get(i).getOrdineID() %></td>
                        <td><a href=""><%=ub.getEmail() %></a></td>
                        <td><%=obarr.get(i).getData() %></td>
                        <%
                        	if(obarr.get(i).getStatus().equals("In consegna"))
                        	{
                        %>
                        	<td><span class="label label-warning">In attesa</span></td>
                        <%
                        	}
                        	else
                        	{
                        %>
                        	<td><span class="label label-success">Completato</span></td>
                        <%
                        	}
                        %>
                        <td>EUR <%=obarr.get(i).getTotale() %></td>
                        <td>
                            <a href="#"><span class="action-btn label label-success"><button type="submit" name="order" value="<%=obarr.get(i).getOrdineID() %>" style="background-color:transparent;border-color:transparent;" class="fas fa-pen"></button></span></a>
                            <a href="#"><span class="action-btn label label-alert"><button type="submit" name="orderDelete" value="<%=obarr.get(i).getOrdineID() %>" style="background-color:transparent;border-color:transparent;" class="fas fa-trash-alt"></button></span></a>
                        </td>
                    </tr>
                    <%
                    		}
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