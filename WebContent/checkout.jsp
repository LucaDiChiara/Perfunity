<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
       <%@ include file="head.jsp" %>
    </head>
	<%
	HttpSession ssn = request.getSession();
	UserBean ub = (UserBean) ssn.getAttribute("userbean");
	float tot = (float) request.getAttribute("tot");
	
	%>
    <body>
    	<%@ include file="navbar.jsp" %>
        <div id="bodycover" class="col-12"><h2>Cassa</h2></div>
		<%
			if(ub!=null)
			{
		%>
        <div id="container">
            <form method="POST" action="carrello" name="ServletCarrello"><button type="submit" name="action" value="cart" class="full-btn"><i class="fas fa-long-arrow-alt-left"></i> Torna al carrello</button></form>
                <form class="large-form" name="ServletCheckout" method="POST" action="profile">
                    <h3 class="title-box">Informazioni spedizione</h3>
                    <table style="margin:auto;">
                        <tbody>
                            <tr>
                                <td><input type="text" name="" placeholder="Nome" value="<%=ub.getNome() %>" /></td>
                                <td><input type="text" name="" placeholder="Cognome" value="<%=ub.getCognome() %>" /></td>
                            </tr>
                            <tr>
                                <td colspan="2"><input type="text" name="via" placeholder="Indirizzo completo (via, civico)" /></td>
                            </tr>
                            <tr>
                                <td><input type="text" name="" placeholder="Paese" value="Italia" disabled/></td>
                                <td><input type="text" name="citta" placeholder="Città " /></td>
                            </tr>
                            <tr>
                                <td><input type="numeric" name="CAP" placeholder="CAP" /></td>
                                <td><input type="numeric" name="" placeholder="Telefono" value="<%=ub.getNumTel() %>" /></td>
                            </tr>
                            <tr>
                                <td colspan="2"><span style="display: flex; justify-content: flex-end;">Totale: EUR <%= tot %></span></td>
                            </tr>
                        </tbody>
                    </table>
                    <button type="submit" class="full-btn bg-primary">Concludi ordine</button>

                </form>
        </div>
        <%
			}
        %>
        <%@include file="footer.jsp" %>
    </body>
</html>