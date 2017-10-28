package lab4.query;

import lab4.common.SourceQueryExpressionType;

public class SourceQueryExpression {
	public SourceQueryExpression(String tableName, String alias) {
		setExpressionType(SourceQueryExpressionType.TableName);
		setTableName(tableName);
		setAlias(alias);
	}
	public SourceQueryExpression(String tableName) {
		this(tableName, null);
	}
	public SourceQueryExpression(IQuery subSelectQuery) {
		setExpressionType(SourceQueryExpressionType.SubSelect);
		setSubSelectQuery(subSelectQuery);
	}
	
	public SourceQueryExpressionType getExpressionType() {
		return m_expressionType;
	}
	public void setExpressionType(SourceQueryExpressionType expressionType) {
		this.m_expressionType = expressionType;
	}
	public String getTableName() {
		return m_tableName;
	}
	public void setTableName(String tableName) {
		this.m_tableName = tableName;
	}
	public String getAlias() {
		return m_alias;
	}
	public void setAlias(String alias) {
		this.m_alias = alias;
	}
	public IQuery getSubSelectQuery() {
		return m_subSelectQuery;
	}
	public void setSubSelectQuery(IQuery subSelectQuery) {
		this.m_subSelectQuery = subSelectQuery;
	}

	private SourceQueryExpressionType m_expressionType;
	private String m_tableName;
	private String m_alias;
	private IQuery m_subSelectQuery;
}