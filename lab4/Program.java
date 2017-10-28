package lab4;

import java.sql.*;

import lab4.common.ComparisonType;
import lab4.common.LogicalOperator;
import lab4.common.QueryConditionType;
import lab4.common.SqlJoinType;
import lab4.domain.*;
import lab4.model.*;
import lab4.query.ColumnQueryExpression;
import lab4.query.JoinExpression;
import lab4.query.QueryCondition;
import lab4.query.SelectQuery;
import lab4.query.SourceQueryExpression;
import lab4.sqlGenerator.MSSqlSelectGenerator;

public class Program {

	public static void main(String[] args) throws SQLException {
		/*String connString = "jdbc:sqlserver://;Data Source=home-pc; Initial Catalog=java_lab4; Persist Security Info=True; MultipleActiveResultSets=True; Pooling = true; Max Pool Size = 100; Async = true; Connection Timeout=500";
		String user = "Supervisor";
		String pass = "";
		Connection msSqlConnection = DriverManager.getConnection(connString, user, pass);
		Repository<Train> rep = new Repository<Train>(null);
		rep.getAll();
		msSqlConnection.close();
		int a = 0;*/
		MSSqlSelectGenerator gen = new MSSqlSelectGenerator();
		SelectQuery q = new SelectQuery(gen);
		q.Column("ab").Column("asd")
			.From("asd");
		q.getCondition().getConditions().add(new QueryCondition() {{
			setComparisonType(ComparisonType.Equal);
			setLeftExpression(new ColumnQueryExpression("ab"));
			getRightExpressions().add(new ColumnQueryExpression(5));
		}});
		q.getJoins().add(new JoinExpression(
			new SourceQueryExpression("abcd"),
			SqlJoinType.Inner,
			new QueryCondition() {{
				setType(QueryConditionType.CompareFilter);
				setLeftExpression(new ColumnQueryExpression("sd", "abcd"));
				setComparisonType(ComparisonType.Less);
				getRightExpressions().add(new ColumnQueryExpression(2));
			}}));
		System.out.println(q.getSqlText());
	}
	
}