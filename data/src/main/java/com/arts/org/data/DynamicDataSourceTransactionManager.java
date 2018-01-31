package com.arts.org.data;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionDefinition;

/**
 * DynamicDataSourceTransactionManager
 * @author Mick
 * @date 2017-9-18
 */
public class DynamicDataSourceTransactionManager extends
		DataSourceTransactionManager {

	private static final long serialVersionUID = 7160082287881717832L;

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
