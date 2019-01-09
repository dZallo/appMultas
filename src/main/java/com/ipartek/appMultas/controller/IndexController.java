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

/**
 * Servlet implementation class IndexController
 * CONTROLLER PARA CARGAR EL USUARIO Y REDIRECCIONAR A INDEX.JSP
 */
@WebServlet("/index")
public class IndexController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private AgenteDAO dao;
	
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		dao =AgenteDAO.getInstance();
	}
	
	@Override
	public void destroy() {
		super.destroy();
		dao=null;
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Cargar usuario
		Long idAgente= 1L;
		
		Agente agente = dao.getByID(idAgente);
		
		HttpSession session= request.getSession();
		session.setAttribute("agenteLogueado", agente);
		session.setMaxInactiveInterval(6*100);
		
		//Redireccionar a index.jsp
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
