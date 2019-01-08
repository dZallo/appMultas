package com.ipartek.appMultas.modelo.dao;

import org.apache.log4j.Logger;

public class MultaDAO {

	private final static String SQL_GETBYID="";
	private final static String SQL_GETALL="";
	private final static String SQL_INSERT="";
	
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
	
}
