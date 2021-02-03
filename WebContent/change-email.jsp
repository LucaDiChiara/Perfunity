<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.Perfunity.Model.*" import="java.util.*"%>
<!DOCTYPE html>
<html>
    <head>
    	<%@ include file="head.jsp" %>
    </head>

    <body>
    	<%@ include file="navbar.jsp" %>
        <div id="bodycover" class="col-12"><h2>Aggiorna email</h2></div>

        <div id="container">
        	<form method="POST" name="ServletProfile" action="profilo">
            <button type="submit" name="button" value="profile" class="full-btn"><i class="fas fa-long-arrow-alt-left"></i> Torna al profilo</button>
            </form>
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
            
            <form class="control-form" name="" method="POST" action="ServletUpdate">
                <table>
                    <tbody>
                        <tr>
                            <td class="col-4">Inserisci l'attuale email</td>
                            <td class="col-4"><input type="email" name="email" id="email" value="<%=ub.getEmail()%>" placeholder="Attuale email"/></td>
                        </tr>
                        <tr>
                            <td class="col-4">Inserisci una nuova email</td>
                            <td class="col-4"><input type="email" name="newEmail" id="new-email" placeholder="Nuova email"/></td>
                        </tr>
                        <tr>
                            <td class="col-4">Ripeti la nuova email</td>
                            <td class="col-4"><input type="email" name="repeatEmail" id="repeat-email" placeholder="Ripeti email"/></td>
                        </tr>
                    </tbody>
                </table>
			<button type="submit" name="action" value="updateEmail" class="full-btn bg-primary" onClick="return validateModificheEmail();">Aggiorna email <i class="fas fa-long-arrow-alt-right"></i></button>
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
       <%@include file="footer.jsp" %>
    </body>
</html>