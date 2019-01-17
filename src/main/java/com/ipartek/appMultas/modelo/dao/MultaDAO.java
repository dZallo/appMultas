package com.ipartek.appMultas.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.pojo.Coche;
import com.ipartek.appMultas.modelo.pojo.Multa;

public class MultaDAO {

	private final static String SQL_GETALLBYIDAGENTE = "{call multa_getAllByIdAgente(?)}";
	private final static String SQL_GETALLBYIDAGENTE_BAJA = "{call multa_getAllByIdAgenteBaja(?)}";

	private final static String SQL_GETBYID = "{call multa_getById(?)}";

	private final static String SQL_INSERT = "{call multa_insert(?,?,?,?,?)}";
	private final static String SQL_UPDATE_FECHA_BAJA = "{call multa_updateFechaBaja(?)}";
	private final static String SQL_DESANULAR = "{call multa_desAnular(?)}";
	private final static String SQL_OBJETIVOS_ANIO="SELECT id_agente,anio,multasAsignadas,totalMultasAnual FROM v_objetivos_anio WHERE id_agente=? AND anio=?;";
	private final static String SQL_OBJETIVOS_MES="SELECT id_agente,mes,anio,multasAsignadas,totalMultasMes FROM v_objetivos_mes WHERE id_agente=? AND mes=? AND anio=?;";
	

	private final static Logger LOG = Logger.getLogger(MultaDAO.class);
	private static MultaDAO INSTANCE = null;

	// constructor privado, solo acceso por getInstance
	private MultaDAO() {
		super();
	}

