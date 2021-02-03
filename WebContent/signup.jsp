<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
		<%@include file="head.jsp" %>
		<script src="assets/js/Script.js"></script>
    </head>
	<p id="errore"></p>
    <body class="auth-container">
        <div class="auth-box">
            <a href="index.jsp"><button class="full-btn bg-light"><i class="fas fa-long-arrow-alt-left"></i> Torna alla home</button></a>
            <h3 class="title-box">Registrati</h3>
            <form name="ServletRegistrazione" method="POST" action="signup">
            	<label for="nome-input">Nome:</label>
                <input type="text" name="nome" id="nome" placeholder="Nome" style="width:100%; height:30px;" /><br>
                <label for="cognome-input">Cognome:</label>
                <input type="text" name="cognome" id="cognome" placeholder="Cognome" style="width:100%; height:30px;" /><br>
                <label for="email-input">Email:</label>
                <input type="email" name="email" id="email" placeholder="Email" style="width:100%; height:30px;" /><br>
                <label for="password-input">Password:</label>                
                <input type="password" name="password" id="password" placeholder="Password" style="width:100%; height:30px;" /><br>
                <label for="password-input">Ripeti password:</label>                
                <input type="password" name="repeat-password" id="repeat-password" placeholder="Ripeti password" style="width:100%; height:30px;" /><br><br>
                <label for="gender">Sesso</label>
                <select name="gender">
                    <option value="Uomo">Uomo</option>
                    <option value="Donna">Donna</option>
                </select><br><br>
                <label for="telefono">Telefono:</label>
                <input type="text" name="cellulare" id="cellulare" placeholder="Telefono" style="width:100%; height:30px;" /><br>
                <br><br>
                <button class="full-btn bg-primary" onClick="if(validateRegistrazione()==false){return false;}">Registrati</button>
            </form>
            <br><br>
            <p>Hai già un'account? <a href="login.jsp">Accedi</a></p>
        </div>
    
    </body>
</html>