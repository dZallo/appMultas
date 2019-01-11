package com.ipartek.appMultas.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.pojo.Coche;
import com.ipartek.appMultas.modelo.pojo.Multa;

public class MultaDAO {

	private final static String SQL_GETALLBYIDAGENTE="SELECT m.id AS id_multa, importe, concepto, fecha_alta ,id_agente,id_coche, c.matricula, c.modelo, c.km"
														+ " FROM multa AS m INNER JOIN coche AS c ON m.id_coche= c.id WHERE id_agente=? AND fecha_baja IS NULL ORDER BY fecha_alta DESC";
	private final static String SQL_GETALLBYIDAGENTE_BAJA="SELECT m.id AS id_multa, importe, concepto, fecha_alta ,id_agente,id_coche, c.matricula, c.modelo, c.km"
														+ " FROM multa AS m INNER JOIN coche AS c ON m.id_coche= c.id WHERE id_agente=? AND m.fecha_baja IS NOT NULL"
														+ "ORDER BY fecha_alta DESC ";
	
	private final static String SQL_GETBYID="SELECT m.id AS id_multa, importe, concepto, fecha_alta ,id_coche, c.matricula, c.modelo, c.km"
											+ "	FROM multa AS m INNER JOIN coche AS c ON m.id_coche= c.id WHERE m.id=? AND fecha_baja IS NULL ORDER BY fecha_alta DESC";
	//private final static String SQL_GETALL="";
	private final static String SQL_INSERT="INSERT INTO multa (importe,concepto,id_coche,id_agente) VALUES(?,?,?,?);";
	private final static String SQL_UPDATE_FECHA_BAJA="UPDATE multa SET fecha_baja=CURRENT_TIMESTAMP() WHERE id =?";
	
	private final static Logger LOG = Logger.getLogger(MultaDAO.class);
	private static MultaDAO INSTANCE=null;
	
	//constructor privado, solo acceso por getInstance
	private MultaDAO() {
		super();
	}
	//Esta sincronizado para que no haya dos peticiones al mismo tiempo SINGLETON
	public synchronized static MultaDAO getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new MultaDAO();
		}
		return INSTANCE;
	}
	
	public Multa getById(Long idMulta) {
		Multa m = new Multa();
		
		String sql =SQL_GETBYID;
		try(
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			){
			pst.setLong(1, idMulta);
			try(
				ResultSet rs = pst.executeQuery()
				){
				while(rs.next()) {
					m = rowMapper(rs);
				}
			}
			
		}catch (Exception e) {
			LOG.debug(e);
		}
		
		return m;
	}
	

	public HashMap<Long, Multa> getAllByIdAgente(Long idAgente) throws SQLException{
		HashMap<Long, Multa> multasAgente= new HashMap<>();
		Multa m = new Multa();
		String sql = SQL_GETALLBYIDAGENTE;
		try(
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);	
			){
			pst.setLong(1, idAgente);
			try(
					ResultSet rs = pst.executeQuery()
					){
					while(rs.next()) {
						m = rowMapper(rs);
						multasAgente.put(m.getId(), m);
					}
				}
		}catch (Exception e) {
			LOG.debug(e);
		}
		
		return multasAgente;
	}
	public HashMap<Long, Multa> getAllByIdAgenteDarBaja(Long idAgente) throws SQLException{
		HashMap<Long, Multa> multasAgente= new HashMap<>();
		Multa m = new Multa();
		String sql = SQL_GETALLBYIDAGENTE_BAJA;
		try(
			Connection conn = ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);	
			){
			pst.setLong(1, idAgente);
			try(
					ResultSet rs = pst.executeQuery()
					){
					while(rs.next()) {
						m = rowMapper(rs);
						multasAgente.put(m.getId(), m);
					}
				}
		}catch (Exception e) {
			LOG.debug(e);
		}
		
		return multasAgente;
	}
	
	public boolean insert(Multa m, Long id_agente) throws SQLException {
		boolean resul= false;
		String sql = SQL_INSERT;
		try(Connection conn= ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			){
			pst.setDouble(1, m.getImporte());
			pst.setString(2, m.getConcepto());
			pst.setLong(3, m.getCoche().getId());
			pst.setLong(4, id_agente);
			
			int affectedRows = pst.executeUpdate();
			if(affectedRows== 1){
				resul =true;
			}	
		}
		return resul;
	}
	
	public boolean darBajaMulta(Multa m) throws SQLException{
		boolean result = false;
		String sql =SQL_UPDATE_FECHA_BAJA;
		try(Connection conn = ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			){
			pst.setLong(1, m.getId());
			
			int affectedRows = pst.executeUpdate();
			if(affectedRows== 1){
				result =true;
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
			//Seteo el coche
			m.setCoche(new Coche(rs.getLong("id_coche"), rs.getString("matricula"), rs.getString("modelo"), rs.getLong("km")));
		return m;
	}
}
