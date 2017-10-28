package lab4.sqlGenerator;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.function.BiFunction;
import lab4.common.ColumnQueryExpressionType;
import lab4.common.ComparisonType;
import lab4.common.QueryConditionType;
import lab4.common.SourceQueryExpressionType;
import lab4.query.ColumnQueryExpression;
import lab4.query.ExpressionNames;
import lab4.query.IQuery;
import lab4.query.JoinExpression;
import lab4.query.QueryCondition;
import lab4.query.SourceQueryExpression;

public class MSSqlSelectGenerator extends BaseMSSqlGenerator {
	public MSSqlSelectGenerator() {
		initColumnSqlGenerators();
		initQuerySourceSqlGenerators();
		initConditionSqlGenerators();
		initConditionRightExpressionSqlGenerators();
	}
	
	@Override
	public String generate(IQuery query) {
		return generate(new StringBuilder(), query).toString();
	}
	public StringBuilder generate(StringBuilder sb, IQuery query) {
		int queryDeep = getQueryDeep(query);
		sb.append("select ");
		Collection<ColumnQueryExpression> columns = (Collection<ColumnQueryExpression>)query.getParameter(ExpressionNames.columnExpressionsName);
		if (columns != null && !columns.isEmpty()) {
			appendColumns(sb, columns);
		}
		appendSeparator(sb, queryDeep);
		SourceQueryExpression sourceQuery = (SourceQueryExpression)query.getParameter(ExpressionNames.fromExpressionName);
		if (sourceQuery != null) {
			appendSourceQuery(sb.append("from "), sourceQuery);
		}
		Collection<JoinExpression> joins = (Collection<JoinExpression>)query.getParameter(ExpressionNames.joinExpressionsName);
		if (joins != null && !joins.isEmpty()) {
			appendJoins(sb, joins, queryDeep);
		}
		appendSeparator(sb, queryDeep);
		QueryCondition condition = (QueryCondition)query.getParameter(ExpressionNames.queryConditionName);
		if (condition != null) {
			appendCondition(sb.append("where "), condition);
		}
		
		return sb;
	}
	
	protected StringBuilder appendColumns(StringBuilder sb, Iterable<ColumnQueryExpression> columns) {
		columns.forEach(column -> appendColumn(sb, column).append(','));
		sb.setLength(sb.length() - 1);
		return sb;
	}
	protected StringBuilder appendColumn(StringBuilder sb, ColumnQueryExpression column) {
		return m_columnSqlGenerators.get(column.getExpressionType()).apply(sb, column);
	}
	protected StringBuilder appendSourceQuery(StringBuilder sb, SourceQueryExpression sourceQuery) {
		return m_querySourceSqlGenerators.get(sourceQuery.getExpressionType()).apply(sb, sourceQuery);
	}
	protected StringBuilder appendJoins(StringBuilder sb, Iterable<JoinExpression> joins, int queryDeep) {
		joins.forEach(join -> {
			appendSeparator(sb, queryDeep + 1).append(getSqlJoinTypeString(join.getJoinType()) + " ");
			appendSourceQuery(sb, join.getJoinedSourceExpression()).append(" on ");
			appendCondition(sb, join.getCondition());
		});
		return sb;
	}
	protected StringBuilder appendCondition(StringBuilder sb, QueryCondition condition) {
		return m_conditionSqlGenerators.get(condition.getType()).apply(sb, condition);
	}
	protected String getColumnPath(ColumnQueryExpression column) {
		String sourceAlias = column.getSourceAlias();
		if (sourceAlias == null || sourceAlias.isEmpty()) {
			return column.getColumnName();
		}
		else {
			return sourceAlias + '.' + column.getColumnName();
		}
	}
	
	private HashMap<ColumnQueryExpressionType, BiFunction<StringBuilder, ColumnQueryExpression, StringBuilder>> m_columnSqlGenerators;
	private HashMap<SourceQueryExpressionType, BiFunction<StringBuilder, SourceQueryExpression, StringBuilder>> m_querySourceSqlGenerators;
	private HashMap<QueryConditionType, BiFunction<StringBuilder, QueryCondition, StringBuilder>> m_conditionSqlGenerators;
	private HashMap<ComparisonType, BiFunction<StringBuilder, List<ColumnQueryExpression>, StringBuilder>> m_conditionRightExpressionSqlGenerators;
	
