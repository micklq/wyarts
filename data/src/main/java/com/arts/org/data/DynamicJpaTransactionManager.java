package com.arts.org.data;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * DynamicDataSourceTransactionManager
 * @author Mick
 * @date 2017-9-18
 */
public class DynamicJpaTransactionManager extends JpaTransactionManager {


	/**
	 * 
	 */
	private static final long serialVersionUID = -6190183512719593765L;

	/**
	 * 只读事务到从库
	 * 读写事务到主库
	 */
	@Override
	protected void doBegin(Object transaction, TransactionDefinition definition) {
		boolean readOnly = definition.isReadOnly();
		if(readOnly){			
			DataSourceHolder.setMasterSlave(MasterSlave.SLAVE);			
		}else{
			DataSourceHolder.setMasterSlave(MasterSlave.MASTER);
		}		
		super.doBegin(transaction, definition);
	}
	
	/**
	 * 清理本地线程的数据源
	 */
	@Override
	protected void doCleanupAfterCompletion(Object transaction) {
		super.doCleanupAfterCompletion(transaction);
		DataSourceHolder.remove();
	}
	
	
}
