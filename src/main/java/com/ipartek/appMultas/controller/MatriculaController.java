package com.ipartek.appMultas.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class MatriculaController
 * PARA COMPROBAR QUE LA MATRÍCULA EXISTE
 */
@WebServlet("/matricula")
public class MatriculaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Llama a matricula.jsp para la introducción de la matrícula a comprobar 
		request.getRequestDispatcher("matricula.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Comprobar matrícula
		
		//Si es correcta, enviar a formulario.jsp
		request.getRequestDispatcher("formulario.jsp").forward(request, response);
		//Si no existe, crear alerta y volver a matricula.jsp
	}

}
