package com.Perfunity.Control;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Perfunity.Model.*;

/**
 * Servlet implementation class ServletRegistrazione
 */
@WebServlet(name="ServletRegistrazione", urlPatterns="/signup")
public class ServletRegistrazione extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletRegistrazione() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserBean ub = new UserBean();
		UserDAO ud = new UserDAO();
		
		
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String email = request.getParameter("email");
		String cellulare = request.getParameter("cellulare");
		String password = request.getParameter("password");
		String repeatPassword = request.getParameter("repeat-password");
		String gender = request.getParameter("gender");
	

		String regexEmail = "^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$";
		if(email.matches(regexEmail)) {
			ub.setEmail(email);
		}
		
		/*String regexCel = "/^((00|\\+)39[\\. ]??)??3\\d{2}[\\. ]??\\d{6,7}$/";
		if(cellulare.matches(regexCel)) {
			
		}*/
		ub.setNumTel(cellulare);
		
		if(password.equals(repeatPassword))
		{
			String regexPassword = "^[a-zA-Z0-9\\_\\*\\-\\+\\!\\?\\,\\:\\;\\.\\xE0\\xE8\\xE9\\xF9\\xF2\\xEC\\x27]{8,20}";
			if(password.matches(regexPassword)) {
			
				String crippsw = MyCript.encrypt(password);
				ub.setPassword(crippsw);
			}
		} else ub.setPassword("error");
		
		String regexNome = "^[a-zA-Z\\'\\s]+$";
		if(nome.matches(regexNome)) {
			ub.setNome(nome);
		}
		
		String regexCognome = "^[a-zA-Z\\s]+$";
		if(cognome.matches(regexCognome)) {
			ub.setCognome(cognome);
		}
		
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		String rData = sdf.format(d);
		ub.setRegistrationDate(rData);
		
		ub.setGender(gender);
	
		if(ud.registraAccount(ub)){	
			request.setAttribute("userbean", ub);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
			requestDispatcher.forward(request, response);				
		}
		else{
			request.setAttribute("controllo", false);
			RequestDispatcher requestDispatcher = request.getRequestDispatcher("signup.jsp");
			requestDispatcher.forward(request, response);
		}
	}

}
