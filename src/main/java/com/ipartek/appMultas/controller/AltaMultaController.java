package com.ipartek.appMultas.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.dao.CocheDAO;
import com.ipartek.appMultas.modelo.dao.MultaDAO;
import com.ipartek.appMultas.modelo.pojo.Coche;
import com.ipartek.appMultas.modelo.pojo.Mensaje;
import com.ipartek.appMultas.modelo.pojo.Multa;

/**
 * Servlet implementation class AltaMultaController
 */
@WebServlet("/altamulta")
public class AltaMultaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final static Logger LOG = Logger.getLogger(AltaMultaController.class);
	private CocheDAO daoCoche;
	private MultaDAO daoMulta;
	private Double importe;
	private String concepto ="";
	private Coche c;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		daoCoche = CocheDAO.getInstance();
		daoMulta = MultaDAO.getInstance();
	}
	
	@Override
	public void destroy() {
		super.destroy();
		daoCoche = null;
		daoMulta = null;
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//Recoger datos
			//String matricula = request.getParameter("matricula");
			concepto = request.getParameter("concepto");
			importe = Double.parseDouble(request.getParameter("importe"));
			Long idCoche = Long.parseLong(request.getParameter("id_coche"));
			c = daoCoche.getBYId(idCoche);
			Long idAgente = Long.parseLong(request.getParameter("id_agente"));
			//Comprobar datos
			if (!concepto.equals("")) {
				Multa m = new Multa();
				m.setImporte(importe);
				m.setConcepto(concepto);
				m.setCoche(c);
				//Correcto: Insert en la DB
				daoMulta.insert(m, idAgente);
				//Crear mensaje
				request.setAttribute("mensaje", new Mensaje(Mensaje.TIPO_SUCCESS, "Multa registrada correctamente."));
				//Volver a index
				request.getRequestDispatcher("index").forward(request, response);
			}else {
				//Incorrecto: 
				
					//Alerta
					request.setAttribute("mensaje", new Mensaje(Mensaje.TIPO_DANGER, "Introduce los campos correctamente."));

					//Reesccribir campos
					request.setAttribute("importe", importe);
					request.setAttribute("concepto", concepto);
					request.setAttribute("coche", c);
					
					//Volver al formulario
					request.getRequestDispatcher("multar").forward(request, response);
			}

		}catch (NumberFormatException e) {
			// Importe no es numérico
			LOG.debug(e.getCause());
			request.setAttribute("mensaje", new Mensaje(Mensaje.TIPO_DANGER, " A ver si te hackeo la cara"));
			
			//Reesccribir campos
			request.setAttribute("importe", importe);
			request.setAttribute("concepto", concepto);
			request.setAttribute("coche", c);
			
			//Volver al formulario
			request.getRequestDispatcher("multar").forward(request, response);
			
		}catch (Exception e) {
			LOG.debug(e.getCause());
		}
	}

}
