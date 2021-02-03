package com.Perfunity.Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.Perfunity.Model.MyCript;
import com.Perfunity.Model.UserBean;
import com.Perfunity.Model.UserDAO;

/**
 * Servlet implementation class ServletUpdate
 */
@WebServlet("/ServletUpdate")
public class ServletUpdate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUpdate() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession ssn = request.getSession();
		UserBean ub = (UserBean) ssn.getAttribute("userbean");
		UserDAO udao = new UserDAO();
		String thisEmail = ub.getEmail();
		String thisPsw = ub.getPassword();
		String action = request.getParameter("action");
		
		switch(action)
		{
			case "updateEmail":
					String email = request.getParameter("email");
					String newEmail = request.getParameter("newEmail");
					String repeatEmail = request.getParameter("repeatEmail");
					
					if(thisEmail.equals(email))
					{
						if(newEmail.equals(repeatEmail))
						{
							if(udao.updateEmail(newEmail,email))
							{
								request.setAttribute("successEmail", "true");
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
								requestDispatcher.forward(request, response);
							}
						}
					}
					
					
			break;
			
			case "updatePassword":
				    String password_req = request.getParameter("password");
				    String password = MyCript.encrypt(password_req);
				    String newPassword = request.getParameter("newPassword");
				    String repeatPassword = request.getParameter("repeatPassword");
				    
				    if(thisPsw.equals(password))
				    {
				    	if(newPassword.equals(repeatPassword))
				    	{
				    		if(udao.updatePassword(newPassword,thisEmail))
				    		{
				    			request.setAttribute("successPass", "true");
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("profile.jsp");
								requestDispatcher.forward(request, response);
				    		}
				    		else
				    		{
				    			System.out.println("Errore nell'update");
				    			request.setAttribute("errorPass", "true");
								RequestDispatcher requestDispatcher = request.getRequestDispatcher("change-password.jsp");
								requestDispatcher.forward(request, response);
				    		}
				    	}
				    	else
				    	{
				    		System.out.println("Errore nel match tra la nuova password e il repeat");
				    		request.setAttribute("errorPass", "true");
							RequestDispatcher requestDispatcher = request.getRequestDispatcher("change-password.jsp");
							requestDispatcher.forward(request, response);
				    	}
				    }
				    else
				    {
				    	System.out.println("Errore nel match tra la password salvata a database e la password presa da ssn");
				    	request.setAttribute("errorPass", "true");
						RequestDispatcher requestDispatcher = request.getRequestDispatcher("change-password.jsp");
						requestDispatcher.forward(request, response);
				    }
			break;
		}
		
	}

}
