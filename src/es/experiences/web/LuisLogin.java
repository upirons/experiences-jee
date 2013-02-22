package es.experiences.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class LuisLogin
 * Servlet recibe del cliente el nombre y la pw, y si el 1º es de longitud > 4 y la longitud de la pw > 6,
 * manda al cliente "OK", si no es así, mandamos un error (el cliente ha mandado los datos mediante Post, por eso hacemos un doPost) 
 */
@WebServlet("/loginluis")
public class LuisLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
     * Default constructor. 
     */
    public LuisLogin() {
        
    	//super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.getWriter().print("metodo de login no soportado");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	// DEBERIAN DE PEDIRNOS LOGIN POR EL POST, PARA QUE NO SE VEA EL PASS :
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String user = request.getParameter("username");
		String pass = request.getParameter("password");
		
		response.getWriter().println ("Voy a ejecutar mi Servlet, soy Luis");
		
		if (user.length() > 4 && pass.length() > 6) {
			// A falta de otra validacion (en BD por ejemplo), si cumple este if, lo damos por bueno
			response.getWriter().println("OK (Luis)");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);
			request.getRequestDispatcher("ok.jsp").forward(request, response);
			} else {
				response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				request.getRequestDispatcher("login.jsp").forward(request, response);
				
				if (user.length() > 4) {
					response.getWriter().println("Nombre no válido (Luis)");
					} else if (pass.length() > 6) {
						response.getWriter().println("PW no válido (Luis)");
						} else {
							response.getWriter().println("Nombre y PW no válido (Luis)");
							}
			}
	}
		
}

