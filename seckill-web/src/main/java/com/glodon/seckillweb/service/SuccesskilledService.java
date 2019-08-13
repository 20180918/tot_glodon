package com.glodon.seckillweb.service;

import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillweb.dto.SeckillExecution;
import com.glodon.seckillweb.dto.UrlExposer;

import java.util.List;

/**
 * 秒杀结果服务接口
 *
 * @author wupx
 */
public interface SuccesskilledService {

    /**
     * 获取秒杀结果列表
     *
     * @return
     */
    List<SuccessKilled> getSuccessSeckillList(String phone);

}
