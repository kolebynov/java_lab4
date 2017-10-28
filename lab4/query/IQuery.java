package lab4.query;

public interface IQuery {
	void setParameter(String name, Object value);
	Object getParameter(String name);
	String getSqlText();
	void setParent(IQuery parent);
	IQuery getParent();
}
