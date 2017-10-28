package lab4.query;

public class QueryParameter {
	public QueryParameter(String name, Object value) {
		setName(name);
		setValue(value);
	}
	
	public Object getValue() {
		return m_value;
	}
	public void setValue(Object m_value) {
		this.m_value = m_value;
	}
	public String getName() {
		return m_name;
	}
	public void setName(String name) {
		this.m_name = name;
	}

	private Object m_value;
	private String m_name;
}
