package lab4.query;

import java.util.ArrayList;
import java.util.List;

import lab4.common.ComparisonType;
import lab4.common.LogicalOperator;
import lab4.common.QueryConditionType;

public class QueryCondition {
	public QueryConditionType getType() {
		return m_type;
	}
	public void setType(QueryConditionType type) {
		this.m_type = type;
	}
	public ComparisonType getComparisonType() {
		return m_comparisonType;
	}
	public void setComparisonType(ComparisonType comparisonType) {
		this.m_comparisonType = comparisonType;
	}
	public ColumnQueryExpression getLeftExpression() {
		return m_leftExpression;
	}
	public void setLeftExpression(ColumnQueryExpression leftExpression) {
		this.m_leftExpression = leftExpression;
	}
	public List<ColumnQueryExpression> getRightExpressions() {
		return m_rightExpressions;
	}
	public LogicalOperator getLogicalOperator() {
		return m_logicalOperator;
	}
	public void setLogicalOperator(LogicalOperator logicalOperator) {
		this.m_logicalOperator = logicalOperator;
	}
	public boolean isInverted() {
		return m_inverted;
	}
	public void setInverted(boolean inverted) {
		this.m_inverted = inverted;
	}
	public List<QueryCondition> getConditions() {
		return m_conditions;
	}

	private QueryConditionType m_type = QueryConditionType.CompareFilter;
	private ComparisonType m_comparisonType;
	private LogicalOperator m_logicalOperator = LogicalOperator.And;
	private ColumnQueryExpression m_leftExpression;
	private ArrayList<ColumnQueryExpression> m_rightExpressions = new ArrayList<>();
	private boolean m_inverted = false;
	private ArrayList<QueryCondition> m_conditions = new ArrayList<>();
}
