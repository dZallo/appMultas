package com.ipartek.appMultas.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MultarController
 * PARA DAR DE ALTA LA MULTA
 */
@WebServlet("/multar")
public class MultarController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Llama al controlador matricula para cargar matricula.jsp y hacer la comprobación de la misma
		request.getRequestDispatcher("matricula").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Si los datos son incorrectos, crea alerta y redirecciona a formulario.jsp
		
		//Si los datos son correctos
		
		//Da de alta la matricula y redirecciona a la página principal
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
