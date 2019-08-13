package com.glodon.seckilladmin.controller;


import com.glodon.seckilladmin.service.AddProductService;
import com.glodon.seckillcommon.domain.SeckillProduct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;


@Controller
@RequestMapping("/admin")
public class AddProductController {
    @Resource
    private AddProductService addProductService;

    @RequestMapping("/add")
    public String addProduct(Model model, SeckillProduct seckillProduct) {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // new Date()为获取当前系统时间
        String product_id = df.format(new Date());
        seckillProduct.setSeckillId(product_id);
        model.addAttribute("product_id", product_id);
        model.addAttribute("seckillProduct", seckillProduct);
        return "add_product";
    }
    @RequestMapping(value = "/store_img", method = RequestMethod.POST)
    @ResponseBody
    public String input(@RequestParam(value = "productImg", required = false) MultipartFile productImg) {
        return addProductService.save(productImg);
    }
    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public String store(SeckillProduct seckillProduct) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String product_id = df.format(new Date());
        seckillProduct.setSeckillId(product_id);
        seckillProduct.setProductStocks(100000);
        seckillProduct.setCreateTime(new Date());
        seckillProduct.setProductState(-1);
        addProductService.store(seckillProduct);
        return "product_manager";
    }



}
