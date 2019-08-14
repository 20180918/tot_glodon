package com.glodon.seckillweb.controller;

import com.alibaba.fastjson.JSON;
import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.enums.StatusEnum;
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
import org.springframework.util.DigestUtils;
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
     * 秒杀结果查询
     * @param seckillId
     * @param phone
     * @return
     */
    @RequestMapping(value = "/taskseckillresult/{seckillId}/{phone}", method = RequestMethod.POST)
    @ResponseBody
    public  SeckillResult<SeckillExecution> searchSeckillResult(@PathVariable("seckillId") String seckillId,@PathVariable("phone") String phone) {
        //获取秒杀列表
        SeckillExecution execution = new SeckillExecution(Long.parseLong(seckillId), seckillService.searchSeckillResult(seckillId,phone));//未注册
        return new SeckillResult<SeckillExecution>(true, execution);
    }


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
        String seckillInfoContent = JSON.toJSONString(new SeckillInfoContent(seckillId, "", userPhone, (byte) -1));
        if (userPhone == null) {
            SeckillExecution execution = new SeckillExecution(seckillId, StatusEnum.UNLOAD.getCode());//未注册
            return new SeckillResult<SeckillExecution>(true, execution);
        }
        if (md5 == null || !md5.equals(getSaltMD5(seckillId))) {
            SeckillExecution execution = new SeckillExecution(seckillId, StatusEnum.UNSAFE.getCode());//密钥验证不通过
            return new SeckillResult<SeckillExecution>(true, execution);
        }
        try {
            // 向 clientdistribution 主题发送kafka请求信息内容
            kafkaSender.sendChannelMess("clientdistribution", seckillInfoContent);
            SeckillExecution execution = new SeckillExecution(seckillId, StatusEnum.WAIT.getCode()); //等待中
            return new SeckillResult<SeckillExecution>(true, execution);
        } catch (Exception e) {
            SeckillExecution execution = new SeckillExecution(seckillId, StatusEnum.WRONG.getCode()); //系统异常
            return new SeckillResult<SeckillExecution>(false, execution);
        }
    }

    private String getSaltMD5(Long seckillId) {
        String base = seckillId + "/" + "wangpengpengasdas54d57asd754as";
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }

}
