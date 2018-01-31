package com.arts.org.webcomn.util;
import java.lang.reflect.Field;  
import java.lang.reflect.InvocationTargetException;  
import java.lang.reflect.Method;  
import java.text.ParseException;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.HashMap;  
import java.util.List;
import java.util.Map;  
import java.util.Set;

import com.arts.org.base.util.Util;

public class ClassHelper {
	
	 public static  <T> T convertClass(Object obj,Class<T> cla)  {  
	        Map<String,Object> maps = new HashMap<String,Object>();  
	        T  dataBean = null;  
	        if(null==obj) {  
	            return null;  
	        }  
	        Class<?> cls = obj.getClass();  
	        try {
				dataBean = cla.newInstance();
			} catch (Exception e1) {				
				e1.printStackTrace();
			}       
	        
	        Set<Field> fields = ClassHelper.getClassFields(cls,true);  
	        Set<Field> beanFields = ClassHelper.getClassFields(cla,true);  	        
	        
	        for(Field field:fields){  
	           String fieldName = field.getName();  	           
	            String strGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());              
	            try { 
	            	  Method methodGet = cls.getMethod(strGet);  	            
	            		Object object = methodGet.invoke(obj);  			           
			            maps.put(fieldName,object==null?"":object);  
	            }
	            catch (Exception e) {  
	            	
	            	if("getSerialVersionUID".equals(strGet)){
	            		System.out.println(String.format("method getSerialVersionUID() no finded in class %s",cls.getName()));
	            		//continue;
	            	}
	            	else{
	            		e.printStackTrace();
	            	}
               } 
	            
	            
		            
	        }  
	        
	        for(Field field:beanFields){  
	            field.setAccessible(true);  
	            String fieldName = field.getName();  	           
	            Class<?> fieldType = field.getType();  
	            String fieldValue = maps.get(fieldName)==null?null:maps.get(fieldName).toString();  
	            if(fieldValue!=null){  
	                try {  
	                    if(String.class.equals(fieldType)){  
	                    	
	                        field.set(dataBean, fieldValue);  
	                    }else if(byte.class.equals(fieldType)){  
	                        field.setByte(dataBean, Byte.parseByte(fieldValue));  
	  
	                    }else if(Byte.class.equals(fieldType)){  
	                        field.set(dataBean, Byte.valueOf(fieldValue));  
	  
	                    }else if(boolean.class.equals(fieldType)){  
	                        field.setBoolean(dataBean, Boolean.parseBoolean(fieldValue));  
	  
	                    }else if(Boolean.class.equals(fieldType)){  
	                        field.set(dataBean, Boolean.valueOf(fieldValue));  
	  
	                    }else if(short.class.equals(fieldType)){  
	                        field.setShort(dataBean, Short.parseShort(fieldValue));  
	  
	                    }else if(Short.class.equals(fieldType)){  
	                        field.set(dataBean, Short.valueOf(fieldValue));  
	  
	                    }else if(int.class.equals(fieldType)){  
	                        field.setInt(dataBean, Util.toInt(fieldValue));  
	  
	                    }else if(Integer.class.equals(fieldType)){  
	                        field.set(dataBean, Util.toInt(fieldValue) );  
	  
	                    }else if(long.class.equals(fieldType)){  
	                        field.setLong(dataBean, Util.toLong(fieldValue) );  
	  
	                    }else if(Long.class.equals(fieldType)){  
	                        field.set(dataBean,Util.toLong(fieldValue) );  
	  
	                    }else if(float.class.equals(fieldType)){  
	                        field.setFloat(dataBean, Float.parseFloat(fieldValue));  
	  
	                    }else if(Float.class.equals(fieldType)){  
	                        field.set(dataBean, Float.valueOf(fieldValue));  
	  
	                    }else if(double.class.equals(fieldType)){  
	                        field.setDouble(dataBean, Double.parseDouble(fieldValue));  
	  
	                    }else if(Double.class.equals(fieldType)){  
	                        field.set(dataBean, Double.valueOf(fieldValue));  
	  
	                    }else if(Date.class.equals(fieldType)){  
	                    	if(! Util.isNullOrEmpty(fieldValue)){	                    		
		                    	try  
		                    	{  
		                    		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		                    	    Date date = sdf.parse(fieldValue);  
		                    	    field.set(dataBean, date);  
		                    	}  
		                    	catch (ParseException e)  
		                    	{  
		                    	    //System.out.println(e.getMessage());
		                    	    
		                    	    try{
		                    	    	Date date = new Date(Util.toLong(fieldValue));  
		                    	    	field.set(dataBean, date);  
		                    	    }
		                    	    catch(Exception ex){
	
			                    	    System.out.println(ex.getMessage());
		                    	    }
		                    	}  
	                    	}

	                    }  
	                } catch (Exception e) {  
	                    e.printStackTrace();  
	                   
	                }                 
	  
	            }  
	        }  
	        return dataBean;  
	  
	    }  

	 public static Set<Field> getClassFields ( Class clazz, boolean includeParentClass )
	 {			
		   
		  Set<Field> fields = Util.toHashSet(clazz.getDeclaredFields());				  
			if ( includeParentClass )
			{
				Class superClazz = clazz.getSuperclass();
				if(superClazz!=null){
					ClassHelper.getParentClassFields ( fields, superClazz);					
				}				
			}				
			return fields;
	}
	private static Set<Field> getParentClassFields (Set<Field> fields, Class clazz )
	{
		Set<Field> superFields =  Util.toHashSet(clazz.getDeclaredFields());
			for ( Field field : superFields )
			{
				fields.add(field);				
			}
			if (clazz.getSuperclass() == null )
			{
				return fields;
			}
			ClassHelper.getParentClassFields ( fields, clazz.getSuperclass ( ) );
			return fields;
	}
}
