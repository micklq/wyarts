package com.arts.org.model.entity;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * Created by
 * User: djyin
 * Date: 12/16/13
 * Time: 7:37 PM
 * 代表系统中对象的访问许可
 */
@Entity
@Table(name = "permission")
public class Permission extends BaseEntity {
	
    /**
     * The Name.
     */
    @Column(nullable = true)
    private String name;//名称    
    /**
     * The Description.
     */
    @Column(length = 1024, nullable = true)
    private String description;    
    
    private long parentId;//父结点id    
    /**
     * The Url.
     */
    @Column(length = 255)
    private String url;//url 
    
    private int sort;//排序字段
    
    private int status; //0有效  1无效    
	
    public long getPermissionId() {
		return ((this.getId()==null)?0:this.getId());
	}

	public void setPermissionId(long permissionId) {
		this.setId(permissionId);
	}
	
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	
	public int getSort() {
		return sort;
	}

	public void setSort(int sort) {
		this.sort = sort;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
   

   


	// The Type.实现类型,比如URI,BIObject     
//    @Column(length = 255, nullable = false)
//    @Enumerated(EnumType.STRING)
//    private PermissionType type=PermissionType.URI;

    
     //The enum Permission type.目前有两种类型,URI和基于业务对象的     
//    public enum PermissionType {
//        URI, BIObject
//    }   


	
	
}
