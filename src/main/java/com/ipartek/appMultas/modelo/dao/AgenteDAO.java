package com.ipartek.appMultas.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.pojo.Agente;

public class AgenteDAO {

	private final static String SQL_GETBYID = "{call agente_getById(?)}";
	private final static String SQL_LOGIN = "{call agente_login(?,?)}";

	private final static Logger LOG = Logger.getLogger(AgenteDAO.class);
	private static AgenteDAO INSTANCE = null;
	private MultaDAO daoMulta;

	// constructor privado, solo acceso por getInstance
	private AgenteDAO() {
		super();
	}

	// Esta sincronizado para que no haya dos peticiones al mismo tiempo SINGLETON
	public synchronized static AgenteDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new AgenteDAO();
		}
		return INSTANCE;
	}
	
	public Agente login(String placa, String password) {
		Agente a = null;

		String sql = SQL_LOGIN;
		try (Connection conn = ConnectionManager.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			){
			cs.setString(1, placa);
			cs.setString(2, password);
			try (ResultSet rs = cs.executeQuery();) {
				while (rs.next()) {
					a = rowMapper(rs);
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}
		return a;
	}

	/**
	 * Este metodo solamente devuelve el agente sin las multas
	 * 
	 * @param id
	 * @return
	 */
	public Agente getByID(Long id) {
		Agente a = new Agente();

		String sql = SQL_GETBYID;
		try (Connection conn = ConnectionManager.getConnection();
			CallableStatement cs = conn.prepareCall(sql);
			){
			cs.setLong(1, id);
			try (ResultSet rs = cs.executeQuery();) {
				while (rs.next()) {
					a = rowMapper(rs);
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}
		return a;
	}
	
	public Agente obtenerImportes(Agente a) {
		daoMulta = MultaDAO.getInstance();
		a.setImporteAnual(daoMulta.getObjetivoAnual(a.getId(), (long)obtenerAño()));
		a.setImporteMensual(daoMulta.getObjetivoMensual(a.getId(), (long)obtenerAño(), (long)obtenerMes()));
		
		daoMulta = null;
		return a;
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

	/**
	 * rowMapper del Agente sin añadirle el listado de multas que ha puesto
	 * 
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Agente rowMapper(ResultSet rs) throws SQLException {
		Agente a = new Agente();
		a.setId(rs.getLong("id"));
		a.setNombre(rs.getString("nombre"));
		a.setPlaca(rs.getString("placa"));
		a.setId_departamento(rs.getLong("id_departamento"));
		return a;
	}
}
