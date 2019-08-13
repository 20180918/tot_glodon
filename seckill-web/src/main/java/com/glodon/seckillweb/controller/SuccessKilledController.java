package com.glodon.seckillweb.controller;

import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import com.glodon.seckillcommon.exception.ClosedSeckillException;
import com.glodon.seckillcommon.exception.RepeatSeckillException;
import com.glodon.seckillweb.dto.SeckillExecution;
import com.glodon.seckillweb.dto.SeckillResult;
import com.glodon.seckillweb.dto.UrlExposer;
import com.glodon.seckillweb.mapper.SuccessKilledDAO;
import com.glodon.seckillweb.service.SeckillService;
import com.glodon.seckillweb.service.SuccesskilledService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * 秒杀
 *
 * @author wangpp-b
 * @date 2019/08/12
 */
@Controller
@RequestMapping("/seckillkilled")
public class SuccessKilledController {


    @Autowired
    private SuccesskilledService successkilledService;

    /**
     * 秒杀成功列表
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/list/{phone}", method = RequestMethod.GET)
    public String killedlist(@PathVariable("phone") String  phone,Model model) {
        List<SuccessKilled> successKilledList = successkilledService.getSuccessSeckillList(phone);
        model.addAttribute("successKilledList", successKilledList);
        return "recordlist";
    }

}
