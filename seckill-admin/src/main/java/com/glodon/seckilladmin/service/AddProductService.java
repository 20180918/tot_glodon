package com.glodon.seckilladmin.service;

import com.glodon.seckillcommon.domain.SeckillProduct;
import org.springframework.web.multipart.MultipartFile;

public interface AddProductService {
    String save(MultipartFile multipartFile);
    void store(SeckillProduct seckillProduct);

}
