<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
       <%@ include file="head.jsp" %>
       <script src="assets/js/Script.js"></script>
    </head>

    <body>
    	<%@ include file="navbar.jsp" %>
        <div id="bodycover" class="col-12"><h2>Aggiorna password</h2></div>
        <%
            	HttpSession ssn = request.getSession();
				UserBean ub = (UserBean) ssn.getAttribute("userbean");
				if(ub==null)
				{
					Cookie[] arr = request.getCookies();
					Cookie ck = null;
					for(Cookie c: arr)
					{
						if(c.getName().equals("userCookie")) ck = c;
					}
					String email = ck.getValue();
					try
					{
						UserDAO udao = new UserDAO();
						ub = udao.getUtente(email);
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					
				}
				
				if(ub!=null)
				{
            %>

        <div id="container">
        	<form method="POST" name="ServletProfile" action="profilo">
            <button type="submit" name="button" value="profile" class="full-btn"><i class="fas fa-long-arrow-alt-left"></i> Torna al profilo</button>
            </form>
            <form class="control-form" name="" method="POST" action="ServletUpdate">
                <table>
                    <tbody>
                        <tr>
                            <td class="col-4">Inserisci l'attuale password</td>
                            <td class="col-4"><input type="password" name="password" id="password" placeholder="Attuale password"/></td>
                        </tr>
                        <tr>
                            <td class="col-4">Inserisci una nuova password</td>
                            <td class="col-4"><input type="password" name="newPassword" id="new-password" placeholder="Nuova password"/></td>
                        </tr>
                        <tr>
                            <td class="col-4">Ripeti la nuova password</td>
                            <td class="col-4"><input type="password" name="repeatPassword" id="repeat-password" placeholder="Ripeti password"/></td>
                        </tr>
                    </tbody>
                </table>
                <button type="submit" name="action" value="updatePassword" class="full-btn bg-primary" onClick="return validateModifichePassword();">Aggiorna password <i class="fas fa-long-arrow-alt-right"></i></button>
            </form>
            <%
				}
				else
				{
					response.sendRedirect("login.jsp");
				}
            %>
            
        </div>
        <br><br>
        <p id="erroreModifiche"></p> 
        <%
			if(request.getAttribute("errorPass")==null) { }
			else {
		%>
					<script>alert("Errore nel cambio della password!");</script>
		<%
			  	 }
    	%>
        <%@include file="footer.jsp" %>
    </body>
</html>