package org.wz.repository.dao;


import org.apache.ibatis.annotations.Mapper;
import org.wz.config.bean.User;

import java.util.List;
import java.util.Map;

/**
 * @project: userManager
 * @package: com.wonders.dao
 * @author: Administrator
 * @crDate: 2019/3/19 11:10
 * @editor: IntelliJ IDEA
 * @role:
 **/

public interface UserDao {

    List<User> queryList(Map<String, String> params) throws Exception;

    int addUser(User user)throws Exception;

    int deleteUser(Map<String, String> map)throws Exception;

    int editUser(User user)throws Exception;

    User queryInfo(String id) throws Exception;

    int count(Map<String, String> params) throws Exception;
}
