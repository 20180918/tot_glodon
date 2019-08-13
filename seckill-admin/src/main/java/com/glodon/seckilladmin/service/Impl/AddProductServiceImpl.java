package com.glodon.seckilladmin.service.Impl;

import com.glodon.seckilladmin.mapper.SeckillProductDAO;
import com.glodon.seckilladmin.service.AddProductService;
import com.glodon.seckillcommon.utils.QiNiuCloudUtil;
import com.glodon.seckillcommon.domain.SeckillProduct;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.UUID;

@Service
public class AddProductServiceImpl implements AddProductService {
    @Resource
    private SeckillProductDAO seckillProductMapper;
    private String uri;
    public String getUri() {
        return uri;
    }
    @Override
    public String save(@RequestParam("productImg") MultipartFile multipartFile) {
        try {
            byte[] bytes = multipartFile.getBytes();
            String imageName = UUID.randomUUID().toString();
            QiNiuCloudUtil qiNiuCloudUtil = new QiNiuCloudUtil();
            //使用base64方式上传到七牛云
            uri = qiNiuCloudUtil.put64image(bytes, imageName);
            return imageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
       // seckillProductMapper.insert(seckillProduct);
        return "上传成功";
    }

    @Override
    public void store(SeckillProduct seckillProduct) {
        seckillProduct.setProductImg(getUri());
        seckillProductMapper.insert(seckillProduct);
    }
}
