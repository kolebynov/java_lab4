package lab4.query;

import java.util.ArrayList;
import java.util.List;

import lab4.common.LogicalOperator;
import lab4.common.QueryConditionType;
import lab4.sqlGenerator.ISqlGenerator;

public abstract class Query extends BaseQuery {
	public Query(ISqlGenerator sqlGenerator) {
		super(sqlGenerator);
		setParameter(ExpressionNames.queryConditionName, m_condition);
		setParameter(ExpressionNames.joinExpressionsName, m_joins);
	}
	public Query() {
		this(null);
	}
	
	public QueryCondition getCondition() {
		return m_condition;
	}
	public List<JoinExpression> getJoins() {
		return m_joins;
	}
	public Query From(String tableName) {
		return From(tableName, null);
	}
	public Query From(String tableName, String alias) {
		SourceQueryExpression fromExpression = new SourceQueryExpression(tableName);
		fromExpression.setAlias(alias);
		setFrom(fromExpression);
		return this;
	}
	public QueryCondition Where(String columnName, String sourceAlias) {
		QueryCondition condition = getNewQueryCondition(new ColumnQueryExpression(columnName, sourceAlias));
		getCondition().getConditions().add(condition);
		return condition;
	}
	public QueryCondition And() {
		return null;
	}
	public QueryCondition Or() {
		return null;
	}
	
	protected void setFrom(SourceQueryExpression fromExpression) {
		setParameter(ExpressionNames.fromExpressionName, fromExpression);
	}
	protected QueryCondition getNewQueryCondition(ColumnQueryExpression columnExpression) {
		QueryCondition condition = new QueryCondition() {{
			setLeftExpression(columnExpression);
		}};
		condition.setParentQuery(this);
		return condition;
	}

	private QueryCondition m_condition = new QueryCondition() {{
		setType(QueryConditionType.FilterGroup);
		setLogicalOperator(LogicalOperator.And);
	}};
	private ArrayList<JoinExpression> m_joins = new ArrayList<>();
}
