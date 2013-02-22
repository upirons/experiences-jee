package es.experiences.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import es.experiences.model.Box;

/**
 * Servlet implementation class BoxesServlet
 */
@WebServlet("/boxes")
public class BoxesServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final List<Box> BOXES = new ArrayList<Box>();
	
	static {
		BOXES.add(new Box("1","brasil-con-encanto","Brasil con encanto", false));
		BOXES.add(new Box("2","usa-ruta-66","Conoceras Radiador Spring", false));
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * GET /boxes 				: listado de todas las cajas
	 * GET /boxes?id=<box_id>	: mostrar la caja con identificador <box_id>
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		if(id == null) {
			//list
			request.setAttribute("boxes", BoxesServlet.BOXES);
			request.getRequestDispatcher("/boxes/list.jsp").forward(request, response);
		} else {
			//show
			Box box = null;
			int i = 0;
			while(box == null && i < BOXES.size()) {
				Box current = BOXES.get(i);
				if(current.getId().equals(id)) {
					box = current;
				} else {
					i++;
				}
			}
			if(box != null) {
				request.setAttribute("box", box);
				request.getRequestDispatcher("/boxes/show.jsp").forward(request, response);
			} else {
				request.setAttribute("error", "Caja " + id + " no encontrada");
				request.setAttribute("boxes", BoxesServlet.BOXES);
				request.getRequestDispatcher("/boxes/list.jsp").forward(request, response);
			}
			
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 * 
	 * POST /boxes					: añadir una nueva caja
	 * POST /boxes?_action=update	: modificar una caja
	 * POST /boxes?_action=delete	: borrar una caja
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("_action");
		if(action == null) {
			//create
			request.getRequestDispatcher("/boxes/show.jsp").forward(request, response);
		} else if ("update".equals(action)) {
			//update
			request.getRequestDispatcher("/boxes/show.jsp").forward(request, response);
		} else if ("delete".equals(action)) {
			//delete
			request.getRequestDispatcher("/boxes/list.jsp").forward(request, response);
		}
	}

}
