package lab4.model;

import java.lang.reflect.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class Repository<TEntity> {
	public Repository(Connection connection, Class<TEntity> entityClass) {
		m_connection = connection;
		m_entityClass = entityClass;
	}
	
	public List<TEntity> getAll() {
		String selectSql = GetSelectSql();
		return new ArrayList<TEntity>();
	}
	
	private Connection m_connection;
	private String m_selectSql;
	private Class<TEntity> m_entityClass;
	
	private String GetSelectSql() {
		return m_selectSql != null ? m_selectSql : (m_selectSql = GenerateSelectSql());
	}
	private String GenerateSelectSql() {
		
		return "";
	}
}
