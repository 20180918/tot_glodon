package com.glodon.seckillweb.controller;

import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillweb.dto.SeckillResult;
import com.glodon.seckillweb.dto.UrlExposer;
import com.glodon.seckillweb.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
