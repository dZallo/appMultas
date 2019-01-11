package com.ipartek.appMultas.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.dao.MultaDAO;
import com.ipartek.appMultas.modelo.pojo.Multa;

/**
 * Servlet implementation class MultaController
 */
@WebServlet("/multa")
public class MultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOG = Logger.getLogger(MultaController.class);
	
	private static final String OP_LISTAR = "1";
	private static final String OP_IR_FORMULARIO = "2";
	
	private MultaDAO daoMulta = null;

	private String vista;
	//Parámetros
	private String op;
	private Long id;
	
	@Override
	public void init() throws ServletException {
		super.init();
		daoMulta = MultaDAO.getInstance();
	}
	
	@Override
	public void destroy() {
		super.destroy();
		daoMulta = null;
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		vista = "listado";
//		alerta = new Mensaje();
		try {
			//Recoger parámetros
			getParametros(request);
			
			//Realizar operación
			switch (op) {
				case OP_IR_FORMULARIO:
					irFormulario(request);
					break;
				default:
					listar(request);
					break;
			}
			
			//Enviar atributos
			
	
		}catch (Exception e) {
//			LOG.error(e);
//			alerta.setAlerta("Error inesperado, sentimos las molestias");
//			alerta.setTipo(ALERTA_DANGER);
		}finally {
			//Mensaje para el usuario
			//request.setAttribute("mensaje", alerta);
			//Ir a una vista
			request.getRequestDispatcher(vista).forward(request, response);
		}
		
		//Obtener multa
		
		//Existe multa
			//SI
			//Enviar multa
		
			//NO
			//Volver al listado
		
		//Redireccionar
		
	//	request.getRequestDispatcher("multa.jsp").forward(request, response);
	}

	private void listar(HttpServletRequest request) {
		// Está predefinada la lista
		//Las multas se sacan en el propio controller de listar
		
	}

	private void irFormulario(HttpServletRequest request) {
		vista = "multa.jsp";
		Multa m = new Multa();
		
		if (id > 0) {
			m = daoMulta.getById(id);
		}
		request.setAttribute("multa", m);
	}

	private void getParametros(HttpServletRequest request) {
		op = request.getParameter("op");
		if (op==null) {
			op = OP_LISTAR;
		}
		
		id = Long.parseLong(request.getParameter("id"));
		
		LOG.debug(String.format("Parametros: op=%s id=%s", op, id));
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
