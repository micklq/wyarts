package com.arts.org.webcomn.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import com.arts.org.model.entity.ArticlesCategory;
import com.arts.org.model.entity.Department;
import com.arts.org.model.entity.Permission;
import com.arts.org.model.entity.RolePermission;

public class WebUtil {
	
	public static List<Permission> filterPermissionList(List<Permission> list , long parentId)  
	{
		 List<Permission> result = new ArrayList<Permission>();
		 if(list!=null && list.size()>0){
			    for(Permission o :list){
			      if(o.getParentId()==parentId){result.add(o); }
			    }
		 }
		 return result;
	}
	
	public static boolean checkPermissionValue(List<RolePermission> list , long permissionId, int actionValue)  
	{
		 if(list!=null && list.size()>0)
		 {
		   for(RolePermission o :list){
			 if(o.getPermissionId()==permissionId&& o.getActionValue()==actionValue){return true;}
		   }
		 }
		 return false;
	}	
	
   public static List<Department> getDepartmentTree(List<Department> list){
	   if(list!=null&&list.size()>0)
	   {
		   List<Department> result = new  ArrayList<Department>();		
		   departmentRecursion(list,0,0,result);	  
		   return result;		   
	   }
	   return null;
   }
   
   private static void departmentRecursion(List<Department> source ,long pid,int depth,List<Department> result){
		for(Department o :source){
			if(o.getParentId()==pid ){
				o.setDepth(depth+1);				
				result.add(o);
				departmentRecursion(source ,o.getId(),(int)o.getDepth(),result);
			}			
		}
	}
   
   public static List<ArticlesCategory> getArticlesCategoryTree(List<ArticlesCategory> list){
	   if(list!=null&&list.size()>0)
	   {
		   List<ArticlesCategory> result = new  ArrayList<ArticlesCategory>();		
		   articlesCategoryRecursion(list,0,0,result);	  
		   return result;		   
	   }
	   return null;
   }
   
   private static void articlesCategoryRecursion(List<ArticlesCategory> source ,long pid,int depth,List<ArticlesCategory> result){
		for(ArticlesCategory o :source){
			if(o.getParentId()==pid ){
				o.setDepth(depth+1);	//在	ArticlesCategory实体中添加这个字段 不生成		
				result.add(o);
				articlesCategoryRecursion(source ,o.getId(),(int)o.getDepth(),result);
			}			
		}
	}
	

}
