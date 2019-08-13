package com.glodon.seckilladmin.mapper;

import com.glodon.seckilladmin.domain.UserAdmin;
import com.glodon.seckillcommon.domain.SeckillProduct;
import org.springframework.stereotype.Repository;

/**
 * UserAdminMapper继承基类
 *
 * @author lic-s
 */
@Repository
public interface UserAdminMapper extends MyBatisBaseDao<UserAdmin, Integer> {
    UserAdmin selectByRootName(String name);

}