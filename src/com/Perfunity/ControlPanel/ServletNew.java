package com.Perfunity.ControlPanel;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.Perfunity.Model.MyCript;
import com.Perfunity.Model.ProdottoBean;
import com.Perfunity.Model.ProdottoDAO;
import com.Perfunity.Model.UserBean;
import com.Perfunity.Model.UserDAO;





@MultipartConfig(fileSizeThreshold=1024*1024*2, // 2MB
maxFileSize=1024*1024*20,      // 10MB
maxRequestSize=1024*1024*200)		//200MB

/**
 * Servlet implementation class ServletNew
 */
@WebServlet(name="ServletNew", urlPatterns= {"/nuovo-utente","/nuovo-prodotto","/modifica-utente"})
public class ServletNew extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR="C:\\Users\\Luca\\eclipse-workspace\\Perfunity.zip_expanded\\Perfunity\\WebContent\\assets\\img";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNew() {
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
		// TODO Auto-generated method stub
		//doGet(request, response);
		HttpSession session = request.getSession();
		Boolean adminRoles = (Boolean) session.getAttribute("adminRoles");
		if (adminRoles!=null && adminRoles == true)
		{
			ProdottoBean pb = new ProdottoBean();
			ProdottoDAO pdao = new ProdottoDAO();
			UserBean ub = new UserBean();
			UserDAO udao = new UserDAO();
			String action = request.getParameter("action");
			switch(action)
			{
				case "nuovoU":
					request.setAttribute("ub", ub);
					RequestDispatcher requestDispatcher1 = request.getRequestDispatcher("panel/single_client.jsp");
					requestDispatcher1.forward(request, response);
				break;
				
				case "nuovoP":
					request.setAttribute("pb",pb);
					RequestDispatcher requestDispatcher = request.getRequestDispatcher("panel/single_product.jsp");
					requestDispatcher.forward(request, response);
				break;
				
				case "insertP":
					String nome = request.getParameter("nome");
					String categoria = request.getParameter("categoria");
					String prezzo_req = request.getParameter("prezzo");
					float prezzo = Float.parseFloat(prezzo_req);
					String descrizione = request.getParameter("descrizione");
					Random rand = new Random();
					int id_int = rand.nextInt(1000)+1;
					String prodottoID = String.valueOf(id_int);
					
					File uploads = new File(SAVE_DIR);
			        List<Part> fileParts = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
			        
			        if(!fileParts.get(0).getSubmittedFileName().isEmpty()) /* CONTROLLA SE E' STATO EFFETTUATO UN UPLOAD */
					{
			        	for (Part filePart : fileParts)
			        	{
			        		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			        		
			            	InputStream fileContent = filePart.getInputStream();

			            	File file = new File(uploads, fileName);
			            	
			            	Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			            
			            	pb.setLinkImmagine(file.getName()); /* SETTA IMMAGINE DA UPLOAD */
			        	}
			        }
			        pb.setNome(nome);
			        pb.setStandard(categoria);
			        pb.setProdottoID(prodottoID);
			        pb.setPrezzo(prezzo);
			        pb.setDescrizione(descrizione);
			        try
			        {
			        	pdao.insertProdotto(pb);
			        	RequestDispatcher requestDispatcher11 = request.getRequestDispatcher("panel/products.jsp");
						requestDispatcher11.forward(request, response);
			        }
			        catch(Exception e)
			        {
			        	e.printStackTrace();
			        }
				break;
				
				case "insertU":
					String nomeUser = request.getParameter("nome");
					String cognomeUser = request.getParameter("cognome");
					String emailUser = request.getParameter("email");
					String numTelUser = request.getParameter("cellulare");
					String passwordUser = request.getParameter("password");
					String genderUser = request.getParameter("gender");
					Date d = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
					String rData = sdf.format(d);
					
					ub.setNome(nomeUser);
					ub.setCognome(cognomeUser);
					ub.setEmail(emailUser);
					ub.setNumTel(numTelUser);
					String crippsw = MyCript.encrypt(passwordUser);
					ub.setPassword(crippsw);
					ub.setGender(genderUser);
					ub.setRegistrationDate(rData);
					try
					{
						udao.insertAccount(ub);
						RequestDispatcher requestDispatcher11 = request.getRequestDispatcher("panel/clients.jsp");
						requestDispatcher11.forward(request, response);
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				break;
				
				case "modifyU":
					String nomeUser1 = request.getParameter("nome");
					String cognomeUser1 = request.getParameter("cognome");
					String emailUser1 = request.getParameter("email");
					String numTelUser1 = request.getParameter("cellulare");
					String passwordUser1 = request.getParameter("password");
					String genderUser1 = request.getParameter("gender");
					Date d1 = new Date();
					SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy/MM/dd");
					String rData1 = sdf1.format(d1);
					
					ub.setNome(nomeUser1);
					ub.setCognome(cognomeUser1);
					ub.setEmail(emailUser1);
					ub.setNumTel(numTelUser1);
					String crippsw1 = MyCript.encrypt(passwordUser1);
					ub.setPassword(crippsw1);
					ub.setGender(genderUser1);
					ub.setRegistrationDate(rData1);
					try
					{
						udao.modifyAccount(ub);
						RequestDispatcher requestDispatcher111 = request.getRequestDispatcher("panel/clients.jsp");
						requestDispatcher111.forward(request, response);
						
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				break;
				
				case "modifyP":
					String nome1 = request.getParameter("nome");
					String categoria1 = request.getParameter("categoria");
					String prezzo_req1 = request.getParameter("prezzo");
					float prezzo1 = Float.parseFloat(prezzo_req1);
					String descrizione1 = request.getParameter("descrizione");
					Random rand1 = new Random();
					int id_int1 = rand1.nextInt(1000)+1;
					String prodottoID1 = String.valueOf(id_int1);
					
					File uploads1 = new File(SAVE_DIR);
			        List<Part> fileParts1 = request.getParts().stream().filter(part -> "file".equals(part.getName())).collect(Collectors.toList()); // Retrieves <input type="file" name="file" multiple="true">
			        
			        if(!fileParts1.get(0).getSubmittedFileName().isEmpty()) /* CONTROLLA SE E' STATO EFFETTUATO UN UPLOAD */
					{
			        	for (Part filePart : fileParts1)
			        	{
			        		String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
			        		
			            	InputStream fileContent = filePart.getInputStream();

			            	File file = new File(uploads1, fileName);
			            	
			            	Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
			            
			            	pb.setLinkImmagine(file.getName()); /* SETTA IMMAGINE DA UPLOAD */
			        	}
			        }
			        pb.setNome(nome1);
			        pb.setStandard(categoria1);
			        pb.setProdottoID(prodottoID1);
			        pb.setPrezzo(prezzo1);
			        pb.setDescrizione(descrizione1);
			        try
			        {
			        	pdao.modifyProdotto(pb);
			        	RequestDispatcher requestDispatcher11 = request.getRequestDispatcher("panel/products.jsp");
						requestDispatcher11.forward(request, response);
			        }
			        catch(Exception e)
			        {
			        	e.printStackTrace();
			        }
				break;
			}
		}
		else
		{
			String redirectedPage;
			request.getSession().setAttribute("adminRoles", new Boolean(false));
			redirectedPage = "/login.jsp";
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}
	}

}
