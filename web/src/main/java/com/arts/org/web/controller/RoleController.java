package com.arts.org.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.arts.org.webcomn.MethodResult;
import com.arts.org.webcomn.RespBody;
import com.arts.org.webcomn.U;
import com.arts.org.webcomn.controller.CRUDController;
import com.arts.org.webcomn.util.ClassHelper;
import com.arts.org.base.util.Util;
import com.arts.org.model.Filter;
import com.arts.org.model.entity.Permission;
import com.arts.org.model.entity.Role;
import com.arts.org.model.entity.RolePermission;
import com.arts.org.model.entity.UserPassport;
import com.arts.org.model.entity.view.UserPassportView;
import com.arts.org.service.BaseService;
import com.arts.org.service.PermissionService;
import com.arts.org.service.RolePermissionService;
import com.arts.org.service.RoleService;

/**
 * 菜单暂时是写死的
 * @author meizhiwen
 *
 */
@Controller
@RequestMapping("role")
public class RoleController extends CRUDController<Role, Long> {
	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@Resource(name = "roleService")
	@Override
	public void setBaseService(BaseService<Role, Long> baseService) {
		this.baseService = baseService;		
	}
	
	@RequestMapping("index")
	public String index(ModelMap model){	
		
		 List<Role> list = this.findWithAll();
		 model.put("list", list);
		 
		return "role/index";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam(value="id", required=false, defaultValue="0") Long id,ModelMap model){		
		
		Role role = new Role();
		if( id>0) {
			role = roleService.queryById(id);				
		}
		model.put("role",role);
		
		List<Permission> list = permissionService.findAll();
		model.put("plist", list);
		
		List<Filter> filters = new ArrayList<Filter>();		
		filters.add(Filter.eq("roleId", id));
		Sort sort = new Sort(Direction.ASC,"permissionId");	
		List<RolePermission> rlist = rolePermissionService.findAll(0,100,filters,sort);			
		model.put("rlist", rlist);
		
		return "role/detail";
	}
	
	@RequestMapping( value = "updateAction", method= RequestMethod.POST)
	@ResponseBody
	public RespBody updateAction(Role entity){		
		   
		    entity.setCreatorId(U.getUid());	
			if(entity.getId() == null || entity.getId() == 0 ){										
			  roleService.save(entity); //add								
			}
			else{				
				roleService.update(entity);	//update													
			}
			  String requestValues = request.getParameter("permissionValue"); 
			 if(!Util.isNullOrEmpty(requestValues)) {
				 String[] permissionValues = requestValues.split(","); 
				   //添加权限	        
		                if (permissionValues.length > 0)
		                {
		                	rolePermissionService.deleteByRoleId(entity.getRoleId());
		                    for(String o : permissionValues)
		                    {
		                        String[] oo = o.split("-"); 
		                        if (oo.length == 3)
		                        {
		                           RolePermission rolePermission = new RolePermission();
		                           rolePermission.setRoleId(entity.getRoleId());
		                           rolePermission.setParentPermissionId(Util.toLong(oo[0]));
		                           rolePermission.setPermissionId(Util.toLong(oo[1]));
		                           rolePermission.setActionValue(Util.toInt(oo[2]));
		                           rolePermissionService.save(rolePermission);	                            
		                        }
		                    }
		                }
				 
			 }
			     
			return respBodyWriter.toSuccess();
		}
	
	@RequestMapping( value = "remove", method= RequestMethod.POST)
	@ResponseBody
	public RespBody remove(@RequestParam(value="id", required=false, defaultValue="0") Long id){	
		
		 if (id > 0)
         {
			 Role item = roleService.queryById(id);
             if (item == null)
             {
            	  return respBodyWriter.toError("数据不存在", 500);
             }             
             rolePermissionService.deleteByRoleId(id); //删除当前权限下的角色关系 
             roleService.delete(id);
             return respBodyWriter.toSuccess();             
         }
         return respBodyWriter.toError("参数错误", 400);         
		   			
	}
}
