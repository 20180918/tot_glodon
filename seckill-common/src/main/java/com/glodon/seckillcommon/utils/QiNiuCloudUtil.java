package com.glodon.seckillcommon.utils;

import com.qiniu.common.Zone;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import com.qiniu.util.Base64;
import com.qiniu.util.StringMap;
import com.qiniu.util.UrlSafeBase64;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import javax.security.auth.login.Configuration;

public class QiNiuCloudUtil {
    // 设置需要操作的账号的AK和SK
    private static final String ACCESS_KEY = "evABcKdq0dpyCwyjrhk57DJ0vqMm4iy4btEdh5Nl";
    private static final String SECRET_KEY = "DJLQIRdEljgUwBlJfJUgRJ_25KP_xUgLBP1pTiVC";

    // 要上传的空间
    private static final String bucketname = "test";

    // 密钥
    private static final Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);
    //图片上传路径
    private static final String DOMAIN = "http://pw5tbmfil.bkt.clouddn.com/";
    //自定义图片样式
    private static final String style = "jpg";

    public String getUpToken() {
        return auth.uploadToken(bucketname, null, 3600, new StringMap().put("insertOnly", 1));
    }
    //base64方式上传
    public String put64image(byte[] base64, String key) throws Exception{
        String file64 = Base64.encodeToString(base64, 0);
        Integer l = base64.length;
        String url = "http://upload.qiniu.com/putb64/" + l + "/key/"+ UrlSafeBase64.encodeToString(key);
        //非华东空间需要根据注意事项 1 修改上传域名
        RequestBody rb = RequestBody.create(null, file64);
        Request request = new Request.Builder().
                url(url).
                addHeader("Content-Type", "application/octet-stream")
                .addHeader("Authorization", "UpToken " + getUpToken())
                .post(rb).build();
        //System.out.println(request.headers());
        OkHttpClient client = new OkHttpClient();
        okhttp3.Response response = client.newCall(request).execute();
        System.out.println(response);
        //如果不需要添加图片样式，使用以下方式
        //return DOMAIN + key;
        return DOMAIN + key + "?" + style;
    }

}
