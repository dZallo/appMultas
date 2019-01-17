package com.ipartek.appMultas.controller;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.dao.AgenteDAO;
import com.ipartek.appMultas.modelo.dao.MultaDAO;
import com.ipartek.appMultas.modelo.pojo.Agente;
import com.ipartek.appMultas.modelo.pojo.Mensaje;

/**
 * Servlet implementation class LoginController
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AgenteDAO daoAgente;
	private MultaDAO daoMulta;
	
	private final static Logger LOG = Logger.getLogger(LoginController.class);
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoAgente = AgenteDAO.getInstance();
		daoMulta = MultaDAO.getInstance();
		
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
		Agente agente = daoAgente.login(placa, password);
		
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
			
			agente.setImporteAnual(daoMulta.getObjetivoAnual(agente.getId(), (long)obtenerAño()));
			agente.setImporteMensual(daoMulta.getObjetivoMensual(agente.getId(), (long)obtenerAño(), (long)obtenerMes()));
			HttpSession session = request.getSession();
			session.setAttribute("agenteLogueado", agente);
			session.setAttribute("mensaje", null);
			// Redireccionar a index.jsp
			//request.getRequestDispatcher("privado/index.jsp").forward(request, response);
			response.sendRedirect("privado/index.jsp");
		}
		}
	}
	
	private int obtenerAño() {
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		LOG.debug("Año actual para la búsqueda de estadísticas: " + year);
		return year;
	}
	
	private int obtenerMes() {
		Calendar cal = Calendar.getInstance();
		int month = cal.get(Calendar.MONTH);
		LOG.debug("Mes actual para la búsqueda de estadísticas: " + month + 1);
		return month + 1;
	}

}
