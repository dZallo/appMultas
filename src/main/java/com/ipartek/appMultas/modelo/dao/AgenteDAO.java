package com.ipartek.appMultas.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.pojo.Agente;
import com.ipartek.appMultas.modelo.pojo.Objetivo;

public class AgenteDAO {

	private final static String SQL_GETBYID = "{call agente_getById(?)}";
	private final static String SQL_LOGIN = "{call agente_login(?,?)}";
	private final static String SQL_OBJETIVOS_ANIO = "SELECT id_agente,anio,multasAsignadas,totalMultasAnual FROM v_objetivos_anio WHERE id_agente=? AND anio=?;";
	private final static String SQL_OBJETIVOS_MES = "SELECT id_agente,mes,anio,multasAsignadas,totalMultasMes FROM v_objetivos_mes WHERE id_agente=? AND mes=? AND anio=?;";
	private final static String SQL_OBJETIVOS_MESES_ANIO="SELECT id_agente,mes,anio,multasAsignadas,totalMultasMes FROM v_objetivos_mes WHERE id_agente=? AND anio=? ORDER BY mes ASC;";
	
	private final static Logger LOG = Logger.getLogger(AgenteDAO.class);
	private static AgenteDAO INSTANCE = null;

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
		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
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
		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
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

	public Objetivo getObjetivosAnual(Long id_agente, Long anio) {
		Objetivo o = new Objetivo();
		String sql = SQL_OBJETIVOS_ANIO;
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setLong(1, id_agente);
			pst.setLong(2, anio);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					//Crear objeto Objetivo
					o.setAnio(rs.getDouble("anio"));
					o.setImporteTotal(rs.getDouble("totalMultasAnual"));		
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}
		return o;
	}


	public Objetivo getObjetivoMensual(Long id_agente, Long anio, Long mes) {
		Objetivo o = new Objetivo();
		String sql = SQL_OBJETIVOS_MES;
		try (Connection conn = ConnectionManager.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
			pst.setLong(1, id_agente);
			pst.setLong(2, mes);
			pst.setLong(3, anio);
			try (ResultSet rs = pst.executeQuery();) {
				while (rs.next()) {
					//Crear objeto Objetivo
					o.setAnio(rs.getDouble("anio"));
					o.setImporteTotal(rs.getDouble("totalMultasMes"));
					o.setMes(rs.getDouble("mes"));
				}
			}

		} catch (Exception e) {
			LOG.debug(e);
		}
		
		return o;
	}
	//TODO hacer metodo que obtenga todos los objetivos(meses) de un año 
	public ArrayList<Objetivo> getObjetivoMeses(Long id_agente, Long anio){
		ArrayList<Objetivo> objetivos = new ArrayList<Objetivo>();
		String sql = SQL_OBJETIVOS_MESES_ANIO;
		try(
			Connection conn= ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			){
			pst.setLong(1, id_agente);
			pst.setLong(2, anio);
			try(
				ResultSet rs = pst.executeQuery();
				){
				while(rs.next()) {
					Objetivo o = new Objetivo();
					//Crear objeto Objetivo
					o.setAnio(rs.getDouble("anio"));
					o.setImporteTotal(rs.getDouble("totalMultasMes"));
					o.setMes(rs.getDouble("mes"));
					
					//añadir objetivo al array
					objetivos.add(o);
				}
			}	
		}catch (Exception e) {
			LOG.debug(e);
		}
		return objetivos;
	}

	public Agente obtenerImportes(Agente a) {
		a.setAnual(getObjetivosAnual(a.getId(), (long) obtenerAnio()));
		a.setMensual(getObjetivoMensual(a.getId(), (long) obtenerAnio(),(long) obtenerMes()));
		//OBTENER TODOS LOS MESES DE UN AÑO ARRAYLIST OBJETIVOS
		//a.setObjetivoMeses(objetivoMeses);
		return a;
	}

	private int obtenerAnio() {
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
