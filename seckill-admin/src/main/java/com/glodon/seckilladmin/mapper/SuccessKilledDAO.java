package com.glodon.seckilladmin.mapper;

import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillcommon.domain.SuccessKilledKey;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * SuccessKilledDAO继承基类
 */
@Repository
public interface SuccessKilledDAO extends MyBatisBaseDao<SuccessKilled, SuccessKilledKey> {
    List<SuccessKilled> select_phone(Long productCode);
}