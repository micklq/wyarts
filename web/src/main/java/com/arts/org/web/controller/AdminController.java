package com.arts.org.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import com.arts.org.webcomn.security.MemberShipService;
import com.arts.org.webcomn.util.ClassHelper;
import com.arts.org.base.util.Util;
import com.arts.org.model.Filter;
import com.arts.org.model.entity.OperationLog;
import com.arts.org.model.entity.Role;
import com.arts.org.model.entity.UserPassport;
import com.arts.org.model.entity.view.UserPassportView;
import com.arts.org.service.BaseService;
import com.arts.org.service.RoleService;
import com.arts.org.service.UserPassportService;

/**
 * 菜单暂时是写死的
 * @author meizhiwen
 *
 */
@Controller
@RequestMapping("admin")
public class AdminController extends CRUDController<UserPassport, Long> {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	
	@Autowired
	private UserPassportService userPassportService;
	
	@Autowired
	private RoleService roleService;
	
	
	@Autowired
	private MemberShipService memberShipService;
	
	@Resource(name = "userPassportService")
	@Override
	public void setBaseService(BaseService<UserPassport, Long> baseService) {
		this.baseService = baseService;		
	}
	
	@RequestMapping("index")
	public String index(@RequestParam(value="page", required=false, defaultValue="1") Integer page,ModelMap model){		
		
		int dataCount = (int)userPassportService.count();
		int dataPage = (int) (dataCount/15);
		if(dataCount%15==0){
			dataPage= dataPage-1; //分页整除 减一 以下再加一
		}
		int pageCount = dataPage+1;		
		if(page<=0)
		{
			page = 1;
		}	
		if(page>=pageCount){
			page=pageCount;
		}
		int prePage=((page-1)>0)?(page-1):1;
		int nextPage=((page+1)>pageCount)?pageCount:(page+1);
		 List<Filter> filters = new ArrayList<Filter>();
		 Sort sort = new Sort(Direction.DESC,"id");
		 Page<UserPassport> plist = this.findWithPage(page,size,filters,sort);
		 List<UserPassport> rlist = new ArrayList<UserPassport>();
		 if(plist!=null&& plist.getSize()>0)
	     {        	
	       	 rlist = plist.getContent();
	     } 
		model.addAllAttributes(rlist);        
        model.addAttribute("prePage", prePage);
        model.addAttribute("nextPage", nextPage);
        model.addAttribute("currentPage", page);	
        model.addAttribute("pageCount", pageCount);	
        model.addAttribute("dataCount", dataCount);	
        for(UserPassport o : rlist){
        	
        	if(Util.isNullOrEmpty(o.getEmail())){        		
        		o.setEmail("无");
        	}
        	if(Util.isNullOrEmpty(o.getMobile())){        		
        		o.setMobile("无");
        	}
        	if(o.getRoleId()>0){
        	  Role item= roleService.find(o.getRoleId());
        	 if(item!=null){
        		o.setRoleName(item.getName());
        	 }
        	 else{
        		 o.setRoleName("无");
        	 }
        	}
        	else{
        		o.setRoleName("无");
        	}
        }
		model.put("list", rlist);
		
		return "admin/index";
	}
	
	
	@RequestMapping("detail")
	public String detail(@RequestParam(value="id", required=false, defaultValue="0") Long id,ModelMap model){		
		
		UserPassportView p = new UserPassportView();
		if( id>0) {
			UserPassport uPassport = userPassportService.queryById(id);
			if(uPassport!=null){
				p = ClassHelper.convertClass(uPassport, UserPassportView.class);
			}
			
		}
		
		List<Role> roleList  = roleService.findAll();
		
		model.put("userPassport", p);
		model.put("roleList",roleList);
		
		return "admin/detail";
	}
	
	
	@RequestMapping( value = "updateAction", method= RequestMethod.POST)
	@ResponseBody
	public RespBody updateAction(UserPassportView entity){
		
		    entity.setCreatorId(U.getUid());	
			if(entity.getId() == null || entity.getId() == 0 ){
				//add								
				MethodResult<UserPassport> result =memberShipService.addMember(entity);				    
				if(result.isSuccess()){
					
					return respBodyWriter.toSuccess(entity);
				}
				else{
					
					return respBodyWriter.toError(result.getMessage(), 500);
				}				
			}
			else
			{				
				//update				
				MethodResult<Boolean> result =memberShipService.updateMember(entity,true);		
				if(result.isSuccess()){					
					return respBodyWriter.toSuccess();
				}
				else{
					return	respBodyWriter.toError(result.getMessage(), 500);
				}				
			}			
		}

	
}
