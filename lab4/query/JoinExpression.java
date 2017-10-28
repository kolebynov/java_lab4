package lab4.query;

import lab4.common.SqlJoinType;

public class JoinExpression {
	public JoinExpression(SourceQueryExpression joinedSourceExpression, SqlJoinType joinType, QueryCondition condition) {
		setCondition(condition);
		setJoinType(joinType);
		setJoinedSourceExpression(joinedSourceExpression);
	}
	
	public SqlJoinType getJoinType() {
		return m_joinType;
	}
	public void setJoinType(SqlJoinType joinType) {
		this.m_joinType = joinType;
	}
	public QueryCondition getCondition() {
		return m_condition;
	}
	public void setCondition(QueryCondition condition) {
		this.m_condition = condition;
	}
	public SourceQueryExpression getJoinedSourceExpression() {
		return m_joinedSourceExpression;
	}
	public void setJoinedSourceExpression(SourceQueryExpression joinedSourceExpression) {
		this.m_joinedSourceExpression = joinedSourceExpression;
	}

	private SourceQueryExpression m_joinedSourceExpression;
	private SqlJoinType m_joinType;
	private QueryCondition m_condition;
}
