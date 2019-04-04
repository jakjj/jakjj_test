package org.wz.repository.dao;

import org.springframework.stereotype.Repository;

/**
 * @project: userManager
 * @package: com.wonders.dao
 * @author: Administrator
 * @crDate: 2019/3/20 10:12
 * @editor: IntelliJ IDEA
 * @role:
 **/

public interface LogDao {

    int addLog(String type, String ip, String args, String uri);
}
