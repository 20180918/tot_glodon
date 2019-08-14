package com.glodon.seckillweb.controller;

import com.alibaba.fastjson.JSON;
import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.exception.ClosedSeckillException;
import com.glodon.seckillcommon.exception.RepeatSeckillException;
import com.glodon.seckillweb.dto.SeckillExecution;
import com.glodon.seckillweb.dto.SeckillInfoContent;
import com.glodon.seckillweb.dto.SeckillResult;
import com.glodon.seckillweb.dto.UrlExposer;
import com.glodon.seckillweb.service.SeckillService;
import com.glodon.seckillweb.task.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * 秒杀
 *
 * @author wupx
 * @date 2019/08/12
 */
@Controller
@RequestMapping("/seckill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @Autowired
    private KafkaSender kafkaSender;

    /**
     * 秒杀列表 (http://localhost:8080/seckill/list)
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //获取秒杀列表
        List<SeckillProduct> seckillList = seckillService.getSeckillList();
        model.addAttribute("seckillList", seckillList);
        return "list";
    }

    /**
     * 查询单个商品的详情
     *
     * @param seckillId
     * @param model
     * @return
     */
    @RequestMapping(value = "/detail/{seckillId}", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") String seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        SeckillProduct seckillProduct = seckillService.selectBySeckillId(seckillId, false);
        if (seckillProduct == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckillProduct", seckillProduct);
        return "detail";
    }

    /**
     * 获取系统时间
     *
     * @return
     */
    @RequestMapping(value = "/time/now", method = RequestMethod.GET)
    @ResponseBody
    public SeckillResult<Long> time() {
        Date now = new Date();
        return new SeckillResult(true, now.getTime());
    }


    /**
     * 暴露秒杀接口 (http://localhost:8080//seckill//exposer/1000)
     *
     * @param seckillId
     * @return
     */
    @RequestMapping(value = "/exposer/{seckillId}",
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<UrlExposer> exposer(@PathVariable String seckillId) {
        SeckillResult<UrlExposer> result;
        try {
            UrlExposer exposer = seckillService.exportSeckillUrl(seckillId);
            result = new SeckillResult<UrlExposer>(true, exposer);
        } catch (Exception e) {
            result = new SeckillResult<UrlExposer>(false, e.getMessage());
        }
        return result;
    }

    /**
     * 执行秒杀
     *
     * @param seckillId
     * @param md5
     * @param userPhone
     * @return
     */
    @RequestMapping(value = "/execution/{seckillId}/{userPhone}/{md5}",
            method = RequestMethod.POST,
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
                                                   @PathVariable("md5") String md5,
                                                   @PathVariable("userPhone") String userPhone) {
        String seckillInfoContent = JSON.toJSONString(new SeckillInfoContent(seckillId, md5, userPhone, (byte) -1));
        if (userPhone == null) {
            return new SeckillResult<SeckillExecution>(false, "未注册");
        }
        try {
            // 向 clientdistribution 主题发送kafka请求信息内容
            kafkaSender.sendChannelMess("clientdistribution", seckillInfoContent);
            SeckillExecution execution = new SeckillExecution(seckillId, 201);
            return new SeckillResult<SeckillExecution>(true, execution);
        } catch (Exception e) {
            SeckillExecution execution = new SeckillExecution(seckillId, 400);
            return new SeckillResult<SeckillExecution>(false, execution);
        }
    }

}
