package com.arts.org.data.repository;


import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.Query;

import com.arts.org.model.entity.UserProfile;

/**
 * Created by mick on 12/19/2017.
 */
@Resource(name = "userProfileRepository")
public interface UserProfileRepository extends BaseRepository<UserProfile, Long> {

    
}
