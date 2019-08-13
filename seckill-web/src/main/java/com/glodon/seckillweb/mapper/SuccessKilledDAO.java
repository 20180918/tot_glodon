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
     * 根据秒杀商品的id查询明细SuccessKilled对象(该对象携带了Seckill秒杀产品对象)
     * @param seckillId
     * @return
     */
    SuccessKilled queryByIdWithSeckill(@Param("seckillId") String seckillId,@Param("userPhone") String userPhone);

    List<SuccessKilled> selectByPhone (@Param("userPhone") String userPhone);

}