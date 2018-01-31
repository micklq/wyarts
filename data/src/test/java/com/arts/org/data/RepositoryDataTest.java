package com.arts.org.data;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.arts.org.data.mapper.TestsDAO;
import com.arts.org.data.repository.TestsRepository;
import com.arts.org.model.entity.Tests;

public class RepositoryDataTest extends BaseTest {
		
	
//	@Autowired
//	public OperationLogRepository operationLogRepository;
	
	@Autowired
	public TestsRepository testsRepository;	
    
    @Autowired
    public TestsDAO testsMapper;
	
    
    
//	@Test
//	public void saveTest() 
//	{
////		for(int i=0; i<5 ; i++)
////		{
////			String r = RandomStringUtils.random(5,true,true).toLowerCase();		
////			Tests o00 = new Tests();		
////			o00.setName(r+"nameRepo");
////			o00.setDescription(r+"descriptionRepo");
////			Tests co00 = testsRepository.save(o00);
////			System.out.println(co00.toString());	
////			try {
////				Thread.sleep(500);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}
////			
////		}
//		
//		for(int i=0; i<5 ; i++)
//		{
//			String r = RandomStringUtils.random(5,true,true).toLowerCase();		
//			Tests o00 = new Tests();		
//			o00.setName(r+"nameMapper");
//			o00.setDescription(r+"descriptionMapper");	
//			testsMapper.insertTests(o00);				
//			try {
//				Thread.sleep(500);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		
////		for(int i=0; i<5 ; i++)
////		{
////			String r = RandomStringUtils.random(5,true,true).toLowerCase();	
////			
////			OperationLog o0 = new OperationLog();
////			o0.setModuleType(r+"moduletype");
////			o0.setReqType(r+"reqType");
////			o0.setOperType(r+"operType");
////			o0.setReqUrl(r+"reqUrl");
////			o0.setReferer(r+"referer");
////			o0.setObjectid(r+"objectid");
////			o0.setObjectname(r+"objectname");
////			o0.setUid(0L);
////			o0.setUname(r+"uname-w");
////			o0.setDescription(r+"description");
////			OperationLog co0 = operationLogRepository.save(o0);
////			System.out.println(co0.toString());	
////			
////			try {
////				Thread.sleep(500);
////			} catch (InterruptedException e) {
////				// TODO Auto-generated catch block
////				e.printStackTrace();
////			}			
////		}//				
//		
//	}
	
	@Test
	public void findAllTests() {	
		
//		List<OperationLog> olist0 = operationLogRepository.findAll();		
//		if(olist0!=null && olist0.size()>0 ){				
//			for(OperationLog o :olist0){				
//				System.out.println("operationLogRepository==="+o.getId()+">>"+o.getUname().toString());	
//			}
//		}
//		
		List<Tests> tlist0 = testsRepository.findAll();		
		if(tlist0!=null && tlist0.size()>0 ){				
			for(Tests o :tlist0){
				
				System.out.println("testsRepository==="+o.getId()+">>"+o.getName().toString());	
			}
		}
		
		
		List<Tests> tlistw = testsRepository.queryAll();		
		if(tlist0!=null && tlistw.size()>0 ){				
			for(Tests o :tlistw){
				
				System.out.println("testsMasterRepository==="+o.getId()+">>"+o.getName().toString());	
			}
		}
		
		List<Tests> tlist1 = testsMapper.findAllByMapper();		
		if(tlist1!=null && tlist1.size()>0 ){				
			for(Tests o :tlist1){
				
				System.out.println("testsMapper==="+o.getId()+">>"+o.getName().toString());	
			}
		}		
		List<Tests> tlistmw = testsMapper.queryAllByMapper();		
		if(tlistmw!=null && tlistmw.size()>0 ){				
			for(Tests o :tlistmw){
				
				System.out.println("testsMasterMapper==="+o.getId()+">>"+o.getName().toString());	
			}
		}
	
		
	}
	

}
