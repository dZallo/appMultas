package com.ipartek.appMultas.modelo.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.ipartek.appMultas.modelo.pojo.Coche;

public class CocheDAO {

	private final static String SQL_GETBYMATRICULA = "{call coche_getByMatricula(?)}";
	private final static String SQL_GETBYID = "{call coche_getById(?)}";

	private final static Logger LOG = Logger.getLogger(CocheDAO.class);
	private static CocheDAO INSTANCE = null;

	// constructor privado, solo acceso por getInstance
	private CocheDAO() {
		super();
	}

	// Esta sincronizado para que no haya dos peticiones al mismo tiempo SINGLETON
	public synchronized static CocheDAO getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new CocheDAO();
		}
		return INSTANCE;
	}

	public Coche getByMatricula(String matricula) {
		Coche c = null;

		String sql = SQL_GETBYMATRICULA;

		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
			cs.setString(1, matricula);
			try (ResultSet rs = cs.executeQuery();) {
				while (rs.next()) {
					c = rowMapper(rs);
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}
		return c;
	}

	public Coche getBYId(Long id) {
		Coche c = new Coche();

		String sql = SQL_GETBYID;

		try (Connection conn = ConnectionManager.getConnection(); CallableStatement cs = conn.prepareCall(sql);) {
			cs.setLong(1, id);
			try (ResultSet rs = cs.executeQuery();) {
				while (rs.next()) {
					c = rowMapper(rs);
				}
			}
		} catch (Exception e) {
			LOG.debug(e);
		}
		return c;
	}

	private Coche rowMapper(ResultSet rs) throws SQLException {
		Coche c = new Coche();
		c.setId(rs.getLong("id"));
		c.setMatricula(rs.getString("matricula"));
		c.setModelo(rs.getString("modelo"));
		c.setKm(rs.getLong("km"));
		return c;
	}

}
