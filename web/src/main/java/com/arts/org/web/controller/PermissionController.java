package com.arts.org.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.arts.org.model.Filter;
import com.arts.org.model.entity.Permission;
import com.arts.org.model.entity.Role;
import com.arts.org.model.entity.UserPassport;
import com.arts.org.model.entity.view.UserPassportView;
import com.arts.org.service.BaseService;
import com.arts.org.service.PermissionService;
import com.arts.org.service.RolePermissionService;
import com.arts.org.service.UserPassportService;

/**
 * 菜单暂时是写死的
 * @author meizhiwen
 *
 */
@Controller
@RequestMapping("permission")
public class PermissionController extends CRUDController<Permission, Long> {	
	
	private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
	
	@Autowired
	private PermissionService permissionService;
	
	@Autowired
	private RolePermissionService rolePermissionService;
	
	@Resource(name = "permissionService")
	@Override
	public void setBaseService(BaseService<Permission, Long> baseService) {
		this.baseService = baseService;		
	}
	
	@RequestMapping("index")
	public String index(ModelMap model){			
		
		 List<Permission> list = this.findWithAll();
		 model.put("permissionList", list);
		return "permission/index";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam(value="id", required=false, defaultValue="0") Long id,ModelMap model){		
		
		Permission permission = new Permission();
		if( id>0) {
			permission = permissionService.queryById(id);				
		}
		model.put("permission",permission);
		
		 List<Filter> filters = new ArrayList<Filter>();
		 filters.add(Filter.eq("parentId", 0));
		 Sort sort = new Sort(Direction.ASC,"sort");		 
		List<Permission> roots = permissionService.findAll(0, 100, filters, sort);		
		model.put("rootPermission",roots);		
		return "permission/detail";
	}
	
	
	@RequestMapping( value = "remove", method= RequestMethod.POST)
	@ResponseBody
	public RespBody remove(@RequestParam(value="id", required=false, defaultValue="0") Long id){	
		
		 if (id > 0)
         {
			 Permission item = permissionService.queryById(id);
             if (item == null)
             {
            	  return respBodyWriter.toError("数据不存在", 500);
             }             
             rolePermissionService.deleteByPermissionId(id); //删除当前权限下的角色关系 
             permissionService.delete(id);
             return respBodyWriter.toSuccess();             
         }
         return respBodyWriter.toError("参数错误", 400);         
		   			
	}
	
		
	
}
