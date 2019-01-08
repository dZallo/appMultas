package com.ipartek.appMultas.modelo.dao;

import org.apache.log4j.Logger;

public class AgenteDAO {

	private final static String SQL_GETBYID="";
	private final static String SQL_GETALL="";
	
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
}
