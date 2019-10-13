package com.roden.blog.backend.dao;

import com.roden.blog.backend.domain.UserDO;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserDO, Integer> {

}
