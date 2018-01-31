package com.arts.org.data.repository;


import java.util.Collection;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.arts.org.model.entity.Tests;



/**
 * Created by mick on 12/19/2017.
 */
@Resource(name = "testsRepository")
public interface TestsRepository extends BaseRepository<Tests, Long> {
  
//  Tests findByUsername(String username);	
	
//	List findByType(String type);
//	
//	@Query("select a.id from Tests a where a.vipStat=?1")
//	List<Long> findIdsByVipStat(int vipstate);
//	
//	@Query("select count(a.name) from Tests a where a.name=?1 and id!=?2")
//	public int countByName(String name,Long uid);
//	
//	@Query("select count(a.name) from Tests a where a.name=?1")
//	public int countByName(String name);
//	
//	@Query("select count(a.thirdUserame) from Tests a where a.thirdUserame=?1 and a.thirdFrom=?2")
//	public int countByThirdName(String thirdUsername,String thirdFrom);
//	
//	@Query(value="SELECT user FROM Tests user WHERE (user.stat = 0 OR user.stat = 1) AND user.id in ?1 ")
//	public List<Tests> findTestssByIds(Collection<Long> ids);
//	
//	@Modifying
//	@Transactional
//	@Query("update Tests set videoCount = ?1 where id=?2")
//	public void updateUserVideoCount(int count,Long uid);
//	
//	@Query("select count(*) from Tests a where a.type='normal'")
//	public long countAll();
//	
//	@Query("select a.id from Tests a")
//	List<Long> findAllIds();
}
