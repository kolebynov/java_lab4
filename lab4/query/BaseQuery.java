package lab4.query;

import java.util.HashMap;

import lab4.sqlGenerator.ISqlGenerator;

public abstract class BaseQuery implements IQuery {
	public BaseQuery(ISqlGenerator sqlGenerator) {
		setSqlGenerator(sqlGenerator);
	}
	public BaseQuery() {
		this(null);
	}
	
	@Override
	public void setParameter(String name, Object value) {
		m_parameters.put(name, value);
	}
	@Override
	public Object getParameter(String name) {
		return m_parameters.get(name);
	}
	public ISqlGenerator getSqlGenerator() {
		return m_sqlGenerator;
	}
	public void setSqlGenerator(ISqlGenerator sqlGenerator) {
		this.m_sqlGenerator = sqlGenerator;
	}
	@Override
	public String getSqlText() {
		return getSqlGenerator().generate(this);
	}
	@Override
	public void setParent(IQuery parent) {
		m_parent = parent;
	}
	@Override
	public IQuery getParent() {
		return m_parent;
	}

	private HashMap<String, Object> m_parameters = new HashMap<>();
	private ISqlGenerator m_sqlGenerator;
	private IQuery m_parent;
}