	// Esta sincronizado para que no haya dos peticiones al mismo tiempo SINGLETON
	public synchronized static MultaDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new MultaDAO();
		}
		return INSTANCE;
	}

	/**
	 * Obtiene la suma  total de los importes del año y el agente seleccionado
	 * @param id_agente
	 * @param anio
	 * @return
	 */
	public Double getObjetivoAnual( Long id_agente, Long anio) {
		
		Double importeAnual=0.0;
		
		String sql = SQL_OBJETIVOS_ANIO;
		try(
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pst =conn.prepareStatement(sql);
			){
			pst.setLong(1, id_agente);
			pst.setLong(2, anio);
			try(
				ResultSet rs = pst.executeQuery();
				){
				while(rs.next()) {
					importeAnual = rs.getDouble("totalMultasAnual");
				}
			}
			
		}catch (Exception e) {
			LOG.debug(e);
		}
		return importeAnual;
	}
	
	/**
	 *  Obtiene la suma  total de los importes del año, el mes y el agente seleccionado
	 * @param id_agente
	 * @param anio
	 * @param mes
	 * @return
	 */
	public Double getObjetivoMensual( Long id_agente, Long anio, Long mes) {
		Double importeMensual=0.0;
		String sql = SQL_OBJETIVOS_MES;
		try(
				Connection conn = ConnectionManager.getConnection();
				PreparedStatement pst =conn.prepareStatement(sql);
				){
				pst.setLong(1, id_agente);
				pst.setLong(2, mes);
				pst.setLong(3, anio);
				try(
						ResultSet rs = pst.executeQuery();
						){
						while(rs.next()) {
							importeMensual = rs.getDouble("totalMultasMes");
						}
					}
					
				}catch (Exception e) {
					LOG.debug(e);
				}
		
		return importeMensual;
	}
	
	public Multa getById(Long idMulta) {
		Multa m = new Multa();

		String sql = SQL_GETBYID;
		try (Connection conn = ConnectionManager.getConnection(); 
			CallableStatement cs = conn.prepareCall(sql);
			){
			cs.setLong(1, idMulta);
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					m = rowMapperBaja(rs);
				}
			}

		} catch (Exception e) {
			LOG.debug(e);
		}

		return m;
	}

	public HashMap<Long, Multa> getAllByIdAgente(Long idAgente) throws SQLException {
		HashMap<Long, Multa> multasAgente = new HashMap<>();
		Multa m = new Multa();
		String sql = SQL_GETALLBYIDAGENTE;
		try (Connection conn = ConnectionManager.getConnection();
			CallableStatement cs = conn.prepareCall(sql);) {
			cs.setLong(1, idAgente);
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					m = rowMapper(rs);
					multasAgente.put(m.getId(), m);
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}

		return multasAgente;
	}

	public HashMap<Long, Multa> getAllByIdAgenteDarBaja(Long idAgente) throws SQLException {
		HashMap<Long, Multa> multasAgente = new HashMap<>();
		Multa m = new Multa();
		String sql = SQL_GETALLBYIDAGENTE_BAJA;
		try (Connection conn = ConnectionManager.getConnection(); 
			CallableStatement cs = conn.prepareCall(sql);
			){
			cs.setLong(1, idAgente);
			try (ResultSet rs = cs.executeQuery()) {
				while (rs.next()) {
					m = rowMapperBaja(rs);
					multasAgente.put(m.getId(), m);
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}

		return multasAgente;
	}


	public boolean insert(Multa m, Long id_agente) throws SQLException {
		boolean resul = false;
		String sql = SQL_INSERT;
		try (Connection conn = ConnectionManager.getConnection(); 
			CallableStatement cs = conn.prepareCall(sql);
			) {
			//parametros de entrada
			cs.setDouble(1, m.getImporte());
			cs.setString(2, m.getConcepto());
			cs.setLong(3, m.getCoche().getId());
			cs.setLong(4, id_agente);
			
			//parametros de salida
			cs.registerOutParameter(5, Types.INTEGER);

			int affectedRows = cs.executeUpdate();
			if (affectedRows == 1) {
				// conseguir id generado
				m.setId(cs.getLong(5));
				resul = true;
			}
		}
		return resul;
	}

	/**
	 * Este metodo actualiza el atributo fecha_baja, por lo tanto al tener un dato en dicho campo significará que la multa se ha dado de baja
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public boolean darBajaMulta(Long id) throws SQLException {
		boolean result = false;
		String sql = SQL_UPDATE_FECHA_BAJA;
		try (Connection conn = ConnectionManager.getConnection(); 
			CallableStatement cs = conn.prepareCall(sql);
			){
			cs.setLong(1, id);

			int affectedRows = cs.executeUpdate();
			if (affectedRows == 1) {
				result = true;
				
			}
		}

		return result;
	}
	
	public boolean desAnularMulta(Long id) throws SQLException {
		boolean result = false;
		String sql = SQL_DESANULAR;
		try (Connection conn = ConnectionManager.getConnection(); 
			CallableStatement cs = conn.prepareCall(sql);
			){
			cs.setLong(1, id);

			int affectedRows = cs.executeUpdate();
			if (affectedRows == 1) {
				result = true;
				
			}
		}

		return result;
	}

	private Multa rowMapper(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		m.setId(rs.getLong("id_multa"));
		m.setConcepto(rs.getString("concepto"));
		m.setImporte(rs.getDouble("importe"));
		m.setFecha(rs.getTimestamp("fecha_alta"));
		// Seteo el coche
		m.setCoche(
				new Coche(rs.getLong("id_coche"), rs.getString("matricula"), rs.getString("modelo"), rs.getLong("km")));
		return m;
	}
	
	private Multa rowMapperBaja(ResultSet rs) throws SQLException {
		Multa m = new Multa();
		m.setId(rs.getLong("id_multa"));
		m.setConcepto(rs.getString("concepto"));
		m.setImporte(rs.getDouble("importe"));
		m.setFecha(rs.getTimestamp("fecha_alta"));
		m.setFecha_baja(rs.getTimestamp("fecha_baja"));
		// Seteo el coche
		m.setCoche(
				new Coche(rs.getLong("id_coche"), rs.getString("matricula"), rs.getString("modelo"), rs.getLong("km")));
		return m;
	}

}
