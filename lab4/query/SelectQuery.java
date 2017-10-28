package lab4.query;

import java.util.ArrayList;
import java.util.List;

import lab4.sqlGenerator.ISqlGenerator;

public class SelectQuery extends Query {
	public SelectQuery(ISqlGenerator selectSqlGenerator) {
		super(selectSqlGenerator);
		setParameter(ExpressionNames.columnExpressionsName, m_columnExpressions);
		setParameter(ExpressionNames.joinExpressionsName, m_joins);
	}
	
	public SelectQuery Column(String columnName) {
		return Column(columnName, null);
	}
	public SelectQuery Column(String columnName, String sourceAlias) {
		getColumnExpressions().add(new ColumnQueryExpression(columnName, sourceAlias));
		return this;
	}
	public SelectQuery From(String tableName) {
		return From(tableName, null);
	}
	public SelectQuery From(String tableName, String alias) {
		SourceQueryExpression fromExpression = new SourceQueryExpression(tableName);
		fromExpression.setAlias(alias);
		setFrom(fromExpression);
		return this;
	}
	public List<ColumnQueryExpression> getColumnExpressions() {
		return m_columnExpressions;
	}
	public List<JoinExpression> getJoins() {
		return m_joins;
	}

	protected void setFrom(SourceQueryExpression fromExpression) {
		setParameter(ExpressionNames.fromExpressionName, fromExpression);
	}

	private ArrayList<ColumnQueryExpression> m_columnExpressions = new ArrayList<>();
	private ArrayList<JoinExpression> m_joins = new ArrayList<>();
	
}
