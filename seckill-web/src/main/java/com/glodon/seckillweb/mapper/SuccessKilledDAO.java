package com.glodon.seckillweb.mapper;

import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillcommon.domain.SuccessKilledKey;
import org.springframework.stereotype.Repository;

/**
 * SuccessKilledDAO继承基类
 */
@Repository
public interface SuccessKilledDAO extends MyBatisBaseDao<SuccessKilled, SuccessKilledKey> {
}