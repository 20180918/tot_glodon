package com.glodon.seckillweb.mapper;

import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillcommon.domain.SuccessKilledKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * SuccessKilledDAO继承基类
 */
@Repository
public interface SuccessKilledDAO extends MyBatisBaseDao<SuccessKilled, SuccessKilledKey> {

    /**
     * 插入购买明细,可过滤重复
     * @param seckillId
     * @param userPhone
     * @return插入的行数
     */
    int insertSuccessKilled(@Param("seckillId") String seckillId, @Param("userPhone") String userPhone);


    /**
     * @param seckillId
     * @return
     */
    SuccessKilled selectBySeckillIdIdAndUserPhone(@Param("seckillId") String seckillId,@Param("userPhone") String userPhone);

    List<SuccessKilled> selectByPhone (@Param("userPhone") String userPhone);

    void updateBySeckillIdIdAndUserPhone(@Param("seckillId") String seckillId,@Param("userPhone") String userPhone);

}