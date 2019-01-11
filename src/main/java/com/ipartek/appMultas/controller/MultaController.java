package com.ipartek.appMultas.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.dao.MultaDAO;
import com.ipartek.appMultas.modelo.pojo.Agente;
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
	private static final String OP_DAR_DE_BAJA = "3";
	private static final String OP_LISTADO_BAJA = "4";

	private MultaDAO daoMulta = null;

	private String vista;
	// Parámetros
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		vista = "listado";
		//alerta = new Mensaje();
		try {
			// Recoger parámetros
			getParametros(request);

			// Realizar operación
			switch (op) {
			case OP_IR_FORMULARIO:
				irFormulario(request);
				break;
			case OP_DAR_DE_BAJA:
				darDeBaja(request);
				break;
			case OP_LISTADO_BAJA:
				listadoBaja(request);
				break;
			default:
				listar(request);
				break;
			}

		} catch (Exception e) {
			LOG.error(e);
		} finally {

			// Ir a una vista
			request.getRequestDispatcher(vista).forward(request, response);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void listadoBaja(HttpServletRequest request) {
		vista = "listadoBajas.jsp";
		HttpSession session = request.getSession();
		Agente a = (Agente) session.getAttribute("agenteLogueado");
		try {
			request.setAttribute("multas", daoMulta.getAllByIdAgenteDarBaja(a.getId()));
		} catch (SQLException e) {
			LOG.error(e);
		}

	}

	private void darDeBaja(HttpServletRequest request) {
		try {
			daoMulta.darBajaMulta(id);
			listadoBaja(request);
		} catch (SQLException e) {
			LOG.error(e);
			// No ha sido posible retirar la multa
		}

	}

	private void listar(HttpServletRequest request) {
		// Está predefinida la lista
		// Las multas se sacan en el propio controller de listar

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
		if (op == null) {
			op = OP_LISTAR;
		}
		String idTexto = request.getParameter("id");
		if (idTexto == null) {
			id = 0L;
		} else {
			id = Long.parseLong(request.getParameter("id"));
		}

		LOG.debug(String.format("Parametros: op=%s id=%s", op, id));

	}

}
