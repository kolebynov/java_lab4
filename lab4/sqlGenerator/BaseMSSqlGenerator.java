package lab4.sqlGenerator;

import java.util.HashMap;

import lab4.common.ComparisonType;
import lab4.common.LogicalOperator;
import lab4.common.SqlJoinType;
import lab4.query.IQuery;

public abstract class BaseMSSqlGenerator extends BaseSqlGenerator {
	protected String getSqlCompareOperatorString(ComparisonType comparisonType) {
		return m_sqlCompareOperators.get(comparisonType);
	}
	protected String getSqlLogicalOperatorString(LogicalOperator logicalOperator) {
		return m_sqlLogicalOperators.get(logicalOperator);
	}
	protected String getSqlJoinTypeString(SqlJoinType joinType) {
		return m_sqlJoinTypes.get(joinType);
	}
	protected StringBuilder appendAlias(StringBuilder sb, String alias) {
		if (alias != null && !alias.isEmpty()) {
			sb.append(" as " + alias);
		}
		return sb;
	}
	
	private HashMap<ComparisonType, String> m_sqlCompareOperators = new HashMap<ComparisonType, String>() {{
		put(ComparisonType.Between, "between");
		put(ComparisonType.Equal, "=");
		put(ComparisonType.Greater, ">");
		put(ComparisonType.GreaterOrEqual, ">=");
		put(ComparisonType.Less, "<");
		put(ComparisonType.LessOrEqual, "<=");
		put(ComparisonType.NotEqual, "!=");
		put(ComparisonType.In, "in");
	}};
	private HashMap<LogicalOperator, String> m_sqlLogicalOperators = new HashMap<LogicalOperator, String>() {{
		put(LogicalOperator.And, "and");
		put(LogicalOperator.Or, "or");
	}};
	private HashMap<SqlJoinType, String> m_sqlJoinTypes = new HashMap<SqlJoinType, String>() {{
		put(SqlJoinType.Left, "left join");
		put(SqlJoinType.Right, "right join");
		put(SqlJoinType.Cross, "cross join");
		put(SqlJoinType.Full, "full join");
		put(SqlJoinType.Inner, "inner join");
	}};
}
