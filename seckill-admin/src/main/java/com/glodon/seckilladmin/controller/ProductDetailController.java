package com.glodon.seckilladmin.controller;

import com.glodon.seckilladmin.service.SecKillProService;
import com.glodon.seckillcommon.domain.SeckillProduct;
import com.glodon.seckillcommon.domain.SuccessKilled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/detail")
public class ProductDetailController {
    @Autowired
    private SecKillProService secKillProService;
    @RequestMapping(value = "/display")
    public String display(@RequestParam(value = "productCode", required = false) String  productCode, Model model){
        SeckillProduct seckillProduct = secKillProService.findByProductCode(productCode);
        model.addAttribute("product_detail",seckillProduct);
        List<SuccessKilled> phone_list = secKillProService.select_phone(productCode);
        model.addAttribute("person",phone_list);
        return "detail_Info";
    }
}
