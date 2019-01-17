package com.ipartek.appMultas.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ipartek.appMultas.modelo.dao.AgenteDAO;
import com.ipartek.appMultas.modelo.pojo.Agente;
import com.ipartek.appMultas.modelo.pojo.Mensaje;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AgenteDAO dao;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao = AgenteDAO.getInstance();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String placa = request.getParameter("placa");
		String password = request.getParameter("password");
		Agente agente = dao.login(placa, password);
		
		if (placa.equals("") || password.equals("")) {
			request.setAttribute("placa", placa);
			request.setAttribute("password", password);
			request.setAttribute("mensaje", new Mensaje(Mensaje.TIPO_DANGER, "No puede haber campos vacíos"));
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
		if (agente == null ) {
			request.setAttribute("mensaje", new Mensaje(Mensaje.TIPO_DANGER, "Nº Placa o Contraseña no válidos"));
			request.setAttribute("placa", placa);
			request.setAttribute("password", password);
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}else {
			HttpSession session = request.getSession();
			session.setAttribute("agenteLogueado", agente);
			session.setAttribute("mensaje", null);
			// Redireccionar a index.jsp
			//request.getRequestDispatcher("privado/index.jsp").forward(request, response);
			response.sendRedirect("privado/index.jsp");
		}
		}
	}

}
