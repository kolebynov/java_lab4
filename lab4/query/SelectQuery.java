package lab4.query;

import java.util.ArrayList;
import java.util.List;

import lab4.sqlGenerator.ISqlGenerator;

public class SelectQuery extends Query {
	public SelectQuery(ISqlGenerator selectSqlGenerator) {
		super(selectSqlGenerator);
		setParameter(ExpressionNames.columnExpressionsName, m_columnExpressions);
	}
	
	public SelectQuery Column(String columnName) {
		return Column(columnName, null);
	}
	public SelectQuery Column(String columnName, String sourceAlias) {
		getColumnExpressions().add(new ColumnQueryExpression(columnName, sourceAlias));
		return this;
	}
	public List<ColumnQueryExpression> getColumnExpressions() {
		return m_columnExpressions;
	}

	private ArrayList<ColumnQueryExpression> m_columnExpressions = new ArrayList<>();
}
