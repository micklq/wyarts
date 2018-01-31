package com.arts.org.data;

import javax.sql.DataSource;

/**
 * DataSourceHolder
 * 
 * @author Mick
 * @date 2017-9-18
 */
public class DataSourceHolder {
	
	
	private static final ThreadLocal<MasterSlave> msholder = new ThreadLocal<>();
	private static final ThreadLocal<String> dbholder = new ThreadLocal<>();
	
	public DataSourceHolder(){
		
	}
	
	public static MasterSlave getMasterSlave(){
		return msholder.get();
	}
	
	public static void setMasterSlave(MasterSlave value){
		msholder.set(value);
	}
	
	public static String getCluster(){
		return dbholder.get();
	}
	
	public static void setCluster(String value){
		dbholder.set(value);
	}
	
	public static void remove(){
		msholder.remove();
		dbholder.remove();
	}	

}
