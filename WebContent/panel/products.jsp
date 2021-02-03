<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<html>
    <head>
        <%@include file="headPanel.jsp" %>
    </head>
    <body>
        <%@include file="navbarPanel.jsp" %>
        <%
        	ArrayList<ProdottoBean> pbarr = (ArrayList<ProdottoBean>) request.getAttribute("pbarr");
        %>

        <div id="container">
            <h1 class="title"><i class="fas fa-cubes"></i> Lista prodotti</h1>
            <hr>
            <form method="POST" action="nuovo-prodotto" name="ServletNew"><button type="submit" name="action" value="nuovoP" class="btn btn-azure f-left"><i class="fas fa-plus"></i> Nuovo prodotto</button></form>
            
            <form name="ServletProdottiPanel" method="POST" action="products" style="margin-top: 30px;">
                <label for="filter-category">Filtra per categoria: </label>
                <select name="type">
                    <option>Tutti</option>
                    <option>Uomo</option>
                    <option>Donna</option>
                    <option>Kids</option>
                    <option value="gift">Gift box</option>
                </select>
                <button type="submit" class="btn btn-light small-btn"><i class="fas fa-angle-double-right"></i></button>
            </form>
            <br>
            <form method="GET" name="ServletProdottiPanel" action="products">
            <table>
                <tbody>
                    <tr>
                        <th class="col-1">ID</th>
                        <th class="col-1">Img</th>
                        <th class="col-5 ta-left">Titolo prodotto</th>
                        <th class="col-2">Prezzo</th>
                        <th class="col-2">Azioni</th>
                    </tr>
                    <%
                    if(pbarr!=null)
                    {
                    	int i = 0;
                    	while(i<pbarr.size())
                    	{
                    %>
                    <tr>
                        <td><%=pbarr.get(i).getProdottoID() %></td>
                        <td><img src="./assets/img/<%=pbarr.get(i).getLinkImmagine() %>" width="64" height="64" style="margin:auto;"/></td>
                        <td class="ta-left"><%=pbarr.get(i).getNome() %></td>
                        <td>EUR <%=pbarr.get(i).getPrezzo() %></td>
                        <td>
                            <a href="#"><span class="action-btn label label-success"><button type="submit" name="modify" value="<%=pbarr.get(i).getProdottoID() %>" style="background-color:transparent;border-color:transparent;" class="fas fa-pen"></button></span></a>
                            <a href="#"><span class="action-btn label label-alert"><button type="submit" name="delete" value="<%=pbarr.get(i).getProdottoID() %>" onClick="alert('Prodotto eliminato')" style="background-color:transparent;border-color:transparent;" class="fas fa-trash-alt"></button></span></a>
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