package com.arts.org.data.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.arts.org.model.entity.Tests;


public interface TestsDAO { 
	public List<Tests> findAllByMapper();
	
	public List<Tests> queryAllByMapper();
	
	public void insertTests(Tests entity);
	
//	public List<Tests> getTestss(List<Long> ids);
//	public void incAllVideoCount(@Param("uid")Long uid);
//	public void decVideoCountAndAllCount(@Param("uid")Long uid);
//	public void decAllVideoCount(@Param("uid")Long uid);
//	public List<Tests> getNormalUser(Map<String,Object> params);
//	public List<Tests> findUserByPhoneOrName(Map<String,Object> params);
//	public Integer getNormalUserCount(Map<String,Object> params);
//	public void execDayUserPopularityProc(Map<String,Object> params);
//	public List<Tests> findDayUserPopularityTop50(Integer count);
	
//	<select id="selectRusers" resultType="com.ibumeng.vcs.model.entity.Ruser">
//	select 
//	id,                       
//	create_at as createDate,               
//	email,                 
//	login_ip  as loginIp,                
//	name,                  
//	phone,                    
//	type,                     
//	username,                 
//	addr,                     
//	sex,                      
//	signature,                
//	pic,                      
//	home_pic as homePic,                 
//	data_from as dataFrom,                
//	video_count as videoCount,              
//	third_from as thirdFrom,              
//	third_userame as thirdUserame,          
//	birthday,                 
//	stat,                     
//	vstat as vipStat,fans_count as fansCount,attention_count as attentionCount,sign_sum as signSum
//	from ruser where id in 
//	<foreach item="item" index="index" collection="list" open="(" separator="," close=")">  
//	 	#{item}  
//	</foreach>
//</select> 
//
//<update id="incAllVideoCount">
//	update ruser u set u.all_video_count=u.all_video_count+1 where u.id=#{uid}
//</update>
//
//<update id="decVideoCountAndAllCount">
//	update ruser u set u.all_video_count=u.all_video_count-1,u.video_count=u.video_count-1 where u.id=#{uid}
//</update>
//
//
//<update id="decAllVideoCount">
//	update ruser u set u.all_video_count=u.all_video_count-1 where u.id=#{uid}
//</update>
//
//<sql id="page">limit #{pageStart},#{pageSize}</sql>
//
//<select id="selectNormalUser" resultType="com.ibumeng.vcs.model.entity.Ruser">
//		select 
//			id,                       
//			create_at as createDate,               
//			email,                 
//			login_ip  as loginIp,                
//			name,                  
//			phone,                    
//			type,                     
//			username,                 
//			addr,                     
//			sex,                      
//			signature,                
//			pic,                      
//			home_pic as homePic,                 
//			data_from as dataFrom,                
//			video_count as videoCount,              
//			third_from as thirdFrom,              
//			third_userame as thirdUserame,          
//			birthday,                 
//			stat,                     
//			vstat as vipStat,fans_count as fansCount,attention_count as attentionCount,sign_sum as signSum
//		from ruser 
//			where type='normal'
//			<if test="name != null">
//				and name like "%"#{name}"%"
//			</if>
//			<if test="username != null">
//				and username like "%"#{username}"%"
//			</if>
//			<if test="phone != null">
//				and phone like "%"#{phone}"%"
//			</if>
//		order by create_at desc
//		
//		<include refid="page"/>
//</select>	
//<select id="selectUserByPhoneOrName" resultType="com.ibumeng.vcs.model.entity.Ruser">
//		select 
//			a.id,                       
//			a.create_at as createDate,               
//			a.email,                 
//			a.login_ip  as loginIp,                
//			a.name,                  
//			a.phone,                    
//			a.type,                     
//			a.username,                 
//			a.addr,                     
//			a.sex,                      
//			a.signature,                
//			a.pic,                      
//			a.home_pic as homePic,                 
//			a.data_from as dataFrom,                
//			a.video_count as videoCount,              
//			a.third_from as thirdFrom,              
//			a.third_userame as thirdUserame,          
//			a.birthday,                 
//			a.stat,                     
//			a.vstat as vipStat,a.fans_count as fansCount,a.attention_count as attentionCount,sign_sum as signSum
//			<if test="uid!=null">
//				,(select count(b.attention_id) from attention b where b.creator_id=#{uid} and b.attention_id=a.id)>0 as isAttention
//			</if>
//		from ruser a
//			where (a.name like "%"#{keyWord}"%" or (a.username like "%"#{keyWord}"%" and a.third_from !='sina' and a.third_from !='qq' and a.third_from !='wechat'))
//			<if test="timestamp!=null">
//		    	 <![CDATA[and a.create_at<#{timestamp}]]>
//		    </if>
//		order by a.create_at desc limit #{count}			
//</select>  
//<select id="getNormalUserCount" resultType="Integer">
//	select count(*) from ruser 
//	where type='normal'
//		<if test="name != null">
//			and name like "%"#{name}"%"
//		</if>
//		<if test="username != null">
//			and username like "%"#{username}"%"
//		</if>
//		<if test="phone != null">
//			and phone like "%"#{phone}"%"
//		</if>
//</select>
//
//<select id="execDayUserPopularityProc" statementType="CALLABLE" >
//	 <![CDATA[
//        {call proc_userDayHot(
//            #{videoCountRate,mode=IN,jdbcType=DOUBLE},
//            #{fansCountRate,mode=IN,jdbcType=DOUBLE}
//        )}
//    ]]>
//</select>
//
//<select id="findDayUserPopularityTop50" resultType="com.ibumeng.vcs.model.entity.Ruser">  
//    select 
//			id,create_at as createDate,email,login_ip  as loginIp, 
//			name,phone, type,username,addr,sex,signature, pic,                      
//			home_pic as homePic,data_from as dataFrom,video_count as videoCount,              
//			third_from as thirdFrom,third_userame as thirdUserame,          
//			birthday, stat,vstat as vipStat,fans_count as fansCount,
//			attention_count as attentionCount,sign_sum as signSum,day_popularity as dayPopularity
//		from ruser 
//		order by day_popularity desc limit #{count}
//</select>   
	
}
