package org.imooc.dao.interceptor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.imooc.bean.BaseBean;
import org.imooc.bean.Page;
import org.omg.CORBA.portable.Delegate;

@Intercepts({@Signature(type=StatementHandler.class,method="prepare",args={Connection.class})})
public class PageInterceptor implements Interceptor{

	public Object intercept(Invocation arg0) throws Throwable {
		StatementHandler statementHandler = (StatementHandler)arg0.getTarget();
		MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
		//MetaObject是Mybatis提供的一个的工具类，通过它包装一个对象后可以获取或设置该对象的原本不可访问的属性（比如那些私有属性）
		MappedStatement mappedStatement = (MappedStatement)metaObject.getValue("delegate.mappedStatement");

		//你按照这个delegete的参数类型自定义一个处理方法，赋值过去，触发这个delegete的时候，就会执行你添加的方法了
		String id = mappedStatement.getId();

		//System.out.println("log--id:"+id);
		if(id.endsWith("ByPage")) {
			BoundSql boundSql = statementHandler.getBoundSql();
			String sql = boundSql.getSql();
			
			String countSql = "select count(*) from(" + sql + ")t";//总条目数
			//System.out.println("countsql: "+countSql);
			Connection conn = (Connection)arg0.getArgs()[0];
			PreparedStatement statement = conn.prepareStatement(countSql);
			ParameterHandler parameterHandler = (ParameterHandler)metaObject.getValue("delegate.parameterHandler");
			parameterHandler.setParameters(statement);
			ResultSet rs = statement.executeQuery();
			BaseBean bean = (BaseBean)boundSql.getParameterObject();
			Page page = bean.getPage();
			if(rs.next()) {
				page.setTotalNumber(rs.getInt(1));
			}
			System.out.println("pageinter:"+sql+" +over2");
			String pageSql = sql + " limit " + (page.getCurrentPage() - 1) * page.getPageNumber() + "," + page.getPageNumber();
			//表名 limit a，b    （limit 显示从a开始的b条数据）
			metaObject.setValue("delegate.boundSql.sql", pageSql);
		}
		return arg0.proceed();
	}

	public Object plugin(Object arg0) {
		return Plugin.wrap(arg0, this);
	}

	public void setProperties(Properties arg0) {
		
	}

}