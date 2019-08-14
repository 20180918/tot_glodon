package com.glodon.seckilladmin.service.impl;

import com.glodon.seckilladmin.domain.UserAdmin;
import com.glodon.seckilladmin.mapper.UserAdminMapper;
import com.glodon.seckilladmin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


/**
 * @author lic-s
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    UserAdminMapper userAdminMapper;

    /**
     * 用户验证
     *
     * @param code      生成的验证码
     * @param name      用户名
     * @param password  用户密码
     * @param checkcode 用户输入的验证码
     * @return
     */
    @Override
    public boolean validate(String code, String name, String password, String checkcode) throws Exception {

        UserAdmin userAdmin = userAdminMapper.selectByRootName(name);
        if (userAdmin == null) {
            return false;
        }
        if (code == null) {
            return false;
        }
        String uName = userAdmin.getRootName();
        String uPassword = userAdmin.getRootPassword();
        if (name.equals(uName) && password.equals(uPassword) && code.equals(checkcode)) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public UserAdmin selectByRootName(String username) {
        return userAdminMapper.selectByRootName(username);
    }
}