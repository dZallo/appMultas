package com.ipartek.appMultas.modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.pojo.Coche;

public class CocheDAO {

	private final static String SQL_GETBYMATRICULA="SELECT c.id, c.matricula, c.modelo, c.km FROM coche AS c WHERE c.matricula =?;";
	private final static String SQL_GETBYID="SELECT c.id, c.matricula, c.modelo, c.km FROM coche AS c WHERE c.id =?;";
	//private final static String SQL_GETALL="SELECT c.id, c.matricula, c.modelo, c.km FROM coche AS c;";
	
	private final static Logger LOG = Logger.getLogger(CocheDAO.class);
	private static CocheDAO INSTANCE=null;
	
	//constructor privado, solo acceso por getInstance
	private CocheDAO() {
		super();
	}
	//Esta sincronizado para que no haya dos peticiones al mismo tiempo SINGLETON
	public synchronized static CocheDAO getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new CocheDAO();
		}
		return INSTANCE;
	}
	
	public Coche getByMatricula(String matricula) {
		Coche c = new Coche();
		
		String sql = SQL_GETBYMATRICULA;
		
		try(
			Connection conn	= ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			){
			pst.setString(1, matricula);
			try(
				ResultSet rs = pst.executeQuery();
				){
				while(rs.next()) {
					c= rowMapper(rs);
				}
			}
			
		}catch (Exception e) {
			LOG.debug(e);
		}
		
		return c;
	}
	
	
	public Coche getBYId(Long id) {
		Coche c = new Coche();
		
		String sql = SQL_GETBYID;
		
		try(
			Connection conn	= ConnectionManager.getConnection();
			PreparedStatement pst = conn.prepareStatement(sql);
			){
			pst.setLong(1, id);
			try(
				ResultSet rs = pst.executeQuery();
				){
				while(rs.next()) {
					c= rowMapper(rs);
				}
			}
			
		}catch (Exception e) {
			LOG.debug(e);
		}
		
		return c;
	}
	private Coche rowMapper(ResultSet rs) throws SQLException {
		Coche c  =new Coche();
			c.setId(rs.getLong("id"));
			c.setMatricula(rs.getString("matricula"));
			c.setModelo(rs.getString(rs.getString("modelo")));
			c.setKm(rs.getLong("km"));
		return c;
	}
	
}
