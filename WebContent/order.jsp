<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
       <%@ include file="head.jsp" %>
    </head>

    <body>
    	<%@ include file="navbar.jsp" %>
        <div id="bodycover" class="col-12"><h2>I miei ordini</h2></div>
        <%
        	OrdiniBean ordine = (OrdiniBean) request.getAttribute("ob");
        	OrdiniDAO odao = new OrdiniDAO();
        	ProdottoBean pb = new ProdottoBean();
        	ProdottoDAO pdao = new ProdottoDAO();
        	ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
        	ArrayList<OrdiniBean> obarr = new ArrayList<OrdiniBean>();
        	IndirizzoBean indirizzo = new IndirizzoBean();
        	IndirizzoDAO idao = new IndirizzoDAO();
        	try
        	{
        		indirizzo = idao.getIndirizzo(ordine.getOrdineID());
        		obarr = odao.getQtyTotale(ordine.getOrdineID());
        		int i = 0;
        		while(i<obarr.size())
        		{
        			String prodottoID = obarr.get(i).getFkProdottoID();
        			pb = pdao.getProdotto(prodottoID);
        			pbarr.add(pb);
        			i++;
        		}
        	}
        	catch(Exception e)
        	{
        		e.printStackTrace();
        	}
        	
    		if(ordine!=null)
    		{
        %>

        <div id="container">
        	<form method="POST" name="ServletProfile" action="profilo">
            <button type="submit" name="button" value="profile" class="full-btn"><i class="fas fa-long-arrow-alt-left"></i> Torna al profilo</button>
            </form>

            <div class="col-11 order-details">
                <h3 class="title-box">Dettagli ordine del <%=ordine.getData()%> 
                <% if(ordine.getStatus().equals("In consegna"))
            	{
            	%>
            		<span class="label-warning">In transito</span></h3><br>
            	<%
            	}
            	else
            	{
            	%>
            		<span class="label-success">Consegnato</span></h3><br>
            	<%
            	}
           		%>
                
                <table class="col-6 table-order">
                    <tr>
                        <td class="ta-left">ID ordine</td>
                        <td class="ta-left"># <%=ordine.getOrdineID() %></td>
                    </tr>
                    <tr>
                        <td class="ta-left">Totale</td>
                        <td class="ta-left">EUR <%=ordine.getTotale() %></td>
                    </tr>
                    <tr>
                        <td class="ta-left">Indirizzo spedizione</td>
                        <td class="ta-left"><%=indirizzo.getVia() %></td>
                    </tr>
                    <tr>
                        <td class="ta-left">Telefono</td>
                        <td class="ta-left"><%=ordine.getNumTel() %></td>
                    </tr>
                </table>
                <%
        			}
        		%>
                <table class="col-12 table-order">
                    <tbody>
                        <tr>
                            <th class="col-1 ta-center">ID</th>
                            <th class="col-4 ta-left">Descrizione prodotto</th>
                            <th class="col-3 ta-center">Prezzo</th>
                            <th class="col-1 ta-center">Quantità </th>
                            <th class="col-3 ta-center">Prezzo totale</th>
                        </tr>
                        <%
                        	if(obarr!=null)
                        	{
                        		if(pbarr!=null)
                        		{
                        			int j = 0;
                        			while(j<pbarr.size())
                        			{
                        %>
                        <tr>
                            <td class="ta-center"><%=pbarr.get(j).getProdottoID() %></td>
                            <td class="ta-left"><%=pbarr.get(j).getNome() %></td>
                            <td class="ta-center">EUR <%=pbarr.get(j).getPrezzo() %></td>
                            <td class="ta-center"><%=obarr.get(j).getQty() %></td>
                            <td class="ta-center">EUR <%=obarr.get(j).getTotale_prodotto() %></td>
                        </tr>
                        <%
                        				j++;
                        			}
                        		}
                        	}
                        %>
                    </tbody>
                </table>
            </div>

        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>