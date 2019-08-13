package com.glodon.seckilladmin.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.druid.util.Utils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.glodon.seckilladmin.service.SecKillProService;
import com.glodon.seckilladmin.vo.SecKillProductVo;
import com.glodon.seckillcommon.domain.SeckillProduct;
import org.omg.CORBA.SetOverrideType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/product")
public class SeckillProController {

    @Autowired
    private SecKillProService secKillProService;

    @RequestMapping(value = "/select")
    public String selectProduct(Model model,@RequestParam(value="page",defaultValue="1") int currentPage,
                                @RequestParam(value="pagesize",defaultValue="13") int pageSize, @RequestParam(value = "productCode", required = false) String productCode,
                                @RequestParam(value = "name", required = false) String name,
                                @RequestParam(value = "productState", required = false) Integer productState) {
        Page page = PageHelper.startPage(currentPage,pageSize);
        if (StringUtils.isEmpty(productCode)) {
            productCode = null;
        }
        if (StringUtils.isEmpty(name)) {
            name = null;
        }
        List<SeckillProduct> list = secKillProService.selectMul(productCode, name, productState);
        List<SecKillProductVo> listVo = new ArrayList<>();
        for (SeckillProduct seckillProduct : list) {
            SecKillProductVo secKillProductVo = new SecKillProductVo();
            BeanUtils.copyProperties(seckillProduct, secKillProductVo);
            listVo.add(secKillProductVo);
        }
        System.out.println(listVo.size()+"............"+list.size());
        PageInfo<SecKillProductVo> pageInfo = new PageInfo<>(listVo);
        List<Integer> pagelist = new ArrayList<>();
        for (int i=0;i<=pageInfo.getPages();i++){
            pagelist.add(i+1);
        }
        System.out.println(pagelist);


        model.addAttribute("listVo", listVo);
        model.addAttribute("prepage", currentPage==1?currentPage:currentPage-1);  //上一页
        model.addAttribute("pagelist", pagelist);  //页数列表
        model.addAttribute("currentpage", currentPage);  //当前页
        model.addAttribute("nextpage", currentPage+1);  //下一页
//       model.addAttribute("totle",totle);//总页数
        return "ProductManage";
    }

    @RequestMapping("/update")
    public String save(SeckillProduct seckillProduct){
        System.out.println(seckillProduct);
        secKillProService.updataPro(seckillProduct);
       // System.out.println(seckillProduct);
        return "ProductManage";
    }
//    @RequestMapping("/update")
//    public String save(@RequestParam(value = "productCode", required = false) String  productCode){
//       secKillProService.updataPro(productCode);
//        return "ProductManage";
//    }
    @RequestMapping("/findByProCode")
    public String findById(@RequestParam(value = "productCode", required = false) String  productCode,Model model){
        SeckillProduct seckillPro = secKillProService.findByProductCode(productCode);
       System.out.println(seckillPro);
        model.addAttribute("pro", seckillPro);
        return "editProPage";
    }

}
