package lab4.query;

import lab4.common.LogicalOperator;
import lab4.common.QueryConditionType;
import lab4.sqlGenerator.ISqlGenerator;

public abstract class Query extends BaseQuery {
	public Query(ISqlGenerator sqlGenerator) {
		super(sqlGenerator);
		setParameter(ExpressionNames.queryConditionName, m_condition);
	}
	public Query() {
		this(null);
	}
	
	public QueryCondition getCondition() {
		return m_condition;
	}

	private QueryCondition m_condition = new QueryCondition() {{
		setType(QueryConditionType.FilterGroup);
		setLogicalOperator(LogicalOperator.And);
	}};
}
