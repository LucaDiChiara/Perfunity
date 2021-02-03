<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<html>
    <head>
        <%@include file="headPanel.jsp" %>
    </head>
    <body>
        <%@include file="navbarPanel.jsp" %>
        <%
        	OrdiniBean ob = (OrdiniBean) request.getAttribute("ob");
        	IndirizzoBean ib = new IndirizzoBean();
        	IndirizzoDAO idao = new IndirizzoDAO();
        	OrdiniDAO odao = new OrdiniDAO();
        	ProdottoBean pb = new ProdottoBean();
        	ProdottoDAO pdao = new ProdottoDAO();
        	ArrayList<ProdottoBean> pbarr = new ArrayList<ProdottoBean>();
        	ArrayList<OrdiniBean> obarr = new ArrayList<OrdiniBean>();
        	//String email = (String) request.getAttribute("email");
        	UserBean ub = new UserBean();
        	UserDAO udao = new UserDAO();
        	try
        	{
        		ub = udao.getUtenteTel(ob.getNumTel());
        		ib = idao.getIndirizzo(ob.getOrdineID());
        		obarr = odao.getQtyTotale(ob.getOrdineID());
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
        	
        	if(ob!=null && ub!=null)
        	{
        %>

        <div id="container">
            <h1 class="title"><i class="fas fa-cubes"></i> Dettagli ordine</h1>
            <hr>
            <br><br>
            <h1 class="sub-title">Ordine #<%=ob.getOrdineID() %></h1>

            <div class="order-info col-12 flex">
            	<form class="col-8" method="POST" action="ordine" name="ServletOrdiniPanel">
                <table>
                    <tbody>
                        <tr>
                            <td style="font-weight: bold;">ID ordine</td>
                            <td>#<%=ob.getOrdineID() %></td>
                        </tr>
                        <tr>
                            <td style="font-weight: bold;">Data</td>
                            <td><%=ob.getData() %></td>
                        </tr>
                        <tr>
                            <td style="font-weight: bold;">Cliente</td>
                            <td><%=ub.getNome()%> <%=ub.getCognome() %></td>
                        </tr>
                        <tr>
                            <td style="font-weight: bold;">Stato</td>
                            <td>
                               <select name="status">
                                  <option value="completato">Completato</option>
                                  <option value="In consegna">In attesa</option>
                               </select>
                               <button type="submit" class="btn btn-light small-btn" style="vertical-align: middle;"><i class="fas fa-angle-double-right"></i></button>
                            </td>
                        </tr>
                    </tbody>
                </table>
                <input type="hidden" name="ordineID" value="<%=ob.getOrdineID() %>">
                </form>
                <div style="border-left:1px solid #ccc;height:auto"></div>
                <table>
                    <tbody>
                        <tr>
                            <td colspan="2" style="font-weight: bold; border:0 !important;">Indirizzo di spedizione</td>
                        </tr>
                        <tr>
                            <td><%=ib.getVia() %></td>
                            <td><%=ib.getCAP()%> <%=ib.getCitta()%> </td>
                        </tr>
                        <tr>
                            <td>Telefono</td>
                            <td><%=ob.getNumTel() %></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <%
        	}
            %>
            <br><br>
            <table>
                <tbody>
                    <tr>
                        <th></th>
                        <th class="col-6 ta-left">Titolo prodotto</th>
                        <th class="col-2">Prezzo</th>
                        <th class="col-1">Qtà </th>
                        <th class="col-2">Totale</th>
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
                        <td><img src="./assets/img/<%=pbarr.get(j).getLinkImmagine() %>" width="64" height="64" style="margin:auto;"/></td>
                        <td class="ta-left"><%=pbarr.get(j).getNome() %></td>
                        <td>EUR <%=pbarr.get(j).getPrezzo() %></td>
                        <td><%=obarr.get(j).getQty() %></td>
                        <td>EUR <%=obarr.get(j).getTotale_prodotto() %></td>
                    </tr>
                    <%
                    			j++;
                			}
                		}
                    %>
                </tbody>
            </table>
            <br>
            <h1 class="sub-title" style="display: flex; justify-content: flex-end;">Totale EUR <%=ob.getTotale() %></h1>
        </div>
        <%
                	}
        %>
       <%@include file="footerPanel.jsp" %>
    </body>
</html>