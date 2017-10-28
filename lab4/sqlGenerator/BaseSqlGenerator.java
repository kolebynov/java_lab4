package lab4.sqlGenerator;

import lab4.query.IQuery;
import lab4.query.QueryParameter;

public abstract class BaseSqlGenerator implements ISqlGenerator {
	protected StringBuilder appendParameter(StringBuilder sb, QueryParameter parameter) {
		return sb.append(":" + parameter.getName());
	}
	protected StringBuilder appendSeparator(StringBuilder sb) {
		return appendSeparator(sb, 0);
	}
	protected StringBuilder appendSeparator(StringBuilder sb, int tabsCount) {
		sb.append(System.lineSeparator());
		for (int i = 0; i < tabsCount; ++i) {
			sb.append('\t');
		}
		
		return sb;
	}
	protected int getQueryDeep(IQuery query) {
		int deep = 0;
		IQuery parentQuery = query;
		for (;(parentQuery = parentQuery.getParent()) != null; ++deep) {}
		return deep;
	}
}
