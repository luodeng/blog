package com.roden.blog.api.dao;



import com.roden.blog.api.domain.UserDO;

import java.util.List;

public interface UserDAO {
   UserDO getByUserName(String userName);
   List<UserDO> listAll();
   int countUser();
   int insertUser(UserDO userDO);
   int deleteUser(Integer id);
   int updateUser(UserDO userDO);


}
