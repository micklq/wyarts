package com.arts.org.data;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

@Intercepts({
@Signature(type = Executor.class, method = "update", args = {
        MappedStatement.class, Object.class }),
@Signature(type = Executor.class, method = "query", args = {
        MappedStatement.class, Object.class, RowBounds.class,
        ResultHandler.class }) })
public class DynamicInterceptorPlugin implements Interceptor {
	
	private static final String FIND = "find";
	
	private static final String SELECT = "select";
	
	private static final String QUERY = "query";
	
	private static final String INSERT = "insert";
	
	private static final String DELETE = "delete";
	
	private static final String UPDATE = "update";
	
	private static final String SAVE = "save";	 
	
	    @Override
	    public Object intercept(Invocation invocation) throws Throwable {

	    	try {
				boolean synchronizationActive = TransactionSynchronizationManager.isSynchronizationActive();
				if(!synchronizationActive){
					 Object[] objects = invocation.getArgs();
			          MappedStatement ms = (MappedStatement) objects[0];
					String methodName =ms.getId().substring((ms.getId().lastIndexOf(".")+1),ms.getId().length()).toLowerCase();
					if(methodName.startsWith(SELECT)||
							methodName.startsWith(FIND)){
						//获取读集群的数据源
						DataSourceHolder.setMasterSlave(MasterSlave.SLAVE);
					}else if(methodName.startsWith(INSERT)||
							methodName.startsWith(QUERY)||
							methodName.startsWith(UPDATE)||
							methodName.startsWith(SAVE)||
							methodName.startsWith(DELETE)){
						//获取主库数据源
						DataSourceHolder.setMasterSlave(MasterSlave.MASTER);
					}
					
				}				
			} catch (Exception e) {
				throw e;
			}
	    	return invocation.proceed();
	    	  
	       
	    }

	    @Override
	    public Object plugin(Object target) {
	        if (target instanceof Executor) {
	            return Plugin.wrap(target, this);
	        } else {
	            return target;
	        }
	    }

	    @Override
	    public void setProperties(Properties properties) {
	        //
	    }


	
}
