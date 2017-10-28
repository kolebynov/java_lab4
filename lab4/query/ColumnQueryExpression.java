package lab4.query;

import lab4.common.ColumnQueryExpressionType;

public class ColumnQueryExpression {
	public ColumnQueryExpression(String columnName, String sourceAlias) {
		setColumnName(columnName);
		setSourceAlias(sourceAlias);
		setExpressionType(ColumnQueryExpressionType.Column);
	}
	public ColumnQueryExpression(String columnName) {
		this(columnName, null);
	}
	public ColumnQueryExpression(QueryParameter queryParameter) {
		setQueryParameter(queryParameter);
		setExpressionType(ColumnQueryExpressionType.Parameter);
	}
	public ColumnQueryExpression(Object constValue) {
		setConstValue(constValue);
		setExpressionType(ColumnQueryExpressionType.Const);
	}
	public ColumnQueryExpression(IQuery subSelect) {
		setSubSelect(subSelect);
		setExpressionType(ColumnQueryExpressionType.SubSelect);
	}
	public ColumnQueryExpression() { }
	
	public ColumnQueryExpressionType getExpressionType() {
		return m_expressionType;
	}
	public void setExpressionType(ColumnQueryExpressionType expressionType) {
		m_expressionType = expressionType;
	}
	public String getColumnName() {
		return m_columnName;
	}
	public void setColumnName(String columnName) {
		m_columnName = columnName;
	}
	public String getSourceAlias() {
		return m_sourceAlias;
	}
	public void setSourceAlias(String sourceAlias) {
		m_sourceAlias = sourceAlias;
	}
	public Object getConstValue() {
		return m_constValue;
	}
	public void setConstValue(Object constValue) {
		m_constValue = constValue;
	}
	public QueryParameter getQueryParameter() {
		return m_queryParameter;
	}
	public void setQueryParameter(QueryParameter queryParameter) {
		m_queryParameter = queryParameter;
	}
	public IQuery getSubSelect() {
		return m_subSelect;
	}
	public void setSubSelect(IQuery subSelect) {
		m_subSelect = subSelect;
	}

	private ColumnQueryExpressionType m_expressionType;
	private String m_columnName;
	private String m_sourceAlias;
	private Object m_constValue;
	private QueryParameter m_queryParameter;
	private IQuery m_subSelect;
}
