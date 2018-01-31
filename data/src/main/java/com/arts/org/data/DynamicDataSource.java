package com.arts.org.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicInteger;
import javax.sql.DataSource;
import org.apache.log4j.Logger;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import com.alibaba.druid.pool.DruidDataSource;
//import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 动态数据源
 * @author Mick
 * @date 2017-9-18
 */
public class DynamicDataSource extends AbstractRoutingDataSource{
	
	private AtomicInteger counter = new AtomicInteger();
	private Object master;
	private List<Object> slaves;
	private Map<Object, Object> targetDataSources;
	private int slaveSize = 0;
	
	public Object getMaster() {
	return master;
	}
	
	public void setMaster(Object master) {
		this.master = master;
	}
	
	public List<Object> getSlaves() {
		return slaves;
	}
	
	public void setSlaves(List<Object> slaves) {
		this.slaves = slaves;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		// TODO Auto-generated method stub
		//return null;
		
		String name = null;

		MasterSlave type = DataSourceHolder.getMasterSlave();//读写方式
		
		if(type == null || type == MasterSlave.MASTER || slaveSize == 0){
			name = MasterSlave.MASTER.name();
		}
		else
		{
			
			int count = counter.incrementAndGet();
			if(count>1000000){
				counter.set(0);
			}
			int n = slaves.size();
			int index = count%n;
			name = MasterSlave.SLAVE.name() + "_" + index;
			
		}
		
		System.out.println(">>determineCurrentLookupKey-->" + name);
		
		return name;
	}
	
	@Override
	public void afterPropertiesSet() {

		if (this.master == null) {
			throw new IllegalArgumentException( "Property 'masterDataSource' is required");
		}

		this.setDefaultTargetDataSource(this.master);
		this.targetDataSources = new HashMap<>();
		this.targetDataSources.put(MasterSlave.MASTER.name(), this.master);

		if (this.slaves != null) {
			slaveSize = this.slaves.size();
			
			for (int i = 0; i < slaveSize; i++) {
				String name = MasterSlave.SLAVE.name() + "_" + i;
				
				this.targetDataSources.put(name, this.slaves.get(i));
			}
		}
		
		this.setTargetDataSources(targetDataSources);

		super.afterPropertiesSet();
	}

	@Override
	public Connection getConnection() throws SQLException {
		return super.getConnection();
	}

	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		return super.getConnection(username, password);
	}

	@Override
	protected DataSource determineTargetDataSource() {
		return super.determineTargetDataSource();
	}
	
	
}
