<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="head.jsp" %>
    </head>

    <body>
    	<%@ include file="navbar.jsp" %>
        <div id="bodycover" class="col-12"><h2>Il mio ticket</h2></div>
		<%
			HttpSession ssn = request.getSession();
			UserBean ub = (UserBean) ssn.getAttribute("userbean");
		%>
        <div id="container">
        	<form method="POST" name="ServletProfile" action="profilo">
            <button type="submit" name="button" value="profile" class="full-btn"><i class="fas fa-long-arrow-alt-left"></i> Torna al profilo</button><br>
            </form>
            <form class="control-form ticket-form" action="assistenza" method="POST" name="ServletTicket">
                <h3 class="title-box">Crea un nuovo ticket</h3>

                <label for="objTicket">Oggetto: </label> <input type="text" name="objTicket" placeholder="Oggetto del messaggio"/>
                <br>
                <textarea class="col-12" name="testoTicket" id=""  placeholder="Descrivi dettagliamente il tuo problema."></textarea>
                <input type="hidden" name="emailUser" value="<%=ub.getEmail()%>">
                <button type="submit" name="action" value="newTicket" class="full-btn bg-primary">Invia <i class="fas fa-long-arrow-alt-right"></i></button><br>
            </form>

        </div>
       <%@include file="footer.jsp" %>
    </body>
</html>