package lab4.sqlGenerator;

import lab4.query.IQuery;

public interface ISqlGenerator {
	String generate(IQuery query);
}
