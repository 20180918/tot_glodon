package com.glodon.seckillweb.controller;

import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillweb.dto.SeckillResult;
import com.glodon.seckillweb.dto.UrlExposer;
import com.glodon.seckillweb.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 秒杀列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(Model model) {
        //获取秒杀列表
        List<SeckillProduct> seckillList = seckillService.getSeckillList();
        for (SeckillProduct seckillProduct : seckillList) {
            System.out.println(seckillProduct.toString());
        }
        model.addAttribute("seckillList", seckillList);
        return "list";
    }

    @RequestMapping(value = "/detail/{seckillId}", method = RequestMethod.GET)
    public String detail(@PathVariable("seckillId") String seckillId, Model model) {
        if (seckillId == null) {
            return "redirect:/seckill/list";
        }
        SeckillProduct seckillProduct = seckillService.selectBySeckillId(seckillId);
        if (seckillProduct == null) {
            return "forward:/seckill/list";
        }
        model.addAttribute("seckillProduct", seckillProduct);
        return "detail";
    }

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

//    @RequestMapping(value = "/{seckillId}/{md5}/execution",
//            method = RequestMethod.POST,
//            produces = {"application/json;charset=UTF-8"})
//    @ResponseBody
//    public SeckillResult<SeckillExecution> execute(@PathVariable("seckillId") Long seckillId,
//                                                   @PathVariable("md5") String md5,
//                                                   @CookieValue(value = "userPhone",required = false) Long userPhone)
//    {
//        if (userPhone==null)
//        {
//            return new SeckillResult<SeckillExecution>(false,"未注册");
//        }
//        SeckillResult<SeckillExecution> result;
//
//        try {
//            SeckillExecution execution = seckillService.executeSeckill(seckillId, userPhone, md5);
//            return new SeckillResult<SeckillExecution>(true, execution);
//        }catch (RepeatKillException e1)
//        {
//            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.REPEAT_KILL);
//            return new SeckillResult<SeckillExecution>(true,execution);
//        }catch (SeckillCloseException e2)
//        {
//            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.END);
//            return new SeckillResult<SeckillExecution>(true,execution);
//        }
//        catch (Exception e)
//        {
//            SeckillExecution execution=new SeckillExecution(seckillId, SeckillStatEnum.INNER_ERROR);
//            return new SeckillResult<SeckillExecution>(true,execution);
//        }
//
//    }

}
