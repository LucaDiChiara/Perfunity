<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
    	<%@include file="head.jsp" %>
    	<script src="assets/js/Script.js"></script>
    </head>
    <%
		if(request.getAttribute("error")==null) { }
		else {
		%>
		<script>alert("Non hai effettuato l'accesso. Verrai reindirizzato alla pagina di login.");</script>
		<%
		  	 }
    %>

    <body class="auth-container">
        <div class="auth-box">
            <a href="index.jsp"><button class="full-btn bg-light"><i class="fas fa-long-arrow-alt-left"></i> Torna alla home</button></a>
            <h3 class="title-box">Accedi</h3>
            <form name="ServletLogin" method="POST" action="login">
                <label for="email-input">Email:</label>
                <input type="email" name="email" id="mail" placeholder="Email" style="width:100%; height:30px;" /><br>
                <label for="email-input">Password:</label>                
                <input type="password" name="password" id="id-password" placeholder="Password" style="width:100%; height:30px;" />
                <label for="remember">
                    <input type="checkbox" name="remember" value="remember"> Ricordami
                </label><br><br>
                <button class="full-btn bg-primary" onClick="return validateLogin();">Accedi</button>
            </form>
            <br><br>
            <p>Non hai un account? <a href="signup.jsp">Registrati</a></p>
        </div>
    	 <%
				  if(request.getSession().getAttribute("errorLogin")==null) { }
				  else {
				  %>
				  	<br>
				  	<p style="color:red" align="center"> E-mail o password errate. Riprovare.</p>
				  <%
				  	   }
		%>
    </body>
</html>