	private void initColumnSqlGenerators() {
		m_columnSqlGenerators = new HashMap<>();
		m_columnSqlGenerators.put(ColumnQueryExpressionType.Asterisk, this::appendAsteriskColumn);
		m_columnSqlGenerators.put(ColumnQueryExpressionType.Column, this::appendTableColumn);
		m_columnSqlGenerators.put(ColumnQueryExpressionType.Const, this::appendConstantColumn);
		m_columnSqlGenerators.put(ColumnQueryExpressionType.Parameter, this::appendParameterColumn);
		m_columnSqlGenerators.put(ColumnQueryExpressionType.SubSelect, this::appendSubSelectColumn);
	}
	private void initQuerySourceSqlGenerators() {
		m_querySourceSqlGenerators = new HashMap<>();
		m_querySourceSqlGenerators.put(SourceQueryExpressionType.TableName, this::appendTableSource);
		m_querySourceSqlGenerators.put(SourceQueryExpressionType.SubSelect, this::appendSubSelectSource);
	}
	private void initConditionSqlGenerators() {
		m_conditionSqlGenerators = new HashMap<>();
		m_conditionSqlGenerators.put(QueryConditionType.CompareFilter, this::appendCompareCondition);
		m_conditionSqlGenerators.put(QueryConditionType.FilterGroup, this::appendFilterGroupCondition);
	}
	private void initConditionRightExpressionSqlGenerators() {
		m_conditionRightExpressionSqlGenerators = new HashMap<>();
		m_conditionRightExpressionSqlGenerators.put(ComparisonType.Equal, this::appendDefaultConditionRightExpression);
		m_conditionRightExpressionSqlGenerators.put(ComparisonType.NotEqual, this::appendDefaultConditionRightExpression);
		m_conditionRightExpressionSqlGenerators.put(ComparisonType.Greater, this::appendDefaultConditionRightExpression);
		m_conditionRightExpressionSqlGenerators.put(ComparisonType.GreaterOrEqual, this::appendDefaultConditionRightExpression);
		m_conditionRightExpressionSqlGenerators.put(ComparisonType.Less, this::appendDefaultConditionRightExpression);
		m_conditionRightExpressionSqlGenerators.put(ComparisonType.LessOrEqual, this::appendDefaultConditionRightExpression);
		m_conditionRightExpressionSqlGenerators.put(ComparisonType.Between, this::appendBetweenConditionRightExpression);
		m_conditionRightExpressionSqlGenerators.put(ComparisonType.In, this::appendInConditionRightExpression);
	}
	private StringBuilder appendAsteriskColumn(StringBuilder sb, ColumnQueryExpression column) {
		return sb.append("*");
	}
	private StringBuilder appendTableColumn(StringBuilder sb, ColumnQueryExpression column) {
		return sb.append(getColumnPath(column));
	}
	private StringBuilder appendConstantColumn(StringBuilder sb, ColumnQueryExpression column) {
		return sb.append(column.getConstValue());
	}
	private StringBuilder appendParameterColumn(StringBuilder sb, ColumnQueryExpression column) {
		return appendParameter(sb, column.getQueryParameter());
	}
	private StringBuilder appendSubSelectColumn(StringBuilder sb, ColumnQueryExpression column) {
		return generate(sb.append('('), column.getSubSelect()).append(')');
	}
	private StringBuilder appendTableSource(StringBuilder sb, SourceQueryExpression source) {
		sb.append(source.getTableName());
		return appendAlias(sb, source.getAlias());
	}
	private StringBuilder appendSubSelectSource(StringBuilder sb, SourceQueryExpression source) {
		generate(sb.append('('), source.getSubSelectQuery()).append(')');
		return appendAlias(sb, source.getAlias());
	}
	private StringBuilder appendCompareCondition(StringBuilder sb, QueryCondition condition) {
		appendColumn(sb.append(condition.isInverted() ? "not (" : '('), condition.getLeftExpression())
			.append(' ' + getSqlCompareOperatorString(condition.getComparisonType()) + ' ');
		return m_conditionRightExpressionSqlGenerators.get(condition.getComparisonType())
			.apply(sb, condition.getRightExpressions()).append(')');
	}
	private StringBuilder appendFilterGroupCondition(StringBuilder sb, QueryCondition condition) {
		if (!condition.getConditions().isEmpty()) {
			String conditionSeparator = ' ' + getSqlLogicalOperatorString(condition.getLogicalOperator()) + ' ';
			condition.getConditions().forEach(childCondition -> 
				appendCondition(sb, childCondition).append(conditionSeparator)
			);
			sb.setLength(sb.length() - conditionSeparator.length());
		}
		return sb;
	}
	private StringBuilder appendDefaultConditionRightExpression(StringBuilder sb, 
			List<ColumnQueryExpression> rightExpressions) {
		if (!rightExpressions.isEmpty()) {
			appendColumn(sb, rightExpressions.get(0));
		}
		return sb;
	}
	private StringBuilder appendBetweenConditionRightExpression(StringBuilder sb, 
			List<ColumnQueryExpression> rightExpressions) {
		if (rightExpressions.size() > 1) {
			appendColumn(sb, rightExpressions.get(0)).append(" and ");
			appendColumn(sb, rightExpressions.get(1));
		}
		return sb;
	}
	private StringBuilder appendInConditionRightExpression(StringBuilder sb, 
			List<ColumnQueryExpression> rightExpressions) {
		sb.append('(');
		if (!rightExpressions.isEmpty()) {
			rightExpressions.forEach(column -> appendColumn(sb, column).append(','));
			sb.setLength(sb.length() - 1);
		}
		
		return sb.append(')');
	}
}
