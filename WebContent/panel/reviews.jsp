<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<html>
    <head>
        <%@include file="headPanel.jsp" %>
    </head>
    <body>
        <%@include file="navbarPanel.jsp" %>
        <%
        	Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
			if ((adminRoles == null) || (!adminRoles.booleanValue()))
			{	
				response.sendRedirect("./login.jsp");
				return;
			}
			
			RecensioneDAO rdao = new RecensioneDAO();
			ArrayList<RecensioneBean> rbarrT = new ArrayList<RecensioneBean>();
			ArrayList<RecensioneBean> rbarrF = new ArrayList<RecensioneBean>();
			try
			{
				rbarrT = rdao.getRecensioniTrue();
				rbarrF = rdao.getRecensioniFalse();
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
        %>

        <div id="container">
            <h1 class="title"><i class="fas fa-star"></i> Lista recensioni</h1>
            <hr><br><br>
            <h2 class="sub-title yellow">In sospeso</h2>
            <div >
            <form method="POST" action="updated" name="ServletRecensioniPanel">
            <table style="margin-top: 20px;">
                <tbody>
                    <tr>
                        <th class="col-1">ID</th>
                        <th class="col-4 ta-left">Descrizione</th>
                        <th class="col-3">Cliente</th>
                        <th class="col-2">ID Prodotto</th>
                        <th class="col-1">Stelle</th>
                        <th class="col-1">Azioni</th>
                    </tr>
                    <%
                    	if(rbarrF!=null)
                    	{
                    		int i = 0;
                    		while(i<rbarrF.size())
                    		{
                    %>
                    <tr>
                        <td><%=rbarrF.get(i).getRecensioneID()%></td>
                        <td class="ta-left"><%=rbarrF.get(i).getTesto() %></td>
                        <td><%=rbarrF.get(i).getEmailUser() %></td>
                        <td><%=rbarrF.get(i).getProdottoID() %></td>
                        <td><%=rbarrF.get(i).getValutazione()%>/5</td>
                        <td>
                        	<input type="hidden" name="id" value="<%=rbarrF.get(i).getRecensioneID()%>">
                            <a href="#"><span class="action-btn label label-success"><button type="submit" name="action" value="update" style="background-color:transparent;border-color:transparent;" class="fas fa-check"></button></span></a>
                            <a href="#"><span class="action-btn label label-alert"><button type="submit" name="action" value="delete" style="background-color:transparent;border-color:transparent;" class="fas fa-trash-alt"></button></span></a>
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
            <h2 class="sub-title green">Accettati</h2>

            <table style="margin-top: 20px;">
                <tbody>
                    <tr>
                        <th class="col-1">ID</th>
                        <th class="col-5 ta-left">Descrizione</th>
                        <th class="col-3">Cliente</th>
                        <th class="col-2">ID Prodotto</th>
                        <th class="col-1">Stelle</th>
                    </tr>
                    <%
                    	if(rbarrT!=null)
                    	{
                    		int j = 0;
                    		while(j<rbarrT.size())
                    		{
                    %>
                    <tr>
                        <td><%=rbarrT.get(j).getRecensioneID() %></td>
                        <td class="ta-left"><%=rbarrT.get(j).getTesto() %></td>
                        <td><%=rbarrT.get(j).getEmailUser() %></td>
                        <td><%=rbarrT.get(j).getProdottoID() %></td>
                        <td><%=rbarrT.get(j).getValutazione() %>/5</td>
                    </tr>
                    <%
                    			j++;
                    		}
                    	}
                    %>
                </tbody>
            </table>
        </div>
        <%@include file="footerPanel.jsp" %>
    </body>
</html>