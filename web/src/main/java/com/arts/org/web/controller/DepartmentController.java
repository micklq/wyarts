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

import com.arts.org.webcomn.RespBody;
import com.arts.org.webcomn.controller.CRUDController;
import com.arts.org.webcomn.util.WebUtil;
import com.arts.org.base.util.Util;
import com.arts.org.model.Filter;
import com.arts.org.model.entity.Department;
import com.arts.org.model.entity.Organization;
import com.arts.org.model.entity.Permission;
import com.arts.org.model.entity.Role;
import com.arts.org.model.entity.RolePermission;
import com.arts.org.model.entity.UserPassport;
import com.arts.org.service.BaseService;
import com.arts.org.service.DepartmentService;
import com.arts.org.service.PermissionService;
import com.arts.org.service.RoleService;
import com.arts.org.service.UserProfileService;

/**
 * 菜单暂时是写死的
 * @author meizhiwen
 *
 */
@Controller
@RequestMapping("department")
public class DepartmentController extends CRUDController<Department, Long> {
	
	private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);
	
	@Autowired
	private DepartmentService departmentService;
	
	@Autowired
	private UserProfileService userProfileService;
	
	
	@Resource(name = "departmentService")
	@Override
	public void setBaseService(BaseService<Department, Long> baseService) {
		this.baseService = baseService;		
	}
	
	@RequestMapping("index")
	public String index(@RequestParam(value="orgid", required=false, defaultValue="0") Long orgid,ModelMap model){	
		
		 if(orgid==null || orgid<=0){
		    	model.addAttribute("error","参数错误");
				return "shared/error";
		  } 
		  List<Filter> filters = new ArrayList<Filter>();		
		  filters.add(Filter.eq("organizationId", orgid));
		  Sort sort = new Sort(Direction.ASC,"sort");	
		  List<Department> rlist = departmentService.findAll(0,1000,filters,sort);			
		  model.put("departmentList", rlist);
		  model.put("organizationId", orgid);
		  
		return "department/index";
	}
	
	@RequestMapping("detail")
	public String detail(@RequestParam(value="id", required=false, defaultValue="0")Long id,@RequestParam(value="orgid", required=false, defaultValue="0")Long orgid,ModelMap model){		
				
		Department department = new Department();
		if(id!=null&&id>0) {
			department = departmentService.queryById(id);				
		}
		model.put("department",department);		
		model.put("organizationId", ((orgid!=null&&orgid>0)?orgid:0));
		List<Filter> filters = new ArrayList<Filter>();
		filters.add(Filter.eq("organizationId", orgid));
		Sort sort = new Sort(Direction.ASC,"sort");		 
		List<Department> departTree  = WebUtil.getDepartmentTree(departmentService.findAll(0, 1000, filters, sort));
		if(departTree!=null && departTree.size()>0){
			for(Department o :departTree){				
				if(o.getDepth()>1){
					String pre ="";
					for(int i=1; i<o.getDepth(); i++){
					 	pre +="├";
					}
					o.setName(pre+o.getName());
				}
			}
		}	
		model.put("departTree",departTree);	
		
		return "department/detail";
	}
	@RequestMapping( value = "remove", method= RequestMethod.POST)
	@ResponseBody
	public RespBody remove(@RequestParam(value="id", required=false, defaultValue="0") Long id){	
		
		 if (id > 0)
         {
			 Department item = departmentService.queryById(id);
             if (item == null)
             {
            	  return respBodyWriter.toError("数据不存在", 500);
             }             
             userProfileService.clearByDepartmentId(id);;  //用户所属部门归0 
             departmentService.delete(id);
             return respBodyWriter.toSuccess();             
         }
         return respBodyWriter.toError("参数错误", 400);         
		   			
	}
}
