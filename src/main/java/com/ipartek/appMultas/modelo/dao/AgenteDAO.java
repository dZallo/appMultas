package com.ipartek.appMultas.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.pojo.Agente;

public class AgenteDAO {

	/*private final static String SQL_GETBYID="SELECT a.id AS id_agente, a.nombre, a.placa, a.id_departamento,"
											+ " m.id AS id_multa, m.importe, m.concepto, m.fecha, m.id_coche,"
											+ " c.matricula, c.modelo, c.km FROM agente AS a "
											+ "INNER JOIN multa AS m ON a.id = m.id_agente "
											+ "INNER JOIN coche AS c ON m.id_coche = c.id "
											+ "WHERE a.id= ?;";
	*/
	private final static String SQL_GETBYID="SELECT id, nombre, placa, id_departamento FROM agente WHERE id=?";
	
	
	private final static Logger LOG = Logger.getLogger(AgenteDAO.class);
	private static AgenteDAO INSTANCE=null;
	
	//constructor privado, solo acceso por getInstance
	private AgenteDAO() {
		super();
	}
	//Esta sincronizado para que no haya dos peticiones al mismo tiempo SINGLETON
	public synchronized static AgenteDAO getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AgenteDAO();
		}
		return INSTANCE;
	}
	
	/**
	 * Este metodo solamente devuelve el agente sin las multas
	 * @param id
	 * @return
	 */
	public Agente getByID(Long id) {
		Agente a = new Agente();
		
		String sql =SQL_GETBYID;
		try(
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			){
			pst.setLong(1, id);
			try(
				ResultSet rs = pst.executeQuery();
				){
				while(rs.next()) {
					a = rowMapper(rs);
				}
			}
		}catch (Exception e) {
			LOG.debug(e);
		}
		return a;
	}
	/**
	 * rowMapper del Agente sin añadirle el listado de multas que ha puesto
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Agente rowMapper(ResultSet rs) throws SQLException {
		Agente a = new Agente();
			a.setId(rs.getLong("id_agente"));
			a.setNombre(rs.getString("nombre"));
			a.setPlaca(rs.getString("placa"));
			a.setId_departamento(rs.getLong("id_departamento"));
		return a;
	}
}
